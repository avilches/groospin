package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.J2K
import org.hs5tb.groospin.base.MameIni
import org.hs5tb.groospin.base.RetroArch

class ResetAllMappings {

    static File mirror

    static List<File> emptyAllJoyToKeyProfiles(HyperSpin hs) {
        println "- JoyToKey: Empty all profiles:"
        println hs.newRocketLauncherFile("Profiles/JoyToKey").absolutePath + "\\**\\*"
        List<File> files = []
        (hs.listAllJoyToKeyProfiles() + new J2K(hs, "HyperSpin")).each {
            it.empty()
            files << it.cfg.file
            if (mirror) {
                hs.mirrorFile(mirror, it.cfg.file)
            }
        }
        return files
    }

    static void mirrorAllJoyToKeyProfiles(HyperSpin hs) {
        if (mirror) {
            (hs.listAllJoyToKeyProfiles() + new J2K(hs, "HyperSpin")).each {
                hs.mirrorFile(mirror, it.cfg.file)
            }
        }
    }

    static List<File> setHyperSpinDefaultKeys(HyperSpin hs) {
        List<File> files = HyperSpinMapping.writeMapping(hs, HyperSpinMapping.createDefaultMapping())
        if (mirror) {
            files.each { hs.mirrorFile(mirror, it) }
        }
        return files
        /*
        Resetea los joystick 1 y 2 para que funcionen de fabrica y TODAS las teclas de sistema.
        */
    }

    static List<File> setMameCtrlToKeyboard(HyperSpin hs) {
        /*
        Se usa JoyToKey porque SI SE DESENCHUFA EL JOYSTICK CUALQUIER CONFIGURACIÓN QUE SE TENGA ECHA EN EL default.cfg
        SE BORRA. Por lo tanto, cuando se usan mandos que se pueden enchufar y desenchufar, lo mejor es no editar el default.cfg
        y hacer el mapeo en el JoyToKey.
        */
        println "- MAME/HBMAME: Creating 'arcadeAT' ctrlr"
        List<File> files = []

        [hs.mameMapping, hs.HBMameMapping].each { MameMapping it ->
            it.setDefault4PlayersKeys()
            files << it.saveCtrl("arcadeAT")
        }

        println "- MAME/HBMAME: setting ctrlr to 'arcadeAT'"
        [hs.getMameIni("ini/presets/mame.ini"),
         hs.getHBMameIni("ini/presets/hbmame.ini")].each { MameIni mameIni ->
            files <<  mameIni.file
            mameIni.set("ctrlr", "arcadeAT")
            mameIni.save()
        }

        if (mirror) {
            files.each { hs.mirrorFile(mirror, it) }
        }
        return files

    }
    static List<File> setNoMameCtrlAndDefaultCfg(HyperSpin hs) {
        // MAME si hlsl
        List<File> files = []
        hs.getMameIni("ini/presets/mame.ini").with { MameIni mameIni ->
            mameIni.set("waitvsync", "1")   // deshabilitar para graficas integradas
            mameIni.set("hlsl_enable", "1")   // deshabilitar para graficas integradas
            mameIni.save()

        }

        // HBMAME no Vsync
        hs.getHBMameIni("ini/presets/hbmame.ini").with { MameIni mameIni ->
            mameIni.set("waitvsync", "1")   // deshabilitar para graficas integradas
            mameIni.set("hlsl_enable", "0")   // deshabilitar para graficas integradas
            mameIni.save()
        }

        [hs.getMameIni("ini/presets/mame.ini"),
         hs.getHBMameIni("ini/presets/hbmame.ini")].each { MameIni mameIni ->
            println "- MAME: reset ctrlr to none:"
            mameIni.set("mouse", "1")
            mameIni.set("cheat", "1")
            mameIni.set("skip_gameinfo", "1")

            mameIni.set("paddle_device", "mouse")
            mameIni.set("adstick_device", "keyboard")
            mameIni.set("pedal_device", "keyboard")
            mameIni.set("dial_device", "mouse")
            mameIni.set("trackball_device", "mouse")
            mameIni.set("lightgun_device", "mouse")
            mameIni.set("positional_device", "keyboard")
            mameIni.set("mouse_device", "mouse")

            mameIni.set("keyboardprovider", "dinput")  // ensure MAME can read JoyToKey mappings
            mameIni.set("ctrlr", "")
            mameIni.save()

            files << mameIni.file
        }

        files << hs.mameMapping.backupAndCleanDefaultCfg()
        files << hs.HBMameMapping.backupAndCleanDefaultCfg()

        if (mirror) {
            files.each { hs.mirrorFile(mirror, it) }
        }
        return files
    }

    static List<File> emptyRetroArch(RetroArch retroArch) {
        println "- Retroarch reset: empty players button&joystick, force default keys for extra):"
        println retroArch.iniFile.file.absolutePath

        retroArch.iniFile.put("system_directory", ":\\system")
        retroArch.save()

        retroArch.with {
            resetKeysToSystemDefault()
            emptyKeysForPlayer(1, 1)
            emptyKeysForPlayer(2, 2)
            save()
        }

        List<File> files = [retroArch.iniFile.file]
        if (mirror) {
            files.each { retroArch.hyperSpin.mirrorFile(mirror, it) }
        }
        return files
    }

