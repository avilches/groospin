package examples

import operation.Operations
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.checker.Checker
import org.hs5tb.groospin.checker.handlers.DatabaseTransformer
import org.hs5tb.groospin.checker.handlers.RomDatabase
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult

List systems = null

new Operations(new HyperSpin(
        "D:/Games/HyperSpin-fe",
        "D:/Games/RocketLauncher")).removeFromDatabase("-backup",
        [Operations.MISSING, Operations.NO_VIDEO, Operations.NO_WHEEL],
        systems)
