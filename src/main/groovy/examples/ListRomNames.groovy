package examples

import org.hs5tb.groospin.base.HyperSpin

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")
println hs.getSystem("Nintendo Wii U").listRoms().collect{ it.description}.join("\n- ")
