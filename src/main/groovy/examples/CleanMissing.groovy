package examples

import operation.DatabaseOperations
import operation.Operations
import org.hs5tb.groospin.base.HyperSpin

List systems = ["Sega Mega Drive"]

new DatabaseOperations("D:/Games/RocketLauncher").removeFromDatabase("-with-missing", [Operations.MISSING], systems)
