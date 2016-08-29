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
                // limpia los clones
                if (romNode.cloneof) {
                    romNode.remove()
                }
            }

            @Override
            void endDatabaseUpdate(CheckTotalResult checkResult) {
                backupOriginalDatabaseAndSave("${checkResult.systemName}-with-clones.xml")
            }

        }).
        checkSystems(["Namco Classics",
                      "Atari Classics", "Capcom Classics", "Cave", "Data East Classics", "MAME",
                      "Banpresto", "Kaneko", "Irem Classics", "Williams Classics", "Midway Classics",
                      "Sega Classics", "Konami Classics", "Taito Classics", "SNK Classics"])