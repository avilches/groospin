package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 28-Jul-17.
 */
class DemulMapping {

    static String NONE = "0"
    static String JOY0_A = "805306380"
    static String JOY0_B = "805306381"
    static String JOY0_X = "805306382"
    static String JOY0_Y = "805306383"
    static String JOY0_LT = "805306376"
    static String JOY0_RT = "805306377"
    static String JOY0_LB = "1342177280"
    static String JOY0_RB = "1342177536"
    static String JOY0_BACK = "805306372"
    static String JOY0_START = "805306373"
    static String JOY0_UP = "805306368"
    static String JOY0_DOWN = "805306369"
    static String JOY0_LEFT = "805306370"
    static String JOY0_RIGHT = "805306371"
    static String JOY0_ANALOG1_UP = "-1879047680"
    static String JOY0_ANALOG1_DOWN = "-1879047424"
    static String JOY0_ANALOG1_LEFT = "-1879048192"
    static String JOY0_ANALOG1_RIGHT = "-1879047936"
    static String JOY0_ANALOG2_UP = "-1879046656"
    static String JOY0_ANALOG2_DOWN = "-1879046400"
    static String JOY0_ANALOG2_LEFT = "-1879047168"
    static String JOY0_ANALOG2_RIGHT = "-1879046912"
    static String JOY1_A = "805371916"
    static String JOY1_B = "805371917"
    static String JOY1_X = "805371918"
    static String JOY1_Y = "805371919"
    static String JOY1_LT = "805371912"
    static String JOY1_RT = "805371913"
    static String JOY1_LB = "1342242816"
    static String JOY1_RB = "1342243072"
    static String JOY1_BACK = "805371908"
    static String JOY1_START = "805371909"
    static String JOY1_UP = "805371904"
    static String JOY1_DOWN = "805371905"
    static String JOY1_LEFT = "805371906"
    static String JOY1_RIGHT = "805371907"
    static String JOY1_ANALOG1_UP = "-1878982144"
    static String JOY1_ANALOG1_DOWN = "-1878981888"
    static String JOY1_ANALOG1_LEFT = "-1878982656"
    static String JOY1_ANALOG1_RIGHT = "-1878982400"
    static String JOY1_ANALOG2_UP = "-1878981120"
    static String JOY1_ANALOG2_DOWN = "-1878980864"
    static String JOY1_ANALOG2_LEFT = "-1878981632"
    static String JOY1_ANALOG2_RIGHT = "-1878981376"
    static String F1 = "59"
    static String F2 = "60"
    static String F3 = "61"
    static String F4 = "62"
    static String F5 = "63"
    static String F6 = "64"
    static String F7 = "65"
    static String F8 = "66"
    static String F9 = "67"
    static String F10 = "68"
    static String KEY_Z = "44"
    static String KEY_X = "45"
    static String KEY_C = "46"
    static String KEY_V = "47"
    static String KEY_B = "48"
    static String KEY_N = "49"
    static String KEY_M = "50"
    static String KEY_A = "30"
    static String KEY_S = "31"
    static String KEY_D = "32"
    static String KEY_F = "33"
    static String KEY_G = "34"
    static String KEY_H = "35"
    static String KEY_K = "37"
    static String KEY_J = "36"
    static String KEY_L = "38"
    static String KEY_1 = "2"
    static String KEY_2 = "3"
    static String KEY_3 = "4"
    static String KEY_4 = "5"
    static String KEY_5 = "6"
    static String KEY_6 = "7"
    static String KEY_7 = "8"
    static String KEY_8 = "9"
    static String KEY_9 = "10"
    static String KEY_0 = "11"
    static String KEY_Q = "16"
    static String KEY_W = "17"
    static String KEY_E = "18"
    static String KEY_R = "19"
    static String KEY_T = "20"
    static String KEY_Y = "21"
    static String KEY_U = "22"
    static String KEY_I = "23"
    static String KEY_O = "24"
    static String KEY_P = "25"
    static String UP = "200"
    static String DOWN = "208"
    static String LEFT = "203"
    static String RIGHT = "205"
    static String KEY_2_PAD = "80"
    static String KEY_8_PAD = "72"
    static String KEY_4_PAD = "75"
    static String KEY_6_PAD = "77"


    static Map reverse = [
            "0"          : "NONE",
            "805306380"  : "JOY0_A",
            "805306381"  : "JOY0_B",
            "805306382"  : "JOY0_X",
            "805306383"  : "JOY0_Y",
            "805306376"  : "JOY0_LT",
            "805306377"  : "JOY0_RT",
            "1342177280" : "JOY0_LB",
            "1342177536" : "JOY0_RB",
            "805306372"  : "JOY0_BACK",
            "805306373"  : "JOY0_START",
            "805306368"  : "JOY0_UP",
            "805306369"  : "JOY0_DOWN",
            "805306370"  : "JOY0_LEFT",
            "805306371"  : "JOY0_RIGHT",
            "-1879047680": "JOY0_ANALOG1_UP",
            "-1879047424": "JOY0_ANALOG1_DOWN",
            "-1879048192": "JOY0_ANALOG1_LEFT",
            "-1879047936": "JOY0_ANALOG1_RIGHT",
            "-1879046656": "JOY0_ANALOG2_UP",
            "-1879046400": "JOY0_ANALOG2_DOWN",
            "-1879047168": "JOY0_ANALOG2_LEFT",
            "-1879046912": "JOY0_ANALOG2_RIGHT",
            "805371916"  : "JOY1_A",
            "805371917"  : "JOY1_B",
            "805371918"  : "JOY1_X",
            "805371919"  : "JOY1_Y",
            "805371912"  : "JOY1_LT",
            "805371913"  : "JOY1_RT",
            "1342242816" : "JOY1_LB",
            "1342243072" : "JOY1_RB",
            "805371908"  : "JOY1_BACK",
            "805371909"  : "JOY1_START",
            "805371904"  : "JOY1_UP",
            "805371905"  : "JOY1_DOWN",
            "805371906"  : "JOY1_LEFT",
            "805371907"  : "JOY1_RIGHT",
            "-1878982144": "JOY1_ANALOG1_UP",
            "-1878981888": "JOY1_ANALOG1_DOWN",
            "-1878982656": "JOY1_ANALOG1_LEFT",
            "-1878982400": "JOY1_ANALOG1_RIGHT",
            "-1878981120": "JOY1_ANALOG2_UP",
            "-1878980864": "JOY1_ANALOG2_DOWN",
            "-1878981632": "JOY1_ANALOG2_LEFT",
            "-1878981376": "JOY1_ANALOG2_RIGHT",
            "59"         : "F1",
            "60"         : "F2",
            "61"         : "F3",
            "62"         : "F4",
            "63"         : "F5",
            "64"         : "F6",
            "65"         : "F7",
            "66"         : "F8",
            "67"         : "F9",
            "68"         : "F10",
            "44"         : "KEY_Z",
            "45"         : "KEY_X",
            "46"         : "KEY_C",
            "47"         : "KEY_V",
            "48"         : "KEY_B",
            "49"         : "KEY_N",
            "50"         : "KEY_M",
            "30"         : "KEY_A",
            "31"         : "KEY_S",
            "32"         : "KEY_D",
            "33"         : "KEY_F",
            "34"         : "KEY_G",
            "35"         : "KEY_H",
            "37"         : "KEY_K",
            "36"         : "KEY_J",
            "38"         : "KEY_L",
            "2"          : "KEY_1",
            "3"          : "KEY_2",
            "4"          : "KEY_3",
            "5"          : "KEY_4",
            "6"          : "KEY_5",
            "7"          : "KEY_6",
            "8"          : "KEY_7",
            "9"          : "KEY_8",
            "10"         : "KEY_9",
            "11"         : "KEY_0",
            "16"         : "KEY_Q",
            "17"         : "KEY_W",
            "18"         : "KEY_E",
            "19"         : "KEY_R",
            "20"         : "KEY_T",
            "21"         : "KEY_Y",
            "22"         : "KEY_U",
            "23"         : "KEY_I",
            "24"         : "KEY_O",
            "25"         : "KEY_P",
            "200"        : "UP",
            "208"        : "DOWN",
            "203"        : "LEFT",
            "205"        : "RIGHT",
            "80"         : "KEY_2_PAD",
            "72"         : "KEY_8_PAD",
            "75"         : "KEY_4_PAD",
            "77"         : "KEY_6_PAD"]


