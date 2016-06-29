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
        addHandler(new HaveHtmlList("D:/Games/Soft/GrooSpin/report/all.html", true)).
        addHandler(new HaveHtmlList("D:/Games/Soft/GrooSpin/report/have-list.html", false)).
        addHandler(new MissingTxtList("D:/Games/Soft/GrooSpin/report/missing.csv", ";")).
        addHandler(new AllRomsCsvList("D:/Games/Soft/GrooSpin/report/roms.csv", ";")).
        addHandler(new SystemCsvList("D:/Games/Soft/GrooSpin/report/systems.csv", ";")).
        checkSystems()

