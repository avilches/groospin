package examples.operations

import operation.DatabaseOperations
import operation.Operations

List systems = ["Nintendo Wii U"]

def operations = new DatabaseOperations("D:/Games/RocketLauncher")
operations.simulation = false
operations.removeFromDatabase("-with-missing", [Operations.MISSING], systems)
