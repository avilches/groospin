import org.hs5tb.groospin.base.*
import org.hs5tb.groospin.checker.*
import org.hs5tb.groospin.checker.handlers.*

HyperSpin hs = new HyperSpin(
        "D:/Games/Hyperspin-fe",
        "D:/Games/RocketLauncher")

String missingCsv = "D:/Games/Soft/Groospin/report/check-missing.csv"
String reportTxt = "D:/Games/Soft/Groospin/report/human-report.txt"

new Checker(hs).
        addHandler(new HumanInfo(reportTxt, false)).
        addHandler(new MissingTxtList(missingCsv, ";")).
        checkSystems()

// println new File(missingCsv).text

