package org.hs5tb.groospin.base

import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 17-Jun-17.
 */
class J2K {
    HyperSpin hs
    String systemName
    String emulator
    IniFile cfg
    Preset presets = new Preset()

    J2K(HyperSpin hs, String systemName, String emulator = null) {
        this.hs = hs
        this.systemName = systemName
        this.emulator = emulator
        File cfgFile
        if (emulator) {
            cfgFile = hs.newRocketLauncherFile("Profiles/JoyToKey/${systemName}/${emulator}/${emulator}.cfg")
        } else {
            cfgFile = hs.newRocketLauncherFile("Profiles/JoyToKey/${systemName}/${systemName}.cfg")
        }
        if (!cfgFile.exists()) {
            cfg = new IniFile(file: cfgFile)
            empty()
        } else {
            cfg = new IniFile().parse(cfgFile)
        }
    }

    class Preset {
        static int XBOX360_A = 1
        static int XBOX360_B = 2
        static int XBOX360_X = 3
        static int XBOX360_Y = 4
        static int XBOX360_LB = 5
        static int XBOX360_LT_ANALOG = 11 // ANALOG
        static int XBOX360_RB = 6
        static int XBOX360_BACK = 7
        static int XBOX360_SELECT = 7
        static int XBOX360_START = 8
        static int XBOX360_RT_ANALOG = 12 // ANALOG

        static int MAPPING_XBOX360_A = 56 // Button1
        static int MAPPING_XBOX360_B = 57 // Button2
        static int MAPPING_XBOX360_X = 58 // Button3
        static int MAPPING_XBOX360_Y = 59 // Button4
        static int MAPPING_XBOX360_LB = 60 // Button5
        static int MAPPING_XBOX360_RB = 61 // Button6
        static int MAPPING_XBOX360_BACK = 62 // Button7
        static int MAPPING_XBOX360_START = 63 // Button8

        static String none =  null
        static String ESC =  "1B"
        static String F1 =  "70"
        static String F2 =  "71"
        static String F3 =  "72"
        static String F4 =  "73"
        static String F5 =  "74"
        static String F6 =  "75"
        static String F7 =  "76"
        static String F8 =  "77"
        static String F9 =  "78"
        static String F10 =  "79"

        static String SHIFT =  "10"
        static String CTRL =  "11"
        static String ALT =  "12"

        static String LSHIFT = "A0"
        static String RSHIFT = "A1"

        static String LCTRL = "A2"
        static String LCONTROL = "A2"
        static String RCTRL = "A3"
        static String RCONTROL = "A3"

        static String LALT = "A4"
        static String RALT = "A5"

        static String SPACE =  "20"
        static String ENTER =  "0D"
        static String RETURN =  "0D"
        static String CAPS =  "14"
        static String CAPSLOCK =  "14"
        static String PAUSE =  "13"
        static String TAB =  "09"
        static String BACKSPACE =  "08"
        static String INSERT =  "2D"
        static String DELETE =  "2E"
        static String HOME =  "24"
        static String END =  "23"
        static String PAGEUP =  "21"
        static String PAGEDOWN =  "22"
        static String BELOW_ESC =  "17" // º ª \ LA TECLA QUE ESTA DEBAJO DEL ESCAPE Y ENCIMA DEL BLOQ MAYUSCULAS

        static String CURSOR_LEFT =  "25"
        static String CURSOR_DOWN =  "28"
        static String CURSOR_RIGHT =  "27"
        static String CURSOR_UP =  "26"

        static String KEY_0 = k("0")
        static String KEY_1 = k("1")
        static String KEY_2 = k("2")
        static String KEY_3 = k("3")
        static String KEY_4 = k("4")
        static String KEY_5 = k("5")
        static String KEY_6 = k("6")
        static String KEY_7 = k("7")
        static String KEY_8 = k("8")
        static String KEY_9 = k("9")


        static String KEY_F1 =  "70"
        static String KEY_F2 =  "71"
        static String KEY_F3 =  "72"
        static String KEY_F4 =  "73"
        static String KEY_F5 =  "74"
        static String KEY_F6 =  "75"
        static String KEY_F7 =  "76"
        static String KEY_F8 =  "77"
        static String KEY_F9 =  "78"
        static String KEY_F10 = "79"

        static String KEY_A = k("A")
        static String KEY_B = k("B")
        static String KEY_C = k("C")
        static String KEY_D = k("D")
        static String KEY_E = k("E")
        static String KEY_F = k("F")
        static String KEY_G = k("G")
        static String KEY_H = k("H")
        static String KEY_I = k("I")
        static String KEY_J = k("J")
        static String KEY_K = k("K")
        static String KEY_L = k("L")
        static String KEY_M = k("M")
        static String KEY_N = k("N")
        static String KEY_O = k("O")
        static String KEY_P = k("P")
        static String KEY_Q = k("Q")
        static String KEY_R = k("R")
        static String KEY_S = k("S")
        static String KEY_T = k("T")
        static String KEY_U = k("U")
        static String KEY_V = k("V")
        static String KEY_W = k("W")
        static String KEY_X = k("X")
        static String KEY_Y = k("Y")
        static String KEY_Z = k("Z")



