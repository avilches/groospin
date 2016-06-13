package org.hs5tb.groospin

import org.hs5tb.groospin.base.HyperSpin
import spock.lang.Specification
import spock.lang.Unroll


/**
 * Created by Alberto on 13-Jun-16.
 */
class HyperSpinSpec extends Specification {

    @Unroll
    void mainMenu() {
        setup:
        HyperSpin hs = createDefaultHS()

        expect:
        hs.getSystems() == ["AAE"]
        hs.getGamesFromSystem("AAE") == ["alienst", "alphaona"]

    }

    private HyperSpin createDefaultHS() {
        new HyperSpin("src/test/resources/HyperSpin", "src/test/resources/RocketLauncher")
    }

}