package examples.operations

import examples.mame.CombineMediaMame
import operation.MediaOperations
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.HyperSpinDatabase
import org.hs5tb.groospin.base.RLSystem

HyperSpin spin = new HyperSpin("A:/RocketLauncher")
MediaOperations operations = new MediaOperations(spin)
operations.simulation = false

println "Ejecutar antes ${CombineMediaMame.class.toString()} " +
        "para tener ya en la carpeta Media/_MAME/* todos los medias combinados"

// Combina virtualmente todos los juegos del MAME en un unico sistema llamado _MAME
// (que no existe realmente en el Main Menu)
RLSystem mameCombinedSystem = new RLSystem(hyperSpin: spin, name: "_MAME")
def allMameRomsCombined = spin.listSystems().findAll { it.defaultEmulator.name.startsWith("MAME") }.
        collect { it.listRoms() }.flatten().unique { it.name }

// Escribe la base de datos _MAME.xml ya que la necesita el borrado
HyperSpinDatabase.write(allMameRomsCombined as List, spin.newHyperSpinFile("Databases/_MAME/_MAME.xml"))

// Borra los ficheros innecesarios del sistema _MAME
operations.deleteUnneededMedia(mameCombinedSystem)

