/*
* @author Alberto Vilches (alberto.vilches@)
* @date 4/9/16
* Copyright. All Rights Reserved.
*/
package examples

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.common.IniFile


HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

// hs.changeHyperSpinSettings("AAE", "exe info", "hola", "que tal4")

hs.withHyperSpinAllSystemSettings { String filename, IniFile ini ->

    ini.put("navigation", "use_last_game", "true")
    ini.put("navigation", "random_game", "false")

    ini.put("themes", "use_parent_vids", "true")
    ini.put("themes", "use_parent_themes", "true")

    ini.put("filters", "parents_only", "false")
    ini.put("filters", "themes_only", "false")
    ini.put("filters", "wheels_only", "false")
    ini.put("filters", "roms_only", "false")

    if (ini.dirty) ini.store()

//    println "${filename}: ${ini.get("navigation", "use_last_game")}"
//    println "${filename}: ${ini.get("navigation", "random_game")}"
//    println "${filename}: ${ini.get("themes", "use_parent_vids")}"

//    println "${filename}: ${ini.get("filters", "parents_only")}"
//    println "${filename}: ${ini.get("filters", "themes_only")}"
//    println "${filename}: ${ini.get("filters", "wheels_only")}"
//    println "${filename}: ${ini.get("filters", "roms_only")}"
}