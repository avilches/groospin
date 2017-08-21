package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.J2K
import org.hs5tb.groospin.base.MameIni
import org.hs5tb.groospin.base.RetroArch

class MappingManager {

    HyperSpin hs
    Set updatedFiles = []

    MappingManager(HyperSpin hs) {
        this.hs = hs
    }

    List<File> emptyAllJoyToKeyProfiles() {
        println "- JoyToKey: Empty all profiles:"
        List<File> files = []
        (hs.listAllJoyToKeyProfiles() + new J2K(hs, "HyperSpin")).each {
            it.empty()
            files << it.cfg.file
        }
        updatedFiles.addAll(files)
        return files
    }

    void mirrorUpdatedFiles(File mirror) {
        if (mirror && updatedFiles) {
            println "MIRRORING:"
            updatedFiles.each { File file ->
                println hs.mirrorFile(mirror, file).absolutePath
            }
        }
    }

    List<File> configureHyperSpinKeys() {
        List<File> files = HyperSpinMapping.writeMapping(hs, HyperSpinMapping.createDefaultMapping())
        updatedFiles.addAll(files)
        return files
        /*
        Resetea los joystick 1 y 2 para que funcionen de fabrica y TODAS las teclas de sistema.
        */
    }

    List<File> configMameCtrlrKeys() {
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

        updatedFiles.addAll(files)
        return files

    }
    List<File> resetMameConfig() {
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

        updatedFiles.addAll(files)
        return files
    }

    List<File> resetRetroArch() {
        RetroArch retroArch = hs.retroArch
        println "- Retroarch reset: empty players button&joystick, force default keys for extra):"

        retroArch.with {
            resetKeysToSystemDefault()
            emptyKeysForPlayer(1, 1)
            emptyKeysForPlayer(2, 2)
            save()
        }

        List<File> files = [retroArch.iniFile.file]
        updatedFiles.addAll(files)
        return files
    }

    List<File> configPS2KeysAnd360() {
        List<File> files = PCSX2Mapping.setDefault360AndKeys(hs)
        updatedFiles.addAll(files)
        return files
    }

    List<File> configPSPKeysAnd360() {
        List<File> files = PPSSPPMapping.setDefault360AndKeys(hs)
        updatedFiles.addAll(files)
        return files
    }


    List<File> configGamecubeKeyboard() {
        List<File> allFiles = []
        [hs.getDolphinGameCubeFolder(),
         hs.getDolphinTriforceFolder()
        ].each { File dolphin ->
            List<File> files = DolphinGamecube.setDefaultKeyboard(dolphin)
            allFiles.addAll(files)
        }
        updatedFiles.addAll(allFiles)
        return allFiles
    }

    List<File> configGamecube360() {
        List<File> allFiles = []
        [hs.getDolphinGameCubeFolder(),
         hs.getDolphinTriforceFolder()
        ].each { File dolphin ->
            List<File> files = DolphinGamecube.setDefault360(dolphin)
            allFiles.addAll(files)
        }
        updatedFiles.addAll(allFiles)
        return allFiles
    }

    List<File> configWii360() {
        List<File> files = DolphinWiiMapping.setDefault360(hs.getDolphinWiiFolder())
        updatedFiles.addAll(files)
        return files
    }

    List<File> configSuperModel3KeysAndJoy() {
        List<File> files = SuperModelMapping.setDefaultKeysAndJoy(hs)
        updatedFiles.addAll(files)
        return files
    }

    List<File> configCommodoreWinViceKeys() {
        List<File> files = WinViceMapping.writeMapping(hs, WinViceMapping.createDefaultMapping())
        updatedFiles.addAll(files)
        return files
    }

    List<File> configDaphneKeys() {
        List<File> files = DaphneMapping.writeMapping(hs, DaphneMapping.createDefaultMapping())
        updatedFiles.addAll(files)
        return files
    }

    List<File> configFourDOKeys() {
        List<File> files = FourDOMapping.writeMapping(hs, FourDOMapping.createDefaultMapping())
        updatedFiles.addAll(files)
        return files
    }

    List<File> configZincKeys() {
        List<File> files = ZincMapping.writeMapping(hs, ZincMapping.createDefaultMapping())
        updatedFiles.addAll(files)
        return files
    }

    void configPinballs() {

        /*
        Pinball FX2. Ya funciona con Xbox 360 directamente
        */

        /*
        Pinball Arcade. Ya funciona con Xbox directamente
        HKEY_CURRENT_USER\Software\PinballArcade\PinballArcade
         */

        println "****** Future Pinball: Run 'Future Pinball Keys.reg'"
        println "****** Pinball Arcade. Run app at least one time"
        println "****** Pinball Arcade. Go to menu -> options -> controls -> reset default. HKEY_CURRENT_USER\\Software\\PinballArcade\\PinballArcade"
        println "****** Pinball FX2. Go to menu -> options -> controls -> reset default "
    }

    void configDICE() {
        println "****** DICE: Delete folder C:\\Users\\%USERNAME%\\AppData\\Roaming\\dice\\"
    }

    List<File> configPokeMiniKeys() {
        List<File> files = PokeMiniMapping.setDefaultKeys(hs)
        updatedFiles.addAll(files)
        return files
    }

    List<File> configNeoRaineKeys() {
        List<File> files = NeoRaineMapping.writeMapping(hs, NeoRaineMapping.createDefaultMapping())
        updatedFiles.addAll(files)
        return files
    }

    List<File> configNullDcKeys() {
        List<File> files = NullDcMapping.setKeyboard(hs.getNullDcFolder())
        updatedFiles.addAll(files)
        return files
    }

    List<File> configDemul360() {
        List<File> files = DemulMapping.set360(hs)
        updatedFiles.addAll(files)
        return files
    }

    List<File> configNullDc360() {
        List<File> files = NullDcMapping.set360(hs.getNullDcFolder())
        updatedFiles.addAll(files)
        return files
    }
}

