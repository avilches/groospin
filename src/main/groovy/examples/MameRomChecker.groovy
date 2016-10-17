package examples

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.hs5tb.groospin.base.MameMachine


import java.security.DigestInputStream
import java.security.MessageDigest
import java.util.zip.CRC32
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

/**
 * Created by Alberto on 10-Oct-16.
 */

class MameRomChecker2 {


    @EqualsAndHashCode(excludes = ["owner"])
    @ToString
    class RomFile {
        File owner
        String name
        int size
        String sha1
    }

    Map<String, RomFile> bins = new HashMap(200000)

    void chec(String datFileName, List folders) {


        folders.each { String folderName ->
            File folder = new File(folderName)
            if (!folder.exists()) return
            folder.eachFileRecurse(groovy.io.FileType.FILES) {
                if (it.name.endsWith(".zip")) {
                    ZipFile zipFile = new ZipFile(it)
                    zipFile.entries().each { ZipEntry zipEntry ->
                        // println "${zipEntry.name}"
                        newRomFile([owner: it, name:zipEntry.name.toLowerCase(), size: zipEntry.size, sha1: sha1(zipFile.getInputStream(zipEntry))])
                    }
                } else {
                    newRomFile([owner: it, name:(it.name - ".chd").toLowerCase(), size: it.size(), sha1: sha1(it.newInputStream())])
                }
                if (bins.size() % 1000 == 0) {
                    println bins.size()
                }
            }
        }

        List<MameMachine> roms = MameMachine.loadRoms(new File(datFileName))
        return
        roms.eachWithIndex { MameMachine rom, int idx ->
            if (rom.roms.every { it.toLowerCase() in bins } && rom.disks.every { it.toLowerCase() in bins }) {
            } else {
                println "Falta ${rom.name}: ${(rom.roms+rom.disks)-bins}"
            }
        }
    }

    void newRomFile(Map map) {
        RomFile romFile = new RomFile(map)
        RomFile collision = bins[romFile.name]
        if (!collision) {
            bins[romFile.name] = romFile
        } else {
            if (collision != romFile) {
                println "Warning: ${romFile} collied with ${collision}"
            }
        }
    }

    String crc(InputStream is) {
        CRC32 crc = new CRC32()
        crc.update(is.bytes)
        return Integer.toHexString((int)crc.value).padLeft(8, "0")

    }

    String sha1(InputStream is) {
        MessageDigest md = MessageDigest.getInstance("SHA1")
        byte[] buffer = new byte[8192]
        DigestInputStream dis = new DigestInputStream(is, md)
        try {
            while (dis.read(buffer) != -1);
        } finally{
            dis.close()
        }
        return new BigInteger(1, md.digest()).toString(16).toLowerCase()
    }
}
