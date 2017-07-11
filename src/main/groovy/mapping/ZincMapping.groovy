package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 11-Jul-17.
 */
class ZincMapping {

    static String EMPTY           = "k0"

    static String J1_BUTTON1 = "j1b1"
    static String J1_BUTTON2 = "j1b2"
    static String J1_BUTTON3 = "j1b3"
    static String J1_BUTTON4 = "j1b4"
    static String J1_BUTTON5 = "j1b5"
    static String J1_BUTTON6 = "j1b6"
    static String J1_BUTTON7 = "j1b7"
    static String J1_BUTTON8 = "j1b8"

    static String J2_BUTTON1 = "j2b1"
    static String J2_BUTTON2 = "j2b2"
    static String J2_BUTTON3 = "j2b3"
    static String J2_BUTTON4 = "j2b4"
    static String J2_BUTTON5 = "j2b5"
    static String J2_BUTTON6 = "j2b6"
    static String J2_BUTTON7 = "j2b7"
    static String J2_BUTTON8 = "j2b8"

    static String J3_BUTTON1 = "j3b1"
    static String J3_BUTTON2 = "j3b2"
    static String J3_BUTTON3 = "j3b3"
    static String J3_BUTTON4 = "j3b4"
    static String J3_BUTTON5 = "j3b5"
    static String J3_BUTTON6 = "j3b6"
    static String J3_BUTTON7 = "j3b7"
    static String J3_BUTTON8 = "j3b8"

    static String J4_BUTTON1 = "j4b1"
    static String J4_BUTTON2 = "j4b2"
    static String J4_BUTTON3 = "j4b3"
    static String J4_BUTTON4 = "j4b4"
    static String J4_BUTTON5 = "j4b5"
    static String J4_BUTTON6 = "j4b6"
    static String J4_BUTTON7 = "j4b7"
    static String J4_BUTTON8 = "j4b8"


