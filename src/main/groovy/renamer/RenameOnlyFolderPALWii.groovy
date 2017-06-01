package renamer

import operation.RomMediaOperations
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry
import org.apache.commons.compress.archivers.sevenz.SevenZFile
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.HyperSpinDatabase
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.base.Rom
import org.hs5tb.groospin.checker.Checker
import org.hs5tb.groospin.checker.RomChecker
import org.hs5tb.groospin.common.IOTools


/*
Dada una carpeta llena de 7z que contienen dentro una iso con un nombre
distinto (se carga buscando por extension)
- Mira por dentro el 7z (no vale con zips todavia) y mira el veradero nombre
  del juego si es iso/cue
- Con el verdadero nombre del juego, renombra el 7z, renombra la rom en el database y
  todos los medias

*/
String systemName = "Nintendo Wii"
boolean simulation = false


HyperSpin hs = new HyperSpin("d:\\Games\\RocketLauncher")
RomMediaOperations romMediaOperations = new RomMediaOperations(hs)
romMediaOperations.simulation = simulation
RLSystem system = hs.getSystem(systemName)
HyperSpinDatabase database = system.loadHyperSpinDatabase()


database.roms.each { Rom rom ->
    File romFile = system.findValidRom(rom.name)

    if (romFile.absolutePath.contains("PAL_")) {
        String newName = rom.name.replaceAll("\\(USA\\)", "(Europe)")
        println "Renaming ${romFile.absolutePath} to ${newName}"
        romMediaOperations.renameMedia(rom.name, system, newName)
        rom.name = newName
        rom.description = newName
    }
}

database.export(new File(database.db.parentFile, database.db.name+".renamed.${System.currentTimeMillis()}"))