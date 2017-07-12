package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 11-Jul-17.
 */
class NeoRaineMapping {

    static String NONE = "0"

    /*
    Para mapear: hay que tener un juego cargado, hacerlo con RocketLauncherUI.
    TAB -> INPUT
    Apagar RocketLauncher para que al pulsar ESC al volver al menu anterior no se apague el juego
    Salir con la opcion QUIT (ALT+F4 no salva!!!)

     */

    static String ACTION_P1_COIN = "default_game_key_config:Def_Coin_A"
    static String ACTION_P1_COIN_JOYSTICK = "default_game_key_config:Def_Coin_A_joystick"
    static String ACTION_P2_COIN = "default_game_key_config:Def_Coin_B"
    static String ACTION_P2_COIN_JOYSTICK = "default_game_key_config:Def_Coin_B_joystick"
    static String ACTION_P3_COIN = "default_game_key_config:Def_Coin_C"
    static String ACTION_P3_COIN_JOYSTICK = "default_game_key_config:Def_Coin_C_joystick"
    static String ACTION_P4_COIN = "default_game_key_config:Def_Coin_D"
    static String ACTION_P4_COIN_JOYSTICK = "default_game_key_config:Def_Coin_D_joystick"
    static String ACTION_TILT = "default_game_key_config:Def_Tilt"
    static String ACTION_SERVICE = "default_game_key_config:Def_Service"
    static String ACTION_TEST = "default_game_key_config:Def_Test"
    static String ACTION_P1_START = "default_game_key_config:Def_P1_Start"
    static String ACTION_P1_START_JOYSTICK = "default_game_key_config:Def_P1_Start_joystick"
    static String ACTION_P1_UP = "default_game_key_config:Def_P1_Up"
    static String ACTION_P1_UP_JOYSTICK = "default_game_key_config:Def_P1_Up_joystick"
    static String ACTION_P1_DOWN = "default_game_key_config:Def_P1_Down"
    static String ACTION_P1_DOWN_JOYSTICK = "default_game_key_config:Def_P1_Down_joystick"
    static String ACTION_P1_LEFT = "default_game_key_config:Def_P1_Left"
    static String ACTION_P1_LEFT_JOYSTICK = "default_game_key_config:Def_P1_Left_joystick"
    static String ACTION_P1_RIGHT = "default_game_key_config:Def_P1_Right"
    static String ACTION_P1_RIGHT_JOYSTICK = "default_game_key_config:Def_P1_Right_joystick"
    static String ACTION_P1_BUTTON_1 = "default_game_key_config:Def_P1_Button_1"
    static String ACTION_P1_BUTTON_1_JOYSTICK = "default_game_key_config:Def_P1_Button_1_joystick"
    static String ACTION_P1_BUTTON_1_MOUSE = "default_game_key_config:Def_P1_Button_1_mouse"
    static String ACTION_P1_BUTTON_2 = "default_game_key_config:Def_P1_Button_2"
    static String ACTION_P1_BUTTON_2_JOYSTICK = "default_game_key_config:Def_P1_Button_2_joystick"
    static String ACTION_P1_BUTTON_2_MOUSE = "default_game_key_config:Def_P1_Button_2_mouse"
    static String ACTION_P1_BUTTON_3 = "default_game_key_config:Def_P1_Button_3"
    static String ACTION_P1_BUTTON_3_JOYSTICK = "default_game_key_config:Def_P1_Button_3_joystick"
    static String ACTION_P1_BUTTON_3_MOUSE = "default_game_key_config:Def_P1_Button_3_mouse"
    static String ACTION_P1_BUTTON_4 = "default_game_key_config:Def_P1_Button_4"
    static String ACTION_P1_BUTTON_4_JOYSTICK = "default_game_key_config:Def_P1_Button_4_joystick"
    static String ACTION_P1_BUTTON_5 = "default_game_key_config:Def_P1_Button_5"
    static String ACTION_P1_BUTTON_5_JOYSTICK = "default_game_key_config:Def_P1_Button_5_joystick"
    static String ACTION_P1_BUTTON_6 = "default_game_key_config:Def_P1_Button_6"
    static String ACTION_P1_BUTTON_6_JOYSTICK = "default_game_key_config:Def_P1_Button_6_joystick"
    static String ACTION_P1_BUTTON_7 = "default_game_key_config:Def_P1_Button_7"
    static String ACTION_P1_BUTTON_7_JOYSTICK = "default_game_key_config:Def_P1_Button_7_joystick"
    static String ACTION_P1_BUTTON_8 = "default_game_key_config:Def_P1_Button_8"
    static String ACTION_P1_BUTTON_8_JOYSTICK = "default_game_key_config:Def_P1_Button_8_joystick"
    static String ACTION_P2_START = "default_game_key_config:Def_P2_Start"
    static String ACTION_P2_START_JOYSTICK = "default_game_key_config:Def_P2_Start_joystick"
    static String ACTION_P2_UP = "default_game_key_config:Def_P2_Up"
    static String ACTION_P2_UP_JOYSTICK = "default_game_key_config:Def_P2_Up_joystick"
    static String ACTION_P2_DOWN = "default_game_key_config:Def_P2_Down"
    static String ACTION_P2_DOWN_JOYSTICK = "default_game_key_config:Def_P2_Down_joystick"
    static String ACTION_P2_LEFT = "default_game_key_config:Def_P2_Left"
    static String ACTION_P2_LEFT_JOYSTICK = "default_game_key_config:Def_P2_Left_joystick"
    static String ACTION_P2_RIGHT = "default_game_key_config:Def_P2_Right"
    static String ACTION_P2_RIGHT_JOYSTICK = "default_game_key_config:Def_P2_Right_joystick"
    static String ACTION_P2_BUTTON_1 = "default_game_key_config:Def_P2_Button_1"
    static String ACTION_P2_BUTTON_1_JOYSTICK = "default_game_key_config:Def_P2_Button_1_joystick"
    static String ACTION_P2_BUTTON_2 = "default_game_key_config:Def_P2_Button_2"
    static String ACTION_P2_BUTTON_2_JOYSTICK = "default_game_key_config:Def_P2_Button_2_joystick"
    static String ACTION_P2_BUTTON_3 = "default_game_key_config:Def_P2_Button_3"
    static String ACTION_P2_BUTTON_3_JOYSTICK = "default_game_key_config:Def_P2_Button_3_joystick"
    static String ACTION_P2_BUTTON_4 = "default_game_key_config:Def_P2_Button_4"
    static String ACTION_P2_BUTTON_4_JOYSTICK = "default_game_key_config:Def_P2_Button_4_joystick"
    static String ACTION_P2_BUTTON_5 = "default_game_key_config:Def_P2_Button_5"
    static String ACTION_P2_BUTTON_5_JOYSTICK = "default_game_key_config:Def_P2_Button_5_joystick"
    static String ACTION_P2_BUTTON_6 = "default_game_key_config:Def_P2_Button_6"
    static String ACTION_P2_BUTTON_6_JOYSTICK = "default_game_key_config:Def_P2_Button_6_joystick"
    static String ACTION_P2_BUTTON_7 = "default_game_key_config:Def_P2_Button_7"
    static String ACTION_P2_BUTTON_7_JOYSTICK = "default_game_key_config:Def_P2_Button_7_joystick"
    static String ACTION_P2_BUTTON_8 = "default_game_key_config:Def_P2_Button_8"
    static String ACTION_P2_BUTTON_8_JOYSTICK = "default_game_key_config:Def_P2_Button_8_joystick"

