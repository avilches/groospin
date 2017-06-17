package joytokey

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.J2K
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.common.IOTools

new HyperSpin("D:/Games/RocketLauncher").listSystems()*.loadJ2KConfig().each { it.empty() }
