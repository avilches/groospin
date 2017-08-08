package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 11-Jul-17.
 */
class HyperSpinMapping {
    static String UP = "38"
    static String DOWN = "40"
    static String RIGHT = "37"
    static String LEFT = "39"

    static String INSERT = "45"
    static String DEL = "46"
    static String HOME = "36"
    static String END = "35"
    static String PAGEUP = "33"
    static String PAGEDOWN = "34"

    static String ESC = "27"
    static String CAPSLOCK = "20"
    static String SHIFT = "16"
    static String CONTROL = "17"
    static String RETURN = "13"
    static String SPACE = "32"
    static String BACKSPACE = "8"

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

    static String F1 = "112"
    static String F2 = "113"
    static String F3 = "114"
    static String F4 = "115"
    static String F5 = "116"
    static String F6 = "117"
    static String F7 = "118"
    static String F8 = "119"
    static String F9 = "120"

    static String ACTION_P1_START = "P1 Controls:Start"
    static String ACTION_P1_EXIT = "P1 Controls:Exit"
    static String ACTION_P1_UP = "P1 Controls:Up"
    static String ACTION_P1_DOWN = "P1 Controls:Down"
    static String ACTION_P1_SKIPUP = "P1 Controls:SkipUp"
    static String ACTION_P1_SKIPDOWN = "P1 Controls:SkipDown"
    static String ACTION_P1_SKIPUPNUMBER = "P1 Controls:SkipUpNumber"
    static String ACTION_P1_SKIPDOWNNUMBER = "P1 Controls:SkipDownNumber"
    static String ACTION_P1_HYPERSPIN = "P1 Controls:HyperSpin"
    static String ACTION_P1_GENRE = "P1 Controls:Genre"
    static String ACTION_P1_FAVORITES = "P1 Controls:Favorites"
    static String ACTION_P2_START = "P2 Controls:Start"
    static String ACTION_P2_EXIT = "P2 Controls:Exit"
    static String ACTION_P2_UP = "P2 Controls:Up"
    static String ACTION_P2_DOWN = "P2 Controls:Down"
    static String ACTION_P2_SKIPUP = "P2 Controls:SkipUp"
    static String ACTION_P2_SKIPDOWN = "P2 Controls:SkipDown"
    static String ACTION_P2_SKIPUPNUMBER = "P2 Controls:SkipUpNumber"
    static String ACTION_P2_SKIPDOWNNUMBER = "P2 Controls:SkipDownNumber"
    static String ACTION_P2_HYPERSPIN = "P2 Controls:HyperSpin"
    static String ACTION_P2_GENRE = "P2 Controls:Genre"
    static String ACTION_P2_FAVORITES = "P2 Controls:Favorites"

    static Map allKeys = [
            (ACTION_P1_START): RETURN,
            (ACTION_P1_EXIT): ESC,
            (ACTION_P1_UP): UP,
            (ACTION_P1_DOWN): DOWN,
            (ACTION_P1_SKIPUP): RIGHT,
            (ACTION_P1_SKIPDOWN): LEFT,
            (ACTION_P1_SKIPUPNUMBER): PAGEUP,
            (ACTION_P1_SKIPDOWNNUMBER): PAGEDOWN,
            (ACTION_P1_HYPERSPIN): KEY_H,
            (ACTION_P1_GENRE): KEY_G,
            (ACTION_P1_FAVORITES): KEY_F,
            (ACTION_P2_START): SPACE,
            (ACTION_P2_EXIT): BACKSPACE,
            (ACTION_P2_UP): KEY_W,
            (ACTION_P2_DOWN): KEY_S,
            (ACTION_P2_SKIPUP): KEY_A,
            (ACTION_P2_SKIPDOWN): KEY_D,
            (ACTION_P2_SKIPUPNUMBER): KEY_J,
            (ACTION_P2_SKIPDOWNNUMBER): KEY_M,
            (ACTION_P2_HYPERSPIN): KEY_H,
            (ACTION_P2_GENRE): KEY_G,
            (ACTION_P2_FAVORITES): KEY_F,
    ]

    static Map createDefaultMapping() {
        return [:] + allKeys
    }

    static List<File> writeMapping(HyperSpin hs, Map map) {
        println "HyperSpin: setting config"
        File f = new File(hs.hsRoot, "Settings\\Settings.ini")
        writeMapping(f, map)
        return [f]
    }

    static void writeMapping(File f, Map map) {
        Set missingKeys = map.entrySet().findAll { it.value == null }
        if (missingKeys) {
            throw new RuntimeException("HyperSpin mapping error. Actions with no mapping ${missingKeys.join(", ")}")
        }
        IniFile cfg = new IniFile().parse(f)

        cfg.put("P1 Joystick", "Enabled", "false")
        cfg.put("P2 Joystick", "Enabled", "false")
        cfg.put("Keyboard", "Key_Delay", "true")

        map.each { k,v->
            String section = k.split(":")[0]
            String key = k.split(":")[1]
            cfg.put(section, key, v)
        }
        cfg.store()


    }
}

