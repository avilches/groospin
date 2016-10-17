package examples

import operation.DatabaseOperations
import operation.Operations

List systems = null

new DatabaseOperations("D:/Games/RocketLauncher").removeFromDatabase("-backup",
        [Operations.MISSING, Operations.NO_VIDEO, Operations.NO_WHEEL],
        systems)
