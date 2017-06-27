package org.hs5tb.groospin.base

import groovy.xml.MarkupBuilder
import org.hs5tb.groospin.common.IOTools

/**
 * Created by Alberto on 19-Jun-17.
 */
class MameMapping {

    String folder
    String machine = "default"
    Map<String, Set> mapping = [:].withDefault { k -> new LinkedHashSet() }

    // http://easyemu.mameworld.info/mameguide/mameguide-controlini.htm
    MameMapping folder(String folder) {
        this.folder = folder
        this
    }

    File getCfg(String machine = this.machine) {
        return new File(folder + "/cfg/${machine}.cfg")
    }

    File getCtrlr(String machine = this.machine) {
        return new File(folder + "/ctrlr/${machine}.cfg")
    }


    MameMapping backupAndCleanDefaultCfg() {
        println "Backup (if exists) and clean default.cfg: ${cfg.absolutePath}"
        loadDefaultCfg()
        if (mapping) {
            String newName = "${machine}-${new Date().format("yyyyMMdd-HHmmss")}"
            IOTools.move(cfg, getCfg(newName))
        }
        this
    }

    MameMapping loadDefaultCfg() {
        loadCfg("default")
    }

    MameMapping loadCfg(String machine) {
        loadFile(machine, cfg)
    }

    MameMapping loadCtrlr(String machine) {
        loadFile(machine, ctrlr)
    }

    MameMapping loadFile(String machine, File file) {
        this.machine = machine
        mapping.clear()
        if (file.exists()) {
            Node mameconfig = new XmlParser().parse(file)
            mameconfig.system.input.port.each { Node port ->
                String action = port.@type
                String codes = port.newseq ? port.newseq[0].text()?.trim()?.toUpperCase() : null
//                println action
//                println
                if (action && codes) {
                    mapping[action] = codes.split(" OR ")*.trim() as Set
                }
            }
        }
        this
    }

    MameMapping saveCfg(String machine = this.machine) {
        saveFile(machine, getCfg(machine))
    }
    MameMapping saveCtrl(String machine = this.machine) {
        saveFile("default", getCtrlr(machine))
    }

    MameMapping saveFile(String machine = this.machine, File file) {
        MarkupBuilder mb = new MarkupBuilder(new IndentPrinter(new PrintWriter(file.newWriter()), "    "))
        mb.mkp.xmlDeclaration(version: "1.0")
        mb.setOmitNullAttributes(true)
        mb.setDoubleQuotes(true)
        mb.setEscapeAttributes(true)
        mb.mameconfig(version:"10") {
            system(name: machine) {
                input {
                    mapping.keySet().sort().each { String action ->
                        Set codes = mapping[action]
                        port(type: action) {
                            newseq(type:"standard", "\n                        ${codes.join(" OR ")}\n            ")
                        }
                    }
                }
            }
        }
        this
    }

    MameMapping add(String action, Collection keys) {
        keys.each { String key -> mapping[action] << key.toUpperCase() }
        this
    }

    MameMapping set(String action, Collection keys) {
        mapping[action] = keys as Set
        this
    }

    MameMapping add(String action, String key) {
        add(action, [key])
        this
    }

    MameMapping set(String action, String key) {
        set(action, [key])
        this
    }

    MameMapping delete(String action) {
        mapping.remove(action)
        this
    }

    static String NONE = "KEYCODE_NONE"
    static String DEFAULT = "CODE_DEFAULT"

    static String J1_BUTTON1 = "JOYCODE_1_BUTTON1"
    static String J1_BUTTON2 = "JOYCODE_1_BUTTON2"
    static String J1_BUTTON3 = "JOYCODE_1_BUTTON3"
    static String J1_BUTTON4 = "JOYCODE_1_BUTTON4"
    static String J1_BUTTON5 = "JOYCODE_1_BUTTON5"
    static String J1_BUTTON6 = "JOYCODE_1_BUTTON6"
    static String J1_BUTTON7 = "JOYCODE_1_BUTTON7"
    static String J1_BUTTON8 = "JOYCODE_1_BUTTON8"
    static String J1_DOWN = "JOYCODE_1_DOWN"
    static String J1_LEFT = "JOYCODE_1_LEFT"
    static String J1_RIGHT = "JOYCODE_1_RIGHT"
    static String J1_SELECT = "JOYCODE_1_SELECT"
    static String J1_START = "JOYCODE_1_START"
    static String J1_UP = "JOYCODE_1_UP"

