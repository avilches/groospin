package mapping

import groovy.xml.MarkupBuilder
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.common.IOTools

/**
 * Created by Alberto on 05-Jul-17.
 */
class Daphne {


    // http://www.daphne-emu.com/mediawiki/index.php/KeyList
    // http://www.daphne-emu.com/mediawiki/index.php/Input

    static int BACKSPACE	= 8
    static int TAB		= 9
    static int CLEAR		= 12
    static int RETURN	= 13
    static int PAUSE		= 19
    static int ESCAPE		= 27
    static int SPACE		= 32
    static int EXCLAIM	= 33
    static int QUOTEDBL	= 34
    static int HASH		= 35
    static int DOLLAR	= 36
    static int AMPERSAND	= 38
    static int QUOTE		= 39
    static int LEFTPAREN	= 40
    static int RIGHTPAREN	= 41
    static int ASTERISK	= 42
    static int PLUS		= 43
    static int COMMA	= 44
    static int MINUS		= 45
    static int PERIOD		= 46
    static int SLASH		= 47
    static int KEY_0		= 48
    static int KEY_1		= 49
    static int KEY_2		= 50
    static int KEY_3		= 51
    static int KEY_4		= 52
    static int KEY_5		= 53
    static int KEY_6		= 54
    static int KEY_7		= 55
    static int KEY_8		= 56
    static int KEY_9		= 57
    static int COLON		= 58
    static int SEMICOLON	= 59
    static int LESS		= 60
    static int EQUALS	= 61
    static int GREATER	= 62
    static int QUESTION	= 63
    static int AT		= 64
    static int LEFTBRACKET	= 91
    static int BACKSLASH	= 92
    static int RIGHTBRACKET	= 93
    static int CARET		= 94
    static int UNDERSCORE	= 95
    static int BACKQUOTE	= 96
    static int KEY_A		= 97
    static int KEY_B		= 98
    static int KEY_C		= 99
    static int KEY_D		= 100
    static int KEY_E		= 101
    static int KEY_F		= 102
    static int KEY_G		= 103
    static int KEY_H		= 104
    static int KEY_I		= 105
    static int KEY_J		= 106
    static int KEY_K		= 107
    static int KEY_L		= 108
    static int KEY_M		= 109
    static int KEY_N		= 110
    static int KEY_O		= 111
    static int KEY_P		= 112
    static int KEY_Q		= 113
    static int KEY_R		= 114
    static int KEY_S		= 115
    static int KEY_T		= 116
    static int KEY_U		= 117
    static int KEY_V		= 118
    static int KEY_W		= 119
    static int KEY_X		= 120
    static int KEY_Y		= 121
    static int KEY_Z		= 122
    static int DELETE		= 127
    static int KP0		= 256
    static int KP1		= 257
    static int KP2		= 258
    static int KP3		= 259
    static int KP4		= 260
    static int KP5		= 261
    static int KP6		= 262
    static int KP7		= 263
    static int KP8		= 264
    static int KP9		= 265
    static int KP_PERIOD	= 266
    static int KP_DIVIDE	= 267
    static int KP_MULTIPLY	= 268
    static int KP_MINUS	= 269
    static int KP_PLUS	= 270
    static int KP_ENTER	= 271
    static int KP_EQUALS	= 272
    static int UP		= 273
    static int DOWN		= 274
    static int RIGHT		= 275
    static int LEFT		= 276
    static int INSERT		= 277
    static int HOME		= 278
    static int END		= 279
    static int PAGEUP	= 280
    static int PAGEDOWN	= 281
    static int F1		= 282
    static int F2		= 283
    static int F3		= 284
    static int F4		= 285
    static int F5		= 286
    static int F6		= 287
    static int F7		= 288
    static int F8		= 289
    static int F9		= 290
    static int F10		= 291
    static int F11		= 292
    static int F12		= 293
    static int F13		= 294
    static int F14		= 295
    static int F15		= 296
    static int NUMLOCK	= 300
    static int CAPSLOCK	= 301
    static int SCROLLOCK	= 302
    static int RSHIFT		= 303
    static int LSHIFT		= 304
    static int RCTRL		= 305
    static int LCTRL		= 306
    static int RALT		= 307
    static int LALT		= 308
    static int RMETA		= 309
    static int LMETA		= 310
    static int LWINDOWS		= 311		/* Left "Windows" key */
    static int RWINDOWS		= 312		/* Right "Windows" key */
    static int ALT_GR		= 313		/* "Alt Gr" key */
    static int COMPOSE	= 314		/* Multi-key compose key */
/* Miscellaneous function keys */
    static int HELP		= 315
    static int PRINT		= 316
    static int SYSREQ		= 317
    static int BREAK		= 318
    static int MENU		= 319
    static int POWER		= 320		/* Power Macintosh power key */
    static int EURO		= 321

