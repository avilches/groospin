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

//PCLauncherOperations.scanForExecutables("d:\\Games\\PC\\Pack Remasterizados\\")
//return
new PCLauncherOperations(new HyperSpin("D:\\Games\\RocketLauncher")).with {
    //addRom("PC Games", "Mortal Kombat XL", "d:\\Games\\PC\\Microsoft Windows\\Mortal Kombat XL\\Binaries\\Retail\\MKXLauncher.exe")

    addRom("Pack Remasterizados", "Another Metroid 2 Remake", "..\\PC\\Pack Remasterizados\\Another Metroid 2 Remake\\AM2R.exe")
    addRom("Pack Remasterizados", "Another World 20th", "..\\PC\\Pack Remasterizados\\Another World 20th\\anowor.exe")
    addRom("Pack Remasterizados", "Bionic Commando Rearmed", "..\\PC\\Pack Remasterizados\\Bionic Commando Rearmed\\bcr.exe")
    addRom("Pack Remasterizados", "Castle Of Illusion", "..\\PC\\Pack Remasterizados\\Castle of Illusion\\COI.exe")
    addRom("Pack Remasterizados", "Dead Island Retro Revenge", "..\\PC\\Pack Remasterizados\\Dead Island Retro Revenge\\DeadIslandRetroRevenge.exe")
    addRom("Pack Remasterizados", "Dragon's Lair", "..\\PC\\Pack Remasterizados\\Dragon Lair\\dragon's lair.exe")
    addRom("Pack Remasterizados", "Dragon's Lair 2", "..\\PC\\Pack Remasterizados\\Dragon Lair 2\\Dragon's Lair 2.exe")
    addRom("Pack Remasterizados", "DuckTales Remastered", "..\\PC\\Pack Remasterizados\\DuckTales Remastered\\executable\\DuckTales.exe")
    addRom("Pack Remasterizados", "Fossil Echo", "..\\PC\\Pack Remasterizados\\Fossil Echo\\FossilEcho.exe")
    addRom("Pack Remasterizados", "Mighty N9", "..\\PC\\Pack Remasterizados\\Mighty N9\\Binaries\\MN9Game.com")
    addRom("Pack Remasterizados", "Shadow Complex Remasterizado", "..\\PC\\Pack Remasterizados\\Shadow Complex Remasterizado\\Binaries\\Win32\\ShadowComplex-Win32.exe")
    addRom("Pack Remasterizados", "Space Ace", "..\\PC\\Pack Remasterizados\\Space Ace\\Dragon's Lair.exe")
    addRom("Pack Remasterizados", "Z - Steel Soldiers Remastered", "..\\PC\\Pack Remasterizados\\Z - Steel Soldiers Remastered\\Z2.exe")


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
