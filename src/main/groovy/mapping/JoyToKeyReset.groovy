package mapping

import org.hs5tb.groospin.base.HyperSpin

class JoyToKeyReset {

    static void emptyAllProfiles(HyperSpin hs) {
        hs.listAllJoyToKeyProfiles().each { it.empty() }
    }
}