    static String J1_DPAD_UP = "JOYCODE_1_DPADUP"
    static String J1_DPAD_DOWN = "JOYCODE_1_DPADDOWN"
    static String J1_DPAD_LEFT = "JOYCODE_1_DPADLEFT"
    static String J1_DPAD_RIGHT = "JOYCODE_1_DPADRIGHT"

    static String J1_LANA_UP = "JOYCODE_1_YAXIS_UP_SWITCH"
    static String J1_LANA_DOWN = "JOYCODE_1_YAXIS_DOWN_SWITCH"
    static String J1_LANA_LEFT = "JOYCODE_1_XAXIS_LEFT_SWITCH"
    static String J1_LANA_RIGHT = "JOYCODE_1_XAXIS_RIGHT_SWITCH"

    static String J1_RANA_UP = "JOYCODE_1_RYAXIS_NEG_SWITCH"
    static String J1_RANA_DOWN = "JOYCODE_1_RYAXIS_POS_SWITCH"
    static String J1_RANA_LEFT = "JOYCODE_1_RXAXIS_NEG_SWITCH"
    static String J1_RANA_RIGHT = "JOYCODE_1_RXAXIS_POS_SWITCH"

    static String J1_LT_ANA = "JOYCODE_1_ZAXIS_NEG_SWITCH" // bug?? no deberia ser ...1_RZ... le falta la R!!
    static String J1_RT_ANA = "JOYCODE_1_RZAXIS_POS_SWITCH"

    static String J2_BUTTON1 = "JOYCODE_2_BUTTON1"
    static String J2_BUTTON2 = "JOYCODE_2_BUTTON2"
    static String J2_BUTTON3 = "JOYCODE_2_BUTTON3"
    static String J2_BUTTON4 = "JOYCODE_2_BUTTON4"
    static String J2_BUTTON5 = "JOYCODE_2_BUTTON5"
    static String J2_BUTTON6 = "JOYCODE_2_BUTTON6"
    static String J2_BUTTON7 = "JOYCODE_2_BUTTON7"
    static String J2_BUTTON8 = "JOYCODE_2_BUTTON8"
    static String J2_DOWN = "JOYCODE_2_DOWN"
    static String J2_LEFT = "JOYCODE_2_LEFT"
    static String J2_RIGHT = "JOYCODE_2_RIGHT"
    static String J2_SELECT = "JOYCODE_2_SELECT"
    static String J2_START = "JOYCODE_2_START"
    static String J2_UP = "JOYCODE_2_UP"

    static String F1 = "KEYCODE_F1"
    static String F2 = "KEYCODE_F2"
    static String F3 = "KEYCODE_F3"
    static String F4 = "KEYCODE_F4"
    static String F5 = "KEYCODE_F5"
    static String F6 = "KEYCODE_F6"
    static String F7 = "KEYCODE_F7"
    static String F8 = "KEYCODE_F8"
    static String F9 = "KEYCODE_F9"
    static String F10 = "KEYCODE_F10"
    static String F11 = "KEYCODE_F11"
    static String F12 = "KEYCODE_F12"

    static String J3_BUTTON1 = "JOYCODE_3_BUTTON1"
    static String J3_BUTTON2 = "JOYCODE_3_BUTTON2"
    static String J3_BUTTON3 = "JOYCODE_3_BUTTON3"
    static String J3_BUTTON4 = "JOYCODE_3_BUTTON4"
    static String J3_BUTTON5 = "JOYCODE_3_BUTTON5"
    static String J3_BUTTON6 = "JOYCODE_3_BUTTON6"
    static String J3_BUTTON7 = "JOYCODE_3_BUTTON7"
    static String J3_BUTTON8 = "JOYCODE_3_BUTTON8"
    static String J3_DOWN = "JOYCODE_3_DOWN"
    static String J3_LEFT = "JOYCODE_3_LEFT"
    static String J3_RIGHT = "JOYCODE_3_RIGHT"
    static String J3_SELECT = "JOYCODE_3_SELECT"
    static String J3_START = "JOYCODE_3_START"
    static String J3_UP = "JOYCODE_3_UP"

