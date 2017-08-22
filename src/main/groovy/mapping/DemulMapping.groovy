package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 28-Jul-17.
 */
class DemulMapping {

    static String player1 = "_JAMMA0_0"
    static String player2 = "_JAMMA0_1"

    static String NONE = "0"
    static String JOY1_A = "805306380"
    static String JOY1_B = "805306381"
    static String JOY1_X = "805306382"
    static String JOY1_Y = "805306383"
    static String JOY1_LB = "805306376"
    static String JOY1_RB = "805306377"
    static String JOY1_LT_ANALOG = "1342177280"
    static String JOY1_RT_ANALOG = "1342177536"
    static String JOY1_BACK = "805306372"
    static String JOY1_START = "805306373"
    static String JOY1_UP = "805306368"
    static String JOY1_DOWN = "805306369"
    static String JOY1_LEFT = "805306370"
    static String JOY1_RIGHT = "805306371"
    static String JOY1_ANALOG1_UP = "-1879047680"
    static String JOY1_ANALOG1_DOWN = "-1879047424"
    static String JOY1_ANALOG1_LEFT = "-1879048192"
    static String JOY1_ANALOG1_RIGHT = "-1879047936"
    static String JOY1_ANALOG2_UP = "-1879046656"
    static String JOY1_ANALOG2_DOWN = "-1879046400"
    static String JOY1_ANALOG2_LEFT = "-1879047168"
    static String JOY1_ANALOG2_RIGHT = "-1879046912"
    static String JOY2_A = "805371916"
    static String JOY2_B = "805371917"
    static String JOY2_X = "805371918"
    static String JOY2_Y = "805371919"
    static String JOY2_LB = "805371912"
    static String JOY2_RB = "805371913"
    static String JOY2_LT_ANALOG = "1342242816"
    static String JOY2_RT_ANALOG = "1342243072"
    static String JOY2_BACK = "805371908"
    static String JOY2_START = "805371909"
    static String JOY2_UP = "805371904"
    static String JOY2_DOWN = "805371905"
    static String JOY2_LEFT = "805371906"
    static String JOY2_RIGHT = "805371907"
    static String JOY2_ANALOG1_UP = "-1878982144"
    static String JOY2_ANALOG1_DOWN = "-1878981888"
    static String JOY2_ANALOG1_LEFT = "-1878982656"
    static String JOY2_ANALOG1_RIGHT = "-1878982400"
    static String JOY2_ANALOG2_UP = "-1878981120"
    static String JOY2_ANALOG2_DOWN = "-1878980864"
    static String JOY2_ANALOG2_LEFT = "-1878981632"
    static String JOY2_ANALOG2_RIGHT = "-1878981376"
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
            "805306380"  : "JOY1_A",
            "805306381"  : "JOY1_B",
            "805306382"  : "JOY1_X",
            "805306383"  : "JOY1_Y",
            "805306376"  : "JOY1_LB",
            "805306377"  : "JOY1_RB",
            "1342177280" : "JOY1_LT_ANALOG",
            "1342177536" : "JOY1_RT_ANALOG",
            "805306372"  : "JOY1_BACK",
            "805306373"  : "JOY1_START",
            "805306368"  : "JOY1_UP",
            "805306369"  : "JOY1_DOWN",
            "805306370"  : "JOY1_LEFT",
            "805306371"  : "JOY1_RIGHT",
            "-1879047680": "JOY1_ANALOG1_UP",
            "-1879047424": "JOY1_ANALOG1_DOWN",
            "-1879048192": "JOY1_ANALOG1_LEFT",
            "-1879047936": "JOY1_ANALOG1_RIGHT",
            "-1879046656": "JOY1_ANALOG2_UP",
            "-1879046400": "JOY1_ANALOG2_DOWN",
            "-1879047168": "JOY1_ANALOG2_LEFT",
            "-1879046912": "JOY1_ANALOG2_RIGHT",
            "805371916"  : "JOY2_A",
            "805371917"  : "JOY2_B",
            "805371918"  : "JOY2_X",
            "805371919"  : "JOY2_Y",
            "805371912"  : "JOY2_LB",
            "805371913"  : "JOY2_RB",
            "1342242816" : "JOY2_LT_ANALOG",
            "1342243072" : "JOY2_RT_ANALOG",
            "805371908"  : "JOY2_BACK",
            "805371909"  : "JOY2_START",
            "805371904"  : "JOY2_UP",
            "805371905"  : "JOY2_DOWN",
            "805371906"  : "JOY2_LEFT",
            "805371907"  : "JOY2_RIGHT",
            "-1878982144": "JOY2_ANALOG1_UP",
            "-1878981888": "JOY2_ANALOG1_DOWN",
            "-1878982656": "JOY2_ANALOG1_LEFT",
            "-1878982400": "JOY2_ANALOG1_RIGHT",
            "-1878981120": "JOY2_ANALOG2_UP",
            "-1878980864": "JOY2_ANALOG2_DOWN",
            "-1878981632": "JOY2_ANALOG2_LEFT",
            "-1878981376": "JOY2_ANALOG2_RIGHT",
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


