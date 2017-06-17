package examples.generator

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.HyperSpinDatabase
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.base.Rom
import org.hs5tb.groospin.common.IOTools
import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 28-Oct-16.
 */

PCLauncherOperations.scanForExecutables("Big Fish Games", "d:\\Games\\PC\\BigFish\\")
//return
new PCLauncherOperations(new HyperSpin("D:\\Games\\RocketLauncher")).with {

}

class PCLauncherOperations {
    HyperSpin spin

    PCLauncherOperations(HyperSpin spin) {
        this.spin = spin
    }

    static void scanForExecutables(String system, String folder) {
        new File(folder).eachFileRecurse { File f ->
            String ext = IOTools.getExtension(f.name).toLowerCase()
            if (ext in ["exe", "bat"]) {
                println "addRom(\"${system}\",\"${f.parentFile.name}\",\"${f.absolutePath.replaceAll("\\\\", "/")}\")"
            }
        }
    }

    void addRom(String systemName, String rom, String exe) {
        RLSystem system = spin.getSystem(systemName)

        if (system?.defaultEmulator?.name != "PCLauncher") {
            println "ERROR: emulator not configured to PCLauncher: ${system?.defaultEmulator?.name}"
            return

        }
        if (!system.romPathsList || !system.romPathsList[0]) {
            println "ERROR: No rom path list for system ${systemName}"
            return
        }

        if (!spin.newRocketLauncherFile(exe).exists()) {
            println "NOT FOUND: "+exe+" not found!"
            return
        }
        IniFile ini = new IniFile().parse(spin.newRocketLauncherFile("Modules\\PCLauncher\\${systemName}.ini"))
        ini.put(rom, "Application", exe)
        ini.store()

        HyperSpinDatabase spinDatabase = system.loadHyperSpinDatabase()
        spinDatabase.addOnlyIfNew(new Rom(name: rom, description: rom))
        spinDatabase.export()
        new File(system.romPathsList[0], rom+".txt").text = ""

    }
}