    static String J4_BUTTON = "JOYCODE_4_BUTTON"
    static String J4_BUTTON1 = "JOYCODE_4_BUTTON1"
    static String J4_BUTTON2 = "JOYCODE_4_BUTTON2"
    static String J4_BUTTON3 = "JOYCODE_4_BUTTON3"
    static String J4_BUTTON4 = "JOYCODE_4_BUTTON4"
    static String J4_BUTTON5 = "JOYCODE_4_BUTTON5"
    static String J4_BUTTON6 = "JOYCODE_4_BUTTON6"
    static String J4_BUTTON7 = "JOYCODE_4_BUTTON7"
    static String J4_BUTTON8 = "JOYCODE_4_BUTTON8"
    static String J4_DOWN = "JOYCODE_4_DOWN"
    static String J4_LEFT = "JOYCODE_4_LEFT"
    static String J4_RIGHT = "JOYCODE_4_RIGHT"
    static String J4_SELECT = "JOYCODE_4_SELECT"
    static String J4_START = "JOYCODE_4_START"
    static String J4_UP = "JOYCODE_4_UP"

    static String CURSOR_UP = "KEYCODE_UP"
    static String CURSOR_DOWN = "KEYCODE_DOWN"
    static String CURSOR_LEFT = "KEYCODE_LEFT"
    static String CURSOR_RIGHT = "KEYCODE_RIGHT"

    static String KEY_A = "KEYCODE_A"
    static String KEY_B = "KEYCODE_B"
    static String KEY_C = "KEYCODE_C"
    static String KEY_D = "KEYCODE_D"
    static String KEY_E = "KEYCODE_E"
    static String KEY_F = "KEYCODE_F"
    static String KEY_G = "KEYCODE_G"
    static String KEY_H = "KEYCODE_H"
    static String KEY_I = "KEYCODE_I"
    static String KEY_J = "KEYCODE_J"
    static String KEY_K = "KEYCODE_K"
    static String KEY_L = "KEYCODE_L"
    static String KEY_M = "KEYCODE_M"
    static String KEY_N = "KEYCODE_N"
    static String KEY_O = "KEYCODE_O"
    static String KEY_P = "KEYCODE_P"
    static String KEY_Q = "KEYCODE_Q"
    static String KEY_R = "KEYCODE_R"
    static String KEY_S = "KEYCODE_S"
    static String KEY_T = "KEYCODE_T"
    static String KEY_U = "KEYCODE_U"
    static String KEY_V = "KEYCODE_V"
    static String KEY_W = "KEYCODE_W"
    static String KEY_X = "KEYCODE_X"
    static String KEY_Y = "KEYCODE_Y"
    static String KEY_Z = "KEYCODE_Z"

    static String KEY_0_PAD = "KEYCODE_0_PAD"
    static String KEY_1_PAD = "KEYCODE_1_PAD"
    static String KEY_2_PAD = "KEYCODE_2_PAD"
    static String KEY_3_PAD = "KEYCODE_3_PAD"
    static String KEY_4_PAD = "KEYCODE_4_PAD"
    static String KEY_5_PAD = "KEYCODE_5_PAD"
    static String KEY_6_PAD = "KEYCODE_6_PAD"
    static String KEY_7_PAD = "KEYCODE_7_PAD"
    static String KEY_8_PAD = "KEYCODE_8_PAD"
    static String KEY_9_PAD = "KEYCODE_9_PAD"
    static String DEL_PAD = "KEYCODE_DEL_PAD"
    static String ENTER_PAD = "KEYCODE_ENTER_PAD"
    static String MINUS_PAD = "KEYCODE_MINUS_PAD"
    static String PLUS_PAD = "KEYCODE_PLUS_PAD"
    static String SLASH_PAD = "KEYCODE_SLASH_PAD"

    static String NUMLOCK = "KEYCODE_NUMLOCK"

