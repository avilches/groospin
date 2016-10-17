package examples

import operation.Operations
import operation.RomFileOperations
import org.hs5tb.groospin.base.HyperSpin

String dst = "A:/out"
List systems = ["Nintendo Game Boy Advance"]

new RomFileOperations(new HyperSpin("A:/RocketLauncher")).moveRomsTo(dst, [Operations.EXISTS], systems)


