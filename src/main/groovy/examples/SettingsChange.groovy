/*
* @author Alberto Vilches (alberto.vilches@)
* @date 4/9/16
* Copyright. All Rights Reserved.
*/
package examples

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.common.IniFile


HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

def mameSystemNames = hs.listSystems().findAll { it.defaultEmulator?.name?.startsWith("MAME") }*.name
hs.withRocketLauncherInis(mameSystemNames.collect { "Settings/${it}/Emulators.ini"}) { String filename, IniFile ini ->
    ini.put("ROMS", "Rom_Path", "..\\Arcades\\MAME\\roms|..\\Arcades\\MAME\\chds|..\\Arcades\\MAME\\romsfake")
    if (ini.dirty) {
        ini.store()
        println "Updating MAME Rom_Path: ${filename}"
    }
}

hs.withRocketLauncherInis(mameSystemNames.collect { "Settings/$it/RocketLauncher.ini"} ) { String filename, IniFile ini ->
    ini.put("Settings", "Skipchecks", "Rom Only")
    if (ini.dirty) {
        ini.store()
        println "Updating Skipchecks=Rom Only: ${filename}"
    }
}

hs.withRocketLauncherInis(hs.listSystemNames().collect { "Settings/$it/RocketLauncher.ini"} ) { String filename, IniFile ini ->
    ini.put("Keymapper", "Keymapper_Enabled", "use_global")
    if (ini.dirty) {
        ini.store()
        println "Updating Keymapper_Enabled=use_global: ${filename}"
    }
}


hs.withHyperSpinAllSystemSettings { String filename, IniFile ini ->

    ini.put("navigation", "use_last_game", "true")
    ini.put("navigation", "random_game", "false")

    ini.put("themes", "use_parent_vids", "true")
    ini.put("themes", "use_parent_themes", "true")

    ini.put("filters", "parents_only", "false")
    ini.put("filters", "themes_only", "false")
    ini.put("filters", "wheels_only", "false")
    ini.put("filters", "roms_only", "false")

    if (ini.get("Special Art C", "active") != "true") {
        // La imagen molesta es: <sistema>\Images\Special\All genres.png
        println ini.get("Special Art C", "active")+" "+filename
    }

    if (ini.dirty) {
        ini.store()
        println "Updating basic HyperSpin settings: ${filename}"
    }

//    println "${filename}: ${ini.get("navigation", "use_last_game")}"
//    println "${filename}: ${ini.get("navigation", "random_game")}"
//    println "${filename}: ${ini.get("themes", "use_parent_vids")}"

//    println "${filename}: ${ini.get("filters", "parents_only")}"
//    println "${filename}: ${ini.get("filters", "themes_only")}"
//    println "${filename}: ${ini.get("filters", "wheels_only")}"
//    println "${filename}: ${ini.get("filters", "roms_only")}"
}