package examples

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.HyperSpinDatabase
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.base.Rom

//HyperSpinDatabase.rewriteDatabase(new File("B:\\Best of MAME Alex.xml"))
//return

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

// Fix one database
HyperSpinDatabase.rewriteDatabase(hs.getSystem("Nintendo Wii U").loadHyperSpinDatabase(), null, { Rom rom -> rom.genre })
return

// Fix all

// ["Castlevania Collection", "Commodore CDTV", "Microsoft MS-DOS"].collect { hs.getSystem(it) }.each { RLSystem system ->
// ["Sony PSP Minis", "Sega Ages" , "Super Nintendo Entertainment System"].collect { hs.getSystem(it) }.each { RLSystem system ->
hs.listSystems(false).each { RLSystem system ->
    if (!system.defaultEmulator?.module?.startsWith("MAME")) {
        HyperSpinDatabase originalDb = new HyperSpinDatabase().load(system.getDatabaseFile()).loadGenres()
        Map genres = originalDb.romsByGenre?:[:]

        File newSystemFixedFolder = new File(originalDb.db.parent.replaceAll("Databases", "DatabasesFixed"))
        println "Writing ${newSystemFixedFolder}"
        HyperSpinDatabase.rewriteDatabase(originalDb, newSystemFixedFolder, { Rom rom -> rom.genre })

        HyperSpinDatabase fixedDb = new HyperSpinDatabase().load(new File(newSystemFixedFolder, "${system.name}.xml")).loadGenres()
        Map fixedGenres = fixedDb.loadGenres().romsByGenre?:[:]

        if (!fixedGenres && genres) {
            println "    [O] Usar generos antiguos: ${genres.keySet()}"
            originalDb.validateGenres()
        } else if (fixedGenres && !genres) {
            println "    [N] Usar generos nuevos: ${fixedGenres.keySet()}"
        } else if (!fixedGenres && !genres) {
            println "    [.] No hay generos antiguos ni nuevos..."
        } else {
            println "    [n] ${fixedGenres.size()} genres created. New: ${fixedGenres.keySet()-genres.keySet()}"
            println "    [o] ${genres.size()} old genres. Lost: ${genres.keySet()-fixedGenres.keySet()}"
            originalDb.validateGenres()
        }

        println "-------------------------------"
    }
}