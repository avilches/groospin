package examples

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.common.IOTools

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

String specialArtFolder = "English"

hs.listSystems().each {
    File specialImagePath = it.newMediaPath("Images/Special")
    specialImagePath.mkdirs()
    hs.newHyperSpinMediaFile("_Common/Images/Special/${specialArtFolder}").eachFile { File f ->
        if (IOTools.getExtension(f.name).toLowerCase() in ["png", "swf"]) {
//            println IOTools.copy(f, specialImagePath)
            println "${f} ${specialImagePath}"
        }
    }
}

return
