package operation

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.checker.Checker
import org.hs5tb.groospin.checker.handlers.DatabaseTransformer
import org.hs5tb.groospin.checker.handlers.RomDatabase
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult

HyperSpin hs = new HyperSpin(
        "D:/Games/HyperSpin-fe",
        "D:/Games/RocketLauncher")


Closure condition = { CheckRomResult checkRomResult -> checkRomResult.romName.contains("(Japan)")}

split(hs, condition, "Japan", "NoJapan", ["Sega Genesis"])

void split(HyperSpin hs, Closure condition, String suffixYes, String suffixNo, List systems) {
    new Checker(hs).
            addHandler(new DatabaseTransformer() {
                @Override
                void romNodeChecked(CheckRomResult checkRomResult, RomDatabase romNode) {
                    if (!condition.call(checkRomResult)) {
                        romNode.remove()
                    }
                }

                @Override
                void endDatabaseUpdate(CheckTotalResult checkResult) {
                    saveDatabaseTo("${checkResult.systemName}-${suffixYes}.xml")
                }

            }).
            addHandler(new DatabaseTransformer() {
                @Override
                void romNodeChecked(CheckRomResult checkRomResult, RomDatabase romNode) {
                    if (condition.call(checkRomResult)) {
                        romNode.remove()
                    }
                }

                @Override
                void endDatabaseUpdate(CheckTotalResult checkResult) {
                    saveDatabaseTo("${checkResult.systemName}-${suffixNo}.xml")
                }

            }).
            checkSystems(systems)
}