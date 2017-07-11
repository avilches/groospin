package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 11-Jul-17.
 */
class FourDOMapping {

    static String ACTION_P1_UP = "1:Up"
    static String ACTION_P1_DOWN = "1:Down"
    static String ACTION_P1_LEFT = "1:Left"
    static String ACTION_P1_RIGHT = "1:Right"
    static String ACTION_P1_A = "1:A"
    static String ACTION_P1_B = "1:B"
    static String ACTION_P1_C = "1:C"
    static String ACTION_P1_STOP = "1:X"
    static String ACTION_P1_PLAY = "1:P"
    static String ACTION_P1_L = "1:L"
    static String ACTION_P1_R = "1:R"

    static String ACTION_P2_UP = "2:Up"
    static String ACTION_P2_DOWN = "2:Down"
    static String ACTION_P2_LEFT = "2:Left"
    static String ACTION_P2_RIGHT = "2:Right"
    static String ACTION_P2_A = "2:A"
    static String ACTION_P2_B = "2:B"
    static String ACTION_P2_C = "2:C"
    static String ACTION_P2_STOP = "2:X"
    static String ACTION_P2_PLAY = "2:P"
    static String ACTION_P2_L = "2:L"
    static String ACTION_P2_R = "2:R"

    static String ACTION_P3_UP = "3:Up"
    static String ACTION_P3_DOWN = "3:Down"
    static String ACTION_P3_LEFT = "3:Left"
    static String ACTION_P3_RIGHT = "3:Right"
    static String ACTION_P3_A = "3:A"
    static String ACTION_P3_B = "3:B"
    static String ACTION_P3_C = "3:C"
    static String ACTION_P3_STOP = "3:X"
    static String ACTION_P3_PLAY = "3:P"
    static String ACTION_P3_L = "3:L"
    static String ACTION_P3_R = "3:R"

    static String ACTION_P4_UP = "4:Up"
    static String ACTION_P4_DOWN = "4:Down"
    static String ACTION_P4_LEFT = "4:Left"
    static String ACTION_P4_RIGHT = "4:Right"
    static String ACTION_P4_A = "4:A"
    static String ACTION_P4_B = "4:B"
    static String ACTION_P4_C = "4:C"
    static String ACTION_P4_STOP = "4:X"
    static String ACTION_P4_PLAY = "4:P"
    static String ACTION_P4_L = "4:L"
    static String ACTION_P4_R = "4:R"

    static String ACTION_STATESAVE = "0:ConsoleStateSave"
    static String ACTION_STATELOAD = "0:ConsoleStateLoad"
    static String ACTION_STATESLOTPREVIOUS = "0:ConsoleStateSlotPrevious"
    static String ACTION_STATESLOTNEXT = "0:ConsoleStateSlotNext"
    static String ACTION_FULLSCREEN = "0:ConsoleFullScreen"
    static String ACTION_SCREENSHOT = "0:ConsoleScreenShot"
    static String ACTION_PAUSE = "0:ConsolePause"
    static String ACTION_ADVANCEBYSINGLEFRAME = "0:ConsoleAdvanceBySingleFrame"
    static String ACTION_RESET = "0:ConsoleReset"


    static Map allKeys = [
            (ACTION_P1_UP)               : UP,
            (ACTION_P1_DOWN)             : DOWN,
            (ACTION_P1_LEFT)             : LEFT,
            (ACTION_P1_RIGHT)            : RIGHT,
            (ACTION_P1_A)                : KEY_Z,
            (ACTION_P1_B)                : KEY_X,
            (ACTION_P1_C)                : KEY_A,
            (ACTION_P1_STOP)             : KEY_5,
            (ACTION_P1_PLAY)             : KEY_1,
            (ACTION_P1_L)                : KEY_Q,
            (ACTION_P1_R)                : KEY_W,
            (ACTION_P2_UP)               : KEY_8_PAD,
            (ACTION_P2_DOWN)             : KEY_2_PAD,
            (ACTION_P2_LEFT)             : KEY_4_PAD,
            (ACTION_P2_RIGHT)            : KEY_6_PAD,
            (ACTION_P2_A)                : KEY_C,
            (ACTION_P2_B)                : KEY_V,
            (ACTION_P2_C)                : KEY_D,
            (ACTION_P2_STOP)             : KEY_6,
            (ACTION_P2_PLAY)             : KEY_2,
            (ACTION_P2_L)                : KEY_E,
            (ACTION_P2_R)                : KEY_R,
            (ACTION_STATESAVE)           : F2,
            (ACTION_STATELOAD)           : F4,
            (ACTION_STATESLOTPREVIOUS)   : F6,
            (ACTION_STATESLOTNEXT)       : F7,
            (ACTION_FULLSCREEN)          : KEY_F,
            (ACTION_SCREENSHOT)          : F3,
            (ACTION_PAUSE)               : KEY_P,
            (ACTION_ADVANCEBYSINGLEFRAME): F10,
            (ACTION_RESET)               : F12
    ]

