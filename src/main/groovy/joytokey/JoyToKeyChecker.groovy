package joytokey

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.common.IOTools
import org.hs5tb.groospin.common.IniFile

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

hs.listSystems().each { RLSystem system ->
    IniFile ini = system.loadJ2KConfig().cfg
    Map j1 = ini.getSection("Joystick 1")
    j1.keySet().findAll { j1[it] == "0" || j1[it] == null }.each { j1.remove(it) }
    if (j1.size()) {
        println "${system.name} config J1:\n${j1}"
    }
}