    static String ESCAPE          = "k01"
    static String KEY_1           = "k02"
    static String KEY_2           = "k03"
    static String KEY_3           = "k04"
    static String KEY_4           = "k05"
    static String KEY_5           = "k06"
    static String KEY_6           = "k07"
    static String KEY_7           = "k08"
    static String KEY_8           = "k09"
    static String KEY_9           = "k0A"
    static String KEY_0           = "k0B"
    static String MINUS           = "k0C"    /* - on main keyboard */
    static String EQUALS          = "k0D"
    static String BACKSPACE       = "k0E"    /* backspace */
    static String TAB             = "k0F"
    static String KEY_Q           = "k10"
    static String KEY_W           = "k11"
    static String KEY_E           = "k12"
    static String KEY_R           = "k13"
    static String KEY_T           = "k14"
    static String KEY_Y           = "k15"
    static String KEY_U           = "k16"
    static String KEY_I           = "k17"
    static String KEY_O           = "k18"
    static String KEY_P           = "k19"
    static String LBRACKET        = "k1A"
    static String RBRACKET        = "k1B"
    static String RETURN          = "k1C"    /* Enter on main keyboard */
    static String LCONTROL        = "k1D"
    static String KEY_A           = "k1E"
    static String KEY_S           = "k1F"
    static String KEY_D           = "k20"
    static String KEY_F           = "k21"
    static String KEY_G           = "k22"
    static String KEY_H           = "k23"
    static String KEY_J           = "k24"
    static String KEY_K           = "k25"
    static String KEY_L           = "k26"
    static String SEMICOLON       = "k27"
    static String APOSTROPHE      = "k28"
    static String GRAVE           = "k29"    /* accent grave */
    static String LSHIFT          = "k2A"
    static String BACKSLASH       = "k2B"
    static String KEY_Z           = "k2C"
    static String KEY_X           = "k2D"
    static String KEY_C           = "k2E"
    static String KEY_V           = "k2F"
    static String KEY_B           = "k30"
    static String KEY_N           = "k31"
    static String KEY_M           = "k32"
    static String COMMA           = "k33"
    static String PERIOD          = "k34"    /* . on main keyboard */
    static String SLASH           = "k35"    /* / on main keyboard */
    static String RSHIFT          = "k36"
    static String MULTIPLY        = "k37"    /* * on numeric keypad */
    static String LALT            = "k38"    /* left Alt */
    static String SPACE           = "k39"
    static String CAPITAL         = "k3A"
    static String F1              = "k3B"
    static String F2              = "k3C"
    static String F3              = "k3D"
    static String F4              = "k3E"
    static String F5              = "k3F"
    static String F6              = "k40"
    static String F7              = "k41"
    static String F8              = "k42"
    static String F9              = "k43"
    static String F10             = "k44"
    static String NUMLOCK         = "k45"
    static String SCROLL          = "k46"    /* Scroll Lock */
    static String KEY_7_PAD       = "k47"
    static String KEY_8_PAD       = "k48"
    static String KEY_9_PAD       = "k49"
    static String SUBTRACT        = "k4A"    /* - on numeric keypad */
    static String KEY_4_PAD       = "k4B"
    static String KEY_5_PAD       = "k4C"
    static String KEY_6_PAD       = "k4D"
    static String ADD             = "k4E"    /* + on numeric keypad */
    static String KEY_1_PAD       = "k4F"
    static String KEY_2_PAD       = "k50"
    static String KEY_3_PAD       = "k51"
    static String KEY_0_PAD       = "k52"
    static String DECIMAL         = "k53"    /* . on numeric keypad */
    static String OEM_102         = "k56"    /* <> or \| on RT 102-key keyboard (Non-U.S.) */
    static String F11             = "k57"
    static String F12             = "k58"
    static String F13             = "k64"    /*                     (NEC PC98) */
    static String F14             = "k65"    /*                     (NEC PC98) */
    static String F15             = "k66"    /*                     (NEC PC98) */
    static String KANA            = "k70"    /* (Japanese keyboard)            */
    static String ABNT_C1         = "k73"    /* /? on Brazilian keyboard */
    static String CONVERT         = "k79"    /* (Japanese keyboard)            */
    static String NOCONVERT       = "k7B"    /* (Japanese keyboard)            */
    static String YEN             = "k7D"    /* (Japanese keyboard)            */
    static String ABNT_C2         = "k7E"    /* Numpad . on Brazilian keyboard */
    static String NUMPADEQUALS    = "k8D"    /* = on numeric keypad (NEC PC98) */
    static String PREVTRACK       = "k90"    /* Previous Track (DIK_CIRCUMFLEX on Japanese keyboard) */
    static String AT              = "k91"    /*                     (NEC PC98) */
    static String COLON           = "k92"    /*                     (NEC PC98) */
    static String UNDERLINE       = "k93"    /*                     (NEC PC98) */
    static String KANJI           = "k94"    /* (Japanese keyboard)            */
    static String STOP            = "k95"    /*                     (NEC PC98) */
    static String AX              = "k96"    /*                     (Japan AX) */
    static String UNLABELED       = "k97"    /*                        (J3100) */
    static String NEXTTRACK       = "k99"    /* Next Track */
    static String NUMPADENTER     = "k9C"    /* Enter on numeric keypad */
    static String RCONTROL        = "k9D"
    static String MUTE            = "kA0"    /* Mute */
    static String CALCULATOR      = "kA1"    /* Calculator */
    static String PLAYPAUSE       = "kA2"    /* Play / Pause */
    static String MEDIASTOP       = "kA4"    /* Media Stop */
    static String VOLUMEDOWN      = "kAE"    /* Volume - */
    static String VOLUMEUP        = "kB0"    /* Volume + */
    static String WEBHOME         = "kB2"    /* Web home */
    static String NUMPADCOMMA     = "kB3"    /* , on numeric keypad (NEC PC98) */
    static String DIVIDE          = "kB5"    /* / on numeric keypad */
    static String SYSRQ           = "kB7"
    static String RMENU           = "kB8"    /* right Alt */
    static String PAUSE           = "kC5"    /* Pause */
    static String HOME            = "kC7"    /* Home on arrow keypad */
    static String UP              = "kC8"    /* UpArrow on arrow keypad */
    static String PAGEUP          = "kC9"    /* PgUp on arrow keypad */
    static String LEFT            = "kCB"    /* LeftArrow on arrow keypad */
    static String RIGHT           = "kCD"    /* RightArrow on arrow keypad */
    static String END             = "kCF"    /* End on arrow keypad */
    static String DOWN            = "kD0"    /* DownArrow on arrow keypad */
    static String PAGEDOWN        = "kD1"    /* PgDn on arrow keypad */
    static String INSERT          = "kD2"    /* Insert on arrow keypad */
    static String DELETE          = "kD3"    /* Delete on arrow keypad */
    static String LWIN            = "kDB"    /* Left Windows key */
    static String RWIN            = "kDC"    /* Right Windows key */
    static String APPS            = "kDD"    /* AppMenu key */
    static String POWER           = "kDE"    /* System Power */
    static String SLEEP           = "kDF"    /* System Sleep */
    static String WAKE            = "kE3"    /* System Wake */
    static String WEBSEARCH       = "kE5"    /* Web Search */
    static String WEBFAVORITES    = "kE6"    /* Web Favorites */
    static String WEBREFRESH      = "kE7"    /* Web Refresh */
    static String WEBSTOP         = "kE8"    /* Web Stop */
    static String WEBFORWARD      = "kE9"    /* Web Forward */
    static String WEBBACK         = "kEA"    /* Web Back */
    static String MYCOMPUTER      = "kEB"    /* My Computer */
    static String MAIL            = "kEC"    /* Mail */
    static String MEDIASELECT     = "kED"    /* Media Select */

