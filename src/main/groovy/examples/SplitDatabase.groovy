package examples

import operation.DatabaseOperations
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.checker.result.CheckRomResult

Closure conditions = { CheckRomResult checkRomResult ->
    checkRomResult.romName.contains("(Japan)")
}

new DatabaseOperations(new HyperSpin("D:/Games/RocketLauncher")).
        split("-Japan", "-NoJapan", [conditions], ["Sega Genesis"])