    static Map createDefaultXboxMappingJoystick1() {
        ["PUSH1"       : JOY0_A,
         "PUSH2"       : JOY0_B,
         "PUSH3"       : JOY0_X,
         "PUSH4"       : JOY0_Y,
         "PUSH5"       : JOY0_LT,
         "PUSH6"       : JOY0_RT,
         "PUSH7"       : JOY0_LB,
         "PUSH8"       : JOY0_RB,
         "SERVICE"     : NONE,
         "START"       : JOY0_BACK,
         "COIN"        : JOY0_START,
         "DIGITALUP"   : JOY0_UP,
         "DIGITALDOWN" : JOY0_DOWN,
         "DIGITALLEFT" : JOY0_LEFT,
         "DIGITALRIGHT": JOY0_RIGHT,
         "ANALOGUP"    : JOY0_ANALOG1_UP,
         "ANALOGDOWN"  : JOY0_ANALOG1_DOWN,
         "ANALOGLEFT"  : JOY0_ANALOG1_LEFT,
         "ANALOGRIGHT" : JOY0_ANALOG1_RIGHT,
         "ANALOGUP2"   : JOY0_ANALOG2_UP,
         "ANALOGDOWN2" : JOY0_ANALOG2_DOWN,
         "ANALOGLEFT2" : JOY0_ANALOG2_LEFT,
         "ANALOGRIGHT2": JOY0_ANALOG2_RIGHT]
    }

    static Map createDefaultXboxMappingJoystick2() {
        ["PUSH1"       : JOY1_A,
         "PUSH2"       : JOY1_B,
         "PUSH3"       : JOY1_X,
         "PUSH4"       : JOY1_Y,
         "PUSH5"       : JOY1_LT,
         "PUSH6"       : JOY1_RT,
         "PUSH7"       : JOY1_LB,
         "PUSH8"       : JOY1_RB,
         "SERVICE"     : NONE,
         "START"       : JOY1_BACK,
         "COIN"        : JOY1_START,
         "DIGITALUP"   : JOY1_UP,
         "DIGITALDOWN" : JOY1_DOWN,
         "DIGITALLEFT" : JOY1_LEFT,
         "DIGITALRIGHT": JOY1_RIGHT,
         "ANALOGUP"    : JOY1_ANALOG1_UP,
         "ANALOGDOWN"  : JOY1_ANALOG1_DOWN,
         "ANALOGLEFT"  : JOY1_ANALOG1_LEFT,
         "ANALOGRIGHT" : JOY1_ANALOG1_RIGHT,
         "ANALOGUP2"   : JOY1_ANALOG2_UP,
         "ANALOGDOWN2" : JOY1_ANALOG2_DOWN,
         "ANALOGLEFT2" : JOY1_ANALOG2_LEFT,
         "ANALOGRIGHT2": JOY1_ANALOG2_RIGHT]
    }

    static Map createDefaultKeyboardMapping1() {
        ["PUSH1"       : KEY_Z,
         "PUSH2"       : KEY_X,
         "PUSH3"       : KEY_C,
         "PUSH4"       : KEY_V,
         "PUSH5"       : KEY_A,
         "PUSH6"       : KEY_S,
         "PUSH7"       : KEY_D,
         "PUSH8"       : KEY_F,
         "SERVICE"     : KEY_9,
         "START"       : KEY_1,
         "COIN"        : KEY_5,
         "DIGITALUP"   : UP,
         "DIGITALDOWN" : DOWN,
         "DIGITALLEFT" : LEFT,
         "DIGITALRIGHT": RIGHT,
         "ANALOGUP"    : KEY_8_PAD,
         "ANALOGDOWN"  : KEY_2_PAD,
         "ANALOGLEFT"  : KEY_4_PAD,
         "ANALOGRIGHT" : KEY_6_PAD,
         "ANALOGUP2"   : KEY_I,
         "ANALOGDOWN2" : KEY_K,
         "ANALOGLEFT2" : KEY_J,
         "ANALOGRIGHT2": KEY_L]
    }

