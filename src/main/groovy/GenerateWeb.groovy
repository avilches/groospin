import org.hs5tb.groospin.base.*
import org.hs5tb.groospin.checker.*
import org.hs5tb.groospin.checker.handlers.*
import org.hs5tb.groospin.checker.site.MainWebSite


@Grab('com.xlson.groovycsv:groovycsv:1.0')
import com.xlson.groovycsv.CsvParser
import org.hs5tb.groospin.checker.site.RLSystemConfig


HyperSpin hs = new HyperSpin(
        "/Users/avilches/Games/Hyperspin-fe",
        "/Users/avilches/Games/RocketLauncher")



Map systemIndex = loadConfigFromGoogleDoc()
Map systemIndexGroup = systemIndex.values().groupBy { RLSystemConfig config -> config.type }


def shortConfigJustForTest = ["Arcade":["AAE","American Laser Games", "Rockola"],
                "Computer":["Acorn BBC Micro","Apple II"]]


validateSystems(systemIndex.values()*.name, hs)
new Checker(hs).
        addHandler(new HumanInfo()).
        addHandler(new HaveHtmlList("D:/Games/Soft/GrooSpin/report/all.html", true)).
        addHandler(new HaveHtmlList("D:/Games/Soft/GrooSpin/report/have-list.html", false)).
        addHandler(new MissingTxtList("D:/Games/Soft/GrooSpin/report/missing.csv", ";")).
        addHandler(new AllRomsCsvList("D:/Games/Soft/GrooSpin/report/roms.csv", ";")).
        addHandler(new SystemCsvList("D:/Games/Soft/GrooSpin/report/systems.csv", ";")).
        addHandler(new MainWebSite("D:/Games/Soft/GrooSpin/report/website", true)).
        checkSystemGroup(systemIndexGroup)



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
    return this
}


