package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.J2K
import org.hs5tb.groospin.common.IOTools
import org.hs5tb.groospin.common.IniFile

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

println "Resetting all JoyToKey profiles..."
// Vaciamos todos los mapeos de JoyToKey
ResetAllMappings.emptyAllJoyToKeyProfiles(hs)

// Configuramos el menu de HyperSpin con teclas
ResetAllMappings.resetHyperSpinMainMenuControls(hs)

// Vaciamos joystick y teclas
ResetAllMappings.resetRetroArch(hs.retroArch)

// Se elimina el default.cfg para que se vuelva a generar vacio, haciendo antes una copia de seguridad
hs.mame.backupAndCleanDefaultCfg()
