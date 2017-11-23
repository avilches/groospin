package examples

import mapping.configs.ArcadeSetAT
import mapping.configs.ArcadeSetMurciano
import mapping.configs.ConfigJoyToKeyArcade

import org.hs5tb.groospin.base.HyperSpin
boolean GAMECUBE_DREAMCAST_DEMUL_XBOX360_ONLY = true
boolean GAMECUBE_DREAMCAST_DEMUL_JOYSTICK = false

String drive = "G"

HyperSpin hs = new HyperSpin("${drive}:\\Games\\RocketLauncher")
new ConfigureDriveSettings(hs: hs).cpo("C:\\Users\\hyperspin5tb\\cache")
new ConfigJoyToKeyArcade(hs).execute(new ArcadeSetAT(), GAMECUBE_DREAMCAST_DEMUL_XBOX360_ONLY, new File("${drive}:\\mapping\\arcade"))
new ConfigJoyToKeyArcade(hs).execute(new ArcadeSetMurciano(), GAMECUBE_DREAMCAST_DEMUL_XBOX360_ONLY, new File("${drive}:\\mapping\\arcade"))
