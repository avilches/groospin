package org.hs5tb.groospin.base

import org.hs5tb.groospin.common.IOTools
import org.hs5tb.groospin.common.IniTools

/**
 * Created by Alberto on 12-Jun-16.
 */
class RLSystem {
    HyperSpin hyperSpin
    String systemName
    RLEmulator defaultEmulator

    String iniRomPath
    String iniDefaultEmulator
    List<File> romPathsList

    Map romMapping

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
        return defaultEmulator.name == "PCLauncher" ||
                systemName in ["MUGEN", "OpenBOR", "PopCap", "Big Fish Games"]
    }

    File findExecutable(String romName, File romFile) {
        File romPath = romFile.directory ? romFile : romFile.parentFile
        romPath.isAbsolute()
        if (defaultEmulator.name == "PCLauncher") {
            Map gameMapping = romMapping[romName]
            if (!gameMapping) {
                return null
            }
            String application = romMapping[romName]["application"]
            return new File(hyperSpin.rlRoot, application)
        } else if (systemName == "MUGEN") {
            String iniGamePath = romMapping[romName]?.get("gamepath")
            List<String> candidates = iniGamePath ? ["/" + iniGamePath] : []
            candidates << ["/" + romName + "/MUGEN.exe"]
            return IOTools.findFilesInFolder(romPath, candidates)
        } else if (systemName == "OpenBOR") {
            String iniGamePath = romMapping[romName]?.get("gamepath")
            List<String> candidates = iniGamePath ? ["/" + iniGamePath] : []
            candidates << ["/" + romName + "/OpenBOR.exe", "/OpenBOR.exe"]
            return IOTools.findFilesInFolder(romPath, candidates)
        } else if (systemName in ["PopCap", "Big Fish Games"]) {
            String iniGamePath = romMapping[romName]?.get("gamepath")
            if (new File(iniGamePath).exists()) return new File(iniGamePath).absoluteFile
            List<String> candidates = iniGamePath ? ["/" + iniGamePath] : []
            candidates << ["/" + romName +".exe", "/" + romName + "/" + romName +".exe"]
            return IOTools.findFilesInFolder(romPath, candidates)
        }
        return null
    }


    void loadMapping() {
        romMapping = [:]
        if (!defaultEmulator) return
        if (defaultEmulator.name == "PCLauncher") {
            addConfig(new File(hyperSpin.rlRoot, "Modules/PCLauncher/PCLauncher.ini"))
            addConfig(new File(hyperSpin.rlRoot, "Modules/PCLauncher/${systemName}.ini"))
        } else if (systemName in ["PopCap", "Big Fish Games"]) {
            addConfig(new File(hyperSpin.rlRoot, "Modules/Casual Games/Casual Games.ini"))
        } else if (systemName == "OpenBOR") {
            addConfig(new File(hyperSpin.rlRoot, "Modules/OpenBOR/OpenBOR.ini"))
        } else if (systemName == "MUGEN") {
            addConfig(new File(hyperSpin.rlRoot, "Modules/MUGEN/MUGEN.ini"))
        }
    }

    void addConfig(File file) {
        romMapping = IniTools.parseIni(file, romMapping)

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
                    File exe = application.startsWith(".") ? new File(hyperSpin.rlRoot, application.replaceAll("\\\\", "/")).canonicalFile : new File(application)
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
}
