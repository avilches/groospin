package mapping

import org.hs5tb.groospin.base.HyperSpin

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")
// Vaciamos todos los mapeos de JoyToKey
ResetAllMappings.emptyAllJoyToKeyProfiles(hs)

// Configuramos el menu de HyperSpin con teclas
ResetAllMappings.resetHyperSpinMainMenuControls(hs)

// Vaciamos joystick y teclas
ResetAllMappings.resetRetroArch(hs.retroArch)

// Se elimina el default.cfg para que se vuelva a generar vacio, haciendo antes una copia de seguridad
ResetAllMappings.resetMameCtrl(hs)
ResetAllMappings.resetWinViceKeys(hs)
ResetAllMappings.resetPS2Keys(hs)
ResetAllMappings.resetPPSSPP360AndKeys(hs)
ResetAllMappings.resetSuperModel3KeysAndJoy(hs)
ResetAllMappings.resetDaphneKeys(hs)
ResetAllMappings.resetGamecubeKeyboard(hs)
ResetAllMappings.resetPanasonic3DOKeys(hs)