    static ACTION_P1_COIN = "player1:coin"
    static ACTION_P1_START = "player1:start"
    static ACTION_P1_RIGHT = "player1:right"
    static ACTION_P1_LEFT = "player1:left"
    static ACTION_P1_DOWN = "player1:down"
    static ACTION_P1_UP = "player1:up"
    static ACTION_P1_BTN1 = "player1:btn1"
    static ACTION_P1_BTN2 = "player1:btn2"
    static ACTION_P1_BTN3 = "player1:btn3"
    static ACTION_P1_BTN4 = "player1:btn4"
    static ACTION_P1_BTN5 = "player1:btn5"
    static ACTION_P1_BTN6 = "player1:btn6"
    static ACTION_P1_BTN7 = "player1:btn7"
    static ACTION_P1_BTN8 = "player1:btn8"
    static ACTION_P1_BTN9 = "player1:btn9"
    static ACTION_P1_BTN10 = "player1:btn10"
    static ACTION_P1_BTN11 = "player1:btn11"
    static ACTION_P1_BTN12 = "player1:btn12"
    static ACTION_P1_BTN13 = "player1:btn13"

    static ACTION_P2_COIN = "player2:coin"
    static ACTION_P2_START = "player2:start"
    static ACTION_P2_RIGHT = "player2:right"
    static ACTION_P2_LEFT = "player2:left"
    static ACTION_P2_DOWN = "player2:down"
    static ACTION_P2_UP = "player2:up"
    static ACTION_P2_BTN1 = "player2:btn1"
    static ACTION_P2_BTN2 = "player2:btn2"
    static ACTION_P2_BTN3 = "player2:btn3"
    static ACTION_P2_BTN4 = "player2:btn4"
    static ACTION_P2_BTN5 = "player2:btn5"
    static ACTION_P2_BTN6 = "player2:btn6"
    static ACTION_P2_BTN7 = "player2:btn7"
    static ACTION_P2_BTN8 = "player2:btn8"
    static ACTION_P2_BTN9 = "player2:btn9"
    static ACTION_P2_BTN10 = "player2:btn10"
    static ACTION_P2_BTN11 = "player2:btn11"
    static ACTION_P2_BTN12 = "player2:btn12"
    static ACTION_P2_BTN13 = "player2:btn13"




    static Map allKeys = [
            (ACTION_P1_COIN): KEY_5,
            (ACTION_P1_START): KEY_1,

            (ACTION_P1_RIGHT): RIGHT,
            (ACTION_P1_LEFT): LEFT,
            (ACTION_P1_DOWN): DOWN,
            (ACTION_P1_UP): UP,

            (ACTION_P1_BTN1): KEY_Z,
            (ACTION_P1_BTN2): KEY_X,
            (ACTION_P1_BTN3): KEY_C,
            (ACTION_P1_BTN4): KEY_V,
            (ACTION_P1_BTN5): KEY_B,
            (ACTION_P1_BTN6): KEY_N,

            (ACTION_P1_BTN9): KEY_Z,  // teken 2 soul edge
            (ACTION_P1_BTN10): KEY_X, // teken 2 soul edge
            (ACTION_P1_BTN11): KEY_C, //         soul edge
            (ACTION_P1_BTN12): KEY_V, // teken 2 soul edge
            (ACTION_P1_BTN13): KEY_C, // teken 2

            (ACTION_P2_COIN): KEY_6,
            (ACTION_P2_START): KEY_2,

            (ACTION_P2_RIGHT): KEY_6_PAD,
            (ACTION_P2_LEFT): KEY_4_PAD,
            (ACTION_P2_DOWN): KEY_2_PAD,
            (ACTION_P2_UP): KEY_8_PAD,

            (ACTION_P2_BTN1): KEY_A,
            (ACTION_P2_BTN2): KEY_S,
            (ACTION_P2_BTN3): KEY_D,
            (ACTION_P2_BTN4): KEY_F,
            (ACTION_P2_BTN5): KEY_G,
            (ACTION_P2_BTN6): KEY_H,

            (ACTION_P2_BTN9): KEY_A,  // teken 2 soul edge
            (ACTION_P2_BTN10): KEY_S, // teken 2 soul edge
            (ACTION_P2_BTN11): KEY_D, //         soul edge
            (ACTION_P2_BTN12): KEY_F, // teken 2 soul edge
            (ACTION_P2_BTN13): KEY_D, // teken 2


    ]

    static Map createDefaultMapping() {
        return [:] + allKeys
    }

    static void writeMapping(HyperSpin hs, Map map) {
        println "- Zinc: setting config"
        File f = new File(hs.zincFolder, "controller.cfg")
        println f.absolutePath
        writeMapping(f, map)
    }

    static void writeMapping(File f, Map map) {
        Set missingKeys = map.entrySet().findAll { it.value == null }
        if (missingKeys) {
            throw new RuntimeException("Zinc mapping error. Actions with no mapping ${missingKeys.join(", ")}")
        }
        IniFile cfg = new IniFile().parse(f)

        map.each { k,v->
            String section = k.split(":")[0]
            String key = k.split(":")[1]
            cfg.put(section, key, v)
        }
        cfg.store()


    }

}
