package mapping

import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 25-Jul-17.
 */
class DolphinGamecube {
    private static String create360(section, port) {
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

    private static String createKeyboard(section, port) {
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


    static List<File> setDefaults(File dolphin) {
        new File(dolphin, "User\\Config\\Profiles\\GCPad").mkdirs()
        new File(dolphin, "User\\Config\\Profiles\\GCPad\\Xbox 360 port 1.ini").text = create360("Profile", "0")
        new File(dolphin, "User\\Config\\Profiles\\GCPad\\Xbox 360 port 2.ini").text = create360("Profile", "1")
        new File(dolphin, "User\\Config\\Profiles\\GCPad\\Xbox 360 port 3.ini").text = create360("Profile", "2")
        new File(dolphin, "User\\Config\\Profiles\\GCPad\\Xbox 360 port 4.ini").text = create360("Profile", "3");
        new File(dolphin, "User\\Config\\Profiles\\GCPad\\keyboard.ini").text = createKeyboard("Profile", "0");

        File iniDolphinFile = new File(dolphin, "User\\Config\\Dolphin.ini")
        println " - Dolphin: set P1 + P2 + P3 + P4 to standard controller"
        IniFile cfgDolphin = new IniFile(equals: " = ").parse(iniDolphinFile)
        cfgDolphin.put("Core", "SIDevice0", "6") // Standard controller
        cfgDolphin.put("Core", "SIDevice1", "6") // Standard controller
        cfgDolphin.put("Core", "SIDevice2", "6") // Standard controller
        cfgDolphin.put("Core", "SIDevice3", "6") // Standard controller
        cfgDolphin.store()

        return [iniDolphinFile]
    }

    static List<File> setDefaultKeyboard(File dolphin) {
        List<File> files = setDefaults(dolphin)
        File iniPadFile = new File(dolphin, "User\\Config\\GCPadNew.ini")
        println " - Dolphin: set P1 + P2 + P3 + P4 to keyboard"
        iniPadFile.text = createKeyboard("GCPad1", "0")
        files + [iniPadFile]
    }

    static List<File> setDefault360(File dolphin) {
        File iniPadFile = new File(dolphin, "User\\Config\\GCPadNew.ini")
        println " - Dolphin: set P1 + P2 to 360"
        iniPadFile.text =
                """${create360("GCPad1", "0")}
${create360("GCPad2", "1")}
${create360("GCPad3", "2")}
${create360("GCPad4", "3")}
"""

        return setDefaults(dolphin) + [iniPadFile]

    }



}
