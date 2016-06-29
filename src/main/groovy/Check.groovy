import org.hs5tb.groospin.base.*
import org.hs5tb.groospin.checker.*
import org.hs5tb.groospin.checker.handlers.*

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


