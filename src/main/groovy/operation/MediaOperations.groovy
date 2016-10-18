package operation

import groovy.transform.CompileStatic
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.common.IOTools

/**
 * Created by Alberto on 18-Oct-16.
 */
class MediaOperations extends Operations {

    MediaOperations(String hs) {
        super(hs)
    }
    MediaOperations(HyperSpin hs) {
        super(hs)
    }

    void deleteUnneeded(String systemName) {
        deleteUnneeded(hyperSpin.getSystem(systemName))
    }

    void moveMediaSubfolderTo(RLSystem systemName, String subFolder = "delete") {
        executeMediaActionToUnneeded(systemName) { File file ->
            File dst = new File(file.parentFile, subFolder)
            if (!dst.exists()) dst.mkdirs()
            File newFile = new File(dst, file.name)
            log("(${simulation?"simulation":"real"}) Moving to ${newFile}")
            if (!simulation) IOTools.move(file, newFile)
        }
    }

    void addSuffixToUnneeded(RLSystem systemName, String suffix = ".delete") {
        executeMediaActionToUnneeded(systemName) { File file ->
            String newFileName = file.canonicalPath.toString()+suffix
            log("(${simulation?"simulation":"real"}) Adding suffix. Final name ${newFileName}")
            if (!simulation) file.renameTo(new File(newFileName))
        }
    }
    void deleteUnneeded(RLSystem systemName) {
        executeMediaActionToUnneeded(systemName) { File file ->
            log("(${simulation?"simulation":"real"}) Deleting ${file.canonicalPath}")
            if (!simulation) file.delete()
        }
    }

    void executeMediaActionToUnneeded(RLSystem system, Closure<File> action) {
        log("Processing ${system.name}. Simulation: ${simulation}")
        ["Video"].each { String path ->
            Set<String> unneeded = listToLowerCaseSet(system.listMediaPath(path)*.name) - listToLowerCaseSet(system.allowedMediaRomVideos())
            log "${unneeded.size()} unneeded file in $path"
            unneeded.each { String filename ->
                action.call(system.getMediaPath("$path/$filename"))
            }
        }

        ["Images/Wheel", "Images/Gamestart",
         "Images/Artwork1", "Images/Artwork2", "Images/Artwork3", "Images/Artwork4"].each { String path ->

            Set<String> unneeded = listToLowerCaseSet(system.listMediaPath(path)*.name) - listToLowerCaseSet(system.allowedMediaRomImages())
            log "${unneeded.size()} unneeded file in $path"
            unneeded.each { String filename ->
                action.call(system.getMediaPath("$path/$filename"))
            }
        }

        ["Themes"].each { String path ->
            Set<String> unneeded = listToLowerCaseSet(system.listMediaPath(path)*.name) - listToLowerCaseSet(system.allowedMediaRomThemes())
            log "${unneeded.size()} unneeded file in $path"
            unneeded.each { String filename ->
                action.call(system.getMediaPath("$path/$filename"))
            }
        }

        ["Sound/Background Music"].each { String path ->
            Set<String> unneeded = listToLowerCaseSet(system.listMediaPath(path)*.name) - listToLowerCaseSet(system.allowedMediaRomMusic())
            log "${unneeded.size()} unneeded file in $path"
            unneeded.each { String filename ->
                action.call(system.getMediaPath("$path/$filename"))
            }
        }
    }

    private static Set listToLowerCaseSet(Collection<String> l) {
        return l.collect { it.toLowerCase() } as Set
    }

}