    static Map createDefaultKeyboardMapping2() {
        ["PUSH1"       : KEY_Q,
         "PUSH2"       : KEY_W,
         "PUSH3"       : KEY_E,
         "PUSH4"       : KEY_R,
         "PUSH5"       : KEY_3,
         "PUSH6"       : KEY_4,
         "PUSH7"       : NONE,
         "PUSH8"       : NONE,
         "SERVICE"     : NONE,
         "START"       : KEY_2,
         "COIN"        : KEY_6,
         "DIGITALUP"   : KEY_Y,
         "DIGITALDOWN" : KEY_T,
         "DIGITALLEFT" : KEY_G,
         "DIGITALRIGHT": KEY_H,
         "ANALOGUP"    : KEY_U,
         "ANALOGDOWN"  : KEY_M,
         "ANALOGLEFT"  : KEY_B,
         "ANALOGRIGHT" : KEY_N,
         "ANALOGUP2"   : NONE,
         "ANALOGDOWN2" : NONE,
         "ANALOGLEFT2" : NONE,
         "ANALOGRIGHT2": NONE]
    }


    static void reverseConfig(IniFile module) {
        // Dado un Demul.ini de RocketLauncher ya configurado, lo transforma en codigo
        // que, si se ejecutase, generaria la misma configuracion
        Map replaces = [
                JOY0_STARTCOIN : [
                        "START": JOY0_BACK,
                        "COIN" : JOY0_START],

                JOY1_STARTCOIN: [
                        "START": JOY1_BACK,
                        "COIN" : JOY1_START],

                JOY0_DRIVING   : [
                        "ANALOGUP"   : JOY0_RB,
                        "ANALOGDOWN" : JOY0_LB,
                        "ANALOGLEFT" : JOY0_ANALOG1_LEFT,
                        "ANALOGRIGHT": JOY0_ANALOG1_RIGHT],

                JOY0_ANALOG    : [
                        "ANALOGUP"   : JOY0_ANALOG1_UP,
                        "ANALOGDOWN" : JOY0_ANALOG1_DOWN,
                        "ANALOGLEFT" : JOY0_ANALOG1_LEFT,
                        "ANALOGRIGHT": JOY0_ANALOG1_RIGHT],

                JOY1_ANALOG    : [
                        "ANALOGUP"   : JOY1_ANALOG1_UP,
                        "ANALOGDOWN" : JOY1_ANALOG1_DOWN,
                        "ANALOGLEFT" : JOY1_ANALOG1_LEFT,
                        "ANALOGRIGHT": JOY1_ANALOG1_RIGHT],

                JOY0_ANALOG2   : [
                        "ANALOGUP2"   : JOY0_ANALOG2_UP,
                        "ANALOGDOWN2" : JOY0_ANALOG2_DOWN,
                        "ANALOGLEFT2" : JOY0_ANALOG2_LEFT,
                        "ANALOGRIGHT2": JOY0_ANALOG2_RIGHT],

                JOY1_ANALOG2   : [
                        "ANALOGUP2"   : JOY1_ANALOG2_UP,
                        "ANALOGDOWN2" : JOY1_ANALOG2_DOWN,
                        "ANALOGLEFT2" : JOY1_ANALOG2_LEFT,
                        "ANALOGRIGHT2": JOY1_ANALOG2_RIGHT],

                JOY0_DPAD      : [
                        "DIGITALUP"   : JOY0_UP,
                        "DIGITALDOWN" : JOY0_DOWN,
                        "DIGITALLEFT" : JOY0_LEFT,
                        "DIGITALRIGHT": JOY0_RIGHT],

                JOY1_DPAD      : [
                        "DIGITALUP"   : JOY1_UP,
                        "DIGITALDOWN" : JOY1_DOWN,
                        "DIGITALLEFT" : JOY1_LEFT,
                        "DIGITALRIGHT": JOY1_RIGHT]
        ]

        replaces.each { name, repl ->
            Map t = repl.inject([:]) { m, k, v -> m[k] = reverse[v]; m }
            println "Map ${name} = ${mapify(t)}"
        }

        module.getSections().each { sec, map ->
            boolean sected = false
            Map out = map.clone()
            map.each { k, v ->
                def keyName = reverse[v]
                if (keyName && keyName != "NONE") {
                    if (!sected) {
//                        println "[$sec]"
                        sected = true
                    }
//                    println "$k : ${keyName}"
                }
                out[k] = keyName
            }

            if (sected) {
                Set parts = []
                replaces.each { name, repl ->
                    boolean matches = true
                    repl.each { k, v ->
                        if (map[k] != v) {
                            matches = false
                        }
                    }
                    if (matches) {
                        repl.keySet().each { out.remove(it) }
                        parts << name
                    }
                }
                parts.each {
                    println "demul.putAll(\"${sec}\", ${it})"
                }
                [out.keySet()].flatten().each {
                    if (out[it] == "NONE") out.remove(it)
                }
                println "demul.putAll(\"${sec}\", ${mapify(out)})"
            }
        }
    }

