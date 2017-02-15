package examples.operations

import operation.Operations
import operation.RomFileOperations
import org.hs5tb.groospin.base.HyperSpin

String dst = "d:\\Games\\Isos\\Sega Saturn Japan\\out"
List systems = ["Sega Saturn Japan"]

RomFileOperations operations = new RomFileOperations(new HyperSpin("D:/Games/RocketLauncher"))
operations.simulation = false
operations.moveRomsTo(dst, [Operations.EXISTS], systems)


