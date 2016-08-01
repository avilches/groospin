import org.hs5tb.groospin.base.*
import org.hs5tb.groospin.checker.*
import org.hs5tb.groospin.checker.handlers.*

HyperSpin hs = new HyperSpin(
        "M:/Arcade",
        "M:/Arcade/RocketLauncher")

new Checker(hs).addHandler(new HumanInfo()).checkSystem("Sony PlayStation 2")
//new Checker(hs).addHandler(new HumanInfo()).checkSystems()


