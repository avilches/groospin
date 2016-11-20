package examples

import groovy.json.internal.IO
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.common.IOTools
import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 20-Nov-16.
 */

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

hs.listSystems().each {
    File specialImagePath = it.newMediaPath("Images/Special")
    specialImagePath.mkdirs()
    File toDelete = new File(specialImagePath, "All Games.png")
    if (toDelete.exists()) {
        toDelete.delete()
        println "$toDelete deleted"
    }
    hs.newHyperSpinMediaFile("_Common/Images/Special").eachFile { File f ->
        if (IOTools.getExtension(f.name).toLowerCase() in ["png", "swf"]) {
            IOTools.copy(f, specialImagePath)
        }
    }
}

hs.withHyperSpinAllSystemSettings { String filename, IniFile ini ->
    ini.put("Special Art C", "active", "true")
    if (ini.dirty) {
        println "Special Art C activated in $filename"
        ini.store()
    }
}

