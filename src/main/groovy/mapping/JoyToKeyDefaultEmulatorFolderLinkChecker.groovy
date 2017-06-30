package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.J2K
import org.hs5tb.groospin.base.RLSystem

HyperSpin hs = new HyperSpin("G:\\Games\\RocketLauncher")

List MAPPED_EMULATORS = hs.newRocketLauncherFile("Profiles/JoyToKey/HyperSpin").listFiles().findAll {
    it.isDirectory()
}*.name

//MAPPED_EMULATORS.each { emulatorName ->
//    checkMissingFolderLinks(hs, hs.listSystemsByEmu(emulatorName))
//}

checkMissingFolderLinks(hs, hs.listSystems(true))

void checkMissingFolderLinks(HyperSpin hs, Collection<RLSystem> systems) {
    new File("C:\\Users\\ipokampo\\Desktop\\checkMissingFolderLinks.csv").withWriter { out ->
        Map missing = [systemProfileFolders: [], emuProfiles: [], defaultEmuProfileFolderLinks: []]

        println "\n\n${"#".padLeft(3, " ")} - ${"System Name".padRight(45, " ")} ${"Default Emu".padRight(25, " ")} ${"Emu Mapped".padRight(15, " ")} ${"System Profile folder".padRight(25, " ")} ${"Emu Profile folder Link".padRight(25, " ")}\n"
        out << "index,System Name,Default Emu,Emu Mapped,System Profile folder,Emu Profile folder link\n"

        systems.eachWithIndex { RLSystem system, int index ->
            CheckJoyToKeyMissingResult result = new CheckJoyToKeyMissingResult(index: index+1, system: system.name)

            if (system.defaultEmulator?.name) {
                result.defaultEmulator = system.defaultEmulator.name
                result.emuProfile = hs.newRocketLauncherFile("Profiles/JoyToKey/HyperSpin/${system.defaultEmulator.name}")
                if (!result.emuProfile.exists()) {
                    missing.emuProfiles << system.defaultEmulator.name
                }
            }

            J2K j2K = system.loadJ2KConfig()

            if (!j2K.systemProfileFolder.exists()) {
                missing.systemProfileFolders << "mkdir \"$j2K.systemProfileFolder.absolutePath\""
                return result.print(out)
            } else {
                result.systemProfileFolder = j2K.systemProfileFolder
                if (result.emuProfile?.exists() && !j2K.defaultEmuProfileFolderLink?.exists()) {
                    missing.defaultEmuProfileFolderLinks << "mklink /D \"${j2K.defaultEmuProfileFolderLink.absolutePath}\" \"${result.emuProfile.absolutePath}\""
                    return result.print(out)
                } else {
                    result.defaultEmuProfileFolderLink = j2K.defaultEmuProfileFolderLink
                }
            }

            return result.print(out)
        }

        println "\n"

        missing.with {
            if (emuProfiles) {
                println "\n======> WARNING: Some emulators still not mapped:\n"
                emuProfiles.unique().each {
                    println it
                }
            }

            if (systemProfileFolders || defaultEmuProfileFolderLinks) {
                println "\n======> ACTIONS REQUIRED: Some systems have missing profile folders or default emulator folder link, to fix it execute these commands please: \n"
            }

            systemProfileFolders.each {
                println it
            }

            defaultEmuProfileFolderLinks.each {
                println it
            }
            println "\n"
        }
    }
}

void listSystemsAndEmusAndProfiles(HyperSpin hs) {
    new File('C:\\Users\\ipokampo\\Desktop\\systemsEmusProfiles.csv').withOutputStream { out ->
        out << "System Name,Default Emulator,JoyPad Compatible,Joy2Key Profile\n"
        hs.listSystems().each { RLSystem system ->
            def emuProfile = hs.newRocketLauncherFile("Profiles/JoyToKey/HyperSpin/$system.defaultEmulator.name").exists() ?
                hs.newRocketLauncherFile("Profiles/JoyToKey/HyperSpin/$system.defaultEmulator.name").absolutePath : ""
            out << "${system.name},${system.defaultEmulator.name},,${emuProfile}\n"
        }
    }
}

class CheckJoyToKeyMissingResult {
    int index
    String system
    String defaultEmulator = ""
    File emuProfile
    File systemProfileFolder
    File defaultEmuProfileFolderLink

    void print(out) {
        out << csvLine
        println "${(index as String).padLeft(3, " ")} - ${system.padRight(45, ' ')} ${defaultEmulator.padRight(25, " ")} ${hasEmuProfile.padRight(15, " ")} ${hasSystemProfileFolder.padRight(25, " ")} ${hasDefaultEmuProfileFolderLink.padRight(25, " ")}"
    }

    String getCsvLine() {
        "$index,$system,$defaultEmulator,$hasEmuProfile,$hasSystemProfileFolder,$hasDefaultEmuProfileFolderLink\n"
    }

    String getHasEmuProfile() {
        emuProfile && emuProfile.exists() ? 'YES' : 'NO'
    }

    String getHasSystemProfileFolder() {
        systemProfileFolder?.exists() ? 'YES' : 'NO'
    }

    String getHasDefaultEmuProfileFolderLink() {
        defaultEmuProfileFolderLink?.exists() ? 'YES' : 'NO'
    }
}


