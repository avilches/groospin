package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 11-Jul-17.
 */
class WinViceMapping {

     static String KEY_W = "17"
     static String KEY_A = "32"
     static String KEY_S = "31"
     static String KEY_D = "30"
     static String KEY_Q = "16"
     static String KEY_I = "23"
     static String KEY_L = "38"
     static String KEY_K = "37"
     static String KEY_J = "36"
     static String KEY_U = "22"


    static String ACTION_P1_UP = "KeySet1North"
    static String ACTION_P2_UP = "KeySet2North"
    static String ACTION_P1_RIGHT = "KeySet1East"
    static String ACTION_P2_RIGHT = "KeySet2East"
    static String ACTION_P1_DOWN = "KeySet1South"
    static String ACTION_P2_DOWN = "KeySet2South"
    static String ACTION_P1_FIRE = "KeySet1Fire"
    static String ACTION_P2_FIRE = "KeySet2Fire"
    static String ACTION_P2_LEFT = "KeySet2West"
    static String ACTION_P1_LEFT = "KeySet1West"

    static Map allKeys = [
            (ACTION_P1_UP): KEY_W,
            (ACTION_P1_RIGHT): KEY_A,
            (ACTION_P1_DOWN): KEY_S,
            (ACTION_P1_LEFT): KEY_D,
            (ACTION_P1_FIRE): KEY_Q,
            (ACTION_P2_UP): KEY_I,
            (ACTION_P2_RIGHT): KEY_L,
            (ACTION_P2_DOWN): KEY_K,
            (ACTION_P2_LEFT): KEY_J,
            (ACTION_P2_FIRE): KEY_U]

    static Map createDefaultMapping() {
        return [:] + allKeys
    }

    static void writeMapping(HyperSpin hs, Map map) {
        Set missingKeys = map.entrySet().findAll { it.value == null }
        if (missingKeys) {
            throw new RuntimeException("WinVice mapping error. Keys with no mapping ${missingKeys.join(", ")}")
        }

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

            cfg.put(machine, "KeySetEnable", "1")
            cfg.put(machine, "JoyDevice1", JOY_DEVICE_KEYSET_A)
            cfg.put(machine, "JoyDevice2", JOY_DEVICE_KEYSET_B)

            map.each { String k, String v->
                cfg.put(machine, k, v)
            }
            cfg.store()


            cfg.store()
        }
    }

}
