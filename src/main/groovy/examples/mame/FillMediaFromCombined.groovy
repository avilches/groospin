package examples.mame

import operation.RomMediaOperations
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.Checker
import org.hs5tb.groospin.checker.result.CheckRomResult

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

// Se procesan todos los sistemas basados en MAME, menos MAME
// Se supone que antes se ha ejecutado CombineMediaMame y tenemos en MAME todos los medias mezclados de todos
// los sistemas

def systems = (hs.listSystems().findAll { it.defaultEmulator.name.startsWith("MAME") }*.name) - "MAME"
RLSystem mame = hs.getSystem("MAME")
RomMediaOperations romMediaOperations = new RomMediaOperations(hs)
romMediaOperations.simulation = false
new Checker(hs).
        addHandler(new BaseCheckHandler() {
            @Override
            void romChecked(CheckRomResult checkRomResult) {
                romMediaOperations.copyMedia(checkRomResult.romName, mame, checkRomResult.system)
            }

        }).
        checkSystems(systems)