    static String ACTION_UP = "KEY_UP"
    static String ACTION_LEFT = "KEY_LEFT"
    static String ACTION_DOWN = "KEY_DOWN"
    static String ACTION_RIGHT = "KEY_RIGHT"
    static String ACTION_START1 = "KEY_START1"
    static String ACTION_START2 = "KEY_START2"
    static String ACTION_BUTTON1 = "KEY_BUTTON1"
    static String ACTION_BUTTON2 = "KEY_BUTTON2"
    static String ACTION_BUTTON3 = "KEY_BUTTON3"
    static String ACTION_COIN1 = "KEY_COIN1"
    static String ACTION_COIN2 = "KEY_COIN2"
    static String ACTION_SKILL1 = "KEY_SKILL1"
    static String ACTION_SKILL2 = "KEY_SKILL2"
    static String ACTION_SKILL3 = "KEY_SKILL3"
    static String ACTION_SERVICE = "KEY_SERVICE"
    static String ACTION_TEST = "KEY_TEST"
    static String ACTION_RESET = "KEY_RESET"
    static String ACTION_SCREENSHOT = "KEY_SCREENSHOT"
    static String ACTION_QUIT = "KEY_QUIT"
    static String ACTION_PAUSE = "KEY_PAUSE"
    static String ACTION_TILT = "KEY_TILT"

    static HashMap allKeys = [
            (ACTION_UP) : [UP],
            (ACTION_LEFT) : [LEFT],
            (ACTION_DOWN) : [DOWN],
            (ACTION_RIGHT) : [RIGHT],
            (ACTION_START1) : [KEY_1],
            (ACTION_START2) : [KEY_2],
            (ACTION_BUTTON1) : [SPACE, LCTRL],
            (ACTION_BUTTON2) : [LALT],
            (ACTION_BUTTON3) : [LSHIFT],
            (ACTION_COIN1) : [KEY_5],
            (ACTION_COIN2) : [KEY_6],
            (ACTION_SKILL1) : [KP_DIVIDE, KEY_Z],
            (ACTION_SKILL2) : [KP_MULTIPLY, KEY_X],
            (ACTION_SKILL3) : [KP_MINUS, KEY_C],
            (ACTION_SERVICE) : [KEY_9],
            (ACTION_TEST) : [F2],
            (ACTION_RESET) : [F3],
            (ACTION_SCREENSHOT) : [F12],
            (ACTION_QUIT) : [ESCAPE],
            (ACTION_PAUSE) : [KEY_P],
            (ACTION_TILT) : [KEY_T]
    ]

    static Map createDefaultMapping() {
        return [:] + allKeys
    }

    static void writeMapping(HyperSpin hs, Map map) {
        println "Daphne: setting keys"
        File f = new File(hs.daphneFolder, "dapinput.ini")
        println f.absolutePath
        Set missingKeys = map.entrySet().findAll { it.value == null }
        if (missingKeys) {
            throw new RuntimeException("Daphne mapping error. Keys with no mapping ${missingKeys.join(", ")}")
        }
        f.text =
"""[KEYBOARD]
${map.entrySet().collect { 
    Collection keys = it.value instanceof Collection ? it.value : [it.value]
    keys = keys + (["0"] * (3 - keys.size()))
    return it.key+" = "+keys.join(" ")
}.join("\n")}
END
"""                
    }

}
