import org.hs5tb.groospin.base.*
import org.hs5tb.groospin.checker.*
import org.hs5tb.groospin.checker.handlers.*

HyperSpin hs = new HyperSpin(
        "M:/Arcade",
        "M:/Arcade/RocketLauncher")

new Checker(hs).addHandler(new HumanInfo()).checkSystems(["Sega Genesis", "Sega Mega Drive"])
//new Checker(hs).addHandler(new HumanInfo()).checkSystems()


