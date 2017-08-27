package examples

import mapping.configs.ArcadeSetAT
import mapping.configs.ConfigJoyToKeyArcade
import mapping.configs.ConfigJoyToKeyNone
import mapping.configs.ConfigJoyToKeyXbox360
import org.hs5tb.groospin.base.HyperSpin


HyperSpin hs = new HyperSpin("D:\\Games\\RocketLauncher")

new ConfigJoyToKeyXbox360(hs).execute() // new File("D:\\parches\\mapping\\360-nuevo"))
//new ConfigJoyToKeyNone(hs).execute(new File("D:\\parches\\mapping\\reset"))
//new ConfigJoyToKeyArcade(hs).execute(new ArcadeSetAT(), new File("D:\\parches\\mapping\\AT"))

//HyperSpin hs = new HyperSpin("A:\\RocketLauncher")
//new ConfigJoyToKeyArcade(hs).execute(new ArcadeSetAT(), new File("A:\\parches\\mapping\\AT"))