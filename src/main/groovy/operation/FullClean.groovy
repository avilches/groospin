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
                // limpia los juegos que no estan, no se ven bien (sin video y ruedas) o son clones
                if (!checkRomResult.exes ||    // si no existe el juego
                    romNode.cloneof ||         // es un clon
                    !checkRomResult.videos ||  // no tiene video
                    !checkRomResult.wheels     // no tiene rueda
                ) {
                    romNode.remove()
                }
            }

            @Override
            void endDatabaseUpdate(CheckTotalResult checkResult) {
                backupOriginalDatabaseAndSave("${checkResult.systemName}-with-missing.xml")
            }

        }).
        checkSystems()