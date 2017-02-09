package examples

import org.hs5tb.groospin.base.*
import org.hs5tb.groospin.checker.*
import org.hs5tb.groospin.checker.handlers.*

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

def systems = ["Microsoft MS-DOS"]

/*
def systems = [
        "The King of Fighters Collection", "Bomberman Collection", "Castlevania Collection", "DDR Mania", "Donkey Kong Collection",
        "Double Dragon Collection", "Dragon Warrior", "Fatal Fury Collection", "Final Fantasy Collection", "Final Fight Collection",
        "Ghost'n Goblins Collection", "Killer Instinct Collection", "Legend of Zelda Collection", "Mega Man Collection",
        "Metal Slug Collection", "Mortal Kombat Kollection", "Resident Evil Collection", "Samurai Shodown Collection",
        "Shining Force Collection", "Sonic Mega Collection", "Street Fighter Collection", "Street Fighter Hack Collection",
        "Super Mario Collection", "Tekken Collection", "World Heroes Collection"]

//def systems = (hs.listSystems().findAll { it.defaultEmulator.name.startsWith("MAME") }*.name) - "MAME"
/*
def systems = ["HBMAME", "MAME", "MAME 4 Players",
                      "Namco Classics",
                      "Atari Classics", "Capcom Classics", "Cave", "Data East Classics",
                      "Banpresto", "Kaneko", "Irem Classics", "Williams Classics", "Midway Classics",
                      "Sega Classics", "Konami Classics", "Taito Classics", "SNK Classics"].sort()
*/

new Checker(hs).
        addHandler(new HumanInfo(false)).
        addHandler(new PrintMissing()).
        checkSystems()
//        checkSystems(systems)


