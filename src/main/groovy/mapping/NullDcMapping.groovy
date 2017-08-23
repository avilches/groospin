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

    static String KEY_0_PAD = "96"
    static String KEY_1_PAD = "97"
    static String KEY_2_PAD = "98"
    static String KEY_3_PAD = "99"
    static String KEY_4_PAD = "100"
    static String KEY_5_PAD = "101"
    static String KEY_6_PAD = "102"
    static String KEY_7_PAD = "103"
    static String KEY_8_PAD = "104"
    static String KEY_9_PAD = "105"

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

    static String ACTION_P2_A = "PortB_CONT_A"
    static String ACTION_P2_B = "PortB_CONT_B"
    static String ACTION_P2_C = "PortB_CONT_C"
    static String ACTION_P2_START = "PortB_CONT_START"
    static String ACTION_P2_DPAD_UP = "PortB_CONT_DPAD_UP"
    static String ACTION_P2_DPAD_DOWN = "PortB_CONT_DPAD_DOWN"
    static String ACTION_P2_DPAD_LEFT = "PortB_CONT_DPAD_LEFT"
    static String ACTION_P2_DPAD_RIGHT = "PortB_CONT_DPAD_RIGHT"
    static String ACTION_P2_Z = "PortB_CONT_Z"
    static String ACTION_P2_Y = "PortB_CONT_Y"
    static String ACTION_P2_X = "PortB_CONT_X"
    static String ACTION_P2_DPAD2_UP = "PortB_CONT_DPAD2_UP"
    static String ACTION_P2_DPAD2_DOWN = "PortB_CONT_DPAD2_DOWN"
    static String ACTION_P2_DPAD2_LEFT = "PortB_CONT_DPAD2_LEFT"
    static String ACTION_P2_DPAD2_RIGHT = "PortB_CONT_DPAD2_RIGHT"
    static String ACTION_P2_ANALOG_UP = "PortB_CONT_ANALOG_UP"
    static String ACTION_P2_ANALOG_DOWN = "PortB_CONT_ANALOG_DOWN"
    static String ACTION_P2_ANALOG_LEFT = "PortB_CONT_ANALOG_LEFT"
    static String ACTION_P2_ANALOG_RIGHT = "PortB_CONT_ANALOG_RIGHT"
    static String ACTION_P2_LSLIDER = "PortB_CONT_LSLIDER"
    static String ACTION_P2_RSLIDER = "PortB_CONT_RSLIDER"

    static String ACTION_P3_A = "PortC_CONT_A"
    static String ACTION_P3_B = "PortC_CONT_B"
    static String ACTION_P3_C = "PortC_CONT_C"
    static String ACTION_P3_START = "PortC_CONT_START"
    static String ACTION_P3_DPAD_UP = "PortC_CONT_DPAD_UP"
    static String ACTION_P3_DPAD_DOWN = "PortC_CONT_DPAD_DOWN"
    static String ACTION_P3_DPAD_LEFT = "PortC_CONT_DPAD_LEFT"
    static String ACTION_P3_DPAD_RIGHT = "PortC_CONT_DPAD_RIGHT"
    static String ACTION_P3_Z = "PortC_CONT_Z"
    static String ACTION_P3_Y = "PortC_CONT_Y"
    static String ACTION_P3_X = "PortC_CONT_X"
    static String ACTION_P3_DPAD2_UP = "PortC_CONT_DPAD2_UP"
    static String ACTION_P3_DPAD2_DOWN = "PortC_CONT_DPAD2_DOWN"
    static String ACTION_P3_DPAD2_LEFT = "PortC_CONT_DPAD2_LEFT"
    static String ACTION_P3_DPAD2_RIGHT = "PortC_CONT_DPAD2_RIGHT"
    static String ACTION_P3_ANALOG_UP = "PortC_CONT_ANALOG_UP"
    static String ACTION_P3_ANALOG_DOWN = "PortC_CONT_ANALOG_DOWN"
    static String ACTION_P3_ANALOG_LEFT = "PortC_CONT_ANALOG_LEFT"
    static String ACTION_P3_ANALOG_RIGHT = "PortC_CONT_ANALOG_RIGHT"
    static String ACTION_P3_LSLIDER = "PortC_CONT_LSLIDER"
    static String ACTION_P3_RSLIDER = "PortC_CONT_RSLIDER"

    static String ACTION_P4_A = "PortD_CONT_A"
    static String ACTION_P4_B = "PortD_CONT_B"
    static String ACTION_P4_C = "PortD_CONT_C"
    static String ACTION_P4_START = "PortD_CONT_START"
    static String ACTION_P4_DPAD_UP = "PortD_CONT_DPAD_UP"
    static String ACTION_P4_DPAD_DOWN = "PortD_CONT_DPAD_DOWN"
    static String ACTION_P4_DPAD_LEFT = "PortD_CONT_DPAD_LEFT"
    static String ACTION_P4_DPAD_RIGHT = "PortD_CONT_DPAD_RIGHT"
    static String ACTION_P4_Z = "PortD_CONT_Z"
    static String ACTION_P4_Y = "PortD_CONT_Y"
    static String ACTION_P4_X = "PortD_CONT_X"
    static String ACTION_P4_DPAD2_UP = "PortD_CONT_DPAD2_UP"
    static String ACTION_P4_DPAD2_DOWN = "PortD_CONT_DPAD2_DOWN"
    static String ACTION_P4_DPAD2_LEFT = "PortD_CONT_DPAD2_LEFT"
    static String ACTION_P4_DPAD2_RIGHT = "PortD_CONT_DPAD2_RIGHT"
    static String ACTION_P4_ANALOG_UP = "PortD_CONT_ANALOG_UP"
    static String ACTION_P4_ANALOG_DOWN = "PortD_CONT_ANALOG_DOWN"
    static String ACTION_P4_ANALOG_LEFT = "PortD_CONT_ANALOG_LEFT"
    static String ACTION_P4_ANALOG_RIGHT = "PortD_CONT_ANALOG_RIGHT"
    static String ACTION_P4_LSLIDER = "PortD_CONT_LSLIDER"
    static String ACTION_P4_RSLIDER = "PortD_CONT_RSLIDER"



    static void writeMapping(IniFile ini, Map mapping) {
        ini.putAll("ndc_hookjoy", mapping)
    }

    static Map creatKeyboardMapping() {
       [(ACTION_P1_X): KEY_Z,
        (ACTION_P1_Y): KEY_X,
        (ACTION_P1_A): KEY_C,
        (ACTION_P1_B): KEY_V,
        (ACTION_P1_LSLIDER): KEY_B,
        (ACTION_P1_RSLIDER): KEY_N,
        (ACTION_P1_Z): KEY_M,
        (ACTION_P1_C): KEY_O,
        (ACTION_P1_START): KEY_1,
        (ACTION_P1_DPAD_UP): UP,
        (ACTION_P1_DPAD_DOWN): DOWN,
        (ACTION_P1_DPAD_LEFT): RIGHT,
        (ACTION_P1_DPAD_RIGHT): LEFT,
        (ACTION_P1_DPAD2_UP): KEY_9, // BUG EN NullDC!! SE LLAMAN DE OTRA FORMA! KEY_R
        (ACTION_P1_DPAD2_DOWN): KEY_9, // BUG EN NullDC!! SE LLAMAN DE OTRA FORMA! KEY_F
        (ACTION_P1_DPAD2_LEFT): KEY_9, // BUG EN NullDC!! SE LLAMAN DE OTRA FORMA! KEY_D
        (ACTION_P1_DPAD2_RIGHT): KEY_9, // BUG EN NullDC!! SE LLAMAN DE OTRA FORMA! KEY_G
        (ACTION_P1_ANALOG_UP): KEY_I,
        (ACTION_P1_ANALOG_DOWN): KEY_K,
        (ACTION_P1_ANALOG_LEFT): KEY_J,
        (ACTION_P1_ANALOG_RIGHT): KEY_L,


        (ACTION_P2_X): KEY_A,
        (ACTION_P2_Y): KEY_S,
        (ACTION_P2_A): KEY_U,
        (ACTION_P2_B): KEY_Y,
        (ACTION_P2_LSLIDER): KEY_Q,
        (ACTION_P2_RSLIDER): KEY_W,
        (ACTION_P2_Z): KEY_E,
        (ACTION_P2_C): KEY_T,
        (ACTION_P2_START): KEY_2,
        (ACTION_P2_DPAD_UP): KEY_8_PAD,
        (ACTION_P2_DPAD_DOWN): KEY_2_PAD,
        (ACTION_P2_DPAD_LEFT): KEY_4_PAD,
        (ACTION_P2_DPAD_RIGHT): KEY_6_PAD,
        (ACTION_P2_DPAD2_UP): KEY_9,
        (ACTION_P2_DPAD2_DOWN): KEY_9,
        (ACTION_P2_DPAD2_LEFT): KEY_9,
        (ACTION_P2_DPAD2_RIGHT): KEY_9,
        (ACTION_P2_ANALOG_UP): KEY_R,
        (ACTION_P2_ANALOG_DOWN): KEY_F,
        (ACTION_P2_ANALOG_LEFT): KEY_D,
        (ACTION_P2_ANALOG_RIGHT): KEY_G,

        (ACTION_P3_X): KEY_9,
        (ACTION_P3_Y): KEY_9,
        (ACTION_P3_A): KEY_9,
        (ACTION_P3_B): KEY_9,
        (ACTION_P3_LSLIDER): KEY_9,
        (ACTION_P3_RSLIDER): KEY_9,
        (ACTION_P3_Z): KEY_9,
        (ACTION_P3_C): KEY_9,
        (ACTION_P3_START): KEY_9,
        (ACTION_P3_DPAD_UP): KEY_9,
        (ACTION_P3_DPAD_DOWN): KEY_9,
        (ACTION_P3_DPAD_LEFT): KEY_9,
        (ACTION_P3_DPAD_RIGHT): KEY_9,
        (ACTION_P3_DPAD2_UP): KEY_9,
        (ACTION_P3_DPAD2_DOWN): KEY_9,
        (ACTION_P3_DPAD2_LEFT): KEY_9,
        (ACTION_P3_DPAD2_RIGHT): KEY_9,
        (ACTION_P3_ANALOG_UP): KEY_9,
        (ACTION_P3_ANALOG_DOWN): KEY_9,
        (ACTION_P3_ANALOG_LEFT): KEY_9,
        (ACTION_P3_ANALOG_RIGHT): KEY_9,

        (ACTION_P4_X): KEY_9,
        (ACTION_P4_Y): KEY_9,
        (ACTION_P4_A): KEY_9,
        (ACTION_P4_B): KEY_9,
        (ACTION_P4_LSLIDER): KEY_9,
        (ACTION_P4_RSLIDER): KEY_9,
        (ACTION_P4_Z): KEY_9,
        (ACTION_P4_C): KEY_9,
        (ACTION_P4_START): KEY_9,
        (ACTION_P4_DPAD_UP): KEY_9,
        (ACTION_P4_DPAD_DOWN): KEY_9,
        (ACTION_P4_DPAD_LEFT): KEY_9,
        (ACTION_P4_DPAD_RIGHT): KEY_9,
        (ACTION_P4_DPAD2_UP): KEY_9,
        (ACTION_P4_DPAD2_DOWN): KEY_9,
        (ACTION_P4_DPAD2_LEFT): KEY_9,
        (ACTION_P4_DPAD2_RIGHT): KEY_9,
        (ACTION_P4_ANALOG_UP): KEY_9,
        (ACTION_P4_ANALOG_DOWN): KEY_9,
        (ACTION_P4_ANALOG_LEFT): KEY_9,
        (ACTION_P4_ANALOG_RIGHT): KEY_9,
       ]
    }

    static List<File> setKeyboard(File nullDc) {
        set360(nullDc)

        IniFile ini = new IniFile().parse(new File(nullDc, "nullDC.cfg"))
        println "- nullDC Configuring keyboard for player 1 and 2"
        ini.put("nullDC_plugins", "Current_maple0_5", KEYBOARD)
        ini.put("nullDC_plugins", "Current_maple1_5", KEYBOARD)
        ini.store()
        return [ini.file]
    }

    static List<File> set360(File nullDc) {
        IniFile ini = new IniFile().parse(new File(nullDc, "nullDC.cfg"))
        println "- nullDC Configuring Xbox 360 for players 1,2,3 & 4"
        writeMapping(ini, creatKeyboardMapping())
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

}
