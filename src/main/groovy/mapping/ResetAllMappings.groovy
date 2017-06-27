package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.J2K
import org.hs5tb.groospin.base.RetroArch
import org.hs5tb.groospin.common.IniFile

class ResetAllMappings {

    /*
    Vacia todos los mapeos de JoyToKey
     */
    static void emptyAllJoyToKeyProfiles(HyperSpin hs) {
        println "Empty all JoyToKey profiles: "+hs.newRocketLauncherFile("Profiles/JoyToKey").absolutePath+"\\**\\*"
        (hs.listAllJoyToKeyProfiles()+new J2K(hs, "HyperSpin")).each { it.empty() }
    }

    static void resetHyperSpinMainMenuControls(HyperSpin hs) {
        println "Setting default HyperSpin keys: "+hs.newHyperSpinFile("Settings/Settings.ini").absolutePath
        hs.withHyperSpinSettings("Settings") { String filename, IniFile ini ->
            ini.put("P1 Joystick", "Enabled", "false")
            ini.put("P2 Joystick", "Enabled", "false")
            ini.put("Keyboard", "Key_Delay", "true")

            ini.put("P1 Controls", "Start", "13")          // enter
            ini.put("P1 Controls", "Exit", "27")           // esc
            ini.put("P1 Controls", "Up", "38")             // cursor arriba
            ini.put("P1 Controls", "Down", "40")           // cursor abajo
            ini.put("P1 Controls", "SkipUp", "37")         // derecha
            ini.put("P1 Controls", "SkipDown", "39")       // izquierda
            ini.put("P1 Controls", "SkipUpNumber", "33")   // pg up
            ini.put("P1 Controls", "SkipDownNumber", "34") // pg down
            ini.put("P1 Controls", "HyperSpin", "72")      // H
            ini.put("P1 Controls", "Genre", "71")          // G
            ini.put("P1 Controls", "Favorites", "70")      // F

            ini.put("P2 Controls", "Start", "32")          // espacio
            ini.put("P2 Controls", "Exit", "8")            // backspace
            ini.put("P2 Controls", "Up", "87")             // W
            ini.put("P2 Controls", "Down", "83")           // S
            ini.put("P2 Controls", "SkipUp", "65")         // A
            ini.put("P2 Controls", "SkipDown", "68")       // D
            ini.put("P2 Controls", "SkipUpNumber", "74")   // J
            ini.put("P2 Controls", "SkipDownNumber", "77") // M
            ini.put("P2 Controls", "HyperSpin", "72")      // H
            ini.put("P2 Controls", "Genre", "71")          // G
            ini.put("P2 Controls", "Favorites", "70")      // F
            ini.store()
        }
    }

    /*
    Resetea los joystick 1 y 2 para que funcionen de fabrica u TODAS las teclas de sistema.
    */
    static void resetRetroArch(RetroArch retroArch) {
        println "Resetting Retroarch (empty players button&joystick, force default keys for extra): "+retroArch.iniFile.file.absolutePath
        retroArch.with {
            resetKeys()
            resetPlayer(1, 1)
            resetPlayer(2, 2)
            save()
        }
    }

}

