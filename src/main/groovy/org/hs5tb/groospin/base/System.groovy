package org.hs5tb.groospin.base

/**
 * Created by Alberto on 12-Jun-16.
 */
class System {
    HyperSpin hyperSpin
    String systemName
    Set romNames = new TreeSet()
    long totalSize = 0
    EmuConfig emuConfig
    Map exes

    boolean containsRom(String rom) {
        boolean contains = romNames.contains(rom.toLowerCase())
        debug "${systemName} [${contains ? "ok" : "--"}] Rom '${rom}'"
        return contains
    }

    void loadExes() {
        exes = [:]
        if (!emuConfig) return
        if (emuConfig.emulator == "PCLauncher") {
            loadExesFromIni(new File(hyperSpin.rlRoot, "Modules/PCLauncher/PCLauncher.ini"), "Application")
            loadExesFromIni(new File(hyperSpin.rlRoot, "Modules/PCLauncher/${systemName}.ini"), "Application")
        } else if (emuConfig.emulator == "Casual Games") {
            loadExesFromIni(new File(hyperSpin.rlRoot, "Modules/Casual Games/Casual Games.ini"), "gamepath")
        } else if (emuConfig.emulator == "MUGEN") {
            loadExesFromIni(new File(hyperSpin.rlRoot, "Modules/MUGEN/MUGEN.ini"), "gamepath")
        } else if (emuConfig.emulator == "MUGEN") {
            loadExesFromIni(new File(hyperSpin.rlRoot, "Modules/MUGEN/MUGEN.ini"), "gamepath")
        }

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


    void calculateRomPathSizeAndloadRomNames() {
        emuConfig?.romPaths?.each { File romPath ->
            if (romPath.directory) {
                romPath.eachFile(FileType.FILES) { File file ->
                    String name = file.name.contains(".") ? file.name.substring(0, file.name.lastIndexOf(".")) : file.name
                    romNames << name.toLowerCase()
                    // println "Adding candidate rom ${file} as ${name.toLowerCase()}"
                }
                romPath.eachFileRecurse(FileType.FILES) { File file ->
                    totalSize += file.size()
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
}
