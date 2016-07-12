import org.hs5tb.groospin.base.*
import org.hs5tb.groospin.checker.*
import org.hs5tb.groospin.checker.handlers.*

HyperSpin hs = new HyperSpin(
        "/Users/avilches/Games/Hyperspin-fe",
        "/Users/avilches/Games/RocketLauncher")

new Checker(hs).addHandler(new HumanInfo()).checkSystem("Amstrad CPC")