        static String k(String k) {
            return Integer.toHexString((k as char) as int).toUpperCase()
        }

        Preset buttonsTo(int joy, Map map) {
            map.each { button, key ->
                List keys = key instanceof List ? key : [key]
                buttonToKeys(joy, button, keys)
            }
            this
        }

        Preset buttonsTo(int joy, int buttonStart, List keys) {
            keys.eachWithIndex { key, int i ->
                List tkeys = key instanceof List ? key : [key]
                buttonToKeys(joy, buttonStart + i, tkeys)
            }
            this

        }

        Preset buttonToOtherJoy(int joy, int button, int newJoy) {
            cfg.put("Joystick $joy", "Button${button<10?"0":""}$button", "5, ${newJoy}, 0")
            return this
        }

        Preset buttonToKey(int joy, int button, String key) {
            buttonToKey(joy, "Button${button<10?"0":""}$button", key)
        }

        Preset buttonToKeys(int joy, int button, List keys) {
            buttonToKeys(joy, "Button${button<10?"0":""}$button", keys)
        }

        Preset buttonToKey(int joy, String button, String key) {
            buttonToKeys(joy, button, [key])
        }

        Preset buttonToKeys(int joy, String button, List keys) {
            keys = keys + (["00"] * 3 - keys.size())
            String skeys = keys.join(":")
            cfg.put("Joystick $joy", button, "1, ${skeys}, 0.000, 0, 0")
            this
        }

        Preset xbox360MameTab(int joy = 1) {
            // envia TAB al pulsar SELECT(back) + LB (usando mapping boton 31)
            mapping(joy, MAPPING_XBOX360_BACK, MAPPING_XBOX360_LB, TAB, 30)
            this
        }

        Preset xbox360RetroArchF1(int joy = 1) {
            // envia F1 al pulsar SELECT(back) + LB (usando mapping boton 31)
            mapping(joy, MAPPING_XBOX360_BACK, MAPPING_XBOX360_LB, F1, 31)
            this
        }

        Preset xbox360Esc(int joy = 1) {
            // envia ESC al pulsar SELECT(back) + START (usando mapping boton 32)
//            buttonToKey(joy, 32, ESC)
//            cfg.put("ButtonAlias", "Button32", "62, 63")
            mapping(joy, MAPPING_XBOX360_BACK, MAPPING_XBOX360_START, ESC, 32)
            return this
        }

        Preset mapping(int joy, int firstMappingButton, int secondMappingButton, String action, int extraButton) {
            buttonToKey(joy, extraButton, action)
            cfg.put("ButtonAlias", "Button${extraButton}", "$firstMappingButton, $secondMappingButton")
            this
        }

        Preset dPadTo(int joy = 1, String left, String down, String up, String right) {
            buttonToKey(joy, "POV1-1", up)
            buttonToKey(joy, "POV1-3", right)
            buttonToKey(joy, "POV1-5", down)
            buttonToKey(joy, "POV1-7", left)
            return this
        }

        Preset dPadToCursor(int joy = 1) {
            dPadTo(joy, CURSOR_LEFT, CURSOR_DOWN, CURSOR_UP, CURSOR_RIGHT)
            return this
        }

        Preset analogToCursor(int joy = 1) {
            analogLeftToCursor(joy)
            analogRightToCursor(joy)
            return this
        }

        Preset analogLeftToCursor(int joy = 1) {
            analogLeftTo(joy, CURSOR_LEFT, CURSOR_DOWN, CURSOR_UP, CURSOR_RIGHT)
            return this
        }

        Preset analogRightToCursor(int joy = 1) {
            analogRightTo(joy, CURSOR_LEFT, CURSOR_DOWN, CURSOR_UP, CURSOR_RIGHT)
            return this
        }

        Preset analogTo(int joy = 1, String left, String down, String up, String right) {
            analogLeftTo(joy, left, down, up, right)
            analogRightTo(joy, left, down, up, right)
            return this
        }

        Preset analogLeftTo(int joy = 1, String left, String down, String up, String right) {
            buttonToKey(joy, "Axis2n", up)
            buttonToKey(joy, "Axis1p", right)
            buttonToKey(joy, "Axis2p", down)
            buttonToKey(joy, "Axis1n", left)
            return this
        }

        Preset analogRightTo(int joy = 1, String left, String down, String up, String right) {
            buttonToKey(joy, "Axis4n", up)
            buttonToKey(joy, "Axis3p", right)
            buttonToKey(joy, "Axis4p", down)
            buttonToKey(joy, "Axis3n", left)
            return this
        }


        Preset save() {
            cfg.store()
            return this
        }
    }

    J2K empty() {
        cfg = new IniFile(file: cfg.file)
        cfg.put("General","FileVersion","57")
        cfg.put("General","NumberOfJoysticks","4")
        cfg.put("General","DisplayMode","2")
        cfg.put("General","UseDiagonalInput","0")
        cfg.put("General","UsePOV8Way","0")
        cfg.put("General","Threshold","20")
        cfg.put("General","Threshold2","20")
        cfg.put("General","KeySendMode","0")
        cfg.put("General","NumberOfButtons","32")
        cfg.store()
        return this
    }


}
