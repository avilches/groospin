package org.hs5tb.groospin

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.Rom
import org.hs5tb.groospin.checker.result.CheckResult
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult
import org.hs5tb.groospin.checker.Checker
import org.hs5tb.groospin.checker.BaseCheckHandler
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
        Checker checker = new Checker(hs, new BaseCheckHandler())

        when:
        CheckTotalResult checkResult = checker.checkSystemRoms(null, "aae")

        then:
        checkResult.systemName == "aae"

        // El sistema AAE tiene 3 juegos, uno de ellos tiene todos los medias y los otros ninguno
        checkResult.totalRoms == 3
        checkResult.artworks1 == 1
        checkResult.artworks2 == 1
        checkResult.artworks3 == 1
        checkResult.artworks4 == 1
        checkResult.wheels == 1
        checkResult.videos == 1
        checkResult.themes == 1

        and:
        checkResult.roms == 2
        checkResult.exes == 2

        and:
        checkResult.group == null

        and: "No existen las carpetas de roms = 0 bytes"
        checkResult.totalRomSize == 0
    }

    @Unroll
    void "basic single rom and media check"() {
        setup:
        HyperSpin hs = createDefaultHS()
        Checker checker = new Checker(hs, new BaseCheckHandler())

        when:
        CheckTotalResult checkResult = checker.checkSystemRoms("Arcade", "aae", [ROM])

        then:
        checkResult.systemName == "aae"

        and:
        checkResult.group == "Arcade"

        // El sistema AAE tiene 3 juegos, uno de ellos tiene todos los medias y losotros ninguno
        checkResult.totalRoms == 1
        checkResult.artworks1 == 0
        checkResult.artworks2 == 0
        checkResult.artworks3 == 0
        checkResult.artworks4 == 0
        checkResult.wheels == 0
        checkResult.videos == 0
        checkResult.themes == 0

        and:
        checkResult.roms == 0
        checkResult.exes == 0

        and: "No existen las carpetas de roms = 0 bytes"
        checkResult.totalRomSize == 0

        where:
        ROM << ["alphaona"]
    }

    @Unroll
    void "mugen exes"() {
        setup:
        HyperSpin hs = createDefaultHS()
        Checker checker = new Checker(hs, new BaseCheckHandler())

        when:
        CheckTotalResult checkResult = checker.checkSystemRoms(null, "mugen", [ROM])

        then:
        checkResult.totalRoms == 1
        checkResult.roms == ROMS
        checkResult.exes == EXES

        when:
        Rom rom = hs.getSystem("mugen").listRoms([ROM])[0]
        CheckRomResult checkRomResult = checker.romChecker.check(hs.getSystem("mugen"), rom)

        then:
        checkRomResult.romName == ROM
        checkRomResult.roms == ROMS
        checkRomResult.exes == EXES

        and:
        checkRomResult.group == null  // no hay grupo cuando se chequea una rom directamente

        where:
        ROM                      | ROMS | EXES
        "nothing"                | 0    | 0
        "rom only"               | 1    | 0
        "rom only and mugenexe"  | 1    | 1
        "rom and gamepath"       | 1    | 1

    }

    @Unroll
    void "openbor exes"() {
        setup:
        HyperSpin hs = createDefaultHS()
        Checker checker = new Checker(hs, new BaseCheckHandler())

        when:
        CheckResult checkResult = checker.checkSystemRoms(null, "OpenBOR", [ROM])

        then:
        checkResult.roms == ROMS
        checkResult.exes == EXES

        where:
        ROM                      | ROMS | EXES
        "nothing"                | 0    | 0
        "rom only"               | 1    | 0
        "rom only and exe"       | 1    | 1
        "rom and gamepath"       | 1    | 1
    }

    @Unroll
    void "scumm vm"() {
        setup:
        HyperSpin hs = createDefaultHS()
        Checker checker = new Checker(hs, new BaseCheckHandler())

        when:
        CheckResult checkResult = checker.checkSystemRoms(null, "ScummVM", [ROM])

        then:
        checkResult.roms == ROMS
        checkResult.exes == EXES

        where:
        ROM                      | ROMS | EXES
        "zak"                    | 1    | 1     // esta en el xml, esta en el scummvm y en la carpeta
        "wrong"                  | 0    | 0     // esta en el xml, en el scummvm pero no existe la carpeta
        "missing"                | 0    | 0     // esta en el xml, no esta en el scummvm
        "nothing"                | 0    | 0     // no esta ni en el xml del database
    }

}