package operation

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.Checker
import org.hs5tb.groospin.checker.handlers.DatabaseTransformer
import org.hs5tb.groospin.checker.handlers.RomNode
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult

HyperSpin hs = new HyperSpin(
        "D:/Games/HyperSpin-fe",
        "D:/Games/RocketLauncher")

String dst = "d:\\Games\\Roms\\Sega Mega Drive\\out"
new Checker(hs).
        addHandler(new BaseCheckHandler() {
            @Override
            void romChecked(CheckRomResult checkResult) {
                File exe = checkResult.rom.exeFileFound
                if (exe) {
                    exe.renameTo(new File(dst, exe.name))
                }

            }
        }).
        checkSystems(["Sega Mega Drive"])