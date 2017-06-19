package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RetroArch

class ResetAllMappings {

    /*
    Vacia todos los mapeos de JoyToKey
     */
    static void emptyAllJoyToKeyProfiles(HyperSpin hs) {
        hs.listAllJoyToKeyProfiles().each { it.empty() }
    }

    /*
    Resetea los joystick 1 y 2 para que funcionen de fabrica u TODAS las teclas de sistema.
    */
    static void resetRetroArch(RetroArch retroArch) {
        retroArch.with {
            resetKeys()
            resetPlayer(1, 1)
            resetPlayer(2, 2)
            save()
        }
    }

}

