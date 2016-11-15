package examples.mame

import examples.operations.DeleteUnneededMediaWhenCombinedMame
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem

/**
 * Created by Alberto on 17-Oct-16.
 */

// Dado un conjunto de sistemas MAME (MAME incluido), cada uno con sus medias,
// copia todos los medias de los sistemas MAME a una carpeta llamada _MAME que tendra todos los
// videos y wheels juntos. Despues hace que todos apunten a ella para ahorrar espacio

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")
def mameSystems = hs.listSystems().findAll { it.defaultEmulator.name.startsWith("MAME") }

// Crea un sistema nuevo llamado _MAME, copia ahi todo y linka para dejar espacio libre
Combine.generateWindowCommands("D:/Games/HyperSpin-fe/Media/_MAME", mameSystems)

// Solo copia (no borra ni linka) los medias encima de MAME
Combine.generateWindowCommands("D:/Games/HyperSpin-fe/Media_MAME", mameSystems, false, ["Video", "Images/Wheel"])

class Combine {
    static void generateWindowCommands(String dstBase, List systems, boolean deleteAndLink = true, List folders = ["Video", "Images/Wheel", "Images/Artwork1", "Images/Artwork2", "Images/Artwork3", "Images/Artwork4"]) {
        folders.each { String folder ->
            File dstFolder = new File(dstBase, folder)
            println "mkdir \"${dstFolder}\""
            systems.each { RLSystem system ->
                File systemFolder = system.getMediaPath(folder)
                if (systemFolder.toString() != dstFolder.toString()) {
                    println "copy /Y \"${systemFolder}\"\\* \"${dstFolder}\""
                    if (deleteAndLink) {
                        println "rmdir /S \"${systemFolder}\""
                        println "mklink /D \"${systemFolder}\" \"${dstFolder}\""
                    }
                }
            }
        }
    }
}


println "Ejecutar ahora ${DeleteUnneededMediaWhenCombinedMame.class.toString()} para borrar los sobrantes"