package org.hs5tb.groospin

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.checker.CheckResult
import org.hs5tb.groospin.checker.Checker
import spock.lang.IgnoreRest
import spock.lang.Unroll

/*
 * hs5tb
 *
 * @author Alberto Vilches (alberto.vilches@hs5tb)
 * @date 13/6/16
 * Copyright. All Rights Reserved.
 */

class CheckerSpec extends HSSpecification {

    @Unroll
    void "basic rom and media check"() {
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

        and: "Solo tiene valor cuando se le pasa un juego solo"
        checkResult.game == null

        and:
        checkResult.roms == 2
        checkResult.works == 2
        checkResult.exes == 0

        and: "No existen las carpetas de roms = 0 bytes"
        checkResult.totalSize == 0
    }

    @Unroll
    void "basic single rom and media check"() {
        setup:
        HyperSpin hs = createDefaultHS()
        Checker checker = new Checker(hs)

        when:
        CheckResult checkResult = checker.check("aae", ROM)

        then:
        checkResult.systemName == "aae"

        // El sistema AAE tiene 3 juegos, uno de ellos tiene todos los medias y losotros ninguno
        checkResult.games == 1
        checkResult.artwork1 == 0
        checkResult.artwork2 == 0
        checkResult.artwork3 == 0
        checkResult.artwork4 == 0
        checkResult.wheels == 0
        checkResult.videos == 0
        checkResult.themes == 0

        and: "Solo tiene valor cuando se le pasa un juego solo"
        checkResult.game == ROM

        and:
        checkResult.roms == 0
        checkResult.works == 0
        checkResult.exes == 0

        and: "No existen las carpetas de roms = 0 bytes"
        checkResult.totalSize == 0

        where:
        ROM << ["x", "alphaona"]
    }

    @Unroll
    void "mugen exes"() {
        setup:
        HyperSpin hs = createDefaultHS()
        Checker checker = new Checker(hs)

        when:
        CheckResult checkResult = checker.check("mugen", ROM)

        then:
        checkResult.game == ROM
        checkResult.roms == ROMS
        checkResult.exes == EXES
        checkResult.works == WORKS

        where:
        ROM                      | ROMS | EXES | WORKS
        "nothing"                | 0    | 0    | 0
        "rom only"               | 1    | 0    | 0
        "rom only and mugenexe"  | 1    | 1    | 1
        "rom and gamepath"       | 1    | 1    | 1

    }

    @Unroll
    void "openbor exes"() {
        setup:
        HyperSpin hs = createDefaultHS()
        Checker checker = new Checker(hs)

        when:
        CheckResult checkResult = checker.check("OpenBOR", ROM)

        then:
        checkResult.game == ROM
        checkResult.roms == ROMS
        checkResult.exes == EXES
        checkResult.works == WORKS

        where:
        ROM                      | ROMS | EXES | WORKS
        "nothing"                | 0    | 0    | 0
        "rom only"               | 1    | 0    | 0
        "rom only and exe"       | 1    | 1    | 1
        "rom and gamepath"       | 1    | 1    | 1
    }

}