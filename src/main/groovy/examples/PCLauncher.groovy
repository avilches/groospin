package examples

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.HyperSpinDatabase
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.base.Rom
import org.hs5tb.groospin.common.IOTools
import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 28-Oct-16.
 */

PCLauncherOperations.scanForExecutables("d:\\Games\\PC\\Microsoft Windows")

new PCLauncherOperations(new HyperSpin("D:\\Games\\RocketLauncher")).with {
    //addRom("PC Games", "Mortal Kombat XL", "d:\\Games\\PC\\Microsoft Windows\\Mortal Kombat XL\\Binaries\\Retail\\MKXLauncher.exe")
    // addRom("PC Games", "Mortal Kombat Arcade Kollection", "d:\\Games\\PC\\Microsoft Windows\\Mortal Kombat Arcade Kollection\\BINARIES\\WIN32\\MKHDGame.exe")
}

class PCLauncherOperations {
    HyperSpin spin

    PCLauncherOperations(HyperSpin spin) {
        this.spin = spin
    }

    static void scanForExecutables(String folder) {
        new File(folder).eachFileRecurse { File f ->
            String ext = IOTools.getExtension(f.name).toLowerCase()
            if (ext in ["exe", "bat"]) {
                println f.absolutePath
            }
        }
    }

    void addRom(String systemName, String rom, String exe) {
        if (!new File(exe).exists()) {
            println exe+" not found!"
            return
        }
        RLSystem system = spin.getSystem(systemName)
        IniFile ini = new IniFile().parse(spin.findRocketLauncherFile("Modules\\PCLauncher\\${systemName}.ini"))
        ini.put(rom, "Application", exe)
        ini.store()

        HyperSpinDatabase spinDatabase = system.loadHyperSpinDatabase()
        spinDatabase.roms << new Rom(name: rom, description: rom)
        spinDatabase.export()
        new File(system.romPathsList[0], rom+".txt").text = ""

    }
}