    static String KEY_0 = "KEYCODE_0"
    static String KEY_1 = "KEYCODE_1"
    static String KEY_2 = "KEYCODE_2"
    static String KEY_3 = "KEYCODE_3"
    static String KEY_4 = "KEYCODE_4"
    static String KEY_5 = "KEYCODE_5"
    static String KEY_6 = "KEYCODE_6"
    static String KEY_7 = "KEYCODE_7"
    static String KEY_8 = "KEYCODE_8"
    static String KEY_9 = "KEYCODE_9"

    static String DEL = "KEYCODE_DEL"
    static String END = "KEYCODE_END"
    static String HOME = "KEYCODE_HOME"
    static String INSERT = "KEYCODE_INSERT"
    static String PGDN = "KEYCODE_PGDN"
    static String PGUP = "KEYCODE_PGUP"

    static String PAUSE = "KEYCODE_PAUSE"
    static String PRTSCR = "KEYCODE_PRTSCR"
    static String SCRLOCK = "KEYCODE_SCRLOCK"

    static String LWIN = "KEYCODE_LWIN"
    static String RWIN = "KEYCODE_RWIN"

    static String CAPS = "KEYCODE_CAPSLOCK"
    static String CAPSLOCK = "KEYCODE_CAPSLOCK"
    static String ENTER = "KEYCODE_ENTER"
    static String RETURN = "KEYCODE_ENTER"
    static String LALT = "KEYCODE_LALT"
    static String LCONTROL = "KEYCODE_LCONTROL"
    static String LCTRL = "KEYCODE_LCONTROL"
    static String LSHIFT = "KEYCODE_LSHIFT"
    static String RALT = "KEYCODE_RALT"
    static String RCONTROL = "KEYCODE_RCONTROL"
    static String RCTRL = "KEYCODE_RCONTROL"
    static String RSHIFT = "KEYCODE_RSHIFT"
    static String SPACE = "KEYCODE_SPACE"
    static String TAB = "KEYCODE_TAB"
    static String ESC = "KEYCODE_ESC"
    static String ESCAPE = "KEYCODE_ESC"
    static String BACKSPACE = "KEYCODE_BACKSPACE"
    static String MENU = "KEYCODE_MENU"

    static String ASTERISK = "KEYCODE_ASTERISK"
    static String BACKSLASH = "KEYCODE_BACKSLASH"
    static String BACKSLASH2 = "KEYCODE_BACKSLASH2"
    static String CLOSEBRACE = "KEYCODE_CLOSEBRACE"
    static String COLON = "KEYCODE_COLON"
    static String COMMA = "KEYCODE_COMMA"
    static String EQUALS = "KEYCODE_EQUALS"
    static String MINUS = "KEYCODE_MINUS"
    static String OPENBRACE = "KEYCODE_OPENBRACE"
    static String QUOTE = "KEYCODE_QUOTE"
    static String SLASH = "KEYCODE_SLASH"
    static String STOP = "KEYCODE_STOP"
    static String TILDE = "KEYCODE_TILDE"

    static String MOUSE1_BUTTON1 = "MOUSECODE_1_BUTTON1"
    static String MOUSE1_BUTTON2 = "MOUSECODE_1_BUTTON2"
    static String MOUSE1_BUTTON3 = "MOUSECODE_1_BUTTON3"

