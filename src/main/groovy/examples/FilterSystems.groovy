package examples

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem

/**
 * Created by Alberto on 09-Oct-16.
 */
HyperSpin hs = new HyperSpin(
        "D:/Games/HyperSpin-fe",
        "D:/Games/RocketLauncher")


StringBuffer systems = new StringBuffer()
int count = 0
hs.listSystems(false).each { RLSystem system ->
    if (system.defaultEmulator.name in ["MAME", "RetroArch"]) {
        systems << "    <game name=\"${system.name}\"/>\n"
        count ++
    }
}
String main = "<menu>\n${systems}</menu>"
println main
println "Total $count systems"


