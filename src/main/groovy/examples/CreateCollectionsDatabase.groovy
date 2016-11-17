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

creator.create("Donkey Kong Collection") { Rom rom ->
    return rom.description.toLowerCase().contains("donkey kong") ||
            rom.description.toLowerCase().contains("donkey kong") ||
            rom.name.toLowerCase().contains("dkong") ||
            rom.name.toLowerCase().contains("dkong")
}

creator.create("Bomberman Collection") { Rom rom ->
    return rom.description.toLowerCase().contains("bomber man") ||
            rom.description.toLowerCase().contains("bomberman") ||
            rom.name.toLowerCase().contains("bomber man") ||
            rom.name.toLowerCase().contains("bomberman")
}


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

        if (romMediaOperations.simulation) {
            HyperSpinDatabase.write(roms, hyperSpin.findSystemDatabaseFile(system))
        }
        mediaOperations.deleteUnneeded(systemToCreate)
        return this
    }
}