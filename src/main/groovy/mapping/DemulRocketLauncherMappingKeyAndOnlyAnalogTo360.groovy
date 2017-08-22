package mapping

import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 28-Jul-17.
 */
class DemulRocketLauncherMappingKeyAndOnlyAnalogTo360 extends DemulMapping {

    static Map createDefaultXboxMappingJoystick1() {
        createDefaultKeyboardMapping1() +
                ["ANALOGUP"    : JOY1_ANALOG1_UP,
                 "ANALOGDOWN"  : JOY1_ANALOG1_DOWN,
                 "ANALOGLEFT"  : JOY1_ANALOG1_LEFT,
                 "ANALOGRIGHT" : JOY1_ANALOG1_RIGHT,
                 "ANALOGUP2"   : JOY1_ANALOG2_UP,
                 "ANALOGDOWN2" : JOY1_ANALOG2_DOWN,
                 "ANALOGLEFT2" : JOY1_ANALOG2_LEFT,
                 "ANALOGRIGHT2": JOY1_ANALOG2_RIGHT]
    }

    static Map createDefaultXboxMappingJoystick2() {
        createDefaultKeyboardMapping2() +
                ["ANALOGUP"    : JOY2_ANALOG1_UP,
                 "ANALOGDOWN"  : JOY2_ANALOG1_DOWN,
                 "ANALOGLEFT"  : JOY2_ANALOG1_LEFT,
                 "ANALOGRIGHT" : JOY2_ANALOG1_RIGHT,
                 "ANALOGUP2"   : JOY2_ANALOG2_UP,
                 "ANALOGDOWN2" : JOY2_ANALOG2_DOWN,
                 "ANALOGLEFT2" : JOY2_ANALOG2_LEFT,
                 "ANALOGRIGHT2": JOY2_ANALOG2_RIGHT]
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
         "ANALOGUP"    : KEY_Y,
         "ANALOGDOWN"  : KEY_T,
         "ANALOGLEFT"  : KEY_G,
         "ANALOGRIGHT" : KEY_H,
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
         "DIGITALUP"   : KEY_8_PAD,
         "DIGITALDOWN" : KEY_2_PAD,
         "DIGITALLEFT" : KEY_4_PAD,
         "DIGITALRIGHT": KEY_6_PAD,
         "ANALOGUP"    : KEY_U,
         "ANALOGDOWN"  : KEY_M,
         "ANALOGLEFT"  : KEY_B,
         "ANALOGRIGHT" : KEY_N,
         "ANALOGUP2"   : NONE,
         "ANALOGDOWN2" : NONE,
         "ANALOGLEFT2" : NONE,
         "ANALOGRIGHT2": NONE]
    }

    static void configureGames(IniFile demul) {
        Map JOY1_STARTCOIN = [
                "START": KEY_1,
                "COIN" : KEY_5
        ]
        Map JOY2_STARTCOIN = [
                "START": KEY_2,
                "COIN" : KEY_6
        ]
        Map JOY1_DRIVING = [
                "ANALOGUP"   : JOY1_RT_ANALOG,
                "ANALOGDOWN" : JOY1_LT_ANALOG,
                "ANALOGLEFT" : JOY1_ANALOG1_LEFT,
                "ANALOGRIGHT": JOY1_ANALOG1_RIGHT
        ]
        Map JOY1_ANALOG = [
                "ANALOGUP"   : JOY1_ANALOG1_UP,
                "ANALOGDOWN" : JOY1_ANALOG1_DOWN,
                "ANALOGLEFT" : JOY1_ANALOG1_LEFT,
                "ANALOGRIGHT": JOY1_ANALOG1_RIGHT
        ]
        Map JOY2_ANALOG = [
                "ANALOGUP"   : JOY2_ANALOG1_UP,
                "ANALOGDOWN" : JOY2_ANALOG1_DOWN,
                "ANALOGLEFT" : JOY2_ANALOG1_LEFT,
                "ANALOGRIGHT": JOY2_ANALOG1_RIGHT
        ]
        Map JOY1_ANALOG2 = [
                "ANALOGUP2"   : JOY1_ANALOG2_UP,
                "ANALOGDOWN2" : JOY1_ANALOG2_DOWN,
                "ANALOGLEFT2" : JOY1_ANALOG2_LEFT,
                "ANALOGRIGHT2": JOY1_ANALOG2_RIGHT
        ]
        Map JOY2_ANALOG2 = [
                "ANALOGUP2"   : JOY2_ANALOG2_UP,
                "ANALOGDOWN2" : JOY2_ANALOG2_DOWN,
                "ANALOGLEFT2" : JOY2_ANALOG2_LEFT,
                "ANALOGRIGHT2": JOY2_ANALOG2_RIGHT
        ]
        Map JOY1_DPAD = [
                "DIGITALUP"   : UP,
                "DIGITALDOWN" : DOWN,
                "DIGITALLEFT" : LEFT,
                "DIGITALRIGHT": RIGHT
        ]
        Map JOY2_DPAD = [
                "DIGITALUP"   : KEY_8_PAD,
                "DIGITALDOWN" : KEY_2_PAD,
                "DIGITALLEFT" : KEY_4_PAD,
                "DIGITALRIGHT": KEY_6_PAD
        ]


        demul.putAll("standard${player1}", JOY1_STARTCOIN + JOY1_ANALOG + JOY1_ANALOG2 + JOY1_DPAD + [
                "PUSH1": KEY_Z,
                "PUSH2": KEY_X,
                "PUSH3": KEY_C,
                "PUSH4": KEY_V,
                "PUSH5": KEY_A,
                "PUSH6": KEY_S,
                "PUSH7": JOY1_LT_ANALOG,
                "PUSH8": JOY1_RT_ANALOG
        ])
        demul.putAll("standard${player2}", JOY2_STARTCOIN + JOY2_ANALOG + JOY2_ANALOG2 + JOY2_DPAD + [
                "PUSH1": KEY_Q,
                "PUSH2": KEY_W,
                "PUSH3": KEY_E,
                "PUSH4": KEY_R,
                "PUSH5": KEY_3,
                "PUSH6": KEY_4,
                "PUSH7": JOY2_LT_ANALOG,
                "PUSH8": JOY2_RT_ANALOG
        ])
        demul.putAll("kofxi${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_C,
                "PUSH2": KEY_V,
                "PUSH3": KEY_S,
                "PUSH4": KEY_Z,
                "PUSH5": KEY_X
        ])
        demul.putAll("kofxi${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_E,
                "PUSH2": KEY_R,
                "PUSH3": KEY_4,
                "PUSH4": KEY_Q,
                "PUSH5": KEY_W
        ])
        demul.putAll("mslug6${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_Z,
                "PUSH2": KEY_X,
                "PUSH3": KEY_C,
                "PUSH5": KEY_V
        ])
        demul.putAll("mslug6${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_Q,
                "PUSH2": KEY_W,
                "PUSH3": KEY_E,
                "PUSH5": KEY_R
        ])
        demul.putAll("samsptk${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_C,
                "PUSH2": KEY_V,
                "PUSH3": KEY_X,
                "PUSH4": KEY_Z,
                "PUSH5": KEY_S
        ])
        demul.putAll("samsptk${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_E,
                "PUSH2": KEY_R,
                "PUSH3": KEY_W,
                "PUSH4": KEY_Q,
                "PUSH5": KEY_4
        ])
        demul.putAll("ggx15${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_Z,
                "PUSH2": KEY_V,
                "PUSH3": KEY_X,
                "PUSH4": KEY_C,
                "PUSH5": KEY_S
        ])
        demul.putAll("ggx15${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_Q,
                "PUSH2": KEY_R,
                "PUSH3": KEY_W,
                "PUSH4": KEY_E,
                "PUSH5": KEY_4
        ])
        demul.putAll("demofist${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_C,
                "PUSH2": KEY_Z,
                "PUSH3": KEY_X
        ])
        demul.putAll("demofist${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_E,
                "PUSH2": KEY_Q,
                "PUSH3": KEY_W
        ])
        demul.putAll("ftspeed${player1}", JOY1_STARTCOIN + JOY1_DRIVING + [
                "PUSH1": KEY_V,
                "PUSH2": KEY_C,
                "PUSH3": KEY_Z
        ])
        demul.putAll("ftspeed${player2}", JOY2_STARTCOIN)
        demul.putAll("anmlbskt${player1}", JOY1_STARTCOIN + [
                "PUSH1": KEY_C,
                "PUSH2": KEY_Z,
                "PUSH3": KEY_X
        ])
        demul.putAll("anmlbskt${player2}", JOY2_STARTCOIN + [
                "PUSH1": KEY_W,
                "PUSH2": KEY_Q,
                "PUSH3": KEY_E
        ])
        demul.putAll("xtrmhunt${player1}", JOY1_STARTCOIN)
        demul.putAll("xtrmhunt${player2}", JOY2_STARTCOIN)
        demul.putAll("salmankt${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_Z
        ])
        demul.putAll("18wheelr${player1}", JOY1_STARTCOIN + JOY1_DRIVING + [
                "PUSH1": KEY_Z,
                "PUSH2": KEY_X,
                "PUSH3": KEY_X,
                "PUSH4": KEY_V,
                "PUSH5": KEY_C
        ])
        demul.putAll("crzytaxi${player1}", JOY1_STARTCOIN + JOY1_DRIVING + [
                "PUSH1": KEY_X,
                "PUSH2": KEY_Z
        ])
        demul.putAll("capsnk${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_C,
                "PUSH2": KEY_V,
                "PUSH3": KEY_S,
                "PUSH4": KEY_Z,
                "PUSH5": KEY_X,
                "PUSH6": JOY1_RT_ANALOG
        ])
        demul.putAll("capsnk${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_E,
                "PUSH2": KEY_R,
                "PUSH3": KEY_4,
                "PUSH4": KEY_Q,
                "PUSH5": KEY_W,
                "PUSH6": JOY2_RT_ANALOG
        ])
        demul.putAll("ggxx${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_Z,
                "PUSH2": KEY_V,
                "PUSH3": KEY_X,
                "PUSH4": KEY_C,
                "PUSH5": KEY_S
        ])
        demul.putAll("ggxx${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_Q,
                "PUSH2": KEY_R,
                "PUSH3": KEY_W,
                "PUSH4": KEY_E,
                "PUSH5": KEY_4
        ])
        demul.putAll("ggx${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_C,
                "PUSH2": KEY_Z,
                "PUSH3": KEY_V,
                "PUSH4": KEY_X
        ])
        demul.putAll("ggx${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_E,
                "PUSH2": KEY_Q,
                "PUSH3": KEY_R,
                "PUSH4": KEY_W
        ])
        demul.putAll("cspike${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_Z,
                "PUSH2": KEY_X,
                "PUSH3": JOY1_RT_ANALOG
        ])
        demul.putAll("cspike${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_Q,
                "PUSH2": KEY_W,
                "PUSH3": JOY2_RT_ANALOG
        ])
        demul.putAll("pstone${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_C,
                "PUSH2": KEY_V,
                "PUSH3": KEY_Z
        ])
        demul.putAll("pstone${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_E,
                "PUSH2": KEY_R,
                "PUSH3": KEY_Q
        ])
        demul.putAll("pstone2${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_C,
                "PUSH2": KEY_Z,
                "PUSH3": KEY_V
        ])
        demul.putAll("pstone2${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_E,
                "PUSH2": KEY_Q,
                "PUSH3": KEY_R
        ])
        demul.putAll("initd${player1}", JOY1_STARTCOIN + JOY1_DRIVING + [
                "PUSH1": KEY_X,
                "PUSH2": KEY_Z,
                "PUSH3": KEY_V
        ])
        demul.putAll("doa2${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_Z,
                "PUSH2": KEY_C,
                "PUSH3": KEY_V
        ])
        demul.putAll("doa2${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_Q,
                "PUSH2": KEY_E,
                "PUSH3": KEY_R
        ])
        demul.putAll("hotd2${player1}", JOY1_STARTCOIN + JOY1_ANALOG)
        demul.putAll("hotd2${player2}", JOY2_STARTCOIN + JOY2_ANALOG)
        demul.putAll("monkeyba${player1}", JOY1_STARTCOIN + JOY1_ANALOG)
        demul.putAll("alienfnt${player1}", JOY1_STARTCOIN + JOY1_ANALOG + [
                "PUSH1": KEY_X,
                "PUSH2": KEY_Z,
                "PUSH3": JOY1_LT_ANALOG,
                "PUSH4": JOY1_RT_ANALOG
        ])
        demul.putAll("samba${player1}", JOY1_STARTCOIN + JOY1_ANALOG + JOY1_ANALOG2)
        demul.putAll("samba${player1}", [
                "PUSH1": JOY1_LT_ANALOG,
                "PUSH2": JOY1_RT_ANALOG
        ])
        demul.putAll("samba${player2}", JOY2_STARTCOIN + JOY2_ANALOG + JOY2_ANALOG2)
        demul.putAll("samba${player2}", [
                "PUSH1": JOY2_LT_ANALOG,
                "PUSH2": JOY2_RT_ANALOG
        ])
        demul.putAll("alpiltdx${player1}", JOY1_STARTCOIN + JOY1_ANALOG + [
                "PUSH1"       : KEY_Z,
                "PUSH2"       : KEY_X,
                "PUSH3"       : KEY_V,
                "ANALOGUP2"   : JOY1_ANALOG2_RIGHT,
                "ANALOGDOWN2" : JOY1_ANALOG2_LEFT,
                "ANALOGLEFT2" : JOY1_LT_ANALOG,
                "ANALOGRIGHT2": JOY1_RT_ANALOG
        ])
        demul.putAll("ausfache${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_C,
                "PUSH2": KEY_Z,
                "PUSH3": KEY_X,
                "PUSH4": KEY_S
        ])
        demul.putAll("ausfache${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_E,
                "PUSH2": KEY_Q,
                "PUSH3": KEY_W
        ])
        demul.putAll("gunsur2${player1}", JOY1_STARTCOIN + JOY1_ANALOG + JOY1_DPAD + JOY1_ANALOG2 + [
                "PUSH1"       : KEY_Z,
                "PUSH2"       : JOY1_RT_ANALOG
        ])
        demul.putAll("gwing2${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_Z,
                "PUSH2": KEY_X,
                "PUSH3": KEY_C
        ])
        demul.putAll("gwing2${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_Q,
                "PUSH2": KEY_W,
                "PUSH3": KEY_E
        ])
        demul.putAll("senkosp${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_C,
                "PUSH2": KEY_V,
                "PUSH3": KEY_X,
                "PUSH4": KEY_Z,
                "PUSH5": KEY_A
        ])
        demul.putAll("senkosp${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_E,
                "PUSH2": KEY_R,
                "PUSH3": KEY_W,
                "PUSH4": KEY_Q,
                "PUSH5": KEY_3
        ])
        demul.putAll("senko${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_Z,
                "PUSH2": KEY_C,
                "PUSH3": KEY_V
        ])
        demul.putAll("senko${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_Q,
                "PUSH2": KEY_E,
                "PUSH3": KEY_R
        ])
        demul.putAll("zombrvn${player1}", JOY1_STARTCOIN + JOY1_ANALOG + [
                "PUSH1": KEY_V,
                "PUSH2": KEY_X,
                "PUSH3": KEY_C
        ])
        demul.putAll("zombrvn${player2}", JOY2_STARTCOIN + JOY2_ANALOG + [
                "PUSH1": KEY_R,
                "PUSH2": KEY_W,
                "PUSH3": KEY_E
        ])
        demul.putAll("vtennis${player1}", JOY1_STARTCOIN + JOY1_ANALOG + JOY1_DPAD)
        demul.putAll("vtennis${player1}", [
                "PUSH1"      : KEY_Z,
                "PUSH2"      : KEY_X,
                "ANALOGUP2"  : JOY1_ANALOG2_UP,
                "ANALOGDOWN2": JOY1_ANALOG2_DOWN
        ])
        demul.putAll("vtennis${player2}", JOY2_STARTCOIN + JOY2_ANALOG + JOY2_DPAD + [
                "PUSH1"      : KEY_Q,
                "PUSH2"      : KEY_W,
                "ANALOGUP2"  : JOY2_ANALOG2_UP,
                "ANALOGDOWN2": JOY2_ANALOG2_DOWN
        ])
        demul.putAll("vstrik3${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_C,
                "PUSH2": KEY_X,
                "PUSH3": KEY_Z
        ])
        demul.putAll("vstrik3${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_E,
                "PUSH2": KEY_W,
                "PUSH3": KEY_Q
        ])
        demul.putAll("sstrkfgt${player1}", JOY1_STARTCOIN + JOY1_ANALOG + [
                "PUSH1"    : JOY1_RT_ANALOG,
                "PUSH2"    : KEY_S,
                "PUSH3"    : KEY_A,
                "PUSH4"    : KEY_V,
                "ANALOGUP2": JOY1_LT_ANALOG
        ])
        demul.putAll("wldrider${player1}", JOY1_STARTCOIN + JOY1_DRIVING + [
                "PUSH1"      : JOY1_ANALOG1_UP,
                "PUSH2"      : JOY1_ANALOG1_DOWN,
                "ANALOGDOWN2": KEY_Z
        ])
        demul.putAll("meltyb${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_C,
                "PUSH2": KEY_Z,
                "PUSH3": KEY_X,
                "PUSH4": KEY_V,
                "PUSH5": KEY_S
        ])
        demul.putAll("meltyb${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_E,
                "PUSH2": KEY_Q,
                "PUSH3": KEY_W,
                "PUSH4": KEY_R,
                "PUSH5": KEY_4
        ])
        demul.putAll("clubkrte${player1}", JOY1_STARTCOIN + JOY1_DRIVING + [
                "PUSH1": KEY_V
        ])
        demul.putAll("otrigger${player1}", JOY1_STARTCOIN + JOY1_DPAD + JOY1_ANALOG)
        demul.putAll("ss2005${player1}", JOY1_STARTCOIN + JOY1_DPAD+[
                "PUSH1": KEY_Z,
                "PUSH2": KEY_X,
                "PUSH3": KEY_V
        ])
        demul.putAll("ss2005${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_Q,
                "PUSH2": KEY_W,
                "PUSH3": KEY_R
        ])
        demul.putAll("usagiym${player1}", JOY1_STARTCOIN)
        demul.putAll("wrungp${player1}", JOY1_STARTCOIN + [
                "PUSH1"       : KEY_V,
                "ANALOGUP"    : KEY_Z,
                "ANALOGLEFT"  : JOY1_ANALOG1_LEFT,
                "ANALOGRIGHT" : JOY1_ANALOG1_RIGHT,
                "ANALOGUP2"   : JOY1_ANALOG1_UP,
                "ANALOGDOWN2" : JOY1_ANALOG1_DOWN,
                "ANALOGLEFT2" : JOY1_LT_ANALOG,
                "ANALOGRIGHT2": JOY1_RT_ANALOG
        ])
        demul.putAll("vonot${player1}", JOY1_STARTCOIN + [
                "PUSH1"       : JOY1_LT_ANALOG,
                "PUSH2"       : KEY_X,
                "PUSH3"       : KEY_Z,
                "DIGITALUP"   : JOY1_ANALOG1_UP,
                "DIGITALDOWN" : JOY1_ANALOG1_DOWN,
                "DIGITALLEFT" : JOY1_ANALOG1_LEFT,
                "DIGITALRIGHT": JOY1_ANALOG1_RIGHT
        ])
        demul.putAll("vonot${player2}", [
                "PUSH1"       : JOY1_RT_ANALOG,
                "PUSH2"       : KEY_X,
                "DIGITALUP"   : JOY1_ANALOG2_UP,
                "DIGITALDOWN" : JOY1_ANALOG2_DOWN,
                "DIGITALLEFT" : JOY1_ANALOG2_LEFT,
                "DIGITALRIGHT": JOY1_ANALOG2_RIGHT
        ])
        demul.putAll("gundmct${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_C,
                "PUSH2": KEY_V,
                "PUSH3": KEY_Z,
                "PUSH4": KEY_X
        ])
        demul.putAll("shaktamb${player1}", JOY1_STARTCOIN + JOY1_ANALOG + JOY1_DPAD + [
                "PUSH1"      : KEY_Z,
                "PUSH2"      : JOY1_LT_ANALOG,
                "PUSH3"      : JOY1_RT_ANALOG
        ])
        demul.putAll("shaktamb${player2}", JOY2_STARTCOIN + JOY2_ANALOG + JOY2_DPAD + [
                "PUSH1"      : KEY_Q,
                "PUSH2"      : JOY2_LT_ANALOG,
                "PUSH3"      : JOY2_RT_ANALOG
        ])
        demul.putAll("jambo${player1}", JOY1_STARTCOIN + JOY1_DRIVING + [
                "PUSH1": JOY1_ANALOG2_DOWN,
                "PUSH2": JOY1_ANALOG2_UP
        ])
        demul.putAll("quizqgd${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": KEY_C,
                "PUSH2": KEY_Z,
                "PUSH3": KEY_X,
                "PUSH4": KEY_V
        ])
        demul.putAll("quizqgd${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": KEY_E,
                "PUSH2": KEY_Q,
                "PUSH3": KEY_W,
                "PUSH4": KEY_R
        ])
        demul.putAll("ringout${player1}", JOY1_STARTCOIN + [
                "PUSH1"      : KEY_Z,
                "PUSH2"      : JOY1_LT_ANALOG,
                "ANALOGUP"   : JOY1_RT_ANALOG,
                "ANALOGLEFT" : JOY1_ANALOG1_LEFT,
                "ANALOGRIGHT": JOY1_ANALOG1_RIGHT
        ])
        demul.putAll("ringout${player2}", JOY2_STARTCOIN + [
                "PUSH1"      : KEY_Q,
                "PUSH2"      : JOY2_LT_ANALOG,
                "ANALOGUP"   : JOY2_RT_ANALOG,
                "ANALOGLEFT" : JOY2_ANALOG1_LEFT,
                "ANALOGRIGHT": JOY2_ANALOG1_RIGHT
        ])
        demul.putAll("soulsurf${player1}", JOY1_STARTCOIN + [
                "DIGITALLEFT" : JOY1_LEFT,
                "DIGITALRIGHT": JOY1_RIGHT,
                "ANALOGDOWN"  : KEY_Z,
                "ANALOGLEFT"  : JOY1_ANALOG1_LEFT,
                "ANALOGRIGHT" : JOY1_ANALOG1_RIGHT,
                "ANALOGLEFT2" : JOY1_LT_ANALOG,
                "ANALOGRIGHT2": JOY1_RT_ANALOG
        ])
        demul.putAll("kingrt66${player1}", JOY1_STARTCOIN + JOY1_DRIVING + [
                "PUSH1": KEY_Z,
                "PUSH2": KEY_X,
                "PUSH3": KEY_S,
                "PUSH4": KEY_V,
                "PUSH5": KEY_C,
                "PUSH6": KEY_A
        ])
        demul.putAll("smshdrv${player1}", JOY1_STARTCOIN + [
                "PUSH1"      : JOY1_RT_ANALOG,
                "PUSH2"      : JOY1_LT_ANALOG,
                "PUSH3"      : KEY_Z,
                "ANALOGLEFT" : JOY1_ANALOG1_LEFT,
                "ANALOGRIGHT": JOY1_ANALOG1_RIGHT
        ])
        demul.putAll("atvtrack${player1}", JOY1_STARTCOIN + [
                "DIGITALLEFT" : JOY1_LT_ANALOG,
                "DIGITALRIGHT": JOY1_RT_ANALOG,
                "ANALOGUP"    : KEY_Z,
                "ANALOGLEFT"  : JOY1_ANALOG1_LEFT,
                "ANALOGRIGHT" : JOY1_ANALOG1_RIGHT
        ])
        demul.store()
    }
}
