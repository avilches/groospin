import org.hs5tb.groospin.base.*
import org.hs5tb.groospin.checker.*
import org.hs5tb.groospin.checker.handlers.*

HyperSpin hs = new HyperSpin(
        "D:/Games/HyperSpin-fe",
        "D:/Games/RocketLauncher")

new Checker(hs).addHandler(new HumanInfo()).checkSystem("Amstrad CPC")



