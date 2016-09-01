package examples

import operation.Operations
import org.hs5tb.groospin.base.HyperSpin

String dst = "d:/Games/Roms/Sega Mega Drive/out"
List systems = ["Sega Mega Drive"]

new Operations(new HyperSpin(
        "D:/Games/HyperSpin-fe",
        "D:/Games/RocketLauncher")).moveRomsTo([Operations.EXISTS], dst, systems)