    static String mapify(Map out) {
        return "[\n${out.entrySet().collect { e -> "    \"${e.key}\" : ${e.value}" }.join(",\n")}\n]"
    }

    static void set360(HyperSpin hs) {

        println "- Demul"
        IniFile module = new IniFile().parse(hs.newRocketLauncherFile("Modules\\Demul\\Demul (v0.7).ini"))
        println module.file.absolutePath
//        reverseConfig(module)

        set360PerGameConfiguration(module)
        createDefaultXboxMappingJoystick1().each { action, key ->
            module.put("standard_JAMMA0_0", action, key)
        }
        createDefaultXboxMappingJoystick2().each { action, key ->
            module.put("standard_JAMMA0_1", action, key)
        }
        module.store()

        IniFile demul = new IniFile(equals: " = ").parse(new File(hs.getDemulFolder(), "padDemul.ini"))
        println demul.file.absolutePath
        // acciones de XBOX para el jugador 1 Y 2
        createDefaultXboxMappingJoystick1().each { action, key ->
            demul.put("JAMMA0_0", action, key)
        }
        createDefaultXboxMappingJoystick2().each { action, key ->
            demul.put("JAMMA0_1", action, key)
        }
        // teclas para jugador 1 Y 2 (alternativoS)
        createDefaultKeyboardMapping1().each { action, key ->
            demul.put("JAMMA1_0", action, key)
        }
        createDefaultKeyboardMapping2().each { action, key ->
            demul.put("JAMMA1_1", action, key)
        }

        // SYSTEM BOARD
        demul.put("GLOBAL0", "TEST", F9)
        demul.put("GLOBAL0", "SERVICE", F1)
        // GLOBAL/HOTKEYS
        demul.put("GLOBAL0", "SAVESTATE", F2)
        demul.put("GLOBAL0", "LOADSTATE", F4)
        demul.put("GLOBAL0", "NEXTSTATE", F5)
        demul.put("GLOBAL0", "PREVSTATE", F6)
        demul.put("GLOBAL0", "TEST2", F10) // JVS TEST
        demul.put("GLOBAL0", "DEADZONE", NONE)

        demul.store()
    }

    static void set360PerGameConfiguration(IniFile demul) {
            Map JOY0_STARTCOIN = [
                    "START": JOY0_BACK,
                    "COIN" : JOY0_START
            ]
            Map JOY1_STARTCOIN = [
                    "START": JOY1_BACK,
                    "COIN" : JOY1_START
            ]
            Map JOY0_DRIVING = [
                    "ANALOGUP"   : JOY0_RB,
                    "ANALOGDOWN" : JOY0_LB,
                    "ANALOGLEFT" : JOY0_ANALOG1_LEFT,
                    "ANALOGRIGHT": JOY0_ANALOG1_RIGHT
            ]
            Map JOY0_ANALOG = [
                    "ANALOGUP"   : JOY0_ANALOG1_UP,
                    "ANALOGDOWN" : JOY0_ANALOG1_DOWN,
                    "ANALOGLEFT" : JOY0_ANALOG1_LEFT,
                    "ANALOGRIGHT": JOY0_ANALOG1_RIGHT
            ]
            Map JOY1_ANALOG = [
                    "ANALOGUP"   : JOY1_ANALOG1_UP,
                    "ANALOGDOWN" : JOY1_ANALOG1_DOWN,
                    "ANALOGLEFT" : JOY1_ANALOG1_LEFT,
                    "ANALOGRIGHT": JOY1_ANALOG1_RIGHT
            ]
            Map JOY0_ANALOG2 = [
                    "ANALOGUP2"   : JOY0_ANALOG2_UP,
                    "ANALOGDOWN2" : JOY0_ANALOG2_DOWN,
                    "ANALOGLEFT2" : JOY0_ANALOG2_LEFT,
                    "ANALOGRIGHT2": JOY0_ANALOG2_RIGHT
            ]
            Map JOY1_ANALOG2 = [
                    "ANALOGUP2"   : JOY1_ANALOG2_UP,
                    "ANALOGDOWN2" : JOY1_ANALOG2_DOWN,
                    "ANALOGLEFT2" : JOY1_ANALOG2_LEFT,
                    "ANALOGRIGHT2": JOY1_ANALOG2_RIGHT
            ]
            Map JOY0_DPAD = [
                    "DIGITALUP"   : JOY0_UP,
                    "DIGITALDOWN" : JOY0_DOWN,
                    "DIGITALLEFT" : JOY0_LEFT,
                    "DIGITALRIGHT": JOY0_RIGHT
            ]
            Map JOY1_DPAD = [
                    "DIGITALUP"   : JOY1_UP,
                    "DIGITALDOWN" : JOY1_DOWN,
                    "DIGITALLEFT" : JOY1_LEFT,
                    "DIGITALRIGHT": JOY1_RIGHT
            ]
            demul.putAll("standard_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("standard_JAMMA0_0", JOY0_ANALOG)
            demul.putAll("standard_JAMMA0_0", JOY0_ANALOG2)
            demul.putAll("standard_JAMMA0_0", JOY0_DPAD)
            demul.putAll("standard_JAMMA0_0", [
                    "PUSH1": JOY0_A,
                    "PUSH2": JOY0_B,
                    "PUSH3": JOY0_X,
                    "PUSH4": JOY0_Y,
                    "PUSH5": JOY0_LT,
                    "PUSH6": JOY0_RT,
                    "PUSH7": JOY0_LB,
                    "PUSH8": JOY0_RB
            ])
            demul.putAll("standard_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("standard_JAMMA0_1", JOY1_ANALOG)
            demul.putAll("standard_JAMMA0_1", JOY1_ANALOG2)
            demul.putAll("standard_JAMMA0_1", JOY1_DPAD)
            demul.putAll("standard_JAMMA0_1", [
                    "PUSH1": JOY1_A,
                    "PUSH2": JOY1_B,
                    "PUSH3": JOY1_X,
                    "PUSH4": JOY1_Y,
                    "PUSH5": JOY1_LT,
                    "PUSH6": JOY1_RT,
                    "PUSH7": JOY1_LB,
                    "PUSH8": JOY1_RB
            ])
            demul.putAll("kofxi_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("kofxi_JAMMA0_0", JOY0_DPAD)
            demul.putAll("kofxi_JAMMA0_0", [
                    "PUSH1": JOY0_X,
                    "PUSH2": JOY0_Y,
                    "PUSH3": JOY0_RT,
                    "PUSH4": JOY0_A,
                    "PUSH5": JOY0_B
            ])
            demul.putAll("kofxi_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("kofxi_JAMMA0_1", JOY1_DPAD)
            demul.putAll("kofxi_JAMMA0_1", [
                    "PUSH1": JOY1_X,
                    "PUSH2": JOY1_Y,
                    "PUSH3": JOY1_RT,
                    "PUSH4": JOY1_A,
                    "PUSH5": JOY1_B
            ])
            demul.putAll("mslug6_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("mslug6_JAMMA0_0", JOY0_DPAD)
            demul.putAll("mslug6_JAMMA0_0", [
                    "PUSH1": JOY0_A,
                    "PUSH2": JOY0_B,
                    "PUSH3": JOY0_X,
                    "PUSH5": JOY0_Y
            ])
            demul.putAll("mslug6_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("mslug6_JAMMA0_1", JOY1_DPAD)
            demul.putAll("mslug6_JAMMA0_1", [
                    "PUSH1": JOY1_A,
                    "PUSH2": JOY1_B,
                    "PUSH3": JOY1_X,
                    "PUSH5": JOY1_Y
            ])
            demul.putAll("samsptk_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("samsptk_JAMMA0_0", JOY0_DPAD)
            demul.putAll("samsptk_JAMMA0_0", [
                    "PUSH1": JOY0_X,
                    "PUSH2": JOY0_Y,
                    "PUSH3": JOY0_B,
                    "PUSH4": JOY0_A,
                    "PUSH5": JOY0_RT
            ])
            demul.putAll("samsptk_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("samsptk_JAMMA0_1", JOY1_DPAD)
            demul.putAll("samsptk_JAMMA0_1", [
                    "PUSH1": JOY1_X,
                    "PUSH2": JOY1_Y,
                    "PUSH3": JOY1_B,
                    "PUSH4": JOY1_A,
                    "PUSH5": JOY1_RT
            ])
            demul.putAll("ggx15_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("ggx15_JAMMA0_0", JOY0_DPAD)
            demul.putAll("ggx15_JAMMA0_0", [
                    "PUSH1": JOY0_A,
                    "PUSH2": JOY0_Y,
                    "PUSH3": JOY0_B,
                    "PUSH4": JOY0_X,
                    "PUSH5": JOY0_RT
            ])
            demul.putAll("ggx15_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("ggx15_JAMMA0_1", JOY1_DPAD)
            demul.putAll("ggx15_JAMMA0_1", [
                    "PUSH1": JOY1_A,
                    "PUSH2": JOY1_Y,
                    "PUSH3": JOY1_B,
                    "PUSH4": JOY1_X,
                    "PUSH5": JOY1_RT
            ])
            demul.putAll("demofist_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("demofist_JAMMA0_0", JOY0_DPAD)
            demul.putAll("demofist_JAMMA0_0", [
                    "PUSH1": JOY0_X,
                    "PUSH2": JOY0_A,
                    "PUSH3": JOY0_B
            ])
            demul.putAll("demofist_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("demofist_JAMMA0_1", JOY1_DPAD)
            demul.putAll("demofist_JAMMA0_1", [
                    "PUSH1": JOY1_X,
                    "PUSH2": JOY1_A,
                    "PUSH3": JOY1_B
            ])
            demul.putAll("ftspeed_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("ftspeed_JAMMA0_0", JOY0_DRIVING)
            demul.putAll("ftspeed_JAMMA0_0", [
                    "PUSH1": JOY0_Y,
                    "PUSH2": JOY0_X,
                    "PUSH3": JOY0_A
            ])
            demul.putAll("ftspeed_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("anmlbskt_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("anmlbskt_JAMMA0_0", [
                    "PUSH1": JOY0_X,
                    "PUSH2": JOY0_A,
                    "PUSH3": JOY0_B
            ])
            demul.putAll("anmlbskt_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("anmlbskt_JAMMA0_1", [
                    "PUSH1": JOY1_B,
                    "PUSH2": JOY1_A,
                    "PUSH3": JOY1_X
            ])
            demul.putAll("xtrmhunt_JAMMA0_0", [
                    "START": KEY_1,
                    "COIN" : KEY_5
            ])
            demul.putAll("xtrmhunt_JAMMA0_1", [
                    "START": KEY_2,
                    "COIN" : KEY_6
            ])
            demul.putAll("salmankt_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("salmankt_JAMMA0_0", JOY0_DPAD)
            demul.putAll("salmankt_JAMMA0_0", [
                    "PUSH1": JOY0_A
            ])
            demul.putAll("18wheelr_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("18wheelr_JAMMA0_0", JOY0_DRIVING)
            demul.putAll("18wheelr_JAMMA0_0", [
                    "PUSH1": JOY0_A,
                    "PUSH2": JOY0_B,
                    "PUSH3": JOY0_B,
                    "PUSH4": JOY0_Y,
                    "PUSH5": JOY0_X
            ])
            demul.putAll("crzytaxi_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("crzytaxi_JAMMA0_0", JOY0_DRIVING)
            demul.putAll("crzytaxi_JAMMA0_0", [
                    "PUSH1": JOY0_B,
                    "PUSH2": JOY0_A
            ])
            demul.putAll("capsnk_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("capsnk_JAMMA0_0", JOY0_DPAD)
            demul.putAll("capsnk_JAMMA0_0", [
                    "PUSH1": JOY0_X,
                    "PUSH2": JOY0_Y,
                    "PUSH3": JOY0_RT,
                    "PUSH4": JOY0_A,
                    "PUSH5": JOY0_B,
                    "PUSH6": JOY0_RB
            ])
            demul.putAll("capsnk_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("capsnk_JAMMA0_1", JOY1_DPAD)
            demul.putAll("capsnk_JAMMA0_1", [
                    "PUSH1": JOY1_X,
                    "PUSH2": JOY1_Y,
                    "PUSH3": JOY1_RT,
                    "PUSH4": JOY1_A,
                    "PUSH5": JOY1_B,
                    "PUSH6": JOY1_RB
            ])
            demul.putAll("ggxx_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("ggxx_JAMMA0_0", JOY0_DPAD)
            demul.putAll("ggxx_JAMMA0_0", [
                    "PUSH1": JOY0_A,
                    "PUSH2": JOY0_Y,
                    "PUSH3": JOY0_B,
                    "PUSH4": JOY0_X,
                    "PUSH5": JOY0_RT
            ])
            demul.putAll("ggxx_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("ggxx_JAMMA0_1", JOY1_DPAD)
            demul.putAll("ggxx_JAMMA0_1", [
                    "PUSH1": JOY1_A,
                    "PUSH2": JOY1_Y,
                    "PUSH3": JOY1_B,
                    "PUSH4": JOY1_X,
                    "PUSH5": JOY1_RT
            ])
            demul.putAll("ggx_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("ggx_JAMMA0_0", JOY0_DPAD)
            demul.putAll("ggx_JAMMA0_0", [
                    "PUSH1": JOY0_X,
                    "PUSH2": JOY0_A,
                    "PUSH3": JOY0_Y,
                    "PUSH4": JOY0_B
            ])
            demul.putAll("ggx_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("ggx_JAMMA0_1", JOY1_DPAD)
            demul.putAll("ggx_JAMMA0_1", [
                    "PUSH1": JOY1_X,
                    "PUSH2": JOY1_A,
                    "PUSH3": JOY1_Y,
                    "PUSH4": JOY1_B
            ])
            demul.putAll("cspike_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("cspike_JAMMA0_0", JOY0_DPAD)
            demul.putAll("cspike_JAMMA0_0", [
                    "PUSH1": JOY0_A,
                    "PUSH2": JOY0_B,
                    "PUSH3": JOY0_RB
            ])
            demul.putAll("cspike_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("cspike_JAMMA0_1", JOY1_DPAD)
            demul.putAll("cspike_JAMMA0_1", [
                    "PUSH1": JOY1_A,
                    "PUSH2": JOY1_B,
                    "PUSH3": JOY1_RB
            ])
            demul.putAll("pstone_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("pstone_JAMMA0_0", JOY0_DPAD)
            demul.putAll("pstone_JAMMA0_0", [
                    "PUSH1": JOY0_X,
                    "PUSH2": JOY0_Y,
                    "PUSH3": JOY0_A
            ])
            demul.putAll("pstone_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("pstone_JAMMA0_1", JOY1_DPAD)
            demul.putAll("pstone_JAMMA0_1", [
                    "PUSH1": JOY1_X,
                    "PUSH2": JOY1_Y,
                    "PUSH3": JOY1_A
            ])
            demul.putAll("pstone2_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("pstone2_JAMMA0_0", JOY0_DPAD)
            demul.putAll("pstone2_JAMMA0_0", [
                    "PUSH1": JOY0_X,
                    "PUSH2": JOY0_A,
                    "PUSH3": JOY0_Y
            ])
            demul.putAll("pstone2_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("pstone2_JAMMA0_1", JOY1_DPAD)
            demul.putAll("pstone2_JAMMA0_1", [
                    "PUSH1": JOY1_X,
                    "PUSH2": JOY1_A,
                    "PUSH3": JOY1_Y
            ])
            demul.putAll("initd_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("initd_JAMMA0_0", JOY0_DRIVING)
            demul.putAll("initd_JAMMA0_0", [
                    "PUSH1": JOY0_B,
                    "PUSH2": JOY0_A,
                    "PUSH3": JOY0_Y
            ])
            demul.putAll("doa2_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("doa2_JAMMA0_0", JOY0_DPAD)
            demul.putAll("doa2_JAMMA0_0", [
                    "PUSH1": JOY0_A,
                    "PUSH2": JOY0_X,
                    "PUSH3": JOY0_Y
            ])
            demul.putAll("doa2_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("doa2_JAMMA0_1", JOY1_DPAD)
            demul.putAll("doa2_JAMMA0_1", [
                    "PUSH1": JOY1_A,
                    "PUSH2": JOY1_X,
                    "PUSH3": JOY1_Y
            ])
            demul.putAll("hotd2_JAMMA0_0", [
                    "START": KEY_1,
                    "COIN" : KEY_5
            ])
            demul.putAll("hotd2_JAMMA0_1", [
                    "START": KEY_2,
                    "COIN" : KEY_6
            ])
            demul.putAll("monkeyba_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("monkeyba_JAMMA0_0", JOY0_ANALOG)
            demul.putAll("alienfnt_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("alienfnt_JAMMA0_0", JOY0_ANALOG)
            demul.putAll("alienfnt_JAMMA0_0", [
                    "PUSH1": JOY0_B,
                    "PUSH2": JOY0_A,
                    "PUSH3": JOY0_LB,
                    "PUSH4": JOY0_RB
            ])
            demul.putAll("samba_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("samba_JAMMA0_0", JOY0_ANALOG)
            demul.putAll("samba_JAMMA0_0", JOY0_ANALOG2)
            demul.putAll("samba_JAMMA0_0", [
                    "PUSH1": JOY0_LB,
                    "PUSH2": JOY0_RB
            ])
            demul.putAll("samba_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("samba_JAMMA0_1", JOY1_ANALOG)
            demul.putAll("samba_JAMMA0_1", JOY1_ANALOG2)
            demul.putAll("samba_JAMMA0_1", [
                    "PUSH1": JOY1_LB,
                    "PUSH2": JOY1_RB
            ])
            demul.putAll("alpiltdx_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("alpiltdx_JAMMA0_0", JOY0_ANALOG)
            demul.putAll("alpiltdx_JAMMA0_0", [
                    "PUSH1"       : JOY0_A,
                    "PUSH2"       : JOY0_B,
                    "PUSH3"       : JOY0_Y,
                    "ANALOGUP2"   : JOY0_ANALOG2_RIGHT,
                    "ANALOGDOWN2" : JOY0_ANALOG2_LEFT,
                    "ANALOGLEFT2" : JOY0_LB,
                    "ANALOGRIGHT2": JOY0_RB
            ])
            demul.putAll("ausfache_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("ausfache_JAMMA0_0", JOY0_DPAD)
            demul.putAll("ausfache_JAMMA0_0", [
                    "PUSH1": JOY0_X,
                    "PUSH2": JOY0_A,
                    "PUSH3": JOY0_B,
                    "PUSH4": JOY0_RT
            ])
            demul.putAll("ausfache_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("ausfache_JAMMA0_1", JOY1_DPAD)
            demul.putAll("ausfache_JAMMA0_1", [
                    "PUSH1": JOY1_X,
                    "PUSH2": JOY1_A,
                    "PUSH3": JOY1_B
            ])
            demul.putAll("gunsur2_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("gunsur2_JAMMA0_0", JOY0_ANALOG)
            demul.putAll("gunsur2_JAMMA0_0", [
                    "PUSH1"       : JOY0_A,
                    "PUSH2"       : JOY0_RB,
                    "DIGITALUP"   : JOY0_UP,
                    "DIGITALDOWN" : JOY0_DOWN,
                    "ANALOGLEFT2" : JOY0_ANALOG2_LEFT,
                    "ANALOGRIGHT2": JOY0_ANALOG2_RIGHT
            ])
            demul.putAll("gwing2_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("gwing2_JAMMA0_0", JOY0_DPAD)
            demul.putAll("gwing2_JAMMA0_0", [
                    "PUSH1": JOY0_A,
                    "PUSH2": JOY0_B,
                    "PUSH3": JOY0_X
            ])
            demul.putAll("gwing2_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("gwing2_JAMMA0_1", JOY1_DPAD)
            demul.putAll("gwing2_JAMMA0_1", [
                    "PUSH1": JOY1_A,
                    "PUSH2": JOY1_B,
                    "PUSH3": JOY1_X
            ])
            demul.putAll("senkosp_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("senkosp_JAMMA0_0", JOY0_DPAD)
            demul.putAll("senkosp_JAMMA0_0", [
                    "PUSH1": JOY0_X,
                    "PUSH2": JOY0_Y,
                    "PUSH3": JOY0_B,
                    "PUSH4": JOY0_A,
                    "PUSH5": JOY0_LT
            ])
            demul.putAll("senkosp_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("senkosp_JAMMA0_1", JOY1_DPAD)
            demul.putAll("senkosp_JAMMA0_1", [
                    "PUSH1": JOY1_X,
                    "PUSH2": JOY1_Y,
                    "PUSH3": JOY1_B,
                    "PUSH4": JOY1_A,
                    "PUSH5": JOY1_LT
            ])
            demul.putAll("senko_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("senko_JAMMA0_0", JOY0_DPAD)
            demul.putAll("senko_JAMMA0_0", [
                    "PUSH1": JOY0_A,
                    "PUSH2": JOY0_X,
                    "PUSH3": JOY0_Y
            ])
            demul.putAll("senko_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("senko_JAMMA0_1", JOY1_DPAD)
            demul.putAll("senko_JAMMA0_1", [
                    "PUSH1": JOY1_A,
                    "PUSH2": JOY1_X,
                    "PUSH3": JOY1_Y
            ])
            demul.putAll("zombrvn_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("zombrvn_JAMMA0_0", JOY0_ANALOG)
            demul.putAll("zombrvn_JAMMA0_0", [
                    "PUSH1": JOY0_Y,
                    "PUSH2": JOY0_B,
                    "PUSH3": JOY0_X
            ])
            demul.putAll("zombrvn_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("zombrvn_JAMMA0_1", JOY1_ANALOG)
            demul.putAll("zombrvn_JAMMA0_1", [
                    "PUSH1": JOY1_Y,
                    "PUSH2": JOY1_B,
                    "PUSH3": JOY1_X
            ])
            demul.putAll("vtennis_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("vtennis_JAMMA0_0", JOY0_ANALOG)
            demul.putAll("vtennis_JAMMA0_0", JOY0_DPAD)
            demul.putAll("vtennis_JAMMA0_0", [
                    "PUSH1"      : JOY0_A,
                    "PUSH2"      : JOY0_B,
                    "ANALOGUP2"  : JOY0_ANALOG2_UP,
                    "ANALOGDOWN2": JOY0_ANALOG2_DOWN
            ])
            demul.putAll("vtennis_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("vtennis_JAMMA0_1", JOY1_ANALOG)
            demul.putAll("vtennis_JAMMA0_1", JOY1_DPAD)
            demul.putAll("vtennis_JAMMA0_1", [
                    "PUSH1"      : JOY1_A,
                    "PUSH2"      : JOY1_B,
                    "ANALOGUP2"  : JOY1_ANALOG2_UP,
                    "ANALOGDOWN2": JOY1_ANALOG2_DOWN
            ])
            demul.putAll("vstrik3_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("vstrik3_JAMMA0_0", JOY0_DPAD)
            demul.putAll("vstrik3_JAMMA0_0", [
                    "PUSH1": JOY0_X,
                    "PUSH2": JOY0_B,
                    "PUSH3": JOY0_A
            ])
            demul.putAll("vstrik3_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("vstrik3_JAMMA0_1", JOY1_DPAD)
            demul.putAll("vstrik3_JAMMA0_1", [
                    "PUSH1": JOY1_X,
                    "PUSH2": JOY1_B,
                    "PUSH3": JOY1_A
            ])
            demul.putAll("sstrkfgt_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("sstrkfgt_JAMMA0_0", JOY0_ANALOG)
            demul.putAll("sstrkfgt_JAMMA0_0", [
                    "PUSH1"    : JOY0_RB,
                    "PUSH2"    : JOY0_RT,
                    "PUSH3"    : JOY0_LT,
                    "PUSH4"    : JOY0_Y,
                    "ANALOGUP2": JOY0_LB
            ])
            demul.putAll("wldrider_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("wldrider_JAMMA0_0", JOY0_DRIVING)
            demul.putAll("wldrider_JAMMA0_0", [
                    "PUSH1"      : JOY0_ANALOG1_UP,
                    "PUSH2"      : JOY0_ANALOG1_DOWN,
                    "ANALOGDOWN2": JOY0_A
            ])
            demul.putAll("meltyb_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("meltyb_JAMMA0_0", JOY0_DPAD)
            demul.putAll("meltyb_JAMMA0_0", [
                    "PUSH1": JOY0_X,
                    "PUSH2": JOY0_A,
                    "PUSH3": JOY0_B,
                    "PUSH4": JOY0_Y,
                    "PUSH5": JOY0_RT
            ])
            demul.putAll("meltyb_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("meltyb_JAMMA0_1", JOY1_DPAD)
            demul.putAll("meltyb_JAMMA0_1", [
                    "PUSH1": JOY1_X,
                    "PUSH2": JOY1_A,
                    "PUSH3": JOY1_B,
                    "PUSH4": JOY1_Y,
                    "PUSH5": JOY1_RT
            ])
            demul.putAll("clubkrte_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("clubkrte_JAMMA0_0", JOY0_DRIVING)
            demul.putAll("clubkrte_JAMMA0_0", [
                    "PUSH1": JOY0_Y
            ])
            demul.putAll("otrigger_JAMMA0_0", [
                    "START"       : KEY_1,
                    "COIN"        : KEY_5,
                    "DIGITALUP"   : KEY_W,
                    "DIGITALDOWN" : KEY_S,
                    "DIGITALLEFT" : KEY_A,
                    "DIGITALRIGHT": KEY_D,
                    "ANALOGLEFT"  : LEFT,
                    "ANALOGRIGHT" : RIGHT
            ])
            demul.putAll("ss2005_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("ss2005_JAMMA0_0", JOY0_DPAD)
            demul.putAll("ss2005_JAMMA0_0", [
                    "PUSH1": JOY0_A,
                    "PUSH2": JOY0_B,
                    "PUSH3": JOY0_Y
            ])
            demul.putAll("ss2005_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("ss2005_JAMMA0_1", JOY1_DPAD)
            demul.putAll("ss2005_JAMMA0_1", [
                    "PUSH1": JOY1_A,
                    "PUSH2": JOY1_B,
                    "PUSH3": JOY1_Y
            ])
            demul.putAll("usagiym_JAMMA0_0", [
                    "COIN": KEY_5
            ])
            demul.putAll("wrungp_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("wrungp_JAMMA0_0", [
                    "PUSH1"       : JOY0_Y,
                    "ANALOGUP"    : JOY0_A,
                    "ANALOGLEFT"  : JOY0_ANALOG1_LEFT,
                    "ANALOGRIGHT" : JOY0_ANALOG1_RIGHT,
                    "ANALOGUP2"   : JOY0_ANALOG1_UP,
                    "ANALOGDOWN2" : JOY0_ANALOG1_DOWN,
                    "ANALOGLEFT2" : JOY0_LB,
                    "ANALOGRIGHT2": JOY0_RB
            ])
            demul.putAll("vonot_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("vonot_JAMMA0_0", [
                    "PUSH1"       : JOY0_LB,
                    "PUSH2"       : JOY0_B,
                    "PUSH3"       : JOY0_A,
                    "DIGITALUP"   : JOY0_ANALOG1_UP,
                    "DIGITALDOWN" : JOY0_ANALOG1_DOWN,
                    "DIGITALLEFT" : JOY0_ANALOG1_LEFT,
                    "DIGITALRIGHT": JOY0_ANALOG1_RIGHT
            ])
            demul.putAll("vonot_JAMMA0_1", [
                    "PUSH1"       : JOY0_RB,
                    "PUSH2"       : JOY0_B,
                    "DIGITALUP"   : JOY0_ANALOG2_UP,
                    "DIGITALDOWN" : JOY0_ANALOG2_DOWN,
                    "DIGITALLEFT" : JOY0_ANALOG2_LEFT,
                    "DIGITALRIGHT": JOY0_ANALOG2_RIGHT
            ])
            demul.putAll("gundmct_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("gundmct_JAMMA0_0", JOY0_DPAD)
            demul.putAll("gundmct_JAMMA0_0", [
                    "PUSH1": JOY0_X,
                    "PUSH2": JOY0_Y,
                    "PUSH3": JOY0_A,
                    "PUSH4": JOY0_B
            ])
            demul.putAll("shaktamb_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("shaktamb_JAMMA0_0", JOY0_ANALOG)
            demul.putAll("shaktamb_JAMMA0_0", [
                    "PUSH1"      : JOY0_A,
                    "PUSH2"      : JOY0_LB,
                    "PUSH3"      : JOY0_RB,
                    "DIGITALUP"  : JOY0_UP,
                    "DIGITALDOWN": JOY0_DOWN
            ])
            demul.putAll("shaktamb_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("shaktamb_JAMMA0_1", JOY1_ANALOG)
            demul.putAll("shaktamb_JAMMA0_1", [
                    "PUSH1"      : JOY1_A,
                    "PUSH2"      : JOY1_LB,
                    "PUSH3"      : JOY1_RB,
                    "DIGITALUP"  : JOY1_UP,
                    "DIGITALDOWN": JOY1_DOWN
            ])
            demul.putAll("jambo_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("jambo_JAMMA0_0", JOY0_DRIVING)
            demul.putAll("jambo_JAMMA0_0", [
                    "PUSH1": JOY0_ANALOG2_DOWN,
                    "PUSH2": JOY0_ANALOG2_UP
            ])
            demul.putAll("quizqgd_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("quizqgd_JAMMA0_0", JOY0_DPAD)
            demul.putAll("quizqgd_JAMMA0_0", [
                    "PUSH1": JOY0_X,
                    "PUSH2": JOY0_A,
                    "PUSH3": JOY0_B,
                    "PUSH4": JOY0_Y
            ])
            demul.putAll("quizqgd_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("quizqgd_JAMMA0_1", JOY1_DPAD)
            demul.putAll("quizqgd_JAMMA0_1", [
                    "PUSH1": JOY1_X,
                    "PUSH2": JOY1_A,
                    "PUSH3": JOY1_B,
                    "PUSH4": JOY1_Y
            ])
            demul.putAll("ringout_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("ringout_JAMMA0_0", [
                    "PUSH1"      : JOY0_A,
                    "PUSH2"      : JOY0_LB,
                    "ANALOGUP"   : JOY0_RB,
                    "ANALOGLEFT" : JOY0_ANALOG1_LEFT,
                    "ANALOGRIGHT": JOY0_ANALOG1_RIGHT
            ])
            demul.putAll("ringout_JAMMA0_1", JOY1_STARTCOIN)
            demul.putAll("ringout_JAMMA0_1", [
                    "PUSH1"      : JOY1_A,
                    "PUSH2"      : JOY1_LB,
                    "ANALOGUP"   : JOY1_RB,
                    "ANALOGLEFT" : JOY1_ANALOG1_LEFT,
                    "ANALOGRIGHT": JOY1_ANALOG1_RIGHT
            ])
            demul.putAll("soulsurf_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("soulsurf_JAMMA0_0", [
                    "DIGITALLEFT" : JOY0_LEFT,
                    "DIGITALRIGHT": JOY0_RIGHT,
                    "ANALOGDOWN"  : JOY0_A,
                    "ANALOGLEFT"  : JOY0_ANALOG1_LEFT,
                    "ANALOGRIGHT" : JOY0_ANALOG1_RIGHT,
                    "ANALOGLEFT2" : JOY0_LB,
                    "ANALOGRIGHT2": JOY0_RB
            ])
            demul.putAll("kingrt66_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("kingrt66_JAMMA0_0", JOY0_DRIVING)
            demul.putAll("kingrt66_JAMMA0_0", [
                    "PUSH1": JOY0_A,
                    "PUSH2": JOY0_B,
                    "PUSH3": JOY0_RT,
                    "PUSH4": JOY0_Y,
                    "PUSH5": JOY0_X,
                    "PUSH6": JOY0_LT
            ])
            demul.putAll("smshdrv_JAMMA0_0", [
                    "PUSH1"      : JOY0_RB,
                    "PUSH2"      : JOY0_LB,
                    "PUSH3"      : JOY0_A,
                    "COIN"       : JOY0_START,
                    "ANALOGLEFT" : JOY0_ANALOG1_LEFT,
                    "ANALOGRIGHT": JOY0_ANALOG1_RIGHT
            ])
            demul.putAll("atvtrack_JAMMA0_0", JOY0_STARTCOIN)
            demul.putAll("atvtrack_JAMMA0_0", [
                    "DIGITALLEFT" : JOY0_LB,
                    "DIGITALRIGHT": JOY0_RB,
                    "ANALOGUP"    : JOY0_A,
                    "ANALOGLEFT"  : JOY0_ANALOG1_LEFT,
                    "ANALOGRIGHT" : JOY0_ANALOG1_RIGHT
            ])
        demul.store()
    }
}
