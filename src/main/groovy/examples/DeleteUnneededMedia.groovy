package examples

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem

def spin = new HyperSpin("A:/RocketLauncher")

List systems = []
systems.each {
    deleteUnneeded(spin.getSystem(it), false)
}

spin.listSystems().each { deleteUnneeded(it, false) }

void deleteUnneeded(RLSystem mame, boolean simulation = true) {
    println "Simulation: ${simulation}"
    ["Video"].each { String path ->
        Set<String> unneeded = lcase(mame.listMediaPath(path)*.name) - lcase(mame.allowedMediaRomVideos())
        println "Deleting ${unneeded.size()} in $path"
        unneeded.each { String filename ->
            if (simulation) println mame.getMediaPath("$path/$filename")
            else mame.getMediaPath("$path/$filename").delete()
        }
    }

    ["Images/Wheel", "Images/Gamestart",
     "Images/Artwork1", "Images/Artwork2", "Images/Artwork3", "Images/Artwork4"].each { String path ->

        Set<String> unneeded = lcase(mame.listMediaPath(path)*.name) - lcase(mame.allowedMediaRomImages())
        println "Deleting ${unneeded.size()} in $path"
        unneeded.each { String filename ->
            if (simulation) println mame.getMediaPath("$path/$filename")
            else mame.getMediaPath("$path/$filename").delete()
        }
    }

    ["Themes"].each { String path ->
        Set<String> unneeded = lcase(mame.listMediaPath(path)*.name) - lcase(mame.allowedMediaRomThemes())
        println "Deleting ${unneeded.size()} in $path"
        unneeded.each { String filename ->
            if (simulation) println mame.getMediaPath("$path/$filename")
            else mame.getMediaPath("$path/$filename").delete()
        }
    }

    ["Sound/Background Music"].each { String path ->
        Set<String> unneeded = lcase(mame.listMediaPath(path)*.name) - lcase(mame.allowedMediaRomMusic())
        println "Deleting ${unneeded.size()} in $path"
        unneeded.each { String filename ->
            if (simulation) println mame.getMediaPath("$path/$filename")
            else mame.getMediaPath("$path/$filename").delete()
        }
    }
    println "Simulation: ${simulation}"
}
Set lcase(l) {
    return l.collect { it.toLowerCase() } as Set
}
