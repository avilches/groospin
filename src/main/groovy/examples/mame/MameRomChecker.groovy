package examples.mame

import org.hs5tb.groospin.base.MameMachine
import org.hs5tb.groospin.common.IOTools

import java.util.zip.ZipEntry
import java.util.zip.ZipFile

/**
 * Created by Alberto on 10-Oct-16.
 */

println new MameChecker().checkAll("d:/Games/Emulators/MAME/MameUIFX_0.171_64bits_nonag-dinput/dat.xml",
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

    Set checkAll(String datFileName, List romFolderString, List chdFolderString) {
        Set missing = new LinkedHashSet()
        long start = System.currentTimeMillis()
        romFolders = romFolderString.collect { it instanceof String ? new File(it) : it }
        chdFolders = chdFolderString.collect { it instanceof String ? new File(it) : it }
        loadDat(datFileName)
        println "Parsing dat time: ${(System.currentTimeMillis()-start)/1000}s"
        start = System.currentTimeMillis()
        games.each { MameMachine mameMachine ->
            List filesChecked = []
            Map romsPending = checkRomsAndRemoveOk(extractAllRomsToCheck(mameMachine), filesChecked, mameMachine.name)
            if (romsPending) {
                if (mameMachine.cloneof) {
                    MameMachine parent = index[mameMachine.cloneof.toLowerCase()]
                    if (parent) {
                        checkRomsAndRemoveOk(romsPending, filesChecked, parent.name)
                        if (romsPending) {
                            if (parent.romof) {
                                MameMachine bios = index[parent.romof.toLowerCase()]
                                if (bios) {
                                    checkRomsAndRemoveOk(romsPending, filesChecked, bios.name)
                                }
                            }
                        }
                    }
                } else if (mameMachine.romof) {
                    MameMachine bios = index[mameMachine.romof.toLowerCase()]
                    if (bios) {
                        checkRomsAndRemoveOk(romsPending, filesChecked, bios.name)
                    }
                }
            }
            if (romsPending) {
                println "ERROR! Missing files for rom \"${mameMachine.name}\":${romsPending.keySet().join(", ")}. Places checked: ${filesChecked}"
                missing << mameMachine.name
            } else {
                // println "${mameMachine.name} ok!"
            }

            filesChecked = []
            Map chdsPending = checkChdsAndRemoveOk(extractAllDisksToCheck(mameMachine), filesChecked, mameMachine.name)

            if (chdsPending) {
                if (mameMachine.cloneof) {
                    MameMachine parent = index[mameMachine.cloneof.toLowerCase()]
                    if (parent) {
                        checkChdsAndRemoveOk(chdsPending, filesChecked, parent.name)
                        if (chdsPending) {
                            if (parent.romof) {
                                MameMachine bios = index[parent.romof.toLowerCase()]
                                if (bios) {
                                    checkChdsAndRemoveOk(chdsPending, filesChecked, bios.name)
                                }
                            }
                        }
                    }
                } else if (mameMachine.romof) {
                    MameMachine bios = index[mameMachine.romof.toLowerCase()]
                    if (bios) {
                        checkChdsAndRemoveOk(chdsPending, filesChecked, bios.name)
                    }
                }
            }
            if (chdsPending) {
                println "ERROR! Missing chds \"${mameMachine.name}\": ${chdsPending.keySet().join(", ")}. Places chedked: ${filesChecked}"
            } else {
                // println "${mameMachine.name} ok!"
            }
            if (chdsPending || romsPending) {
                missing << mameMachine.name
            }

        }
        println "Checking time: ${(System.currentTimeMillis()-start)/1000}s"
        return missing
    }

    Map checkRomsAndRemoveOk(Map romBins, List filesChecked, String mameMachineName) {
        File zip = IOTools.findFileInFolders(romFolders, mameMachineName+".zip")

        if (zip) {
            filesChecked << zip.toString()
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
        } else {
            filesChecked << "No ${mameMachineName}.zip found"
        }
        return romBins
    }

    Map checkChdsAndRemoveOk(Map romBins, List filesChecked, String mameMachineName) {
        Set chdsFound = new HashSet()
        romBins.values().each { MameMachine.RomBin romBin ->
            File chd = IOTools.findFileInFolders(chdFolders, mameMachineName+"/"+romBin.name+".chd")
            if (!chd && romBin.merge) {
                chd = IOTools.findFileInFolders(chdFolders, mameMachineName+"/"+romBin.merge+".chd")
            }
            if (chd) {
                filesChecked << chd.toString()
                if (romBin.size >0 && chd.size() != romBin.size) {
                    println "warning! bad size checking disk ${chd.canonicalPath}"
                }
                chdsFound << romBin.name.toLowerCase()
            } else {
                filesChecked << chd.toString()+" not found"
            }
        }
        chdsFound.each { romBins.remove(it) }
        return romBins
    }

    private Map extractAllDisksToCheck(MameMachine mameMachine) {
        return extractAllRomBinsToCheck(mameMachine.disks)
    }

    private Map extractAllRomsToCheck(MameMachine mameMachine) {
        return extractAllRomBinsToCheck(mameMachine.roms)
    }

    private Map extractAllRomBinsToCheck(Collection romsOrDisks) {
        Map romBins = romsOrDisks.inject([:]) { Map map, MameMachine.RomBin romBin ->
            if (romBin.status != MameMachine.RomBin.Status.baddump && romBin.status != MameMachine.RomBin.Status.nodump) {
                map[romBin.name.toLowerCase()] = romBin
            }
            map
        }
        romBins
    }

}