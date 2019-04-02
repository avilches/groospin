package examples.operations

import operation.DatabaseOperations
import operation.Operations

/**
 * Elimina de los xml los juegos que no existen.
 * Deja un backup del xml con todos los juegos
 * con el sufijo with-missing.xml
 */


//List systems = ["Sony PlayStation", "Sony PlayStation 2", "Nintendo GameCube", "Nintendo Wii", "Sony PSP", "Sega DreamCast", "Sega Saturn Japan", "Nintendo DS", "Nintendo 3DS"]
//List systems = ["Big Fish Games"]

def operations = new DatabaseOperations("F:/Games/RocketLauncher")
operations.simulation = false
//operations.removeFromDatabase("-with-missing", [Operations.MISSING]) /// , systems)
operations.removeFromDatabase("-with-missing", [Operations.MISSING]) // , ["Sega Genesis"])
