import org.hs5tb.groospin.base.*
import org.hs5tb.groospin.checker.*
import org.hs5tb.groospin.checker.handlers.*

HyperSpin hs = new HyperSpin(
        "D:/Games/Games/Hyperspin-fe",
        "D:/Games/Games/RocketLauncher")

new Checker(hs).addHandler(new HumanInfo()).checkSystem("Amstrad CPC")



