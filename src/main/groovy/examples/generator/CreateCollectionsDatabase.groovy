package examples.generator

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
creator.create("Pokemon Classics Collection",[
        "[Nintendo Super Game Boy] *"
]) { Rom rom ->
    rom.containsTexts(["pokemon","pokken tournament", "pokepark", "pikachu"]) && !rom.cloneof

}

/*
creator.create("Star Trek",[
        "[AAE] startrek",
        "[Vintage Commercials] *",
        "[Nintendo Super Game Boy] *",
        "[Sega Genesis] Star Trek - Deep Space Nine - Crossroads of Time (USA)",
        "[Texas Instruments TI 99-4A] Star Trek",
        "[Apple II] Star Trek - Strategic Operations Simulator (USA)",
        "[Atari 2600] Star Trek - Strategic Operations Simulator (USA)",
        "[Commodore 64] Star Trek - Strategic Operations Simulator (USA)",
]) { Rom rom ->
    rom.containsTexts(["Star Trek", "StarTrek"]) && !rom.cloneof

}

/*
creator.create("Contra Collection",[
        "[Vintage Commercials] *",
        "[Future Pinball] *",
        "[Commodore 64] *",
        "[Microsoft MS-DOS eXoDOS] Contraption Zack (1992)",
        "[MAME] pc_cntra",
        "[Nintendo Super Game Boy] Contra - The Alien Wars (USA)",
        "[Microsoft MS-DOS eXoDOS] International Bridge Contractors (1985)",
        "[Sony PlayStation 2] Hitman - Contracts (Spain)",
        "[Zemmix Neo] Contra (Japan)",
        "[Amstrad CPC] Contraption (Europe)",
]) { Rom rom ->
    rom.containsTexts(["Contra", "gryzor", "Probotector"]) && !rom.cloneof

}


creator.create("Resident Evil Collection",[
        "[Vintage Commercials] *",
        "[Future Pinball] *",
]) { Rom rom ->
    rom.containsTexts(["Resident Evil"]) && !rom.cloneof

}
/*
creator.create("Castlevania Collection", [
        "[Vintage Commercials] *",
        "[Commodore 64] Castlevania (USA)",
        "[Nintendo Super Game Boy] Castlevania Legends (USA, Europe)"
]) { Rom rom ->
    rom.containsTexts(["Castlevania", "Haunted Castle"])
}

creator.create("Sonic Mega Collection",[
        "[Vintage Commercials] *",
        "[VTech CreatiVision] Sonic Invader (Asia, Europe)",
        "[Tiger Game.com] Sonic Jam (USA)",
        "[Super Nintendo Entertainment System] *",
        "[Sega Genesis] Aerobiz Supersonic (USA)",
        "[Amstrad CPC] *",
        "[Atari 8-bit] *",
        "[Microsoft Windows 3.x] *",
        "[Sega Nomad] *",
        "[Commodore 64] *",
        "[Commodore Amiga] *",
        "[MAME] sblast2b",
        "[MAME] sonicbom",
        "[MAME] soniccar",
        "[MAME] sonicwi2",
        "[MAME] sonicwi3",
        "[MUGEN] *",
        "[Zinc] *",
        "[WoW Action Max] *",
        "[Sharp X68000] *",
        "[Nintendo Satellaview] *",
        "[NEC TurboGrafx-16] *",
        "[Nintendo DS] Dragon Ball Z - Supersonic Warriors 2 (USA)",
        "[Nintendo Pokemon Mini] Sonic Arena Color",
]) { Rom rom ->
    return rom.containsTexts(["Sonic"]) && !rom.cloneof && !rom.name.contains("2 Games in 1") && !rom.name.contains("Dragon") && !rom.name.contains("+")
}
/*
creator.create("Super Mario Collection",[
        "[Vintage Commercials] *",
        "[Commodore 64] *",
        "[Nintendo Satellaview] *",
        "[Atari 2600] Mario Bros. (USA)",
        "[Atari 8-bit] Mario Bros. (USA)",
        "[Atari 7800] Mario Bros. (USA)",
        "[Nintendo Entertainment System] Mario's Time Machine (USA)",
        "[Nintendo Entertainment System] Mario Is Missing! (USA)",
        "[Super Nintendo Entertainment System] Super Mario All-Stars + Super Mario World (USA)",
        "[Nintendo Super Game Boy] *",
        "[Microsoft MS-DOS] *",
        "[Microsoft MS-DOS eXoDOS] *",
        "[Nintendo Game Boy Color] Mario Golf (USA)",
        "[Nintendo Game Boy Color] Mario Tennis (USA)",
        "[Sega Genesis] Mario Andretti Racing (USA, Europe)",
        "[Sega Genesis] Mario Lemieux Hockey (USA, Europe)",
        "[Visual Pinball] *",
        "[Flash Games] *",
        "[PC Games] *",
        "[Nintendo 64DD] *",
        "[Atari XEGS] *"
//        "[Microsoft MSX2] *",
]) { Rom rom ->
    rom.containsTexts(["mario","smash brosh"]) && !rom.cloneof && !rom.name.contains("PlayChoice")
}

creator.create("Legend of Zelda Collection",[
        "[Vintage Commercials] *",
        "[Amstrad CPC] *",
        "[Nintendo Satellaview] *",
        "[Nintendo Super Game Boy] *",
]) { Rom rom ->
    rom.containsTexts(["Zelda"]) && !rom.cloneof

}


/*

creator.create("Mortal Kombat Kollection",[
        "[MUGEN] Mortal Kombat 1",
        "[Nintendo Game Boy] Mortal Kombat 3 (USA)",
        "[Nintendo Super Game Boy] Mortal Kombat 4 (USA, Europe)",
        "[Super Nintendo Entertainment System] Mortal Kombat (USA)",
        "[Sega Genesis] Mortal Kombat 3 (USA)",
        "[Sega Genesis] Ultimate Mortal Kombat 3 (USA)",
        "[Sega Game Gear] Mortal Kombat II (World)",
        "[Sega Saturn] Ultimate Mortal Kombat 3 (USA)",
        "[Sega Game Gear] Mortal Kombat 3 (Europe)",
        "[Sony PlayStation 2] Mortal Kombat - Deadly Alliance (Europe) (En,Fr,De,Es,It)",
        "[Tiger Game.com] Mortal Kombat Trilogy (USA)",
]) { Rom rom ->
    rom.containsTexts(["Mortal Kombat"]) && !rom.cloneof

}

creator.create("World Heroes Collection",[
        "[Nintendo Super Game Boy] World Heroes 2 Jet (USA, Europe)",
        "[Super Nintendo Entertainment System] World Heroes (USA)",
]) { Rom rom ->
    return rom.containsTexts(["World Heroes","Neo Geo Battle Coliseum"]) && !rom.cloneof
}



def kings = HyperSpinDatabase.loadRoms(new File("d:\\Games\\HyperSpin-fe\\Databases\\The King of Fighters Collection\\The King of Fighters Collection-no info.xml")).name
creator.create("The King of Fighters Collection",[
]) { Rom rom ->
    rom.containsTexts(kings) && !rom.cloneof
}


creator.create("Ghost'n Goblins Collection") { Rom rom ->
    return rom.containsTexts(["ghosts'n", "ghosts 'n", "ghouls'n", "ghouls 'n",
    "Gargoyle's Quest", "Demon's Crest", "Ghosts to Glory", "Army of Zin", "Makaimura"]) && !rom.containsText("Gremlin")
}


def roms = HyperSpinDatabase.loadRoms(new File("m:\\Arcade\\Databases\\Street Fighter Hack Collection\\Street Fighter Hack Collection.xml")).name
creator.create("Street Fighter Hack Collection",[
]) { Rom rom ->
    rom.containsTexts(roms)
}

creator.create("Shining Force Collection",[
]) { Rom rom ->
    rom.containsTexts(["Shining Force","Shining in the Darkness",
                       "Shining the Holy Ark","Shining Wisdom","Shining Soul",
                       "Shining Tears","Shining Hearts"]) && !rom.cloneof
}
/*creator.find("Shining Force Collection",[
//        "[Nintendo Game Boy Color] Dragon Warrior III (USA)",
//        "[Nintendo Super Game Boy] Dragon Warrior I & II (USA)",
//        "[Microsoft MSX2] *",
]) { Rom rom ->
    rom.containsTexts(["Shining Force","Shining in the Darkness",
                       "Shining the Holy Ark","Shining Wisdom","Shining Soul",
                       "Shining Tears","Shining Hearts"]) && !rom.cloneof
}
/*creator.create("Dragon Warrior Collection",[
        "[Nintendo Game Boy Color] Dragon Warrior III (USA)",
        "[Nintendo Super Game Boy] Dragon Warrior I & II (USA)",
        "[Microsoft MSX2] *",
]) { Rom rom ->
    rom.containsTexts(["Dragon Quest","Dragon Warrior"]) && !rom.cloneof
}
/*creator.create("Samurai Shodown Collection",[
        "[Nintendo Super Game Boy] *",
        "[Panasonic 3DO] *",
        "[Sega CD] *",
        "[Sega Genesis] *",
        "[Sega Game Gear] *",
//        "[Zinc] *",
]) { Rom rom ->
    rom.containsTexts(["Samurai Shodown"]) && !rom.cloneof
}
/*creator.create("Tekken Collection",[
        "[Future Pinball] *",
        "[Zinc] *",
]) { Rom rom ->
    rom.containsTexts(["Tekken"]) && !rom.cloneof

}
/*creator.create("Metal Slug Collection",[
        "[Future Pinball] *",
]) { Rom rom ->
    rom.containsTexts(["Metal SLug"]) && !rom.cloneof

}
/*
creator.find("Mortal Kombat Kollection",[
        "[Future Pinball] *",
]) { Rom rom ->
    rom.containsTexts(["Metal SLug"]) && !rom.cloneof

}
creator.create("Mega Man Collection",[
        "[Atari 5200] *",
        "[Commodore 64] *",
        "[Future Pinball] *",
        "[MUGEN] *",
        "[Microsoft MS-DOS] *",
        "[Nintendo Super Game Boy] *",
]) { Rom rom ->
    rom.containsTexts(["Mega Man", "megaman"]) && !rom.cloneof



creator.create("Final Fight Collection",[
        "[Amstrad CPC] *",
        "[Commodore 64] *",
        "[Commodore Amiga] *",
        "[Commodore CDTV] *",
        "[Sharp X68000] *",
        "[Nintendo Entertainment System] Mighty Final Fight (USA)",
        "[Nintendo Entertainment System] Street Fighter 2010 - The Final Fight (USA)",
]) { Rom rom ->
    rom.containsTexts(["Final Fight"]) && !rom.cloneof

}


creator.create("Final Fantasy Collection",[
        "[Nintendo DS] Final Fantasy III (USA)",
]) { Rom rom ->
    rom.containsTexts(["Final Fantasy"]) && !rom.cloneof
}

creator.create("Fatal Fury Collection",[
        "[Sega CD] Fatal Fury Special (USA)",
        "[Sega Game Gear] Fatal Fury Special (USA)",
        "[Sega Genesis] Fatal Fury (USA)"
]) { Rom rom ->
    rom.containsTexts(["Fatal Fury", "Garou - mark"]) && !rom.cloneof
}


creator.create("Double Dragon Collection", ["[Atari 2600] *",
        "[Atari 7800] *",
        "[Sega Mark III] *",
        "[Amstrad CPC] *",
        "[Microsoft MS-DOS] *",
        "[Technos] *",
        "[Commodore 64] *",
        "[Commodore Amiga] *",
        "[Nintendo Game Boy] Battletoads-Double Dragon (USA)",
        "[Nintendo Game Boy] Double Dragon (USA, Europe)",
        "[Sega Game Gear] Double Dragon (USA, Europe)",
        "[Sega Genesis] Battletoads & Double Dragon (USA)",
        "[Sega Genesis] Double Dragon (USA, Europe)",
        "[Sega Genesis] Double Dragon V - The Shadow Falls (USA)",
        "[Microsoft MSX] *",
        "[Sega Master System] Double Dragon (World)"]) { Rom rom ->
    rom.containsTexts(["Double Dragon", "Rage of the Dragons"]) && !rom.cloneof
}



// https://www.mobygames.com/game-group/ghost-n-goblins-series
//creator.create("Ghost'n Goblins Collection") { Rom rom ->
//    return rom.containsTexts(["ghosts'n", "ghosts 'n", "ghouls'n", "ghouls 'n",
//    "Gargoyle's Quest", "Demon's Crest", "Ghosts to Glory", "Army of Zin", "Makaimura"]) && !rom.containsText("Gremlin")
//}

creator.create("Donkey Kong Collection", [
        "[Mattel Intellivision] Donkey Kong (World)",
        "[Nintendo Super Game Boy] *",
        "[Apple II] Donkey Kong (USA)",
        "[Atari 2600] *",
        "[Atari 7800] *",
]) { Rom rom ->
    return rom.containsTexts(["donkey kong", "dkong"])
}
/*
creator.create("Bomberman Collection",[
        "[NEC TurboGrafx-16] Bomberman (USA)",
        "[Nintendo Entertainment System] Bomberman (USA)",
        "[Nintendo Super Game Boy] Bomberman GB (USA, Europe)",
        "[Nintendo Super Game Boy] Wario Blast Featuring Bomberman! (USA, Europe)"
]) { Rom rom ->
    return rom.containsTexts(["bomber man","bomberman"])
}
*/

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

    Creator find(String system, List ban = [], boolean all = false, Closure filter) {

        def bannedSystems = (hyperSpin.listSystems().findAll { it.defaultEmulator?.name == "MAME" }*.name - "MAME") + "HBMAME"
        Set oldroms = hyperSpin.getSystem(system).listRoms()

        Map bannedRoms = createBannedRomsMap(ban)

        List roms = []
        boolean collisions = false
        Set used = new HashSet()
        new Checker(hyperSpin).setRomFilter(filter).addHandler(new BaseCheckHandler() {
            @Override
            void romChecked(CheckRomResult checkResult) {
                Rom rom = checkResult.rom
                if (rom.exe ||
                        "*" in bannedRoms[checkResult.systemName.toLowerCase()] ||
                        rom.name.toLowerCase() in bannedRoms[checkResult.systemName.toLowerCase()]) {
                    return
                }
                if (rom.name in used) {
                    print "* COLLISION NAME! "
                    collisions = true
                }
                if (all || checkResult.exes) {
                    println "[${checkResult.systemName}] ${rom.name}${rom.description != rom.name?" \"${rom.description}\"":""}"
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
        }).checkSystems(hyperSpin.listSystemNames(false) - (bannedSystems + system))
        println "Total: ${roms.size()} roms"
        println "Faltan: ${oldroms.collect {"[${it.exe}] ${it.name}"}}"
    }

    List getBannedSystems() {
        return (hyperSpin.listSystems().findAll { it.defaultEmulator?.name == "MAME" }*.name - "MAME") + "HBMAME"
    }

    Creator create(String system, List ban = [], boolean all = false, Closure filter) {

        Map bannedRoms = createBannedRomsMap(ban)

        List roms = []
        Set used = new HashSet()
        boolean collisions = false
        RLSystem systemToCreate = hyperSpin.getSystem(system)
        new Checker(hyperSpin).setRomFilter(filter).addHandler(new BaseCheckHandler() {
            @Override
            void romChecked(CheckRomResult checkResult) {
                Rom rom = checkResult.rom
                if (rom.exe ||
                        "*" in bannedRoms[checkResult.systemName.toLowerCase()] ||
                        rom.name.toLowerCase() in bannedRoms[checkResult.systemName.toLowerCase()]) {
                    return
                }
                if (rom.name in used) {
                    println "* COLLISION IGNORED: [${checkResult.systemName}] ${rom.name} ${rom.description != rom.name?"\"${rom.description}\"":""}"
                    collisions = true
                    return
                }
                if (all || checkResult.exes) {
                    println "[${checkResult.systemName}] ${rom.name} ${rom.description != rom.name?"\"${rom.description}\"":""}"
                    if (!rom.exe) {
                        rom.exe = checkResult.systemName
                    }
                    romMediaOperations.copyMedia(rom.name, checkResult.system, systemToCreate)
                    rom.description = checkResult.systemName+ " > "+rom.description
                    roms << rom
                    used << rom.name
                }
            }
        }).checkSystems(hyperSpin.listSystemNames(false) - (bannedSystems + system))

        println "Writing: ${roms.size()} roms (${romMediaOperations.simulation?"simulation":"real!"})"

        if (!romMediaOperations.simulation && !collisions) {
            hyperSpin.getDatabaseFile(system).renameTo("${hyperSpin.getDatabaseFile(system)}.new")
            HyperSpinDatabase.write(roms, hyperSpin.getDatabaseFile(system))
        }
        mediaOperations.deleteUnneededMedia(systemToCreate)
        return this
    }

    private Map createBannedRomsMap(List ban) {
        Map bannedRoms = [:].withDefault { k -> new HashSet() }
        ban.each {
            String l = it.substring(1)
            def parts = l.split("]")
            bannedRoms[parts[0].trim().toLowerCase()] << parts[1].trim().toLowerCase()
        }
        bannedRoms
    }
}