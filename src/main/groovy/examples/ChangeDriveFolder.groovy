package examples

import groovy.io.FileType
import org.hs5tb.groospin.common.IOTools


class ChangeDrive {
    static String find = "(?i)D:\\\\Games"
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
    static extensions = ["ini","conf","xml","ahk","cfg","bat","cmd","txt","properties", "groovy", "java","config"] as Set
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

    void changeResource(File resource) {
        if (resource.directory) {
            resource.eachFileRecurse(FileType.FILES) { File file ->
                if (IOTools.getExtension(file.name.toLowerCase()) in extensions) {
                    replaceFile(file)
                }
            }
        } else {
            replaceFile(resource)
        }
    }

    void replaceFile(File file) {
        String replace = "${desired}:\\\\Games"
        String oldText = file.text
        String newText = oldText.replaceAll(find, replace)
        print "${file.absolutePath}..."
        if (oldText != newText) {
            println " REPLACED!!"
            if (!simulation) {
                IOTools.copy(file, new File(file.parentFile, "${file.name}.D_DRIVE_ORIGINAL"))
                file.text = newText
            }
        } else {
            println ""
        }
    }



}
