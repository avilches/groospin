package joytokey

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.J2K
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.common.IOTools
import org.hs5tb.groospin.common.IniFile

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

hs.listSystems().each { RLSystem system ->

    J2K j2k = system.loadJ2KConfig()
    j2k.presets.xbox360Esc().save()
}