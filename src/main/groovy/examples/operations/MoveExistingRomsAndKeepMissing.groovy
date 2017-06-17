package examples.operations

import operation.Operations
import operation.RomFileOperations
import org.hs5tb.groospin.base.HyperSpin

List systems = ["Nintendo Entertainment System"]

RomFileOperations operations = new RomFileOperations(new HyperSpin("D:/Games/RocketLauncher"))
operations.simulation = false

/*
Mueve las roms correctas a una carpeta especifica, dejando
las incorrectas en su carpeta original
*/

// String dst = "d:\\Games\\Isos\\Sega Saturn Japan\\out"
// operations.moveRomsTo(dst, [Operations.EXISTS], systems)

/*
Les añade una extension nueva (sin borrar la extensión original) a las roms correctas, dejando
las incorrectas con su extensión original
*/

// operations.addSuffixToRomName(".ok", [Operations.EXISTS], systems)


