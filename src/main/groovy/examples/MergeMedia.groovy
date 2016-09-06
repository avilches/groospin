package examples

import groovy.io.FileType
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.common.IOTools


HyperSpin hs = new HyperSpin(
        "D:/Games/Hyperspin-fe",
        "D:/Games/RocketLauncher")

File genreDest = hs.findHyperSpinMediaFolderFor("_Common/Genre")

hs.listSystems(false).each { RLSystem system ->
    File mediaGenreFolder = system.findHyperSpinMediaPath("Images/Genre")
    if (!mediaGenreFolder) return
    // println "${mediaGenreFolder.listFiles()?.size()} ${system.name}: ${mediaGenreFolder?.listFiles()}"
    ["Wheel", "Backgrounds"].each { String genreFolder ->
        File origin = new File(mediaGenreFolder, genreFolder)
        if (!origin.directory) return
        File dst = new File(genreDest, genreFolder)
        origin.eachFile(FileType.FILES) { File f ->
            println "cp $f ${new File(dst, f.name)}"
            IOTools.copy(f, new File(dst, f.name))
        }
    }

}