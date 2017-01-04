package examples

import org.hs5tb.groospin.base.*
import org.hs5tb.groospin.checker.*
import org.hs5tb.groospin.checker.handlers.*
import org.hs5tb.groospin.checker.site.RichRomList
import com.xlson.groovycsv.CsvParser
import org.hs5tb.groospin.checker.site.RLSystemConfig
import org.hs5tb.groospin.common.ZipUtils

String reportRoot = "D:/Games/Soft/Groospin/generated/rich/${new Date().format("yyyy-MM-dd HH-mm")}"
HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")
List systems = ["Daphne", "Sega Genesis"]

new Checker(hs).
        addHandler(new HumanInfo()).
        addHandler(new RichRomList("${reportRoot}/", true)).
        checkSystems(systems)



