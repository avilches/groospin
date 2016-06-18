import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.checker.Checker

HyperSpin hs = new HyperSpin(
        "D:/Games/HyperSpin-fe",
        "D:/Games/RocketLauncher")
        //)

Checker checker = new Checker(hs, "D:/Games/Soft/GrooSpin/report")

//hs.listAllSystems()
//hs.listSystems(["Daphne", "Funtech Super Acan"])
//hs.listSystems(["PopCap", "Nintendo Game and Watch", "Cave", "Doujin Soft"])
println checker.check("PC Games").getLongInfo(";")
