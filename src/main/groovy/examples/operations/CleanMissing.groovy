package examples.operations

import operation.DatabaseOperations
import operation.Operations

/**
 * Elimina de los xml los juegos que no existen.
 * Deja un backup del xml con todos los juegos
 * con el sufijo with-missing.xml
 */


List systems = []

def operations = new DatabaseOperations("A:/Games/RocketLauncher")
operations.simulation = false
operations.removeFromDatabase("-with-missing", [Operations.MISSING], systems)
