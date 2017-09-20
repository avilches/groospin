package examples

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.HyperSpinDatabase
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.base.Rom
import org.hs5tb.groospin.common.IOTools


HyperSpin hs = new HyperSpin("I:/Games/RocketLauncher")
boolean simulation = false
//def systems = hs.listSystemNames()
def systems = ["Sony PlayStation", "Sony PlayStation 2", "Nintendo GameCube", "Nintendo Wii", "Sony PSP", "Sega DreamCast", "Sega Saturn" , "Sega Saturn Japan", "Nintendo DS", "Nintendo 3DS"]
//def systems = ["Sega Mega Drive"]
//def systems = ["Nintendo Wii U"]

systems.each {
    RLSystem system = hs.getSystem(it)
    if (system.defaultEmulator?.module?.contains("MAME") || system.defaultEmulator?.module?.contains("MESS")) return

    int romcount = 0
    HyperSpinDatabase database = system.loadHyperSpinDatabase()
    system.romPathsList.each {
        it.listFiles(new FilenameFilter() {
            @Override
            boolean accept(File dir, String name) {
                return (system.defaultEmulator.romExtensions - "rpx").any { name.endsWith(".${it}") }
            }
        }).each {
            String rom = IOTools.getFilenameWithoutExtension(it)
            if (!database.hasRom(rom)) {
                romcount ++
                //println "[${system.name}] New rom found! ${it.absolutePath}"
                database.addOnlyIfNew(new Rom(name: rom, description: rom))
            }
        }
    }
    println "[${system.name}] ${romcount} New rom found!"
    if (!simulation && romcount) {
        IOTools.move(database.db, new File(database.db.parentFile, database.db.name+".${new Date().format("yyyyMMdd-HHmmss")}.bck"));
        println "NO SIMULATION. Rewriting database ${database.db}"
        HyperSpinDatabase.rewriteDatabase(database)
    }
}