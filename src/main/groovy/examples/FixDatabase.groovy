package examples

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.HyperSpinDatabase
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.base.Rom

HyperSpin hs = new HyperSpin(
        "D:/Games/HyperSpin-fe",
        "D:/Games/RocketLauncher")


// ["Castlevania Collection", "Commodore CDTV", "Microsoft MS-DOS"].collect { hs.getSystem(it) }.each { RLSystem system ->
hs.listSystems(false).each { RLSystem system ->
    if (!system.defaultEmulator?.module?.contains("MAME")) {
        File systemDb = system.findSystemDatabaseFile()
        println systemDb
        File file = new File(systemDb.parent.replaceAll("Databases", "DatabasesFixed"))
        file.parentFile.mkdirs()
        HyperSpinDatabase.fixDatabase(systemDb, file, { Rom rom -> rom.genre })
//    }
    }
}