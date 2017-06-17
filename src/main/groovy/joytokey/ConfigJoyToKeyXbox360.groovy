package joytokey

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.J2K
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.common.IOTools
import org.hs5tb.groospin.common.IniFile

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

// Clean all systems + EXIT with BACK+START (360)
hs.listSystems()*.loadJ2KConfig().each { J2K j2k ->
    j2k.empty()
    j2k.presets.xbox360Esc(1).xbox360Esc(2).save()
}

// AAE
hs.getSystem("AAE").loadJ2KConfig().presets.with {
    dPadToCursor(1)
    dPadToCursor(2)
    analogToCursor(1)
    analogToCursor(2)
    buttonToKey(1, [
            (XBOX360_A): ALT,
            (XBOX360_B): CTRL,
            (XBOX360_X): SHIFT,
            (XBOX360_Y): SPACE,
            (XBOX360_BACK): KEY_5,
            (XBOX360_START): KEY_1
    ])
    buttonToKey(2, [
            (XBOX360_A): ALT,
            (XBOX360_B): CTRL,
            (XBOX360_X): SHIFT,
            (XBOX360_Y): SPACE,
            (XBOX360_BACK): KEY_5,
            (XBOX360_START): KEY_2
    ])
    xbox360Esc(1)
    xbox360Esc(2)
    save()
}


