package examples.genres

import org.hs5tb.groospin.base.HyperSpin

/**
 * Created by Alberto on 06-Sep-16.
 */
HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

File genreDest = hs.newHyperSpinMediaFile("_Common/Genre")

hs.listSystems(false).collect {

    println "${it.name}"
    hs.listGenres(it.name)

}.flatten().unique().each { String genre ->
    File f = new File(genreDest, "Wheel/${genre}.png")
    if (!f.exists())
        println "[${f.exists()?"+":" "}] $f"
}
