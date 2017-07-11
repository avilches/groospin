package mapping.tools

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.common.IOTools

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

copyDefault(hs, hs.listSystemsRetroArch(), hs.newRocketLauncherFile("Profiles/JoyToKey/HyperSpin/RetroArch.cfg"), true)
copyDefault(hs, hs.listSystemsMAME(), hs.newRocketLauncherFile("Profiles/JoyToKey/HyperSpin/MAME.cfg"), true)

void copyDefault(HyperSpin hs, Collection<RLSystem> systems, File emuProfile, boolean force = false) {
    if (!emuProfile.exists()) {
        println "Default ${emuProfile} profile NOT FOUND!!!"
        return
    }
    systems.each { RLSystem system ->
        File cfg = system.defaultEmulator.name == system.name ?
                hs.newRocketLauncherFile("Profiles/JoyToKey/${system.name}/${system.name}.cfg") :
                hs.newRocketLauncherFile("Profiles/JoyToKey/${system.name}/${system.defaultEmulator.name}/${system.defaultEmulator.name}.cfg")
        if (!cfg.exists() || force) {
            println "Copying: ${emuProfile} -> ${cfg}"
            IOTools.copy(emuProfile, cfg, true)
        }
    }
}
