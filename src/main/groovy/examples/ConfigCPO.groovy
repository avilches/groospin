package examples

import mapping.configs.ArcadeSetAT
import mapping.configs.ConfigJoyToKeyArcade
import mapping.configs.ConfigJoyToKeyNone
import mapping.configs.ConfigJoyToKeyXbox360
import org.hs5tb.groospin.base.HyperSpin


String drive = "J"

HyperSpin hs = new HyperSpin("${drive}:\\Games\\RocketLauncher")
new ConfigureDriveSettings(hs: hs).cpo()
new ConfigJoyToKeyArcade(hs).execute(new ArcadeSetAT(), new File("${drive}:\\mapping\\arcade"))
