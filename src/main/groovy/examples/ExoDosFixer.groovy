package examples

import org.hs5tb.groospin.common.IOTools

new File("d:\\Games\\PC\\Microsoft Windows 3.x\\Games\\!win3x\\").eachFile { File f ->
    if (!f.directory) return

    File iniFile = new File(f, "Meagre\\IniFile").listFiles()[0]
    String romName = IOTools.getFilenameWithoutExtension(iniFile.name)
    File zip = new File(f, "../${romName}.zip")
    if (zip.exists()) {
        println "No existe ${zip}"
    } else {
        File title = new File(f, "Meagre\\Title\\title.png")
        File front = new File(f, "Meagre\\Front\\front.png")
        if (title.exists()) {
            IOTools.copy(title, new File("d:\\Games\\HyperSpin-fe\\Media\\Microsoft Windows 3.x\\Images\\Wheel\\${romName}.png"))
        } else if (front.exists()) {
            IOTools.copy(front, new File("d:\\Games\\HyperSpin-fe\\Media\\Microsoft Windows 3.x\\Images\\Wheel\\${romName}.png"))
        }

        File screen = new File(f, "Meagre\\Screen\\screen.png")
        if (screen.exists()) {
            IOTools.copy(screen, new File("d:\\Games\\HyperSpin-fe\\Media\\Microsoft Windows 3.x\\Video\\${romName}.png"))
        }
    }
}