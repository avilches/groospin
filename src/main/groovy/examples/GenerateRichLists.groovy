package examples

import org.hs5tb.groospin.base.*
import org.hs5tb.groospin.checker.*
import org.hs5tb.groospin.checker.handlers.*
import org.hs5tb.groospin.checker.site.RichRomList

String reportRoot = "D:/Games/Soft/Groospin/generated/rich/"
HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")
List systems = ["Microsoft MS-DOS"]

new Checker(hs).
        addHandler(new HumanInfo()).
        addHandler(new RichRomList("${reportRoot}/", true)).
        checkSystems(systems)



