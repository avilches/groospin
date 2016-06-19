package org.hs5tb.groospin

import org.hs5tb.groospin.base.RLEmulator
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.base.Rom
import spock.lang.Unroll


/**
 * Created by Alberto on 13-Jun-16.
 */
class HyperSpinSpec extends HSSpecification {

    @Unroll
    void "list system names and rom names"() {
        setup:
        HyperSpin hs = createDefaultHS()

        expect:
        hs.listSystemNames() == ["AAE", "mame"]
        hs.listRomNames("aae") == ["alienst", "alphaona", "deep"]
        hs.listRoms("AAE")[0].name == "alienst"

        when:
        Rom rom = hs.listRoms("AAE")[1]

        then:
        rom.name == "alphaona"
        rom.description == "Alpha One (5 lives)"

    }

    @Unroll
    void "get emulator"() {
        setup:
        HyperSpin hs = createDefaultHS()

        when:
        RLEmulator emulator = hs.getSystem(system).defaultEmulator

        then:
        emulator.emuPath == createResource("/Emulators/MAME/MameUIFX_0.171_64bits_nonag-dinput/mameuifx64.exe")
        emulator.module == "MAME.ahk"
        emulator.romExtensions == ["zip","7z"]
        emulator.name == "MAME"

        where:
        system << ["mame", "Mame", "MAME"]
    }

    @Unroll
    void "get system"() {
        setup:
        HyperSpin hs = createDefaultHS()

        when:
        RLSystem system = hs.getSystem(systemName)

        then:
        system.name == systemName
        system.defaultEmulator.name == "AAE"
        system.hyperSpin == hs

        system.romPathsList == [createResource("/Roms/AAE")]

        where:
        systemName << ["aae", "Aae", "AAE"]

    }


}