package examples.operations

import operation.MediaOperations
import org.hs5tb.groospin.base.HyperSpin

HyperSpin spin = new HyperSpin("D:/Games/RocketLauncher")
MediaOperations operations = new MediaOperations(spin)

// Elimina los medias innecesarios de todos los sistemas
operations.simulation = true
spin.listSystems()*.name.each {
//    operations.deleteUnneeded(it)
}

// Mueve a una subcarpeta los medias innecesarios
// (solo de los sistemas no MAME, ya que los MAME pueden estar combinados)
spin.listSystems().findAll { !it.defaultEmulator.name.startsWith("MAME") }.each {
 //   operations.moveMediaSubfolderTo(it)
}

// Borra los medias no necesarios de los sistemas MAME (menos de MAME)
spin.listSystems().findAll { it.defaultEmulator.name.startsWith("MAME") && it.name != "MAME" }.each {
//    operations.deleteUnneeded(it)
}