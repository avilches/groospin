package examples.operations

import operation.MediaOperations
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.HyperSpinDatabase
import org.hs5tb.groospin.base.RLSystem

HyperSpin spin = new HyperSpin("A:/RocketLauncher")
MediaOperations operations = new MediaOperations(spin)
operations.simulation = false

// Elimina los medias innecesarios solo de los sistemas no MAME
spin.listSystems().findAll { !it.defaultEmulator.name.startsWith("MAME") }.each {
    operations.moveMediaSubfolderTo(it)
}

// Combina todos los juegos del MAME en un unico sistema llamado _MAME que no existe realmente en el Main Menu
RLSystem mameCombinedSystem = new RLSystem(hyperSpin: spin, name: "_MAME")
def allMameRomsCombined = spin.listSystems().findAll { it.defaultEmulator.name.startsWith("MAME") }.
        collect { it.listRoms() }.flatten().unique { it.name }

// Escribe la base de datos _MAME.xml ya que la necesita el borrado
HyperSpinDatabase.write(allMameRomsCombined as List, spin.findHyperSpinFile("Databases/_MAME/_MAME.xml"))

// Borra los ficheros que no necesita MAME
operations.deleteUnneeded(mameCombinedSystem)

