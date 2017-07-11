package mapping.configs

import mapping.ResetAllMappings
import org.hs5tb.groospin.base.HyperSpin

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")
// Vaciamos todos los mapeos de JoyToKey
ResetAllMappings.emptyAllJoyToKeyProfiles(hs)

// Configuramos el menu de HyperSpin con teclas
ResetAllMappings.setHyperSpinDefaultKeys(hs)

// Vaciamos joystick y teclas
ResetAllMappings.emptyRetroArch(hs.retroArch)

// Se elimina el default.cfg para que se vuelva a generar vacio, haciendo antes una copia de seguridad
ResetAllMappings.setNoMameCtrlAndDefaultCfg(hs)
ResetAllMappings.setWinViceDefaultKeys(hs)
ResetAllMappings.setPS2DefaultKeys(hs)
ResetAllMappings.setPPSSPP360AndKeys(hs)
ResetAllMappings.setSuperModel3DefaultKeysAndJoy(hs)
ResetAllMappings.setDaphneDefaultKeys(hs)
ResetAllMappings.setGamecubeDefaultKeyboard(hs)
ResetAllMappings.setFourDODefaultKeys(hs)
ResetAllMappings.setZincDefaultKeys(hs)