    static List<File> setPS2DefaultKeys(HyperSpin hs) {
        List<File> files = PCSX2Mapping.setDefault360AndKeys(hs)
        if (mirror) {
            files.each { hs.mirrorFile(mirror, it) }
        }
        return files
    }

    static List<File> setPPSSPP360AndKeys(HyperSpin hs) {
        List<File> files = PPSSPPMapping.setDefault360AndKeys(hs)
        if (mirror) {
            files.each { hs.mirrorFile(mirror, it) }
        }
        return files
    }


    static List<File> setGamecubeDefaultKeyboard(HyperSpin hs) {
        List<File> allFiles = []
        [hs.getDolphinGameCubeFolder(),
         hs.getDolphinTriforceFolder()
        ].each { File dolphin ->
            List<File> files = DolphinGamecube.setDefaultKeyboard(dolphin)
            if (mirror) {
                files.each { hs.mirrorFile(mirror, it) }
            }
            allFiles.addAll(files)
        }
        return allFiles
    }

    static List<File> setGamecubeDefault360(HyperSpin hs) {
        List<File> allFiles = []
        [hs.getDolphinGameCubeFolder(),
         hs.getDolphinTriforceFolder()
        ].each { File dolphin ->
            List<File> files = DolphinGamecube.setDefault360(dolphin)
            if (mirror) {
                files.each { hs.mirrorFile(mirror, it) }
            }
            allFiles.addAll(files)
        }
        return allFiles
    }

    static List<File> setWiiDefault360(HyperSpin hs) {
        List<File> files = DolphinWiiMapping.setDefault360(hs.getDolphinWiiFolder())
        if (mirror) {
            files.each { hs.mirrorFile(mirror, it) }
        }
        return files
    }

    static List<File> setSuperModel3DefaultKeysAndJoy(HyperSpin hs) {
        List<File> files = SuperModelMapping.setDefaultKeysAndJoy(hs)
        if (mirror) {
            files.each { hs.mirrorFile(mirror, it) }
        }
        return files
    }

    static List<File> setWinViceDefaultKeys(HyperSpin hs) {
        List<File> files = WinViceMapping.writeMapping(hs, WinViceMapping.createDefaultMapping())
        if (mirror) {
            files.each { hs.mirrorFile(mirror, it) }
        }
        return files
    }

    static List<File> setDaphneDefaultKeys(HyperSpin hs) {
        List<File> files = DaphneMapping.writeMapping(hs, DaphneMapping.createDefaultMapping())
        if (mirror) {
            files.each { hs.mirrorFile(mirror, it) }
        }
        return files
    }

    static List<File> setFourDODefaultKeys(HyperSpin hs) {
        List<File> files = FourDOMapping.writeMapping(hs, FourDOMapping.createDefaultMapping())
        if (mirror) {
            files.each { hs.mirrorFile(mirror, it) }
        }
        return files
    }

    static List<File> setZincDefaultKeys(HyperSpin hs) {
        List<File> files = ZincMapping.writeMapping(hs, ZincMapping.createDefaultMapping())
        if (mirror) {
            files.each { hs.mirrorFile(mirror, it) }
        }
        return files
    }

    static void setPinballDefaults() {

        /*
        Pinball FX2. Ya funciona con Xbox 360 directamente
        */

        /*
        Pinball Arcade. Ya funciona con Xbox directamente
        HKEY_CURRENT_USER\Software\PinballArcade\PinballArcade
         */

        println "****** Future Pinball: Run 'Future Pinball Keys.reg'"
        println "****** Pinball Arcade. Go to menu -> options -> controls -> reset default. HKEY_CURRENT_USER\\Software\\PinballArcade\\PinballArcade"
        println "****** Pinball FX2. Go to menu -> options -> controls -> reset default "
    }

    static void setDICEDefaults(HyperSpin hs) {
        println "****** DICE: Delete folder C:\\Users\\%USERNAME%\\AppData\\Roaming\\dice\\"
    }

    static List<File> setPokeMiniDefaults(HyperSpin hs) {
        List<File> files = PokeMiniMapping.setDefaultKeys(hs)
        if (mirror) {
            files.each { hs.mirrorFile(mirror, it) }
        }
        return files
    }

    static List<File> setNeoRaineDefaults(HyperSpin hs) {
        List<File> files = NeoRaineMapping.writeMapping(hs, NeoRaineMapping.createDefaultMapping())
        if (mirror) {
            files.each { hs.mirrorFile(mirror, it) }
        }
        return files
    }

    static List<File> setNullDcKeyboardControlled(HyperSpin hs) {
        List<File> files = NullDcMapping.setKeyboard(hs.getNullDcFolder())
        if (mirror) {
            files.each { hs.mirrorFile(mirror, it) }
        }
        return files
    }

    static List<File> setDemul360(HyperSpin hs) {
        List<File> files = DemulMapping.set360(hs)
        if (mirror) {
            files.each { hs.mirrorFile(mirror, it) }
        }
        return files
    }

    static List<File> setNullDc360(HyperSpin hs) {
        List<File> files = NullDcMapping.set360(hs.getNullDcFolder())
        if (mirror) {
            files.each { hs.mirrorFile(mirror, it) }
        }
        return files
    }
}