    static void reverseConfig(IniFile module) {
        // Dado un Demul.ini de RocketLauncher ya configurado, lo transforma en codigo
        // que, si se ejecutase, generaria la misma configuracion
        Map replaces = [
                JOY1_STARTCOIN: [
                        "START": JOY1_BACK,
                        "COIN" : JOY1_START],

                JOY2_STARTCOIN: [
                        "START": JOY2_BACK,
                        "COIN" : JOY2_START],

                JOY1_DRIVING  : [
                        "ANALOGUP"   : JOY1_RT_ANALOG,
                        "ANALOGDOWN" : JOY1_LT_ANALOG,
                        "ANALOGLEFT" : JOY1_ANALOG1_LEFT,
                        "ANALOGRIGHT": JOY1_ANALOG1_RIGHT],

                JOY1_ANALOG   : [
                        "ANALOGUP"   : JOY1_ANALOG1_UP,
                        "ANALOGDOWN" : JOY1_ANALOG1_DOWN,
                        "ANALOGLEFT" : JOY1_ANALOG1_LEFT,
                        "ANALOGRIGHT": JOY1_ANALOG1_RIGHT],

                JOY2_ANALOG   : [
                        "ANALOGUP"   : JOY2_ANALOG1_UP,
                        "ANALOGDOWN" : JOY2_ANALOG1_DOWN,
                        "ANALOGLEFT" : JOY2_ANALOG1_LEFT,
                        "ANALOGRIGHT": JOY2_ANALOG1_RIGHT],

                JOY1_ANALOG2  : [
                        "ANALOGUP2"   : JOY1_ANALOG2_UP,
                        "ANALOGDOWN2" : JOY1_ANALOG2_DOWN,
                        "ANALOGLEFT2" : JOY1_ANALOG2_LEFT,
                        "ANALOGRIGHT2": JOY1_ANALOG2_RIGHT],

                JOY2_ANALOG2  : [
                        "ANALOGUP2"   : JOY2_ANALOG2_UP,
                        "ANALOGDOWN2" : JOY2_ANALOG2_DOWN,
                        "ANALOGLEFT2" : JOY2_ANALOG2_LEFT,
                        "ANALOGRIGHT2": JOY2_ANALOG2_RIGHT],

                JOY1_DPAD     : [
                        "DIGITALUP"   : JOY1_UP,
                        "DIGITALDOWN" : JOY1_DOWN,
                        "DIGITALLEFT" : JOY1_LEFT,
                        "DIGITALRIGHT": JOY1_RIGHT],

                JOY2_DPAD     : [
                        "DIGITALUP"   : JOY2_UP,
                        "DIGITALDOWN" : JOY2_DOWN,
                        "DIGITALLEFT" : JOY2_LEFT,
                        "DIGITALRIGHT": JOY2_RIGHT]
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

    static enum Type {
        xbox360Controller,
        keysAndOnlyAnalogToXbox360

    }
    static List<File> configure(HyperSpin hs, Type type) {

        println "- Demul"
        IniFile module = new IniFile().parse(hs.newRocketLauncherFile("Modules\\Demul\\Demul (v0.7).ini"))
        IniFile demul = new IniFile(equals: " = ").parse(new File(hs.getDemulFolder(), "padDemul.ini"))
//        reverseConfig(module)

        if (type == Type.keysAndOnlyAnalogToXbox360) {
            DemulRocketLauncherMappingKeyAndOnlyAnalogTo360.configureGames(module)
            DemulRocketLauncherMappingKeyAndOnlyAnalogTo360.createDefaultXboxMappingJoystick1().each { action, key ->
                module.put("standard_JAMMA0_0", action, key)
            }
            DemulRocketLauncherMappingKeyAndOnlyAnalogTo360.createDefaultXboxMappingJoystick2().each { action, key ->
                module.put("standard_JAMMA0_1", action, key)
            }

            // acciones de XBOX para el jugador 1 Y 2
            DemulRocketLauncherMappingKeyAndOnlyAnalogTo360.createDefaultXboxMappingJoystick1().each { action, key ->
                demul.put("JAMMA0_0", action, key)
            }
            DemulRocketLauncherMappingKeyAndOnlyAnalogTo360.createDefaultXboxMappingJoystick2().each { action, key ->
                demul.put("JAMMA0_1", action, key)
            }
            // teclas para jugador 1 Y 2 (alternativoS)
            DemulRocketLauncherMappingKeyAndOnlyAnalogTo360.createDefaultKeyboardMapping1().each { action, key ->
                demul.put("JAMMA1_0", action, key)
            }
            DemulRocketLauncherMappingKeyAndOnlyAnalogTo360.createDefaultKeyboardMapping2().each { action, key ->
                demul.put("JAMMA1_1", action, key)
            }

        } else if (type == Type.xbox360Controller) {
            DemulRocketLauncherMapping360.configureGames(module)
            DemulRocketLauncherMapping360.createDefaultXboxMappingJoystick1().each { action, key ->
                module.put("standard_JAMMA0_0", action, key)
            }
            DemulRocketLauncherMapping360.createDefaultXboxMappingJoystick2().each { action, key ->
                module.put("standard_JAMMA0_1", action, key)
            }

            // acciones de XBOX para el jugador 1 Y 2
            DemulRocketLauncherMapping360.createDefaultXboxMappingJoystick1().each { action, key ->
                demul.put("JAMMA0_0", action, key)
            }
            DemulRocketLauncherMapping360.createDefaultXboxMappingJoystick2().each { action, key ->
                demul.put("JAMMA0_1", action, key)
            }
            // teclas para jugador 1 Y 2 (alternativoS)
            DemulRocketLauncherMappingKeyAndOnlyAnalogTo360.createDefaultKeyboardMapping1().each { action, key ->
                demul.put("JAMMA1_0", action, key)
            }
            DemulRocketLauncherMappingKeyAndOnlyAnalogTo360.createDefaultKeyboardMapping2().each { action, key ->
                demul.put("JAMMA1_1", action, key)
            }
        }

        module.store()

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

        return [module.file, demul.file]
    }

}
