import org.hs5tb.groospin.base.HyperSpin

HyperSpin hs = new HyperSpin(
        "D:/Games/HyperSpin-fe",
        "D:/Games/RocketLauncher",
        "D:/Games/Soft/GrooSpin/report")

hs.startReport()

//hs.listAllSystems()
//hs.listSystems(["Daphne", "Funtech Super Acan"])
//hs.listSystems(["PopCap", "Nintendo Game and Watch", "Cave", "Doujin Soft"])
hs.listSystem("MUGEN")
