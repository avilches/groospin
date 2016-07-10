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
        addHandler(new DatabaseTransformer("D:/Games/HyperSpin-fe/Databases.clean") {
            @Override
            void romNodeChecked(CheckRomResult checkRomResult, RomNode romNode) {
                if (!checkRomResult.exes || romNode.cloneof
                    || !checkRomResult.videos || !checkRomResult.wheels) { // limpia juegos que no se ven bien
                    romNode.remove()
                }
            }

            @Override
            void endSystem(CheckTotalResult checkResult) {
                if (dirty) {
                    backupOriginalDatabaseAndSave("${checkResult.systemName}-with-missing.xml")
                }
            }

        }).
        checkSystems()