    static String KEY_A = "A"
    static String KEY_B = "B"
    static String KEY_C = "C"
    static String KEY_D = "D"
    static String KEY_E = "E"
    static String KEY_F = "F"
    static String KEY_G = "G"
    static String KEY_H = "H"
    static String KEY_I = "I"
    static String KEY_J = "J"
    static String KEY_K = "K"
    static String KEY_L = "L"
    static String KEY_M = "M"
    static String KEY_N = "N"
    static String KEY_O = "O"
    static String KEY_P = "P"
    static String KEY_Q = "Q"
    static String KEY_R = "R"
    static String KEY_S = "S"
    static String KEY_T = "T"
    static String KEY_U = "U"
    static String KEY_V = "V"
    static String KEY_W = "W"
    static String KEY_X = "X"
    static String KEY_Y = "Y"
    static String KEY_Z = "Z"

    static String DOWN = "Down"
    static String LEFT = "Left"
    static String RIGHT = "Right"
    static String UP = "Up"

    static String CONTROL = "ControlKey"
    static String SHIFT = "ShiftKey"
    static String SPACE = "Space"
    static String RETURN = "Return"


    static String F1 = "F1"
    static String F2 = "F2"
    static String F3 = "F3"
    static String F4 = "F4"
    static String F5 = "F5"
    static String F6 = "F6"
    static String F7 = "F7"
    static String F8 = "F8"
    static String F9 = "F9"
    static String F10 = "F10"
    static String F11 = "F11"
    static String F12 = "F12"

    static String KEY_0 = "D0"
    static String KEY_1 = "D1"
    static String KEY_2 = "D2"
    static String KEY_3 = "D3"
    static String KEY_4 = "D4"
    static String KEY_5 = "D5"
    static String KEY_6 = "D6"
    static String KEY_7 = "D7"
    static String KEY_8 = "D8"
    static String KEY_9 = "D9"

    static String KEY_0_PAD = "NumPad0"
    static String KEY_1_PAD = "NumPad1"
    static String KEY_2_PAD = "NumPad2"
    static String KEY_3_PAD = "NumPad3"
    static String KEY_4_PAD = "NumPad4"
    static String KEY_5_PAD = "NumPad5"
    static String KEY_6_PAD = "NumPad6"
    static String KEY_7_PAD = "NumPad7"
    static String KEY_8_PAD = "NumPad8"
    static String KEY_9_PAD = "NumPad9"

    static Map createDefaultMapping() {
        return [:] + allKeys
    }

    static void writeMapping(HyperSpin hs, Map map) {
        println "- FourDO: setting config"
        File f = new File(hs.fourDOFolder, "Settings\\JohnnyInputBindings.xml")
        println f.absolutePath
        writeMapping(f, map)
    }

    static void writeMapping(File settings, Map map) {
        Set missingKeys = map.entrySet().findAll { it.value == null }
        if (missingKeys) {
            throw new RuntimeException("FourDO mapping error. Actions with no mapping ${missingKeys.join(", ")}")
        }

        settings.text =
                """<InputBindingDevices className="FourDO.Emulation.Plugins.Input.JohnnyInput.InputBindingDevices">
\t<FormatVersion>2</FormatVersion>
\t<Count>7</Count>
\t<System.Collections.IEnumerable>
${inputBindingDevices(map, 0)}
${inputBindingDevices(map, 1)}
${inputBindingDevices(map, 2)}
${inputBindingDevices(map, 3)}
${inputBindingDevices(map, 4)}
${inputBindingDevices(map, 5)}
${inputBindingDevices(map, 6)}
\t</System.Collections.IEnumerable>
</InputBindingDevices>"""


        println "- FourDO reset: keyboard:"
        println settings.absolutePath
    }

    private static String inputBindingDevices(Map allConfig, Integer pos) {
        String bindings = ""

        Map config = allConfig.findAll { String k, v ->
            k.startsWith(pos.toString()+":")
        }
        if (config) {
            bindings = "\n\t\t\t\t\t<System.Collections.IEnumerable>\n"

            config.each {  String k, String v ->
                bindings += """\t\t\t\t\t\t<InputBinding className="FourDO.Emulation.Plugins.Input.JohnnyInput.InputBinding">
\t\t\t\t\t\t\t<Button>${k.substring(k.indexOf(":")+1)}</Button>
\t\t\t\t\t\t\t<Trigger className="FourDO.Emulation.Plugins.Input.JohnnyInput.KeyboardInputTrigger">
\t\t\t\t\t\t\t\t<FriendlyName>${v}</FriendlyName>
\t\t\t\t\t\t\t\t<Key>${v}</Key>
\t\t\t\t\t\t\t</Trigger>
\t\t\t\t\t\t</InputBinding>
"""
            }
            bindings += "\t\t\t\t\t</System.Collections.IEnumerable>"

        }
        return """\t\t<InputBindingDevice className="FourDO.Emulation.Plugins.Input.JohnnyInput.InputBindingDevice">
\t\t\t<BindingSets className="FourDO.Emulation.Plugins.Input.JohnnyInput.InputBindingSets">
\t\t\t\t<InputBindingSet className="FourDO.Emulation.Plugins.Input.JohnnyInput.InputBindingSet">
\t\t\t\t\t<Count>${config.size()}</Count>${bindings}
\t\t\t\t</InputBindingSet>
\t\t\t</BindingSets>
\t\t</InputBindingDevice>"""
    }

}
