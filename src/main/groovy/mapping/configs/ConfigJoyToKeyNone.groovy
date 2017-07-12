package mapping.configs

import mapping.ResetAllMappings
import org.hs5tb.groospin.base.HyperSpin

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")
// Vaciamos todos los mapeos de JoyToKey
ResetAllMappings.emptyAllJoyToKeyProfiles(hs)
ResetAllMappings.setHyperSpinDefaultKeys(hs)
ResetAllMappings.emptyRetroArch(hs.retroArch)
ResetAllMappings.setPinballDefaults()
ResetAllMappings.setNoMameCtrlAndDefaultCfg(hs)
ResetAllMappings.setWinViceDefaultKeys(hs)
ResetAllMappings.setPS2DefaultKeys(hs)
ResetAllMappings.setPPSSPP360AndKeys(hs)
ResetAllMappings.setSuperModel3DefaultKeysAndJoy(hs)
ResetAllMappings.setDaphneDefaultKeys(hs)
ResetAllMappings.setGamecubeDefaultKeyboard(hs)
ResetAllMappings.setFourDODefaultKeys(hs)
ResetAllMappings.setZincDefaultKeys(hs)
ResetAllMappings.setNeoRaineDefaults(hs)

