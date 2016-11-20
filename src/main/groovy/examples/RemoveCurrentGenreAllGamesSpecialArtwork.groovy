package examples

import org.hs5tb.groospin.base.HyperSpin

/**
 * Created by Alberto on 20-Nov-16.
 */

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

hs.listSystems().each {
    File toDelete = it.newMediaPath("Images/Special/All Games.png")
    if (toDelete.exists()) {
        println toDelete
    }
}
