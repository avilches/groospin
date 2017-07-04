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

        retroArch.iniFile.put("system_directory", ":\\system")
        retroArch.save()

        retroArch.with {
            resetKeys()
            resetPlayer(1, 1)
            resetPlayer(2, 2)
            save()
        }
    }

    static void resetPS2Keys(HyperSpin hs) {
        println "- PCSX2 keys: setting LilyPad and set some default keys for P1 & P2: (YOU HAVE TO CONFIGURE 360 or other pads YOURSELF!)"
        IniFile psx2 = new IniFile().parse(new File(hs.getPCSX2Folder(),"inis\\PCSX2_ui.ini"))
        IniFile lilyPad = new IniFile().parse(new File(hs.getPCSX2Folder(),"inis\\LilyPad.ini"))

        println psx2.file.absolutePath
        println lilyPad.file.absolutePath

        psx2.put("Filenames", "PAD", "LilyPad.dll")
        psx2.store()
        if (lilyPad.get("Device 1", "Display Name") != "WM Keyboard") {
            throw new Exception("ERROR CONFIGURING PSX2")
        } else {

            // PLAYER 1
            // CURSORES
            // A -> CUADRADO, S -> TRIANGULO, Z -> CRUZ, X -> CIRCULO,
            // Q -> L1, W -> R1, ESPACIO -> SELECT, ENTER -> START
            lilyPad.put("Device 1", "Binding 0", "0x0004000D, 0, 19, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 1", "0x00040020, 0, 16, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 2", "0x00040025, 0, 23, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 3", "0x00040026, 0, 20, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 4", "0x00040027, 0, 21, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 5", "0x00040028, 0, 22, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 6", "0x00040041, 0, 31, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 7", "0x00040051, 0, 26, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 8", "0x00040053, 0, 28, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 9", "0x00040057, 0, 27, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 10", "0x00040058, 0, 29, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 11", "0x0004005A, 0, 30, 65536, 0, 0, 0")
            // PLAYER 2
            // IJKL
            // CVDFER
            // SELECT N, START M
            lilyPad.put("Device 1", "Binding 12", "0x00040043, 1, 30, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 13", "0x00040044, 1, 31, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 14", "0x00040045, 1, 26, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 15", "0x00040046, 1, 28, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 16", "0x00040049, 1, 20, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 17", "0x0004004A, 1, 23, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 18", "0x0004004B, 1, 22, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 19", "0x0004004C, 1, 21, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 20", "0x0004004D, 1, 19, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 21", "0x0004004E, 1, 16, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 22", "0x00040052, 1, 27, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 23", "0x00040056, 1, 29, 65536, 0, 0, 0");

            lilyPad.store()
        }

    }
    static void resetPPSSPP(HyperSpin hs) {
        File iniFile = new File(hs.getPPSSPPFolder(), "memstick\\PSP\\SYSTEM\\controls.ini")

        println "- PPSSPP: Reseting 360 + keys"
        println iniFile.absolutePath

        IniFile cfg = new IniFile(equals: " = ").parse(iniFile)
        // Cursores, DPAD y XBOX
        cfg.put("ControlMapping", "Up", "1-19,20-19,10-19")
        cfg.put("ControlMapping", "Down", "1-20,20-20,10-20")
        cfg.put("ControlMapping", "Left", "1-21,20-21,10-21")
        cfg.put("ControlMapping", "Right", "1-22,20-22,10-22")

        // XBOX 360
        // A -> CUADRADO, S -> TRIANGULO, Z -> CRUZ, X -> CIRCULO,
        // Q -> L1, W -> R1, ESPACIO -> SELECT, ENTER -> START
        cfg.put("ControlMapping", "Circle", "1-52,20-97")
        cfg.put("ControlMapping", "Cross", "1-54,20-96")
        cfg.put("ControlMapping", "Square", "1-29,20-99")
        cfg.put("ControlMapping", "Triangle", "1-47,20-100")
        cfg.put("ControlMapping", "Start", "1-66,20-108")
        cfg.put("ControlMapping", "Select", "1-62,20-109")
        cfg.put("ControlMapping", "L", "1-45,20-102")
        cfg.put("ControlMapping", "R", "1-51,20-103")

        // AnalogicPAD, 360 stick y IJKL teclas
        cfg.put("ControlMapping", "An.Up", "1-37,20-4002,10-4003")
        cfg.put("ControlMapping", "An.Down", "1-39,20-4003,10-4002")
        cfg.put("ControlMapping", "An.Left", "1-38,20-4001,10-4001")
        cfg.put("ControlMapping", "An.Right", "1-40,20-4000,10-4000")

        // LSHIFT
        cfg.put("ControlMapping", "RapidFire", "1-59")
        cfg.put("ControlMapping", "Unthrottle", "1-61,20-4036")
        cfg.put("ControlMapping", "SpeedToggle", "1-68,20-107")

        // P, home (360),
        cfg.put("ControlMapping", "Pause", "1-44,20-4034,20-3")

        // BACKSPACE
        cfg.put("ControlMapping", "Rewind", "1-67")

        // F2 SAVE, F4 LOAD
        cfg.put("ControlMapping", "Save State", "1-132")
        cfg.put("ControlMapping", "Load State", "1-134")

        // 360 solo
        cfg.put("ControlMapping", "RightAn.Up", "20-4028")
        cfg.put("ControlMapping", "RightAn.Down", "20-4029")
        cfg.put("ControlMapping", "RightAn.Left", "20-4023")
        cfg.put("ControlMapping", "RightAn.Right", "20-4022")

        // RSHIFT
        cfg.put("ControlMapping", "Analog limiter", "1-60")
        cfg.store()
    }

    static void resetDolphin(File folder) {

    }

    static void resetWinVice(HyperSpin hs) {
        File iniFile = new File(hs.winViceFolder, "vice.ini")
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

