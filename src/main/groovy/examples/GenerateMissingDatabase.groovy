package examples

import operation.DatabaseOperations
import operation.Operations

List systems = ["Sega Mega Drive"]

new DatabaseOperations("D:/Games/RocketLauncher").extractFromDatabase("-missing", [Operations.MISSING], systems)