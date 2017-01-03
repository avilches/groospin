package renamer

import operation.RomMediaOperations
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry
import org.apache.commons.compress.archivers.sevenz.SevenZFile
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.HyperSpinDatabase
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.common.IOTools


/*
Dada una carpeta llena de 7z que contienen dentro una iso con un nombre
distinto (se carga buscando por extension)
- Mira por dentro el 7z (no vale con zips todavia) y mira el veradero nombre
  del juego si es iso/cue
- Con el verdadero nombre del juego, renombra el 7z, renombra la rom en el database y
  todos los medias

*/
String systemName = "Sony PlayStation 2"
boolean simulation = false


HyperSpin hs = new HyperSpin("\\\\2017b1\\c\\Games\\RocketLauncher")
RomMediaOperations romMediaOperations = new RomMediaOperations(hs)
romMediaOperations.simulation = simulation
RLSystem system = hs.getSystem(systemName)
HyperSpinDatabase database = system.loadHyperSpinDatabase()


["\\\\2017b1\\c\\Games\\Isos\\Sony PlayStation 2.renamed\\"].collect{ new File(it) }.each { File folder ->
    folder.eachFile { File compressedRom ->
        if (!compressedRom.name.endsWith(".zip") && !compressedRom.name.endsWith(".7z")) {
            return
        }
        String compressedRomName = IOTools.getFilenameWithoutExtension(compressedRom)
        String compressedRomExt = IOTools.getExtension(compressedRom)
        String internalRomName = compressedRom.name.endsWith(".7z")?un7zAndGetRomName(compressedRom):un7zAndGetRomName(compressedRom)
        File newCompressedRomFile = new File(compressedRom.parentFile, internalRomName + "." + compressedRomExt)
        if (internalRomName == compressedRomName) {
            println "[.] Ignorando ${compressedRom.absolutePath}"
        } else if (!internalRomName) {
            println "[!] Atencion! ${compressedRom.absolutePath} no tiene juegos dentro!"
        } else if (!database.renameRomName(compressedRomName, internalRomName)) {
            println "[!] Juego no catalogado ${compressedRom.absolutePath} (${internalRomName})"
        } else {
            println "[*] Renombrando rom y duplicando medias ${compressedRom.absolutePath} -> ${newCompressedRomFile.name}"
            if (!simulation) {
                IOTools.move(compressedRom, newCompressedRomFile, true)
            }
            romMediaOperations.renameMedia(compressedRomName, system, internalRomName, false, true)
        }
    }
}
database.export(new File(database.db.parentFile, database.db.name+".renamed.${System.currentTimeMillis()}"))

String un7zAndGetRomName(File sevenz) {
    SevenZFile sevenZFile
    try {
        sevenZFile = new SevenZFile(sevenz)
        SevenZArchiveEntry entry = sevenZFile.nextEntry
        while (entry != null) {
            String name = entry.name
            if (name.endsWith(".iso") || name.endsWith(".cue")) {
                return IOTools.getFilenameWithoutExtension(name)
            }
            entry = sevenZFile.nextEntry
        }
    } catch(Exception e) {
        println "[!] Error intentando abrir ${sevenz.absolutePath}"
        e.printStackTrace()
    } finally {
        sevenZFile?.close()
    }
    return null
}
