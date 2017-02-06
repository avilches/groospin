package examples

import org.hs5tb.groospin.base.*
import org.hs5tb.groospin.checker.*
import org.hs5tb.groospin.checker.handlers.*
import org.hs5tb.groospin.checker.site.RichRomList

String reportRoot = "D:/Games/Soft/Groospin/generated/rich/"
HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")
List systems = ["Double Dragon Collection",
                "Fatal Fury Collection",
                "Final Fantasy Collection",
                "Final Fight Collection",
                "Legend of Zelda Collection",
                "Mega Man Collection",
                "Metal Slug Collection",
                "Mortal Kombat Kollection",
                "Resident Evil Collection",
                "Tekken Collection",
                "Samurai Shodown Collection",
                "Shining Force Collection",
                "Super Mario Collection",
                "Street Fighter Hack Collection",
                "World Heroes Collection"]

new Checker(hs).
        addHandler(new HumanInfo()).
        addHandler(new RichRomList("${reportRoot}/", true)).
        checkSystems(systems)



