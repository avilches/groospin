package examples.operations

import operation.Operations
import operation.RomFileOperations
import org.hs5tb.groospin.base.HyperSpin

String dst = "A:/out"
List systems = ["Sega Mark III"]

RomFileOperations operations = new RomFileOperations(new HyperSpin("A:/RocketLauncher"))
operations.simulation = false
operations.moveRomsTo(dst, [Operations.EXISTS], systems)


