package examples

import groovy.io.FileType
import org.hs5tb.groospin.common.IOTools


class ChangeDrive {
    static String find = "(?i)[a-zA-Z]:\\\\Games"
    boolean simulation = true
    static folders = [
            "HyperSpin-fe\\Settings",
                      "Arcades\\Fightcade",
                      "Arcades\\HBMAME",
                      "Arcades\\MAME\\cfg",
                      "Extra",
                      "Emulators",
                      "PC\\Lucasarts",
                      "PC\\Microsoft MS-DOS eXoDOS\\MEAGRE.INI",
                      "PC\\Microsoft Windows 3.x\\MEAGRE.INI",
                      "RocketLauncher\\Modules",
                      "RocketLauncher\\Settings",
                      "RocketLauncher\\Profiles"]
    static extensions = ["ini","conf","xml","ahk","cfg","bat","cmd","prof","txt","properties", "groovy", "java","config"] as Set
    String drive
    String desired

    void run(String drive, String desired) {
        this.drive = drive
        this.desired = desired
        folders.collect {
            File resource = new File("${drive}:\\Games\\${it}")
            if (!resource.exists()) {
                throw new Exception("Folder ${resource} not found".toString())
            }
            return resource
        }.each {
            changeResource(it)
        }
    }

    boolean reverse = false

    void changeResource(File resource) {
        println "CHANGE RESOURCE ${resource.directory?"dir":"file"} ${resource} "
        if (resource.directory) {
            resource.eachFileRecurse(FileType.FILES) { File file ->
                if (reverse) {
                    if (IOTools.getExtension(file.name) == "D_DRIVE_ORIGINAL") {
                        String originalName = file.absolutePath - "D_DRIVE_ORIGINAL"
                        new File(originalName).delete()
                        file.renameTo(new File(originalName))
                        println "Reversing ${file.absolutePath}"
                    }
                } else {
                    if (IOTools.getExtension(file.name.toLowerCase()) in extensions) {
                        replaceFile(file)
                    }
                }
            }
        } else {
            if (!reverse) replaceFile(resource)
        }
    }

    void replaceFile(File file) {
        String replace = "${desired}:\\\\Games"
        String oldText = file.text
        String newText = oldText.replaceAll(find, replace)
        if (oldText != newText) {
            println "${file.absolutePath}..."
            if (!simulation) {
                IOTools.copy(file, new File(file.parentFile, "${file.name}.D_DRIVE_ORIGINAL"))
                file.text = newText
            }
        } else {
        }
    }



}
