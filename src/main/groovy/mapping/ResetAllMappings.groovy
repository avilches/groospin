package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.J2K
import org.hs5tb.groospin.base.MameIni
import org.hs5tb.groospin.base.RetroArch
import org.hs5tb.groospin.common.IniFile

class ResetAllMappings {

    /*
    Vacia todos los mapeos de JoyToKey
     */

    static void emptyAllJoyToKeyProfiles(HyperSpin hs) {
        println "- JoyToKey: Empty all profiles:"
        println hs.newRocketLauncherFile("Profiles/JoyToKey").absolutePath + "\\**\\*"
        (hs.listAllJoyToKeyProfiles() + new J2K(hs, "HyperSpin")).each { it.empty() }
    }

    static void resetHyperSpinMainMenuControls(HyperSpin hs) {
        println "- HyperSpin: Setting default keys:"
        println hs.newHyperSpinFile("Settings/Settings.ini").absolutePath
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

    static void resetMameCtrl(HyperSpin hs) {
        MameIni mameIni = hs.getMameIni("ini/presets/mame.ini")
        println "- MAME: reset ctrlr to none:"
        println mameIni.file.absolutePath
        mameIni.set("keyboardprovider", "dinput")  // ensure MAME can read JoyToKey mappings
        mameIni.set("ctrlr", "")
        mameIni.save()

        hs.mameMapping.backupAndCleanDefaultCfg()
    }

    static void resetRetroArch(RetroArch retroArch) {
        println "- Retroarch reset: empty players button&joystick, force default keys for extra):"
        println retroArch.iniFile.file.absolutePath
        retroArch.with {
            resetKeys()
            resetPlayer(1, 1)
            resetPlayer(2, 2)
            save()
        }
    }

    static void resetWinVice(File folder) {
        File iniFile = new File(folder, "vice.ini")
        println "- WinVICE reset: P1:AWSD + Q/ P2:IJKL + U):"
        println iniFile.absolutePath
        IniFile cfg = new IniFile().parse(iniFile)

// http://vice-emu.pokefinder.org/index.php/Hotkey_cleanup
// http://vice-emu.pokefinder.org/index.php/Keymaps
        ["C64", "C64DTV", "C128", "VIC20", "PET", "CBM-II-5x0", "CBM-II", "C64SC", "VSID"].each { String machine ->

            cfg.put(machine, "SaveResourcesOnExit", "1")
            cfg.put(machine, "ConfirmOnExit", "0")

            final String JOY_DEVICE_NONE = "0"
            final String JOY_DEVICE_NUMPAD_CTRL = "1"
            final String JOY_DEVICE_KEYSET_A = "2"
            final String JOY_DEVICE_KEYSET_B = "3"
            final def reset = { p ->
                cfg.put(machine, "KeySet${p}North", "0")
                cfg.put(machine, "KeySet${p}East", "0")
                cfg.put(machine, "KeySet${p}South", "0")
                cfg.put(machine, "KeySet${p}West", "0")
                cfg.put(machine, "KeySet${p}NorthWest", "0")
                cfg.put(machine, "KeySet${p}NorthEast", "0")
                cfg.put(machine, "KeySet${p}SouthWest", "0")
                cfg.put(machine, "KeySet${p}SouthEast", "0")
                cfg.put(machine, "KeySet${p}Fire", "0")
            }

            reset(1)
            reset(2)

            cfg.put(machine, "KeySetEnable", "1")
            cfg.put(machine, "JoyDevice1", JOY_DEVICE_KEYSET_A)
            cfg.put(machine, "JoyDevice2", JOY_DEVICE_KEYSET_B)

            cfg.put(machine, "KeySet1North", "17") // W
            cfg.put(machine, "KeySet1East", "32")  // A
            cfg.put(machine, "KeySet1South", "31") // S
            cfg.put(machine, "KeySet1West", "30")  // D
            cfg.put(machine, "KeySet1Fire", "16")  // Q

            cfg.put(machine, "KeySet2North", "23") // I
            cfg.put(machine, "KeySet2East", "38")  // L
            cfg.put(machine, "KeySet2South", "37") // K
            cfg.put(machine, "KeySet2West", "36")  // J
            cfg.put(machine, "KeySet2Fire", "22")  // U
            cfg.store()
        }

    }

}

