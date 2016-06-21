package org.hs5tb.groospin.base

import org.hs5tb.groospin.common.IOTools
import org.hs5tb.groospin.common.Ini

/**
 * Created by Alberto on 12-Jun-16.
 */
class RLSystem {
    HyperSpin hyperSpin
    String name
    RLEmulator defaultEmulator

    String iniRomPath
    String iniDefaultEmulator
    List<File> romPathsList

    Ini romMapping

    File findValidRom(String game) {
        if (defaultEmulator.module == "ScummVM.ahk") {
            String path = romMapping.get(game, "path")
            if (path) {
                File scummRomFolder = hyperSpin.findRocketLauncherFile(path)
                if (scummRomFolder.exists() && scummRomFolder.directory) {
                    return scummRomFolder
                }
            }
            return null
        }

        for (String ext in defaultEmulator.romExtensions) {
            File rom = IOTools.findFileInFolders(romPathsList, game+ "." + ext)
            rom = rom ?: IOTools.findFileInFolders(romPathsList, game + "/" + game + "." + ext)
            if (rom) return rom
        }
        return null
    }

    boolean needsExecutable() {
        return defaultEmulator.module in ["MUGEN.ahk", "OpenBOR.ahk", "Casual Games.ahk", "PCLauncher.ahk"]
    }

    File findExecutable(String romName, File romFile) {
        File romPath = romFile.directory ? romFile : romFile.parentFile
        if (defaultEmulator.module == "PCLauncher.ahk") {
            String application = romMapping.get(romName, "application")
            return application ? hyperSpin.findRocketLauncherFile(application) : null
        } else if (defaultEmulator.module == "MUGEN.ahk") {
            String iniGamePath = romMapping.get(romName, "gamepath")
            List<String> candidates = iniGamePath ? ["/" + iniGamePath] : []
            candidates.addAll(["/" + romName + "/MUGEN.exe"])
            return IOTools.findFilesInFolder(romPath, candidates)
        } else if (defaultEmulator.module == "OpenBOR.ahk") {
            String iniGamePath = romMapping.get(romName, "gamepath")
            List<String> candidates = iniGamePath ? ["/" + iniGamePath] : []
            candidates.addAll(["/" + romName + "/OpenBOR.exe", "/OpenBOR.exe"])
            return IOTools.findFilesInFolder(romPath, candidates)
        } else if (defaultEmulator.module == "Casual Games.ahk") {
            String iniGamePath = romMapping.get(romName, "gamepath")
            if (iniGamePath && new File(iniGamePath).exists()) return new File(iniGamePath).canonicalFile
            List<String> candidates = iniGamePath ? ["/" + iniGamePath] : []
            candidates.addAll(["/" + romName +".exe", "/" + romName + "/" + romName +".exe"])
            return IOTools.findFilesInFolder(romPath, candidates)
        }
        return null
    }


    void loadMapping() {
        if (!defaultEmulator) return
        if (defaultEmulator.module == "PCLauncher.ahk") {
            romMapping = new Ini().parse(
                    hyperSpin.findRocketLauncherFile("Modules/PCLauncher/${name}.ini"))
            romMapping.parent = new Ini().parse(
                    hyperSpin.findRocketLauncherFile("Modules/PCLauncher/PCLauncher.ini"))
        } else if (defaultEmulator.module == "ScummVM.ahk") {
            Ini moduleIni = new Ini().parse(hyperSpin.findRocketLauncherFile("Modules/ScummVM/ScummVM.ini"), "Settings", ["CustomConfig"])
            String customConfig = moduleIni.get("Settings", "CustomConfig")
            romMapping = new Ini()
            if (customConfig) {
                romMapping = romMapping.parse(hyperSpin.findRocketLauncherFile(customConfig))
            }
        } else if (defaultEmulator.module == "Casual Games.ahk") {
            romMapping = new Ini().parse(hyperSpin.findRocketLauncherFile("Modules/Casual Games/Casual Games.ini"))
        } else if (defaultEmulator.module == "OpenBOR.ahk") {
            romMapping = new Ini().parse(hyperSpin.findRocketLauncherFile("Modules/OpenBOR/OpenBOR.ini"))
        } else if (defaultEmulator.module == "MUGEN.ahk") {
            romMapping = new Ini().parse(hyperSpin.findRocketLauncherFile("Modules/MUGEN/MUGEN.ini"))
        }
    }

    List<Rom> listRoms() {
        return hyperSpin.listRoms(name)
    }

    List<String> listRomNames() {
        return hyperSpin.listRomNames(name)
    }
}
