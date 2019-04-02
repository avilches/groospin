package examples

import mapping.configs.ArcadeSetAT
import mapping.configs.ArcadeSetJulioPandoraBox4S
import mapping.configs.ConfigJoyToKeyArcade
import mapping.configs.ConfigJoyToKeyXbox360
import org.hs5tb.groospin.base.HyperSpin

HyperSpin hs = new HyperSpin("F:\\Games\\RocketLauncher")
new ConfigureDriveSettings(hs: hs).disco()
// new ConfigJoyToKeyArcade(hs).execute(new ArcadeSetJulioPandoraBox4S(), false)
//new ConfigJoyToKeyXbox360(hs).execute(new File("D:\\mapping\\360"))



