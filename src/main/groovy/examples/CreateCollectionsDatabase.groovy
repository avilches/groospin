package examples

import operation.MediaOperations
import operation.RomMediaOperations
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.HyperSpinDatabase
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.base.Rom
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.Checker
import org.hs5tb.groospin.checker.result.CheckRomResult

Creator creator = new Creator(new HyperSpin("D:/Games/RocketLauncher"), false)

creator.create("Killer Instinct Collection") { Rom rom ->
    rom.containsTexts(["Killer Instinct"])

}
//creator.find("Castlevania Collection") { Rom rom ->
//    rom.containsTexts(["Castlevania", "Haunted Castle"])
//}
return
creator.find("Pang Collection") { Rom rom ->
    return rom.containsText("pang")
}


// https://www.mobygames.com/game-group/ghost-n-goblins-series
//creator.create("Ghost'n Goblins Collection") { Rom rom ->
//    return rom.containsTexts(["ghosts'n", "ghosts 'n", "ghouls'n", "ghouls 'n",
//    "Gargoyle's Quest", "Demon's Crest", "Ghosts to Glory", "Army of Zin", "Makaimura"]) && !rom.containsText("Gremlin")
//}
//creator.create("Donkey Kong Collection") { Rom rom ->
//    return rom.containsTexts(["donkey kong", "dkong"])
//}
//creator.create("Bomberman Collection") { Rom rom ->
//    return rom.containsTexts(["bomber man","bomberman"])
//}


class Creator {
    HyperSpin hyperSpin
    RomMediaOperations romMediaOperations
    MediaOperations mediaOperations

    Creator(HyperSpin hyperSpin, boolean simulation) {
        this.hyperSpin = hyperSpin
        romMediaOperations = new RomMediaOperations(hyperSpin)
        mediaOperations = new MediaOperations(hyperSpin)
        romMediaOperations.simulation = mediaOperations.simulation = simulation

    }

    Creator find(String system, boolean all = false, Closure filter) {

        Set oldroms = hyperSpin.getSystem("Castlevania Collection").listRoms()

        List roms = []
        Set used = new HashSet()
        new Checker(hyperSpin).setRomFilter(filter).addHandler(new BaseCheckHandler() {
            @Override
            void romChecked(CheckRomResult checkResult) {
                Rom rom = checkResult.rom
                if (rom.name in used) return
                if (all || checkResult.exes) {
                    println "[${checkResult.systemName}] ${rom.name} ${rom.description != rom.name?"\"${rom.description}\"":""}"
                    if (!rom.exe) {
                        rom.exe = checkResult.systemName
                    }
                    roms << rom
                    used << rom.name

                    Rom already = oldroms.find { it.name == rom.name && it.exe == rom.exe }
                    if (already) {
                        oldroms.remove(already)
                    }
                }
            }
        }).checkSystems(hyperSpin.listSystemNames(false) - system)
        println "Total: ${roms.size()} roms"
        println "Faltan: ${oldroms.collect {"[${it.exe}] ${it.name}"}}"
    }

    Creator create(String system, boolean all = false, Closure filter) {
        List roms = []
        Set used = new HashSet()
        RLSystem systemToCreate = hyperSpin.getSystem(system)
        new Checker(hyperSpin).setRomFilter(filter).addHandler(new BaseCheckHandler() {
            @Override
            void romChecked(CheckRomResult checkResult) {
                Rom rom = checkResult.rom
                if (rom.name in used) return
                if (all || checkResult.exes) {
                    println "[${checkResult.systemName}] ${rom.name} ${rom.description != rom.name?"\"${rom.description}\"":""}"
                    if (!rom.exe) {
                        rom.exe = checkResult.systemName
                    }
                    romMediaOperations.copyMedia(rom.name, checkResult.system, systemToCreate)
                    roms << rom
                    used << rom.name
                }
            }
        }).checkSystems(hyperSpin.listSystemNames(false) - system)

        println "Writing: ${roms.size()} roms (${romMediaOperations.simulation?"simulation":"real!"})"

        if (!romMediaOperations.simulation) {
            HyperSpinDatabase.write(roms, hyperSpin.findSystemDatabaseFile(system))
        }
        mediaOperations.deleteUnneeded(systemToCreate)
        return this
    }
}