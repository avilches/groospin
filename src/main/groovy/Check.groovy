import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.checker.Checker
import org.hs5tb.groospin.checker.handlers.AllRomsCsvList
import org.hs5tb.groospin.checker.handlers.HaveHtmlList
import org.hs5tb.groospin.checker.handlers.HumanInfo
import org.hs5tb.groospin.checker.handlers.MissingTxtList
import org.hs5tb.groospin.checker.handlers.SystemCsvList

HyperSpin hs = new HyperSpin(
        "D:/Games/HyperSpin-fe",
        "D:/Games/RocketLauncher")

new Checker(hs).
        addHandler(new HumanInfo()).

        checkSystems()

