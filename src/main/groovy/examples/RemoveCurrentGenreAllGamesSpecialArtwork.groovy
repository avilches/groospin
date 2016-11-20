package examples

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 20-Nov-16.
 */

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

hs.listSystems().each {
    File toDelete = it.newMediaPath("Images/Special/All Games.png")
    if (toDelete.exists()) {
        toDelete.delete()
        println "$toDelete deleted"
    }
}

hs.withHyperSpinAllSystemSettings { String filename, IniFile ini ->
    ini.put("Special Art C", "active", "true")
    if (ini.dirty) {
        println "Special Art C activated in $filename"
        ini.store()
    }
}
