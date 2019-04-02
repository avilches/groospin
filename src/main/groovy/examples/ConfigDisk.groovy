package examples

import mapping.configs.ArcadeSetAT
import mapping.configs.ConfigJoyToKeyArcade
import mapping.configs.ConfigJoyToKeyNone
import mapping.configs.ConfigJoyToKeyXbox360
import org.hs5tb.groospin.base.HyperSpin


// Mi disco D:
//MapDriver.run("D", "D", false, Mapeo.xbox360)


// TIPICO DISCO EN D: para Xbox360
// MapDriver.run("G", "X", false, Mapeo.xbox360)

// TIPICO DISCO EN OTRA UNIDAD pare recreativa
MapDriver.run("E", "D", false, Mapeo.arcade)


class MapDriver {
    static void run(String currentDrive, String desired, boolean recreativa, Mapeo mapeo) {

        new ChangeDrive(simulation: false).run(currentDrive, desired)

        HyperSpin hs = new HyperSpin("${currentDrive}:\\Games\\RocketLauncher")
        if (recreativa) {
            new ConfigureDriveSettings(hs: hs).recreativa()
        } else {
            new ConfigureDriveSettings(hs: hs).disco()
        }
        if (mapeo == Mapeo.xbox360)
            new ConfigJoyToKeyXbox360(hs).execute(new File("${currentDrive}:\\mapping\\360"))
        else if (mapeo == Mapeo.arcade)
            new ConfigJoyToKeyArcade(hs).execute(new ArcadeSetAT(), true, new File("${currentDrive}:\\mapping\\arcade"))
        else if (mapeo == Mapeo.none)
            new ConfigJoyToKeyNone(hs).execute(new File("${currentDrive}:\\mapping\\reset"))

    }
}
enum Mapeo {
    xbox360, arcade, none
}