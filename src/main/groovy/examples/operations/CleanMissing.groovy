package examples.operations

import operation.DatabaseOperations
import operation.Operations

List systems = ["Sega Mega Drive"]

new DatabaseOperations("D:/Games/RocketLauncher").removeFromDatabase("-with-missing", [Operations.MISSING], systems)
