package examples.operations

import operation.DatabaseOperations
import operation.Operations

/**
 * Elimina de los xml los juegos que no existen.
 * Deja un backup del xml con todos los juegos
 * con el sufijo with-missing.xml
 */


List systems = ["Philips CD-i"]

def operations = new DatabaseOperations("D:/Games/RocketLauncher")
operations.simulation = false
operations.removeFromDatabase("-with-missing", [Operations.MISSING], systems)
