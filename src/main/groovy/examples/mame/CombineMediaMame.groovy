package examples.mame

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem

/**
 * Created by Alberto on 17-Oct-16.
 */

// Combina los media de todos los MAME en uno solo

HyperSpin hs = new HyperSpin("A:/RocketLauncher")
def mameSystems = hs.listSystems().findAll { it.defaultEmulator.name.startsWith("MAME") }

String dst = "A:/HyperSpin-fe/Media/_MAME"
Combine.generateWindowCommands(dst, mameSystems)

class Combine {

    static void generateWindowCommands(String dstBase, List systems, List folders = ["Video", "Images/Wheel", "Images/Artwork1", "Images/Artwork2", "Images/Artwork3", "Images/Artwork4"]) {
        folders.each { String folder ->
            File dstFolder = new File(dstBase, folder)
            println "mkdir \"${dstFolder}\""
            systems.each { RLSystem system ->
                File systemFolder = system.getMediaPath(folder)
                println "copy /Y \"${systemFolder}\"\\* \"${dstFolder}\""
                println "rmdir /S \"${systemFolder}\""
                println "mklink /D \"${systemFolder}\" \"${dstFolder}\""
            }
        }
    }
}