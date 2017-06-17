package joytokey

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.J2K
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.common.IOTools

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")
hs.listSystems().each { RLSystem system ->
    system.loadJ2KConfig().empty()
}
