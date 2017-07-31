package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.J2K
import org.hs5tb.groospin.base.MameIni
import org.hs5tb.groospin.base.RetroArch

class ResetAllMappings {

    /*
    Vacia todos los mapeos de JoyToKey
     */

    static void emptyAllJoyToKeyProfiles(HyperSpin hs) {
        println "- JoyToKey: Empty all profiles:"
        println hs.newRocketLauncherFile("Profiles/JoyToKey").absolutePath + "\\**\\*"
        (hs.listAllJoyToKeyProfiles() + new J2K(hs, "HyperSpin")).each { it.empty() }
    }

    static void setHyperSpinDefaultKeys(HyperSpin hs) {
        HyperSpinMapping.writeMapping(hs, HyperSpinMapping.createDefaultMapping())
        /*
        Resetea los joystick 1 y 2 para que funcionen de fabrica y TODAS las teclas de sistema.
        */
    }

    static void setNoMameCtrlAndDefaultCfg(HyperSpin hs) {
        // MAME si hlsl
        [hs.getMameIni("ini/presets/mame.ini")].each { MameIni mameIni ->
            mameIni.set("waitvsync", "1")   // deshabilitar para graficas integradas
            mameIni.set("hlsl_enable", "1")   // deshabilitar para graficas integradas
        }

        // HBMAME no Vsync
        [hs.getHBMameIni("ini/presets/hbmame.ini")].each { MameIni mameIni ->
            mameIni.set("waitvsync", "1")   // deshabilitar para graficas integradas
            mameIni.set("hlsl_enable", "0")   // deshabilitar para graficas integradas
        }

        [hs.getMameIni("ini/presets/mame.ini"),
         hs.getHBMameIni("ini/presets/hbmame.ini")].each { MameIni mameIni ->
            println "- MAME: reset ctrlr to none:"
            println mameIni.file.absolutePath
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
        }

        hs.mameMapping.backupAndCleanDefaultCfg()
        hs.HBMameMapping.backupAndCleanDefaultCfg()
    }

    static void emptyRetroArch(RetroArch retroArch) {
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
    }

    static void setPS2DefaultKeys(HyperSpin hs) {
        PCSX2Mapping.setDefault360AndKeys(hs)
    }

    static void setPPSSPP360AndKeys(HyperSpin hs) {
        PPSSPPMapping.setDefault360AndKeys(hs)
    }


    static void setGamecubeDefaultKeyboard(HyperSpin hs) {
        [hs.getDolphinGameCubeFolder(),
         hs.getDolphinTriforceFolder()
        ].each { File dolphin ->
            DolphinGamecube.setDefaultKeyboard(dolphin)
        }
    }

    static void setGamecubeDefault360(HyperSpin hs) {
        [hs.getDolphinGameCubeFolder(),
         hs.getDolphinTriforceFolder()
        ].each { File dolphin ->
            DolphinGamecube.setDefault360(dolphin)
        }
    }

    static void setWiiDefault360(HyperSpin hs) {
        DolphinWiiMapping.setDefault360(hs.getDolphinWiiFolder())
    }

    static void setSuperModel3DefaultKeysAndJoy(HyperSpin hs) {
        SuperModelMapping.setDefaultKeysAndJoy(hs)
    }

    static void setWinViceDefaultKeys(HyperSpin hs) {
        WinViceMapping.writeMapping(hs, WinViceMapping.createDefaultMapping())
    }

    static setDaphneDefaultKeys(HyperSpin hs) {
        DaphneMapping.writeMapping(hs, DaphneMapping.createDefaultMapping())
    }

    static setFourDODefaultKeys(HyperSpin hs) {
        FourDOMapping.writeMapping(hs, FourDOMapping.createDefaultMapping())
    }

    static void setZincDefaultKeys(HyperSpin hs) {
        ZincMapping.writeMapping(hs, ZincMapping.createDefaultMapping())
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

    static void setPokeMiniDefaults(HyperSpin hs) {
        PokeMiniMapping.setDefaultKeys(hs)
    }

    static void setNeoRaineDefaults(HyperSpin hs) {
        NeoRaineMapping.writeMapping(hs, NeoRaineMapping.createDefaultMapping())
    }

    static void setNullDcKeyboardControlled(HyperSpin hs) {
        NullDcMapping.setKeyboard(hs.getNullDcFolder())
    }

    static void setDemul360(HyperSpin hs) {
        DemulMapping.set360(hs)
    }

    static void setNullDc360(HyperSpin hs) {
        NullDcMapping.set360(hs.getNullDcFolder())
    }
}

