/*
* @author Alberto Vilches (alberto.vilches@)
* @date 4/9/16
* Copyright. All Rights Reserved.
*/
package examples

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.common.IniFile


HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

boolean recreativa = false


def shouldBe(IniFile ini, String should, String section , String... keys) {
    keys.each { String key ->
        if (ini.get(section, key) != should)
            println "${ini.file.parentFile.name}: ${key} ${ini.get(section, key)}"
    }
}

hs.withRocketLauncherInis(hs.listSystemNames().collect { "Settings/$it/RocketLauncher.ini" }) { String filename, IniFile ini ->
    shouldBe(ini, "use_global", "Fade", "Fade_In", "Fade_Out")
    ini.put("Fade", "Fade_In", "use_global")
    ini.put("Fade", "Fade_Out", "use_global")
    ini.remove("Desktop", "Cursor_Size")
    ini.remove("7z", "7z_Extensions")
    ini.put("Keymapper", "Keymapper_Enabled", "use_global")
    ini.put("Desktop", "Hide_Cursor", "use_global")
    ini.put("Desktop", "Hide_Desktop", "use_global")
    ini.put("Desktop", "Hide_Taskbar", "use_global")
    ini.put("Desktop", "Hide_Emu", "use_global")
    ini.put("Desktop", "Hide_Front_End", "use_global")
    ini.put("Desktop", "Suspend_Front_End", "use_global")
    if (ini.dirty) ini.store()
}

hs.withRocketLauncherInis(["Commodore Amiga CD32",
                           "Commodore CDTV",
                           "NEC PC Engine-CD",
                           "NEC PC-FX",
                           "NEC TurboGrafx-CD",
                           "Nintendo 3DS",
                           "Nintendo DS",
                           "Nintendo GameCube",
                           "Nintendo Wii",
                           "Nintendo Wii U",
                           "Panasonic 3DO",
                           "Sega Mega-CD",
                           "SNK Neo Geo CD",
                           "Sega Ages",
                           "Sega CD",
                           "Sega Dreamcast",
                           "Sega Saturn",
                           "Sony PlayStation",
                           "Sony PlayStation 2",
                           "Sony PSP"].collect { "Settings/$it/RocketLauncher.ini" }) { String filename, IniFile ini ->
    ini.put("Fade", "Fade_In", "true")
    if (ini.dirty) ini.store()
}

hs.withRocketLauncherInis("Settings/Global RocketLauncher.ini"){ String filename, IniFile ini ->
    ini.put("Fade", "Fade_In", recreativa as String)
    ini.put("Fade", "Fade_Out", recreativa as String)
    if (ini.dirty) ini.store()
}

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
    ini.put("7z", "7z_Enabled", "false")
    ini.put("Virtual Drive", "Virtual_Drive_Enabled", "false")
    if (ini.dirty) {
        ini.store()
        println "Updating Skipchecks=Rom Only: ${filename}"
    }
}




hs.withHyperSpinSettings("Settings") { String filename, IniFile ini ->
    ini.put("Main", "Use_Last_Game", "true")
    ini.put("Main", "Use_Last_System", "true")
    ini.put("Main", "Enable_Exit_Menu", "true")
    ini.put("Main", "Enable_Exit", "true")
    ini.put("Main", "Exit_Default", "yes")
    if (recreativa) {
        ini.put("Main", "Exit_Action", "shutdown")
        ini.put("AttractMode", "Active", "true")
    } else {
        ini.put("Main", "Exit_Action", "exit") // "shutdown" para recreativas
        ini.put("AttractMode", "Active", "false")
    }

    if (ini.dirty) {
        ini.store()
        println "Updating Main settings: ${filename}"
    }
}

hs.withHyperSpinAllSystemSettings { String filename, IniFile ini ->

    ini.put("navigation", "use_last_game", "true")
    ini.put("navigation", "random_game", "false")

    ini.put("themes", "use_parent_vids", "true")
    ini.put("themes", "use_parent_themes", "true")

    if (recreativa) {
        ini.put("filters", "parents_only", "true") // no clones
        ini.put("sounds", "game_sounds", "true")
        ini.put("sounds", "wheel_click", "true")
    } else {
        ini.put("filters", "parents_only", "false")
        ini.put("sounds", "game_sounds", "false")
        ini.put("sounds", "wheel_click", "false")
    }
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