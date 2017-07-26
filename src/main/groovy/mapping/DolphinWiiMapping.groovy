package mapping

import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 25-Jul-17.
 */
class DolphinWiiMapping {

    private static String create360(section, port) {
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

    static void setDefaults(File dolphin) {
        new File(dolphin, "User\\Config\\Profiles\\Wiimote").mkdirs()
        new File(dolphin, "User\\Config\\Profiles\\Wiimote\\Xbox 360 port 1.ini").text = create360("Profile", "0")
        new File(dolphin, "User\\Config\\Profiles\\Wiimote\\Xbox 360 port 2.ini").text = create360("Profile", "1")
        new File(dolphin, "User\\Config\\Profiles\\Wiimote\\Xbox 360 port 3.ini").text = create360("Profile", "2")
        new File(dolphin, "User\\Config\\Profiles\\Wiimote\\Xbox 360 port 4.ini").text = create360("Profile", "3");

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

    static void setDefault360(File dolphin) {
        File iniPadFile = new File(dolphin, "User\\Config\\WiimoteNew.ini")
        println " - Dolphin: set P1 + P2 + P3 + P4 to 360"
        println iniPadFile.absolutePath
        iniPadFile.text =
                """${create360("Wiimote1", "0")}
${create360("Wiimote2", "1")}
${create360("Wiimote3", "2")}
${create360("Wiimote4", "3")}
"""
        // Esto debe hacerse al final para que añada la propiedad Source=1 dentro del mapeo
        setDefaults(dolphin)
    }

}
