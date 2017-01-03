package renamer

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry
import org.apache.commons.compress.archivers.sevenz.SevenZFile
import org.hs5tb.groospin.common.IOTools


/*
Dada una carpeta llena de 7z que contienen dentro una iso con un nombre
distinto (se carga buscando por extension)
- Descomprime la iso y la renombra con el nombre del 7z

- No vale para cues/bin, pues habria que modificar el cue si se renombre el bin
- No vale si el nombre del 7z es incorrecto. Por ejemplo, el 7z es USA y el interno es Spain,
  para este caso es mejor renombrar el 7z para que tenga el nombre de la iso (o bin) de dentro y los
  media. Para esto usar la otra clase.

*/
["\\\\2017B1\\C\\Games\\Isos\\Nintendo GameCube.renamed\\a"].collect{ new File(it)}.each { File folder ->

    folder.eachFile { File sevenz ->

        if (sevenz.name.endsWith(".7z")) {
            un7zAndRen(sevenz)
        }
    }

}

void un7zAndRen(File sevenz) {

    SevenZFile sevenZFile = new SevenZFile(sevenz)
    SevenZArchiveEntry entry = sevenZFile.nextEntry
    String sevenzNameOnly = IOTools.getFilenameWithoutExtension(sevenz.name)
    while (entry!=null){
        String name = entry.name
        if (!name.endsWith(".iso")) {
            println "Aqui no hay una iso! $sevenz -> $name"
            break
        }
        File dst = new File(sevenz.parentFile, sevenzNameOnly+".iso")
        if (!dst.exists() || dst.size() != entry.size) {
            dst << sevenZFile.getCurrentStream()
        }
        entry = sevenZFile.nextEntry
    }
    sevenZFile.close();

}
