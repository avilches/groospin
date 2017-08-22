package mapping

import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 28-Jul-17.
 */
class DemulRocketLauncherMapping360 extends DemulMapping {

    static Map createDefaultXboxMappingJoystick1() {
                ["PUSH1"       : JOY1_A,
                 "PUSH2"       : JOY1_B,
                 "PUSH3"       : JOY1_X,
                 "PUSH4"       : JOY1_Y,
                 "PUSH5"       : JOY1_LB,
                 "PUSH6"       : JOY1_RB,
                 "PUSH7"       : JOY1_LT_ANALOG,
                 "PUSH8"       : JOY1_RT_ANALOG,
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

    static Map createDefaultXboxMappingJoystick2() {
                ["PUSH1"       : JOY2_A,
                 "PUSH2"       : JOY2_B,
                 "PUSH3"       : JOY2_X,
                 "PUSH4"       : JOY2_Y,
                 "PUSH5"       : JOY2_LB,
                 "PUSH6"       : JOY2_RB,
                 "PUSH7"       : JOY2_LT_ANALOG,
                 "PUSH8"       : JOY2_RT_ANALOG,
                 "SERVICE"     : NONE,
                 "START"       : JOY2_BACK,
                 "COIN"        : JOY2_START,
                 "DIGITALUP"   : JOY2_UP,
                 "DIGITALDOWN" : JOY2_DOWN,
                 "DIGITALLEFT" : JOY2_LEFT,
                 "DIGITALRIGHT": JOY2_RIGHT,
                 "ANALOGUP"    : JOY2_ANALOG1_UP,
                 "ANALOGDOWN"  : JOY2_ANALOG1_DOWN,
                 "ANALOGLEFT"  : JOY2_ANALOG1_LEFT,
                 "ANALOGRIGHT" : JOY2_ANALOG1_RIGHT,
                 "ANALOGUP2"   : JOY2_ANALOG2_UP,
                 "ANALOGDOWN2" : JOY2_ANALOG2_DOWN,
                 "ANALOGLEFT2" : JOY2_ANALOG2_LEFT,
                 "ANALOGRIGHT2": JOY2_ANALOG2_RIGHT]
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
                "DIGITALUP"   : JOY1_UP,
                "DIGITALDOWN" : JOY1_DOWN,
                "DIGITALLEFT" : JOY1_LEFT,
                "DIGITALRIGHT": JOY1_RIGHT
        ]
        Map JOY2_DPAD = [
                "DIGITALUP"   : JOY2_UP,
                "DIGITALDOWN" : JOY2_DOWN,
                "DIGITALLEFT" : JOY2_LEFT,
                "DIGITALRIGHT": JOY2_RIGHT
        ]


        demul.putAll("standard${player1}", JOY1_STARTCOIN + JOY1_ANALOG + JOY1_ANALOG2 + JOY1_DPAD + [
                        "PUSH1": JOY1_A,
                        "PUSH2": JOY1_B,
                        "PUSH3": JOY1_X,
                        "PUSH4": JOY1_Y,
                        "PUSH5": JOY1_LB,
                        "PUSH6": JOY1_RB,
                        "PUSH7": JOY1_LT_ANALOG,
                        "PUSH8": JOY1_RT_ANALOG
                ])
        demul.putAll("standard${player2}", JOY2_STARTCOIN + JOY2_ANALOG + JOY2_ANALOG2 + JOY2_DPAD + [
                "PUSH1": JOY2_A,
                "PUSH2": JOY2_B,
                "PUSH3": JOY2_X,
                "PUSH4": JOY2_Y,
                "PUSH5": JOY2_LB,
                "PUSH6": JOY2_RB,
                "PUSH7": JOY2_LT_ANALOG,
                "PUSH8": JOY2_RT_ANALOG
        ])
        demul.putAll("kofxi${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_X,
                "PUSH2": JOY1_Y,
                "PUSH3": JOY1_RB,
                "PUSH4": JOY1_A,
                "PUSH5": JOY1_B
        ])
        demul.putAll("kofxi${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_X,
                "PUSH2": JOY2_Y,
                "PUSH3": JOY2_RB,
                "PUSH4": JOY2_A,
                "PUSH5": JOY2_B
        ])
        demul.putAll("mslug6${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_A,
                "PUSH2": JOY1_B,
                "PUSH3": JOY1_X,
                "PUSH5": JOY1_Y
        ])
        demul.putAll("mslug6${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_A,
                "PUSH2": JOY2_B,
                "PUSH3": JOY2_X,
                "PUSH5": JOY2_Y
        ])
        demul.putAll("samsptk${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_X,
                "PUSH2": JOY1_Y,
                "PUSH3": JOY1_B,
                "PUSH4": JOY1_A,
                "PUSH5": JOY1_RB
        ])
        demul.putAll("samsptk${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_X,
                "PUSH2": JOY2_Y,
                "PUSH3": JOY2_B,
                "PUSH4": JOY2_A,
                "PUSH5": JOY2_RB
        ])
        demul.putAll("ggx15${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_A,
                "PUSH2": JOY1_Y,
                "PUSH3": JOY1_B,
                "PUSH4": JOY1_X,
                "PUSH5": JOY1_RB
        ])
        demul.putAll("ggx15${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_A,
                "PUSH2": JOY2_Y,
                "PUSH3": JOY2_B,
                "PUSH4": JOY2_X,
                "PUSH5": JOY2_RB
        ])
        demul.putAll("demofist${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_X,
                "PUSH2": JOY1_A,
                "PUSH3": JOY1_B
        ])
        demul.putAll("demofist${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_X,
                "PUSH2": JOY2_A,
                "PUSH3": JOY2_B
        ])
        demul.putAll("ftspeed${player1}", JOY1_STARTCOIN + JOY1_DRIVING + [
                "PUSH1": JOY1_Y,
                "PUSH2": JOY1_X,
                "PUSH3": JOY1_A
        ])
        demul.putAll("ftspeed${player2}", JOY2_STARTCOIN)
        demul.putAll("anmlbskt${player1}", JOY1_STARTCOIN + [
                "PUSH1": JOY1_X,
                "PUSH2": JOY1_A,
                "PUSH3": JOY1_B
        ])
        demul.putAll("anmlbskt${player2}", JOY2_STARTCOIN + [
                "PUSH1": JOY2_B,
                "PUSH2": JOY2_A,
                "PUSH3": JOY2_X
        ])
        demul.putAll("xtrmhunt${player1}", JOY1_STARTCOIN)
        demul.putAll("xtrmhunt${player2}", JOY2_STARTCOIN)
        demul.putAll("salmankt${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_A
        ])
        demul.putAll("18wheelr${player1}", JOY1_STARTCOIN + JOY1_DRIVING + [
                "PUSH1": JOY1_A,
                "PUSH2": JOY1_B,
                "PUSH3": JOY1_B,
                "PUSH4": JOY1_Y,
                "PUSH5": JOY1_X
        ])
        demul.putAll("crzytaxi${player1}", JOY1_STARTCOIN + JOY1_DRIVING + [
                "PUSH1": JOY1_B,
                "PUSH2": JOY1_A
        ])
        demul.putAll("capsnk${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_X,
                "PUSH2": JOY1_Y,
                "PUSH3": JOY1_RB,
                "PUSH4": JOY1_A,
                "PUSH5": JOY1_B,
                "PUSH6": JOY1_RT_ANALOG
        ])
        demul.putAll("capsnk${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_X,
                "PUSH2": JOY2_Y,
                "PUSH3": JOY2_RB,
                "PUSH4": JOY2_A,
                "PUSH5": JOY2_B,
                "PUSH6": JOY2_RT_ANALOG
        ])
        demul.putAll("ggxx${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_A,
                "PUSH2": JOY1_Y,
                "PUSH3": JOY1_B,
                "PUSH4": JOY1_X,
                "PUSH5": JOY1_RB
        ])
        demul.putAll("ggxx${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_A,
                "PUSH2": JOY2_Y,
                "PUSH3": JOY2_B,
                "PUSH4": JOY2_X,
                "PUSH5": JOY2_RB
        ])
        demul.putAll("ggx${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_X,
                "PUSH2": JOY1_A,
                "PUSH3": JOY1_Y,
                "PUSH4": JOY1_B
        ])
        demul.putAll("ggx${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_X,
                "PUSH2": JOY2_A,
                "PUSH3": JOY2_Y,
                "PUSH4": JOY2_B
        ])
        demul.putAll("cspike${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_A,
                "PUSH2": JOY1_B,
                "PUSH3": JOY1_RT_ANALOG
        ])
        demul.putAll("cspike${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_A,
                "PUSH2": JOY2_B,
                "PUSH3": JOY2_RT_ANALOG
        ])
        demul.putAll("pstone${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_X,
                "PUSH2": JOY1_Y,
                "PUSH3": JOY1_A
        ])
        demul.putAll("pstone${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_X,
                "PUSH2": JOY2_Y,
                "PUSH3": JOY2_A
        ])
        demul.putAll("pstone2${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_X,
                "PUSH2": JOY1_A,
                "PUSH3": JOY1_Y
        ])
        demul.putAll("pstone2${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_X,
                "PUSH2": JOY2_A,
                "PUSH3": JOY2_Y
        ])
        demul.putAll("initd${player1}", JOY1_STARTCOIN + JOY1_DRIVING + [
                "PUSH1": JOY1_B,
                "PUSH2": JOY1_A,
                "PUSH3": JOY1_Y
        ])
        demul.putAll("doa2${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_A,
                "PUSH2": JOY1_X,
                "PUSH3": JOY1_Y
        ])
        demul.putAll("doa2${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_A,
                "PUSH2": JOY2_X,
                "PUSH3": JOY2_Y
        ])
        demul.putAll("hotd2${player1}", JOY1_STARTCOIN + JOY1_ANALOG)
        demul.putAll("hotd2${player2}", JOY2_STARTCOIN + JOY2_ANALOG)
        demul.putAll("monkeyba${player1}", JOY1_STARTCOIN + JOY1_ANALOG)
        demul.putAll("alienfnt${player1}", JOY1_STARTCOIN + JOY1_ANALOG + [
                "PUSH1": JOY1_B,
                "PUSH2": JOY1_A,
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
                "PUSH1"       : JOY1_A,
                "PUSH2"       : JOY1_B,
                "PUSH3"       : JOY1_Y,
                "ANALOGUP2"   : JOY1_ANALOG2_RIGHT,
                "ANALOGDOWN2" : JOY1_ANALOG2_LEFT,
                "ANALOGLEFT2" : JOY1_LT_ANALOG,
                "ANALOGRIGHT2": JOY1_RT_ANALOG
        ])
        demul.putAll("ausfache${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_X,
                "PUSH2": JOY1_A,
                "PUSH3": JOY1_B,
                "PUSH4": JOY1_RB
        ])
        demul.putAll("ausfache${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_X,
                "PUSH2": JOY2_A,
                "PUSH3": JOY2_B
        ])
        demul.putAll("gunsur2${player1}", JOY1_STARTCOIN + JOY1_ANALOG + [
                "PUSH1"       : JOY1_A,
                "PUSH2"       : JOY1_RT_ANALOG,
                "DIGITALUP"   : JOY1_UP,
                "DIGITALDOWN" : JOY1_DOWN,
                "ANALOGLEFT2" : JOY1_ANALOG2_LEFT,
                "ANALOGRIGHT2": JOY1_ANALOG2_RIGHT
        ])
        demul.putAll("gwing2${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_A,
                "PUSH2": JOY1_B,
                "PUSH3": JOY1_X
        ])
        demul.putAll("gwing2${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_A,
                "PUSH2": JOY2_B,
                "PUSH3": JOY2_X
        ])
        demul.putAll("senkosp${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_X,
                "PUSH2": JOY1_Y,
                "PUSH3": JOY1_B,
                "PUSH4": JOY1_A,
                "PUSH5": JOY1_LB
        ])
        demul.putAll("senkosp${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_X,
                "PUSH2": JOY2_Y,
                "PUSH3": JOY2_B,
                "PUSH4": JOY2_A,
                "PUSH5": JOY2_LB
        ])
        demul.putAll("senko${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_A,
                "PUSH2": JOY1_X,
                "PUSH3": JOY1_Y
        ])
        demul.putAll("senko${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_A,
                "PUSH2": JOY2_X,
                "PUSH3": JOY2_Y
        ])
        demul.putAll("zombrvn${player1}", JOY1_STARTCOIN + JOY1_ANALOG + [
                "PUSH1": JOY1_Y,
                "PUSH2": JOY1_B,
                "PUSH3": JOY1_X
        ])
        demul.putAll("zombrvn${player2}", JOY2_STARTCOIN + JOY2_ANALOG + [
                "PUSH1": JOY2_Y,
                "PUSH2": JOY2_B,
                "PUSH3": JOY2_X
        ])
        demul.putAll("vtennis${player1}", JOY1_STARTCOIN + JOY1_ANALOG + JOY1_DPAD)
        demul.putAll("vtennis${player1}", [
                "PUSH1"      : JOY1_A,
                "PUSH2"      : JOY1_B,
                "ANALOGUP2"  : JOY1_ANALOG2_UP,
                "ANALOGDOWN2": JOY1_ANALOG2_DOWN
        ])
        demul.putAll("vtennis${player2}", JOY2_STARTCOIN + JOY2_ANALOG + JOY2_DPAD + [
                "PUSH1"      : JOY2_A,
                "PUSH2"      : JOY2_B,
                "ANALOGUP2"  : JOY2_ANALOG2_UP,
                "ANALOGDOWN2": JOY2_ANALOG2_DOWN
        ])
        demul.putAll("vstrik3${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_X,
                "PUSH2": JOY1_B,
                "PUSH3": JOY1_A
        ])
        demul.putAll("vstrik3${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_X,
                "PUSH2": JOY2_B,
                "PUSH3": JOY2_A
        ])
        demul.putAll("sstrkfgt${player1}", JOY1_STARTCOIN + JOY1_ANALOG + [
                "PUSH1"    : JOY1_RT_ANALOG,
                "PUSH2"    : JOY1_RB,
                "PUSH3"    : JOY1_LB,
                "PUSH4"    : JOY1_Y,
                "ANALOGUP2": JOY1_LT_ANALOG
        ])
        demul.putAll("wldrider${player1}", JOY1_STARTCOIN + JOY1_DRIVING + [
                "PUSH1"      : JOY1_ANALOG1_UP,
                "PUSH2"      : JOY1_ANALOG1_DOWN,
                "ANALOGDOWN2": JOY1_A
        ])
        demul.putAll("meltyb${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_X,
                "PUSH2": JOY1_A,
                "PUSH3": JOY1_B,
                "PUSH4": JOY1_Y,
                "PUSH5": JOY1_RB
        ])
        demul.putAll("meltyb${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_X,
                "PUSH2": JOY2_A,
                "PUSH3": JOY2_B,
                "PUSH4": JOY2_Y,
                "PUSH5": JOY2_RB
        ])
        demul.putAll("clubkrte${player1}", JOY1_STARTCOIN + JOY1_DRIVING + [
                "PUSH1": JOY1_Y
        ])
        demul.putAll("otrigger${player1}", JOY1_STARTCOIN + JOY1_DPAD + JOY1_ANALOG)
        demul.putAll("ss2005${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_A,
                "PUSH2": JOY1_B,
                "PUSH3": JOY1_Y
        ])
        demul.putAll("ss2005${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_A,
                "PUSH2": JOY2_B,
                "PUSH3": JOY2_Y
        ])
        demul.putAll("usagiym${player1}", JOY1_STARTCOIN)
        demul.putAll("wrungp${player1}", JOY1_STARTCOIN + [
                "PUSH1"       : JOY1_Y,
                "ANALOGUP"    : JOY1_A,
                "ANALOGLEFT"  : JOY1_ANALOG1_LEFT,
                "ANALOGRIGHT" : JOY1_ANALOG1_RIGHT,
                "ANALOGUP2"   : JOY1_ANALOG1_UP,
                "ANALOGDOWN2" : JOY1_ANALOG1_DOWN,
                "ANALOGLEFT2" : JOY1_LT_ANALOG,
                "ANALOGRIGHT2": JOY1_RT_ANALOG
        ])
        demul.putAll("vonot${player1}", JOY1_STARTCOIN + [
                "PUSH1"       : JOY1_LT_ANALOG,
                "PUSH2"       : JOY1_B,
                "PUSH3"       : JOY1_A,
                "DIGITALUP"   : JOY1_ANALOG1_UP,
                "DIGITALDOWN" : JOY1_ANALOG1_DOWN,
                "DIGITALLEFT" : JOY1_ANALOG1_LEFT,
                "DIGITALRIGHT": JOY1_ANALOG1_RIGHT
        ])
        demul.putAll("vonot${player2}", [
                "PUSH1"       : JOY1_RT_ANALOG,
                "PUSH2"       : JOY1_B,
                "DIGITALUP"   : JOY1_ANALOG2_UP,
                "DIGITALDOWN" : JOY1_ANALOG2_DOWN,
                "DIGITALLEFT" : JOY1_ANALOG2_LEFT,
                "DIGITALRIGHT": JOY1_ANALOG2_RIGHT
        ])
        demul.putAll("gundmct${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_X,
                "PUSH2": JOY1_Y,
                "PUSH3": JOY1_A,
                "PUSH4": JOY1_B
        ])
        demul.putAll("shaktamb${player1}", JOY1_STARTCOIN + JOY1_ANALOG + JOY1_DPAD + [
                "PUSH1"      : JOY1_A,
                "PUSH2"      : JOY1_LT_ANALOG,
                "PUSH3"      : JOY1_RT_ANALOG
        ])
        demul.putAll("shaktamb${player2}", JOY2_STARTCOIN + JOY2_ANALOG + JOY2_DPAD + [
                "PUSH1"      : JOY2_A,
                "PUSH2"      : JOY2_LT_ANALOG,
                "PUSH3"      : JOY2_RT_ANALOG
        ])
        demul.putAll("jambo${player1}", JOY1_STARTCOIN + JOY1_DRIVING + [
                "PUSH1": JOY1_ANALOG2_DOWN,
                "PUSH2": JOY1_ANALOG2_UP
        ])
        demul.putAll("quizqgd${player1}", JOY1_STARTCOIN + JOY1_DPAD + [
                "PUSH1": JOY1_X,
                "PUSH2": JOY1_A,
                "PUSH3": JOY1_B,
                "PUSH4": JOY1_Y
        ])
        demul.putAll("quizqgd${player2}", JOY2_STARTCOIN + JOY2_DPAD + [
                "PUSH1": JOY2_X,
                "PUSH2": JOY2_A,
                "PUSH3": JOY2_B,
                "PUSH4": JOY2_Y
        ])
        demul.putAll("ringout${player1}", JOY1_STARTCOIN + [
                "PUSH1"      : JOY1_A,
                "PUSH2"      : JOY1_LT_ANALOG,
                "ANALOGUP"   : JOY1_RT_ANALOG,
                "ANALOGLEFT" : JOY1_ANALOG1_LEFT,
                "ANALOGRIGHT": JOY1_ANALOG1_RIGHT
        ])
        demul.putAll("ringout${player2}", JOY2_STARTCOIN + [
                "PUSH1"      : JOY2_A,
                "PUSH2"      : JOY2_LT_ANALOG,
                "ANALOGUP"   : JOY2_RT_ANALOG,
                "ANALOGLEFT" : JOY2_ANALOG1_LEFT,
                "ANALOGRIGHT": JOY2_ANALOG1_RIGHT
        ])
        demul.putAll("soulsurf${player1}", JOY1_STARTCOIN + [
                "DIGITALLEFT" : JOY1_LEFT,
                "DIGITALRIGHT": JOY1_RIGHT,
                "ANALOGDOWN"  : JOY1_A,
                "ANALOGLEFT"  : JOY1_ANALOG1_LEFT,
                "ANALOGRIGHT" : JOY1_ANALOG1_RIGHT,
                "ANALOGLEFT2" : JOY1_LT_ANALOG,
                "ANALOGRIGHT2": JOY1_RT_ANALOG
        ])
        demul.putAll("kingrt66${player1}", JOY1_STARTCOIN + JOY1_DRIVING + [
                "PUSH1": JOY1_A,
                "PUSH2": JOY1_B,
                "PUSH3": JOY1_RB,
                "PUSH4": JOY1_Y,
                "PUSH5": JOY1_X,
                "PUSH6": JOY1_LB
        ])
        demul.putAll("smshdrv${player1}", JOY1_STARTCOIN + [
                "PUSH1"      : JOY1_RT_ANALOG,
                "PUSH2"      : JOY1_LT_ANALOG,
                "PUSH3"      : JOY1_A,
                "ANALOGLEFT" : JOY1_ANALOG1_LEFT,
                "ANALOGRIGHT": JOY1_ANALOG1_RIGHT
        ])
        demul.putAll("atvtrack${player1}", JOY1_STARTCOIN + [
                "DIGITALLEFT" : JOY1_LT_ANALOG,
                "DIGITALRIGHT": JOY1_RT_ANALOG,
                "ANALOGUP"    : JOY1_A,
                "ANALOGLEFT"  : JOY1_ANALOG1_LEFT,
                "ANALOGRIGHT" : JOY1_ANALOG1_RIGHT
        ])
        demul.store()
    }
}
