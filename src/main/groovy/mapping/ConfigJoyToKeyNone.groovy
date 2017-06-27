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

MameIni mameIni = hs.getMameIni("ini/presets/mame.ini")
println "MAME: Set dinput keyboard and no ctrlr: ${mameIni.file.absolutePath}"
mameIni.set("keyboardprovider", "dinput")  // ensure MAME can read JoyToKey mappings
mameIni.set("ctrlr", "")
mameIni.save()

// Se elimina el default.cfg para que se vuelva a generar vacio, haciendo antes una copia de seguridad
hs.mameMapping.backupAndCleanDefaultCfg()
