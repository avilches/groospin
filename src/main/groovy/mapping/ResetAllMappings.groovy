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

    static void setHyperSpinDefaultKeys(HyperSpin hs) {
        HyperSpinMapping.writeMapping(hs, HyperSpinMapping.createDefaultMapping())
        /*
        Resetea los joystick 1 y 2 para que funcionen de fabrica y TODAS las teclas de sistema.
        */
    }

    static void setNoMameCtrlAndDefaultCfg(HyperSpin hs) {
        MameIni mameIni = hs.getMameIni("ini/presets/mame.ini")
        println "- MAME: reset ctrlr to none:"
        println mameIni.file.absolutePath
        mameIni.set("keyboardprovider", "dinput")  // ensure MAME can read JoyToKey mappings
        mameIni.set("ctrlr", "")
        mameIni.save()

        hs.mameMapping.backupAndCleanDefaultCfg()
    }

    static void emptyRetroArch(RetroArch retroArch) {
        println "- Retroarch reset: empty players button&joystick, force default keys for extra):"
        println retroArch.iniFile.file.absolutePath

        retroArch.iniFile.put("system_directory", ":\\system")
        retroArch.save()

        retroArch.with {
            resetKeysToSystemDefault()
            emptyKeysForPlayer(1, 1)
            emptyKeysForPlayer(2, 2)
            save()
        }
    }

    static void setPS2DefaultKeys(HyperSpin hs) {
        PCSX2Mapping.setPPSSPP360AndKeys(hs)
    }

    static void setPPSSPP360AndKeys(HyperSpin hs) {
        PPSSPPMapping.setPPSSPP360AndKeys(hs)
    }

    private static String dolphinGamecube360(section, port) {
        """[${section}]
Device = XInput/${port}/Gamepad
Buttons/A = `Button B`
Buttons/B = `Button A`
Buttons/X = `Button Y`
Buttons/Y = `Button X`
Buttons/Z = Back
Buttons/Start = Start
Main Stick/Up = `Left Y+`
Main Stick/Down = `Left Y-`
Main Stick/Left = `Left X-`
Main Stick/Right = `Left X+`
C-Stick/Up = `Right Y+`
C-Stick/Down = `Right Y-`
C-Stick/Left = `Right X-`
C-Stick/Right = `Right X+`
Triggers/L = `Shoulder L`
Triggers/R = `Shoulder R`
Triggers/L-Analog = `Trigger L`
Triggers/R-Analog = `Trigger R`
Rumble/Motor = `Motor L` | `Motor R`
D-Pad/Up = `Pad N`
D-Pad/Down = `Pad S`
D-Pad/Left = `Pad W`
D-Pad/Right = `Pad E`
"""
    }

    private static String dolphinGamecubeKeyboard(section, port) {
        """[${section}]
Device = DInput/${port}/Keyboard Mouse
Buttons/A = Z
Buttons/B = X
Buttons/X = S
Buttons/Y = A
Buttons/Z = D
Buttons/Start = C
Main Stick/Up = UP
Main Stick/Down = DOWN
Main Stick/Left = LEFT
Main Stick/Right = RIGHT
Main Stick/Modifier = RCONTROL
C-Stick/Up = T
C-Stick/Down = G
C-Stick/Left = F
C-Stick/Right = H
C-Stick/Modifier = LCONTROL
Triggers/L = Q
Triggers/R = W
Triggers/L-Analog = E
Triggers/R-Analog = R
D-Pad/Up = I
D-Pad/Down = K
D-Pad/Left = J
D-Pad/Right = L
"""
    }

    private static String dolphinWii360(section, port) {
        """[${section}]
Device = XInput/${port}/Gamepad
Buttons/A = `Button B`
Buttons/B = `Button A`
Buttons/1 = `Button Y`
Buttons/2 = `Button X`
Buttons/- = Back
Buttons/+ = Start
IR/Up = `Right Y+`
IR/Down = `Right Y-`
IR/Left = `Right X-`
IR/Right = `Right X+`
Extension = Nunchuk
Nunchuk/Buttons/C = `Shoulder L`
Nunchuk/Buttons/Z = `Trigger L`
Nunchuk/Stick/Up = `Left Y+`
Nunchuk/Stick/Down = `Left Y-`
Nunchuk/Stick/Left = `Left X-`
Nunchuk/Stick/Right = `Left X+`
D-Pad/Up = `Pad N`
D-Pad/Down = `Pad S`
D-Pad/Left = `Pad W`
D-Pad/Right = `Pad E`
"""
    }

    static void setWiiDefaults(File dolphin) {
        new File(dolphin, "User\\Config\\Profiles\\Wiimote").mkdirs()
        new File(dolphin, "User\\Config\\Profiles\\Wiimote\\Xbox 360 port 1.ini").text = dolphinWii360("Profile", "0")
        new File(dolphin, "User\\Config\\Profiles\\Wiimote\\Xbox 360 port 2.ini").text = dolphinWii360("Profile", "1")
        new File(dolphin, "User\\Config\\Profiles\\Wiimote\\Xbox 360 port 3.ini").text = dolphinWii360("Profile", "2")
        new File(dolphin, "User\\Config\\Profiles\\Wiimote\\Xbox 360 port 4.ini").text = dolphinWii360("Profile", "3");

        File iniDolphinFile = new File(dolphin, "User\\Config\\WiimoteNew.ini")
        println " - Dolphin: set P1 + P2 + P3 + P4 to Emulated Wiimote"
        println iniDolphinFile.absolutePath
        IniFile cfgDolphin = new IniFile(equals: " = ").parse(iniDolphinFile)
        cfgDolphin.put("Wiimote1", "Source", "1") // Emulated Wiimote
        cfgDolphin.put("Wiimote2", "Source", "1") // Emulated Wiimote
        cfgDolphin.put("Wiimote3", "Source", "1") // Emulated Wiimote
        cfgDolphin.put("Wiimote4", "Source", "1") // Emulated Wiimote
        cfgDolphin.store()

    }

    static void setGamecubeDefaults(File dolphin) {
        new File(dolphin, "User\\Config\\Profiles\\GCPad").mkdirs()
        new File(dolphin, "User\\Config\\Profiles\\GCPad\\Xbox 360 port 1.ini").text = dolphinGamecube360("Profile", "0")
        new File(dolphin, "User\\Config\\Profiles\\GCPad\\Xbox 360 port 2.ini").text = dolphinGamecube360("Profile", "1")
        new File(dolphin, "User\\Config\\Profiles\\GCPad\\Xbox 360 port 3.ini").text = dolphinGamecube360("Profile", "2")
        new File(dolphin, "User\\Config\\Profiles\\GCPad\\Xbox 360 port 4.ini").text = dolphinGamecube360("Profile", "3");
        new File(dolphin, "User\\Config\\Profiles\\GCPad\\keyboard.ini").text = dolphinGamecubeKeyboard("Profile", "0");

        File iniDolphinFile = new File(dolphin, "User\\Config\\Dolphin.ini")
        println " - Dolphin: set P1 + P2 + P3 + P4 to standard controller"
        println iniDolphinFile.absolutePath
        IniFile cfgDolphin = new IniFile(equals: " = ").parse(iniDolphinFile)
        cfgDolphin.put("Core", "SIDevice0", "6") // Standard controller
        cfgDolphin.put("Core", "SIDevice1", "6") // Standard controller
        cfgDolphin.put("Core", "SIDevice2", "6") // Standard controller
        cfgDolphin.put("Core", "SIDevice3", "6") // Standard controller
        cfgDolphin.store()
    }

    static void setGamecubeDefaultKeyboard(HyperSpin hs) {
        [hs.getDolphinGameCubeFolder(),
         hs.getDolphinTriforceFolder()
        ].each { File dolphin ->
            setGamecubeDefaults(dolphin)
            File iniPadFile = new File(dolphin, "User\\Config\\GCPadNew.ini")
            println " - Dolphin: set P1 + P2 + P3 + P4 to keyboard"
            println iniPadFile.absolutePath
            iniPadFile.text = dolphinGamecubeKeyboard("GCPad1", "0")
        }
    }

    static void setWiiDefault360(HyperSpin hs) {
        File dolphin = hs.getDolphinWiiFolder()
        File iniPadFile = new File(dolphin, "User\\Config\\WiimoteNew.ini")
        println " - Dolphin: set P1 + P2 + P3 + P4 to 360"
        println iniPadFile.absolutePath
        iniPadFile.text =
                """${dolphinWii360("Wiimote1", "0")}
${dolphinWii360("Wiimote2", "1")}
${dolphinWii360("Wiimote3", "2")}
${dolphinWii360("Wiimote4", "3")}
"""

        // Esto debe hacerse al final para que añada la propiedad Source=1 dentro del mapeo
        setWiiDefaults(dolphin)

    }

    static void setGamecubeDefault360(HyperSpin hs) {
        [hs.getDolphinGameCubeFolder(),
         hs.getDolphinTriforceFolder()
        ].each { File dolphin ->
            File iniPadFile = new File(dolphin, "User\\Config\\GCPadNew.ini")
            println " - Dolphin: set P1 + P2 to 360"
            println iniPadFile.absolutePath
            iniPadFile.text =
                    """${dolphinGamecube360("GCPad1", "0")}
${dolphinGamecube360("GCPad2", "1")}
${dolphinGamecube360("GCPad3", "2")}
${dolphinGamecube360("GCPad4", "3")}
"""

            setGamecubeDefaults(dolphin)
        }

    }

    static void setSuperModel3DefaultKeysAndJoy(HyperSpin hs) {
        SuperModelMapping.setDefaultKeysAndJoy(hs)
    }

    static void setWinViceDefaultKeys(HyperSpin hs) {
        WinViceMapping.writeMapping(hs, WinViceMapping.createDefaultMapping())
    }

    static setDaphneDefaultKeys(HyperSpin hs) {
        DaphneMapping.writeMapping(hs, DaphneMapping.createDefaultMapping())
    }

    static setFourDODefaultKeys(HyperSpin hs) {
        FourDOMapping.setDefaultKeys(hs)
    }

    static void setZincDefaultKeys(HyperSpin hs) {
        ZincMapping.writeMapping(hs, ZincMapping.createDefaultMapping())
    }
}

