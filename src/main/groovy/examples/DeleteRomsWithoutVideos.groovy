package examples

import operation.DatabaseOperations
import operation.Operations
import operation.RomFileOperations
import org.hs5tb.groospin.base.HyperSpin

def spin = new HyperSpin("A:/RocketLauncher")

def systems = spin.listSystems().findAll { it.defaultEmulator.name == "RetroArch"}*.name


// Primero se añade un sufijo a las roms que no tienen video ni wheel
RomFileOperations romOperations = new RomFileOperations(spin)
romOperations.addSuffixToRomName(".delete", [Operations.NO_VIDEO, Operations.NO_WHEEL], systems)

// Despues se elimina de la base de datos todos los juegos que no tienen video ni wheel
DatabaseOperations dbOperations = new DatabaseOperations(spin)
dbOperations.removeFromDatabase(".backup", [Operations.NO_VIDEO, Operations.NO_WHEEL], systems)

// Finalmente ya se pueden eliminar los ".delete" si se desea (manualmente)
