package mapping

import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 25-Jul-17.
 */
class NullDcMapping {

    static String KEYBOARD = "drkMapleDevices_Win32.dll:0"
    static String KEYBOARD_DOTNET = "drkMapleDevices_Win32.dll:1"
    static String XBOX_360 = "XMaple_Win32.dll:0"

    static String DEVICE_VMU = "drkMapleDevices_Win32.dll:2"
    static String DEVICE_KEYBOARD = "G15_drkMapleDevices_Win32.dll:2"


    static List<File> setKeyboard(File nullDc) {
        set360(nullDc)

        IniFile ini = new IniFile().parse(new File(nullDc, "nullDC.cfg"))
        println "- nullDC Configuring keyboard for player 1"
        ini.put("nullDC_plugins", "Current_maple0_5", KEYBOARD)
        ini.store()
        return [ini.file]
    }

    static List<File> set360(File nullDc) {
        IniFile ini = new IniFile().parse(new File(nullDc, "nullDC.cfg"))
        println "- nullDC Configuring Xbox 360 for players 1,2,3 & 4"
        setDefaultPlayer1Keys(ini)
        ini.put("nullDC_plugins", "Current_maple0_5", XBOX_360)
        ini.put("nullDC_plugins", "Current_maple1_5", XBOX_360)
        ini.put("nullDC_plugins", "Current_maple2_5", XBOX_360)
        ini.put("nullDC_plugins", "Current_maple3_5", XBOX_360)

        (1..3).each { player ->
            ini.put("nullDC_plugins", "Current_maple${player}_0", "NULL")
            ini.put("nullDC_plugins", "Current_maple${player}_1", "NULL")
        }

        // Solo el player 1 tiene dispositivos enganchados al mando
        ini.put("nullDC_plugins", "Current_maple0_1", DEVICE_VMU)
        ini.put("nullDC_plugins", "Current_maple0_1", DEVICE_KEYBOARD)

        ini.store()
        return [ini.file]
    }

    static String UP = "38"
    static String DOWN = "40"
    static String RIGHT = "37"
    static String LEFT = "39"

    static String KEY_A = "65"
    static String KEY_B = "66"
    static String KEY_C = "67"
    static String KEY_D = "68"
    static String KEY_E = "69"
    static String KEY_F = "70"
    static String KEY_G = "71"
    static String KEY_H = "72"
    static String KEY_I = "73"
    static String KEY_J = "74"
    static String KEY_K = "75"
    static String KEY_L = "76"
    static String KEY_M = "77"
    static String KEY_N = "78"
    static String KEY_O = "79"
    static String KEY_P = "80"
    static String KEY_Q = "81"
    static String KEY_R = "82"
    static String KEY_S = "83"
    static String KEY_T = "84"
    static String KEY_U = "85"
    static String KEY_V = "86"
    static String KEY_W = "87"
    static String KEY_X = "88"
    static String KEY_Y = "89"
    static String KEY_Z = "90"

    static String KEY_0 = "48"
    static String KEY_1 = "49"
    static String KEY_2 = "50"
    static String KEY_3 = "51"
    static String KEY_4 = "52"
    static String KEY_5 = "53"
    static String KEY_6 = "54"
    static String KEY_7 = "55"
    static String KEY_8 = "56"
    static String KEY_9 = "57"

    static String ACTION_P1_A = "PortA_CONT_A"
    static String ACTION_P1_B = "PortA_CONT_B"
    static String ACTION_P1_C = "PortA_CONT_C"
    static String ACTION_P1_START = "PortA_CONT_START"
    static String ACTION_P1_DPAD_UP = "PortA_CONT_DPAD_UP"
    static String ACTION_P1_DPAD_DOWN = "PortA_CONT_DPAD_DOWN"
    static String ACTION_P1_DPAD_LEFT = "PortA_CONT_DPAD_LEFT"
    static String ACTION_P1_DPAD_RIGHT = "PortA_CONT_DPAD_RIGHT"
    static String ACTION_P1_Z = "PortA_CONT_Z"
    static String ACTION_P1_Y = "PortA_CONT_Y"
    static String ACTION_P1_X = "PortA_CONT_X"
    static String ACTION_P1_DPAD2_UP = "PortA_CONT_DPAD2_UP"
    static String ACTION_P1_DPAD2_DOWN = "PortA_CONT_DPAD2_DOWN"
    static String ACTION_P1_DPAD2_LEFT = "PortA_CONT_DPAD2_LEFT"
    static String ACTION_P1_DPAD2_RIGHT = "PortA_CONT_DPAD2_RIGHT"
    static String ACTION_P1_ANALOG_UP = "PortA_CONT_ANALOG_UP"
    static String ACTION_P1_ANALOG_DOWN = "PortA_CONT_ANALOG_DOWN"
    static String ACTION_P1_ANALOG_LEFT = "PortA_CONT_ANALOG_LEFT"
    static String ACTION_P1_ANALOG_RIGHT = "PortA_CONT_ANALOG_RIGHT"
    static String ACTION_P1_LSLIDER = "PortA_CONT_LSLIDER"
    static String ACTION_P1_RSLIDER = "PortA_CONT_RSLIDER"


    static void setDefaultPlayer1Keys(IniFile ini) {
        ini.put("ndc_hookjoy", ACTION_P1_A, KEY_X)
        ini.put("ndc_hookjoy", ACTION_P1_B, KEY_Z)
        ini.put("ndc_hookjoy", ACTION_P1_C, KEY_C)
        ini.put("ndc_hookjoy", ACTION_P1_X, KEY_S)
        ini.put("ndc_hookjoy", ACTION_P1_Y, KEY_A)
        ini.put("ndc_hookjoy", ACTION_P1_Z, KEY_E)
        ini.put("ndc_hookjoy", ACTION_P1_LSLIDER, KEY_Q)
        ini.put("ndc_hookjoy", ACTION_P1_RSLIDER, KEY_W)

        ini.put("ndc_hookjoy", ACTION_P1_START, KEY_1)
        ini.put("ndc_hookjoy", ACTION_P1_DPAD_UP, UP)
        ini.put("ndc_hookjoy", ACTION_P1_DPAD_DOWN, DOWN)
        ini.put("ndc_hookjoy", ACTION_P1_DPAD_LEFT, RIGHT)
        ini.put("ndc_hookjoy", ACTION_P1_DPAD_RIGHT, LEFT)

        ini.put("ndc_hookjoy", ACTION_P1_DPAD2_UP, "0") // BUG EN NullDC!! SE LLAMAN DE OTRA FORMA! KEY_R
        ini.put("ndc_hookjoy", ACTION_P1_DPAD2_DOWN, "0") // BUG EN NullDC!! SE LLAMAN DE OTRA FORMA! KEY_F
        ini.put("ndc_hookjoy", ACTION_P1_DPAD2_LEFT, "0") // BUG EN NullDC!! SE LLAMAN DE OTRA FORMA! KEY_D
        ini.put("ndc_hookjoy", ACTION_P1_DPAD2_RIGHT, "0") // BUG EN NullDC!! SE LLAMAN DE OTRA FORMA! KEY_G

        ini.put("ndc_hookjoy", ACTION_P1_ANALOG_UP, KEY_I)
        ini.put("ndc_hookjoy", ACTION_P1_ANALOG_DOWN, KEY_K)
        ini.put("ndc_hookjoy", ACTION_P1_ANALOG_LEFT, KEY_J)
        ini.put("ndc_hookjoy", ACTION_P1_ANALOG_RIGHT, KEY_L)

    }
}
