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

    long calculateRomPathSize() {
        long totalSize = 0
        romPathsList?.each { File romFolder -> totalSize += IOTools.folderSize(romFolder) }
        return totalSize
    }

    File findValidRom(String game) {
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
        } else if (defaultEmulator.module == "Casual Games.ahk") {
            romMapping = new Ini().parse(hyperSpin.findRocketLauncherFile("Modules/Casual Games/Casual Games.ini"))
        } else if (defaultEmulator.module == "OpenBOR.ahk") {
            romMapping = new Ini().parse(hyperSpin.findRocketLauncherFile("Modules/OpenBOR/OpenBOR.ini"))
        } else if (defaultEmulator.module == "MUGEN.ahk") {
            romMapping = new Ini().parse(hyperSpin.findRocketLauncherFile("Modules/MUGEN/MUGEN.ini"))
        }
    }


/*
    Set romNames = new TreeSet()
    long totalSize = 0
    EmuConfig emuConfig
    Map exes

    boolean containsRom(String rom) {
        boolean contains = romNames.contains(rom.toLowerCase())
        debug "${systemName} [${contains ? "ok" : "--"}] Rom '${rom}'"
        return contains
    }

    private void loadExesFromIni(File settings, String key) {
        if (!settings.exists()) return
        String currentSection
        String application
        debug "$systemName: loading exes from ${settings.absolutePath}"
        settings.eachLine { String l ->
            l = l.trim()
            if (l.contains("[") && l.contains("]")) {
                currentSection = l.substring(1, l.size() - 1).trim()
                application = null
            } else {
                application = HyperSpin.extract(l, key, application)
                if (application) {
                    File exe = application.startsWith(".") ? hyperSpin.findRocketLauncherFile(application.replaceAll("\\\\", "/")).canonicalFile : new File(application)
                    exes[currentSection.toLowerCase()] = exe
                    if (exe.exists()) {
                        debug "$systemName: [ok] Loading exe [${currentSection}] = ${exe} (${application})"
                    } else {
                        debug "$systemName: [--] Missing exe [${currentSection}] = ${exe} (${application})"
                    }
                    application = null
                }
            }
        }
    }



    boolean existsInAnyRomPath(String fileToCheck, List<String> extensions) {
        File romFound = emuConfig.romPaths.find { File romPath ->
            extensions.find { String extension ->
                File file = new File("${romPath.absolutePath}/${fileToCheck}.${extension}")
                if (file.exists()) return file
                return null
            }
        }
        debug "${systemName} Exe for rom '${fileToCheck}': ${romFound ? romFound.absolutePath : "NOT FOUND!"}"
        return romFound != null
    }

    boolean mustHaveExe() {
        return emuConfig.emulator in ["Casual Games", "MUGEN", "PCLauncher"]
    }

    boolean haveExe(String game) {
        boolean exeInConfig = ((File) exes?.get(game.toLowerCase()))?.exists()
        if (emuConfig.emulator in ["Casual Games", "MUGEN", "OpenBOR"]) {
            // Busca un exe con el mismo nombre de la rom y en una carpeta como: rom/rom.exe
            boolean exeInPath = emuConfig.emulator == "MUGEN" ? existsInAnyRomPath("${game}/MUGE", ["exe"]) : existsInAnyRomPath("${game}/${game}", ["exe"])
            if (exeInPath && exeInConfig) {
                warn "$systemName: La configuración de ${emuConfig.emulator}.ini [${game}] no es necesaria porque ya hay un ejecutable configurado ok (${game}/${game}.exe)"
            } else if ((exeInPath && !exeInConfig) || (!exeInPath && exeInConfig)) {
                return true
            } else {
                warn "$systemName: Missing exe: not '${game}/${game}.exe' in any romPath or [${game}] configuration in '${emuConfig.emulator}/${emuConfig.emulator}.ini'"
            }
        } else if (emuConfig.emulator == "PCLauncher") {
            if (!exeInConfig) {
                warn "$systemName: Missing exe for PCLauncher: not configuration found for [${game}]"
            }
            return exeInConfig
        }
        return false
    }
*/

    List<Rom> listRoms() {
        return hyperSpin.listRoms(name)
    }

    List<String> listRomNames() {
        return hyperSpin.listRomNames(name)
    }
}