    static class Action {
        static String COIN1 = "COIN1"
        static String COIN2 = "COIN2"
        static String COIN3 = "COIN3"
        static String COIN4 = "COIN4"
        static String START1 = "START1"
        static String START2 = "START2"
        static String START3 = "START3"
        static String START4 = "START4"
        static String SERVICE1 = "SERVICE1"
        static String SERVICE2 = "SERVICE2"
        static String SERVICE3 = "SERVICE3"
        static String SERVICE4 = "SERVICE4"
        static String TILT = "TILT"
        static String UI_UP = "UI_UP"
        static String UI_RIGHT = "UI_RIGHT"
        static String UI_LEFT = "UI_LEFT"
        static String UI_DOWN = "UI_DOWN"
        static String UI_SELECT = "UI_SELECT"
        static String UI_CANCEL = "UI_CANCEL"
        static String UI_PAUSE = "UI_PAUSE"
        static String UI_LOAD_STATE = "UI_LOAD_STATE"
        static String UI_SAVE_STATE = "UI_SAVE_STATE"
        static String UI_ADD_CHEAT = "UI_ADD_CHEAT"
        static String UI_DELETE_CHEAT = "UI_DELETE_CHEAT"
        static String UI_SAVE_CHEAT = "UI_SAVE_CHEAT"
        static String UI_EDIT_CHEAT = "UI_EDIT_CHEAT"
        static String UI_SHOW_FPS = "UI_SHOW_FPS"
        static String UI_THROTTLE = "UI_THROTTLE"
        static String UI_FRAMESKIP_DEC = "UI_FRAMESKIP_DEC"
        static String UI_FRAMESKIP_INC = "UI_FRAMESKIP_INC"
        static String P1_BUTTON2 = "P1_BUTTON2"
        static String P1_BUTTON3 = "P1_BUTTON3"
        static String P1_BUTTON1 = "P1_BUTTON1"
        static String P1_BUTTON4 = "P1_BUTTON4"
        static String P1_BUTTON5 = "P1_BUTTON5"
        static String P1_BUTTON6 = "P1_BUTTON6"
        static String P1_BUTTON7 = "P1_BUTTON7"
        static String P1_BUTTON8 = "P1_BUTTON8"
        static String P1_BUTTON9 = "P1_BUTTON9"
        static String P1_BUTTON10 = "P1_BUTTON10"
        static String P1_DOWN = "P1_JOYSTICK_DOWN"
        static String P1_LEFT = "P1_JOYSTICK_LEFT"
        static String P1_RIGHT = "P1_JOYSTICK_RIGHT"
        static String P1_UP = "P1_JOYSTICK_UP"
        static String P1_ANALOG_LEFT_DOWN = "P1_JOYSTICKLEFT_DOWN"
        static String P1_ANALOG_LEFT_LEFT = "P1_JOYSTICKLEFT_LEFT"
        static String P1_ANALOG_LEFT_RIGHT = "P1_JOYSTICKLEFT_RIGHT"
        static String P1_ANALOG_LEFT_UP = "P1_JOYSTICKLEFT_UP"
        static String P1_ANALOG_RIGHT_DOWN = "P1_JOYSTICKRIGHT_DOWN"
        static String P1_ANALOG_RIGHT_LEFT = "P1_JOYSTICKRIGHT_LEFT"
        static String P1_ANALOG_RIGHT_RIGHT = "P1_JOYSTICKRIGHT_RIGHT"
        static String P1_ANALOG_RIGHT_UP = "P1_JOYSTICKRIGHT_UP"

        static String P2_BUTTON2 = "P2_BUTTON2"
        static String P2_BUTTON3 = "P2_BUTTON3"
        static String P2_BUTTON1 = "P2_BUTTON1"
        static String P2_BUTTON4 = "P2_BUTTON4"
        static String P2_BUTTON5 = "P2_BUTTON5"
        static String P2_BUTTON6 = "P2_BUTTON6"
        static String P2_BUTTON7 = "P2_BUTTON7"
        static String P2_BUTTON8 = "P2_BUTTON8"
        static String P2_BUTTON9 = "P2_BUTTON9"
        static String P2_BUTTON10 = "P2_BUTTON10"
        static String P2_DOWN = "P2_JOYSTICK_DOWN"
        static String P2_LEFT = "P2_JOYSTICK_LEFT"
        static String P2_RIGHT = "P2_JOYSTICK_RIGHT"
        static String P2_UP = "P2_JOYSTICK_UP"
        static String P2_ANALOG_LEFT_DOWN = "P2_JOYSTICKLEFT_DOWN"
        static String P2_ANALOG_LEFT_LEFT = "P2_JOYSTICKLEFT_LEFT"
        static String P2_ANALOG_LEFT_RIGHT = "P2_JOYSTICKLEFT_RIGHT"
        static String P2_ANALOG_LEFT_UP = "P2_JOYSTICKLEFT_UP"
        static String P2_ANALOG_RIGHT_DOWN = "P2_JOYSTICKRIGHT_DOWN"
        static String P2_ANALOG_RIGHT_LEFT = "P2_JOYSTICKRIGHT_LEFT"
        static String P2_ANALOG_RIGHT_RIGHT = "P2_JOYSTICKRIGHT_RIGHT"
        static String P2_ANALOG_RIGHT_UP = "P2_JOYSTICKRIGHT_UP"

