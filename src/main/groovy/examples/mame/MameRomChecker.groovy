package examples.mame

import org.hs5tb.groospin.base.MameMachine
import org.hs5tb.groospin.common.IOTools

import java.util.zip.ZipEntry
import java.util.zip.ZipFile

/**
 * Created by Alberto on 10-Oct-16.
 */

new MameChecker().checkAll("d:/Games/Emulators/MAME/MameUIFX_0.171_64bits_nonag-dinput/dat.xml",
["d:\\Games\\Roms\\MAME\\MAME 0.171 ROMs"],["d:\\Games\\Roms\\MAME\\MAME 0.171 CHDs"])

class MameChecker {
    Map<String, MameMachine> index = [:]
    List<MameMachine> games = []
    List<File> romFolders
    List<File> chdFolders

    private MameChecker loadDat(String datFileName) {
        XmlParser parser = new XmlParser()
        parser.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
        parser.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
        Node mame = parser.parse(new File(datFileName).newInputStream())
        mame.machine.each { Node machine ->
            MameMachine rom = new MameMachine().loadFromMameDat(machine)
            index[rom.name.toLowerCase()] = rom
            if (machine.@isbios != "yes" && machine.@isdevice != "yes" && machine.@runnable != "no") {
                games << rom
            }
        }
        return this
    }

    void checkAll(String datFileName, List romFolderString, List chdFolderString) {
        long start = System.currentTimeMillis()
        romFolders = romFolderString.collect { it instanceof String ? new File(it) : it }
        chdFolders = chdFolderString.collect { it instanceof String ? new File(it) : it }
        loadDat(datFileName)
        println "Parsing dat time: ${(System.currentTimeMillis()-start)/1000}s"
        start = System.currentTimeMillis()
        games.each { MameMachine mameMachine ->
            if (mameMachine.name in ["wondstcka"]) {
//                ERROR! Missing chds konam80j/826jaa01
//                ERROR! Missing chds konam80u/826uaa01
//                wondstcka.zip/8.u83
                println "a"
            }

            Map pending = checkRomsAndRemoveOk(extractAllRomsToCheck(mameMachine), mameMachine.name)
            if (pending) {
                if (mameMachine.cloneof) {
                    MameMachine parent = index[mameMachine.cloneof.toLowerCase()]
                    if (parent) {
                        checkRomsAndRemoveOk(pending, parent.name)
                        if (pending) {
                            if (parent.romof) {
                                MameMachine bios = index[parent.romof.toLowerCase()]
                                if (bios) {
                                    checkRomsAndRemoveOk(pending, bios.name)
                                }
                            }
                        }
                    }
                } else if (mameMachine.romof) {
                    MameMachine bios = index[mameMachine.romof.toLowerCase()]
                    if (bios) {
                        checkRomsAndRemoveOk(pending, bios.name)
                    }
                }
            }
            if (pending) {
                println "ERROR! Missing files ${mameMachine.name}.zip/${pending.keySet().join(", ")}"
            } else {
                // println "${mameMachine.name} ok!"
            }

            Map chdsPending = checkChdsAndRemoveOk(extractAllDisksToCheck(mameMachine), mameMachine.name)

            if (chdsPending) {
                if (mameMachine.cloneof) {
                    MameMachine parent = index[mameMachine.cloneof.toLowerCase()]
                    if (parent) {
                        checkChdsAndRemoveOk(chdsPending, parent.name)
                        if (chdsPending) {
                            if (parent.romof) {
                                MameMachine bios = index[parent.romof.toLowerCase()]
                                if (bios) {
                                    checkChdsAndRemoveOk(chdsPending, bios.name)
                                }
                            }
                        }
                    }
                } else if (mameMachine.romof) {
                    MameMachine bios = index[mameMachine.romof.toLowerCase()]
                    if (bios) {
                        checkChdsAndRemoveOk(chdsPending, bios.name)
                    }
                }
            }
            if (chdsPending) {
                println "ERROR! Missing chds ${mameMachine.name}/${chdsPending.keySet().join(", ")}"
            } else {
                // println "${mameMachine.name} ok!"
            }

        }
        println "Checking time: ${(System.currentTimeMillis()-start)/1000}s"
    }

