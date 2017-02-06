package examples.operations

import operation.Operations
import operation.RomFileOperations
import org.hs5tb.groospin.base.HyperSpin

String dst = "d:\\Games\\Roms\\Nintendo DS\\out"
List systems = ["Nintendo DS"]

RomFileOperations operations = new RomFileOperations(new HyperSpin("D:/Games/RocketLauncher"))
operations.simulation = false
operations.moveRomsTo(dst, [Operations.EXISTS], systems)