        static String P3_BUTTON2 = "P3_BUTTON2"
        static String P3_BUTTON3 = "P3_BUTTON3"
        static String P3_BUTTON1 = "P3_BUTTON1"
        static String P3_BUTTON4 = "P3_BUTTON4"
        static String P3_BUTTON5 = "P3_BUTTON5"
        static String P3_BUTTON6 = "P3_BUTTON6"
        static String P3_BUTTON7 = "P3_BUTTON7"
        static String P3_BUTTON8 = "P3_BUTTON8"
        static String P3_BUTTON9 = "P3_BUTTON9"
        static String P3_BUTTON10 = "P3_BUTTON10"
        static String P3_DOWN = "P3_JOYSTICK_DOWN"
        static String P3_LEFT = "P3_JOYSTICK_LEFT"
        static String P3_RIGHT = "P3_JOYSTICK_RIGHT"
        static String P3_UP = "P3_JOYSTICK_UP"
        static String P3_ANALOG_LEFT_DOWN = "P3_JOYSTICKLEFT_DOWN"
        static String P3_ANALOG_LEFT_LEFT = "P3_JOYSTICKLEFT_LEFT"
        static String P3_ANALOG_LEFT_RIGHT = "P3_JOYSTICKLEFT_RIGHT"
        static String P3_ANALOG_LEFT_UP = "P3_JOYSTICKLEFT_UP"
        static String P3_ANALOG_RIGHT_DOWN = "P3_JOYSTICKRIGHT_DOWN"
        static String P3_ANALOG_RIGHT_LEFT = "P3_JOYSTICKRIGHT_LEFT"
        static String P3_ANALOG_RIGHT_RIGHT = "P3_JOYSTICKRIGHT_RIGHT"
        static String P3_ANALOG_RIGHT_UP = "P3_JOYSTICKRIGHT_UP"

        static String P4_BUTTON2 = "P4_BUTTON2"
        static String P4_BUTTON3 = "P4_BUTTON3"
        static String P4_BUTTON1 = "P4_BUTTON1"
        static String P4_BUTTON4 = "P4_BUTTON4"
        static String P4_BUTTON5 = "P4_BUTTON5"
        static String P4_BUTTON6 = "P4_BUTTON6"
        static String P4_BUTTON7 = "P4_BUTTON7"
        static String P4_BUTTON8 = "P4_BUTTON8"
        static String P4_BUTTON9 = "P4_BUTTON9"
        static String P4_BUTTON10 = "P4_BUTTON10"
        static String P4_DOWN = "P4_JOYSTICK_DOWN"
        static String P4_LEFT = "P4_JOYSTICK_LEFT"
        static String P4_RIGHT = "P4_JOYSTICK_RIGHT"
        static String P4_UP = "P4_JOYSTICK_UP"
        static String P4_ANALOG_LEFT_DOWN = "P4_JOYSTICKLEFT_DOWN"
        static String P4_ANALOG_LEFT_LEFT = "P4_JOYSTICKLEFT_LEFT"
        static String P4_ANALOG_LEFT_RIGHT = "P4_JOYSTICKLEFT_RIGHT"
        static String P4_ANALOG_LEFT_UP = "P4_JOYSTICKLEFT_UP"
        static String P4_ANALOG_RIGHT_DOWN = "P4_JOYSTICKRIGHT_DOWN"
        static String P4_ANALOG_RIGHT_LEFT = "P4_JOYSTICKRIGHT_LEFT"
        static String P4_ANALOG_RIGHT_RIGHT = "P4_JOYSTICKRIGHT_RIGHT"
        static String P4_ANALOG_RIGHT_UP = "P4_JOYSTICKRIGHT_UP"

    }
    
}
