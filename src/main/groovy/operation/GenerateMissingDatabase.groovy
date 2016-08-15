package operation

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.checker.Checker
import org.hs5tb.groospin.checker.handlers.DatabaseTransformer
import org.hs5tb.groospin.checker.handlers.RomNode
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult

HyperSpin hs = new HyperSpin(
        "D:/Games/HyperSpin-fe",
        "D:/Games/RocketLauncher")

new Checker(hs).
        addHandler(new DatabaseTransformer() {
            @Override
            void romNodeChecked(CheckRomResult checkRomResult, RomNode romNode) {
                // limpia los juegos que tengo
                if (checkRomResult.exes) {
                    romNode.remove()
                }
            }

            void endDatabaseUpdate(CheckTotalResult checkResult) {
                saveDatabaseTo("${checkResult.systemName}-missing.xml")
            }

        }).
        checkSystems(["Sega Mega Drive"])