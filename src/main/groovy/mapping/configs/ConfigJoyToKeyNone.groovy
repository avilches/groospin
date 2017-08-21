package mapping.configs

import mapping.MappingManager
import org.hs5tb.groospin.base.HyperSpin

class ConfigJoyToKeyNone {

    MappingManager mappingManager
    HyperSpin hs

    ConfigJoyToKeyNone(HyperSpin hs) {
        this.hs = hs
    }


    void execute(File mirrorPath) {
        this.hs = hs
        mappingManager = new MappingManager(hs)
        mappingManager.emptyAllJoyToKeyProfiles()

        mappingManager.setHyperSpinDefaultKeys()
        mappingManager.emptyRetroArch()
        mappingManager.setPinballDefaults()
        mappingManager.setNoMameCtrlAndDefaultCfg()
        mappingManager.setWinViceDefaultKeys()
        mappingManager.setPS2DefaultKeys()
        mappingManager.setSuperModel3DefaultKeysAndJoy()
        mappingManager.setDaphneDefaultKeys()
        mappingManager.setGamecubeDefaultKeyboard()
        mappingManager.setFourDODefaultKeys()
        mappingManager.setZincDefaultKeys()
        mappingManager.setNeoRaineDefaults()
        mappingManager.setPokeMiniDefaults()
        mappingManager.setDICEDefaults()
        mappingManager.setNullDcKeyboardControlled()

        mappingManager.setWiiDefault360()
        mappingManager.setPPSSPP360AndKeys()
        mappingManager.setDemul360()

        mappingManager.mirrorUpdatedFiles(mirrorPath)

    }
}