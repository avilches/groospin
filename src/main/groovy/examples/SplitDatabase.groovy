package examples

import operation.Operations
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.checker.result.CheckRomResult

Closure conditions = { CheckRomResult checkRomResult ->
    checkRomResult.romName.contains("(Japan)")
}

new Operations(new HyperSpin(
        "D:/Games/HyperSpin-fe",
        "D:/Games/RocketLauncher")).
        split([conditions], "-Japan", "-NoJapan", ["Sega Genesis"])

