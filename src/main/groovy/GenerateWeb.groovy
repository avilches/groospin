import org.hs5tb.groospin.base.*
import org.hs5tb.groospin.checker.*
import org.hs5tb.groospin.checker.handlers.*
import org.hs5tb.groospin.checker.site.MainWebSite


import com.xlson.groovycsv.CsvParser
import org.hs5tb.groospin.checker.site.RLSystemConfig
import org.hs5tb.groospin.common.ZipUtils

String reportRoot = "D:/Games/Soft/GrooSpin/report"

HyperSpin hs = new HyperSpin(
        "D:/Games/Hyperspin-fe",
        "D:/Games/RocketLauncher")

Map systemIndex = loadConfigFromGoogleDoc()
Map systemIndexGroup = systemIndex.values().groupBy { RLSystemConfig config -> config.type }

def shortConfigJustForTest = ["Arcade":[
        new RLSystemConfig(name:"Atari 8-bit", hidden: true, stable: true, perfect: true),
        new RLSystemConfig(name:"Apple II", hidden: false, stable: false, perfect: false),
        new RLSystemConfig(name:"Sega Saturn", hidden: false, stable: true, perfect: false),
        new RLSystemConfig(name:"American Laser Games", hidden: false, stable: true, perfect: true),
]]




validateSystems(systemIndex.values().findAll { !it.hidden } *.name, hs)

new Checker(hs).
        addHandler(new HumanInfo()).
        /*addHandler(new HaveHtmlList("${reportRoot}/all.html", true)).
        addHandler(new HaveHtmlList("${reportRoot}/have-list.html", false)).
        addHandler(new MissingTxtList("${reportRoot}/missing.csv", ";")).
        addHandler(new AllRomsCsvList("${reportRoot}/roms.csv", ";")).
        addHandler(new SystemCsvList("${reportRoot}/systems.csv", ";")).*/
        addHandler(new MainWebSite("${reportRoot}/website", true)).
        checkSystemGroup(systemIndexGroup)

ZipUtils.zip(["${reportRoot}/all.html", "${reportRoot}/roms.csv", "${reportRoot}/systems.csv"].collect { new File(it) }, new File(reportRoot, "listado.zip"))



private Map loadConfigFromGoogleDoc() {
    String csvText = new URL("https://docs.google.com/spreadsheets/d/1VFqny4apTMERBKwD20Wc9oug4OFiU2pWdu9L4osYOM8/pub?gid=0&single=true&output=csv").text
    def csv = CsvParser.parseCsv(csvText)
    Map systemIndex = [:]
    csv.each {
        RLSystemConfig csvRow = new RLSystemConfig(it)
        if (csvRow.name) {
            systemIndex[csvRow.name] = csvRow
        }
    }
    println "Cargando ${systemIndex.size()} sistemas"

    return systemIndex
}

Checker validateSystems(ArrayList configSystems, HyperSpin hyperSpin) {
    Collection<String> hsSystems = hyperSpin.listSystemNames(true)
    Collection<String> faltan = (hsSystems-configSystems)
    Collection<String> sobran = (configSystems-hsSystems)

    if (faltan) {
        throw new IllegalArgumentException("Faltan por configurar en el xml ${faltan}")
    }
    if (sobran) {
        throw new IllegalArgumentException("Sobran de configurar (no estan en el xml) ${sobran}")
    }
}


