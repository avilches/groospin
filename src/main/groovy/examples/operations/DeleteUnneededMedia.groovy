package examples.operations

import operation.MediaOperations
import org.hs5tb.groospin.base.HyperSpin

HyperSpin spin = new HyperSpin("A:/RocketLauncher")
MediaOperations operations = new MediaOperations(spin)

// Elimina los medias innecesarios de todos los sistemas
operations.simulation = true
spin.listSystems()*.name.each {
    operations.deleteUnneeded(it)
}