    static String KEY_1_PAD = "257"
    static String KEY_2_PAD = "258"
    static String KEY_3_PAD = "259"
    static String KEY_4_PAD = "260"
    static String KEY_5_PAD = "261"
    static String KEY_6_PAD = "262"
    static String KEY_7_PAD = "263"
    static String KEY_8_PAD = "264"
    static String KEY_9_PAD = "265"
    static String KEY_1 = "49"
    static String KEY_2 = "50"
    static String KEY_3 = "51"
    static String KEY_4 = "52"
    static String KEY_5 = "53"
    static String KEY_6 = "54"
    static String KEY_7 = "55"
    static String KEY_8 = "56"
    static String KEY_9 = "57"
    static String UP = "273"
    static String DOWN = "274"
    static String LEFT = "276"
    static String RIGHT = "275"
    static String KEY_A = "97"
    static String KEY_B = "98"
    static String KEY_C = "99"
    static String KEY_D = "100"
    static String KEY_E = "101"
    static String KEY_F = "102"
    static String KEY_G = "103"
    static String KEY_H = "104"
    static String KEY_I = "105"
    static String KEY_J = "106"
    static String KEY_K = "107"
    static String KEY_L = "108"
    static String KEY_M = "109"
    static String KEY_N = "110"
    static String KEY_O = "111"
    static String KEY_P = "112"
    static String KEY_Q = "113"
    static String KEY_R = "114"
    static String KEY_S = "115"
    static String KEY_T = "116"
    static String KEY_U = "117"
    static String KEY_V = "118"
    static String KEY_W = "119"
    static String KEY_X = "120"
    static String KEY_Y = "121"
    static String KEY_Z = "122"


