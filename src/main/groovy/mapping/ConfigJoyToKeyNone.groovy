package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.MameIni

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")
// Vaciamos todos los mapeos de JoyToKey
ResetAllMappings.emptyAllJoyToKeyProfiles(hs)

// Configuramos el menu de HyperSpin con teclas
ResetAllMappings.resetHyperSpinMainMenuControls(hs)

// Vaciamos joystick y teclas
ResetAllMappings.resetRetroArch(hs.retroArch)

// Se elimina el default.cfg para que se vuelva a generar vacio, haciendo antes una copia de seguridad
ResetAllMappings.resetMameCtrl(hs)

ResetAllMappings.resetWinVice(hs.winViceFolder)
