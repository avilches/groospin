package org.hs5tb.groospin

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.checker.CheckResult
import org.hs5tb.groospin.checker.Checker

/*
 * hs5tb
 *
 * @author Alberto Vilches (alberto.vilches@hs5tb)
 * @date 13/6/16
 * Copyright. All Rights Reserved.
 */

class CheckerSpec extends HSSpecification {

    void "basicCheck"() {
        setup:
        HyperSpin hs = createDefaultHS()
        Checker checker = new Checker(hs)

        when:
        CheckResult checkResult = checker.check("aae")

        then:
        checkResult.systemName == "aae"

        // El sistema AAE tiene 3 juegos, uno de ellos tiene todos los medias y los otros ninguno
        checkResult.games == 3
        checkResult.artwork1 == 1
        checkResult.artwork2 == 1
        checkResult.artwork3 == 1
        checkResult.artwork4 == 1
        checkResult.wheels == 1
        checkResult.videos == 1
        checkResult.themes == 1
        checkResult.game == null // Solo tiene valor cuando se le pasa un juego solo
        checkResult.roms == 2

        // No existen las carpetas de roms = 0 bytes
        checkResult.totalSize == 0
    }

}