    static Map allKeys = [
            (ACTION_TILT)                : KEY_T,
            (ACTION_SERVICE)             : KEY_Y,
            (ACTION_TEST)                : KEY_U,
            (ACTION_P1_COIN)             : KEY_5,
            (ACTION_P2_COIN)             : KEY_6,
            (ACTION_P3_COIN)             : KEY_7,
            (ACTION_P4_COIN)             : KEY_8,
            (ACTION_P1_START)            : KEY_1,
            (ACTION_P2_START)            : KEY_2,
            (ACTION_P1_UP)               : UP,
            (ACTION_P1_DOWN)             : DOWN,
            (ACTION_P1_LEFT)             : LEFT,
            (ACTION_P1_RIGHT)            : RIGHT,
            (ACTION_P1_BUTTON_1_MOUSE)   : "1",
            (ACTION_P1_BUTTON_2_MOUSE)   : "3",
            (ACTION_P1_BUTTON_3_MOUSE)   : "2",
            (ACTION_P1_BUTTON_1)         : KEY_Z,
            (ACTION_P1_BUTTON_2)         : KEY_X,
            (ACTION_P1_BUTTON_3)         : KEY_C,
            (ACTION_P1_BUTTON_4)         : KEY_V,
            (ACTION_P1_BUTTON_5)         : KEY_B,
            (ACTION_P1_BUTTON_6)         : KEY_N,
            (ACTION_P1_BUTTON_7)         : KEY_M,
            (ACTION_P1_BUTTON_8)         : KEY_L,
            (ACTION_P1_START_JOYSTICK)   : NONE,
            (ACTION_P1_UP_JOYSTICK)      : NONE,
            (ACTION_P1_DOWN_JOYSTICK)    : NONE,
            (ACTION_P1_LEFT_JOYSTICK)    : NONE,
            (ACTION_P1_RIGHT_JOYSTICK)   : NONE,
            (ACTION_P1_BUTTON_1_JOYSTICK): NONE,
            (ACTION_P1_BUTTON_2_JOYSTICK): NONE,
            (ACTION_P1_BUTTON_3_JOYSTICK): NONE,
            (ACTION_P1_BUTTON_4_JOYSTICK): NONE,
            (ACTION_P1_BUTTON_5_JOYSTICK): NONE,
            (ACTION_P1_BUTTON_6_JOYSTICK): NONE,
            (ACTION_P1_BUTTON_7_JOYSTICK): NONE,
            (ACTION_P1_BUTTON_8_JOYSTICK): NONE,
            (ACTION_P2_UP)               : KEY_8_PAD,
            (ACTION_P2_DOWN)             : KEY_2_PAD,
            (ACTION_P2_LEFT)             : KEY_4_PAD,
            (ACTION_P2_RIGHT)            : KEY_6_PAD,
            (ACTION_P2_BUTTON_1)         : KEY_A,
            (ACTION_P2_BUTTON_2)         : KEY_S,
            (ACTION_P2_BUTTON_3)         : KEY_D,
            (ACTION_P2_BUTTON_4)         : KEY_F,
            (ACTION_P2_BUTTON_5)         : KEY_G,
            (ACTION_P2_BUTTON_6)         : KEY_H,
            (ACTION_P2_BUTTON_7)         : KEY_J,
            (ACTION_P2_BUTTON_8)         : KEY_K,
            (ACTION_P2_START_JOYSTICK)   : NONE,
            (ACTION_P2_UP_JOYSTICK)      : NONE,
            (ACTION_P2_DOWN_JOYSTICK)    : NONE,
            (ACTION_P2_LEFT_JOYSTICK)    : NONE,
            (ACTION_P2_RIGHT_JOYSTICK)   : NONE,
            (ACTION_P2_BUTTON_1_JOYSTICK): NONE,
            (ACTION_P2_BUTTON_2_JOYSTICK): NONE,
            (ACTION_P2_BUTTON_3_JOYSTICK): NONE,
            (ACTION_P2_BUTTON_4_JOYSTICK): NONE,
            (ACTION_P2_BUTTON_5_JOYSTICK): NONE,
            (ACTION_P2_BUTTON_6_JOYSTICK): NONE,
            (ACTION_P2_BUTTON_7_JOYSTICK): NONE,
            (ACTION_P2_BUTTON_8_JOYSTICK): NONE,
            (ACTION_P1_COIN_JOYSTICK)    : NONE,
            (ACTION_P2_COIN_JOYSTICK)    : NONE,
            (ACTION_P3_COIN_JOYSTICK)    : NONE,
            (ACTION_P4_COIN_JOYSTICK)    : NONE,

    ]

    static Map createDefaultMapping() {
        return [:] + allKeys
    }

    static void writeMapping(HyperSpin hs, Map map) {
        println "- NeoRaine: setting config"
        File f = new File(hs.neoRaineFolder, "config\\raine32_sdl.cfg")
        println f.absolutePath
        writeMapping(f, map)
    }

    static void writeMapping(File f, Map map) {
        Set missingKeys = map.entrySet().findAll { it.value == null }
        if (missingKeys) {
            throw new RuntimeException("NeoRaine mapping error. Actions with no mapping ${missingKeys.join(", ")}")
        }
        IniFile cfg = new IniFile().parse(f)

        map.each { k, v ->
            String section = k.split(":")[0]
            String key = k.split(":")[1]
            cfg.put(section, key, v)
        }
        cfg.store()


    }

}