    Map checkRomsAndRemoveOk(Map romBins, String mameMachineName) {
        File zip = IOTools.findFileInFolders(romFolders, mameMachineName+".zip")

        if (zip) {
            ZipFile zipFile = new ZipFile(zip)
            zipFile.entries().each { ZipEntry zipEntry ->
                // println "${zipEntry.name}"
                MameMachine.RomBin romBin = romBins[zipEntry.name.toLowerCase()]
                if (romBin != null) {
                    if (romBin.size >0 && zipEntry.size != romBin.size) {
                        println "warning! bad size checking rom ${zip.canonicalPath}/${zipEntry.name}"
                    } else if (Long.decode("0x"+romBin.crc) != zipEntry.crc) {
                        if (romBin.sha1) {
                            if (romBin.sha1 != IOTools.sha1(zipFile.getInputStream(zipEntry))) {
                                println "warning! bad crc & sha1 checking rom ${zip.canonicalPath}/${zipEntry.name}"
                            }
                        } else {
                            println "warning? bad crc (no sha1 found) checking rom ${zip.canonicalPath}/${zipEntry.name}"
                        }
                    }
                    romBins.remove(romBin.name.toLowerCase())
                }
            }
        }
        return romBins
    }

    Map checkChdsAndRemoveOk(Map romBins, String mameMachineName) {
        Set delete = new HashSet()
        romBins.values().each { MameMachine.RomBin romBin ->
            File chd = IOTools.findFileInFolders(chdFolders, mameMachineName+"/"+romBin.name+".chd")
            if (!chd && romBin.merge) {
                chd = IOTools.findFileInFolders(chdFolders, mameMachineName+"/"+romBin.merge+".chd")
            }
            if (chd) {
                if (romBin.size >0 && chd.size() != romBin.size) {
                    println "warning! bad size checking disk ${chd.canonicalPath}"
                /*
                // chds must be uncompressed before calculate any checksum (crc or sha1)
                } else if (romBin.crc) {
                    String crc = IOTools.crc(chd.newInputStream())
                    if (crc != romBin.crc) {
                        if (romBin.sha1) {
                            long start = System.currentTimeMillis()
                            if (romBin.sha1 != IOTools.sha1(chd.newInputStream())) {
                                println "warning! bad crc & sha1 checking disk ${chd.canonicalPath}"
                            } else {
                                println "sha1ok!"
                            }
                            println "Info: sha1 ${chd.canonicalPath} time: ${(System.currentTimeMillis()-start)/1000}s"
                        } else {
                            println "warning? bad crc (no sha1 found) checking disk ${chd.canonicalPath}"
                        }
                    }
                } else if (romBin.sha1) {
                    long start = System.currentTimeMillis()
                    if (romBin.sha1 != IOTools.sha1(chd.newInputStream())) {
                        println "warning! bad sha1 (no crc found) checking disk ${chd.canonicalPath}"
                    } else {
                        println "sha1ok!"
                    }
                    println "Info: sha1 ${chd.canonicalPath} time: ${(System.currentTimeMillis()-start)/1000}s"
                */
                }
                delete << romBin.name.toLowerCase()
            }
        }
        delete.each { romBins.remove(it) }
        return romBins
    }

    private Map extractAllDisksToCheck(MameMachine mameMachine) {
        return extractAllRomBinsToCheck(mameMachine, mameMachine.disks)
    }

    private Map extractAllRomsToCheck(MameMachine mameMachine) {
        return extractAllRomBinsToCheck(mameMachine, mameMachine.roms)
    }

    private Map extractAllRomBinsToCheck(MameMachine mameMachine, Collection collection) {
        Map romBins = collection.inject([:]) { Map map, MameMachine.RomBin romBin ->
            if (romBin.status != MameMachine.RomBin.Status.baddump && romBin.status != MameMachine.RomBin.Status.nodump) {
                map[romBin.name.toLowerCase()] = romBin
            }
            map
        }
        romBins
    }

}