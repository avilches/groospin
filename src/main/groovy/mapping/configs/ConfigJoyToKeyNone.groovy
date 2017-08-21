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

        mappingManager.configureHyperSpinKeys()
        mappingManager.resetRetroArch()
        mappingManager.configPinballs()
        mappingManager.resetMameConfig()
        mappingManager.configCommodoreWinViceKeys()
        mappingManager.configPS2KeysAnd360()
        mappingManager.configSuperModel3KeysAndJoy()
        mappingManager.configDaphneKeys()
        mappingManager.configGamecubeKeyboard()
        mappingManager.configFourDOKeys()
        mappingManager.configZincKeys()
        mappingManager.configNeoRaineKeys()
        mappingManager.configPokeMiniKeys()
        mappingManager.configDICE()
        mappingManager.configNullDcKeys()

        mappingManager.configWii360()
        mappingManager.configPSPKeysAnd360()
        mappingManager.configDemul360()

        mappingManager.mirrorUpdatedFiles(mirrorPath)

    }
}