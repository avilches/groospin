package org.hs5tb.groospin

import org.hs5tb.groospin.base.RLEmulator
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import spock.lang.Unroll


/**
 * Created by Alberto on 13-Jun-16.
 */
class HyperSpinSpec extends HSSpecification {

    @Unroll
    void "get emulator"() {
        setup:
        HyperSpin hs = createDefaultHS()

        when:
        RLEmulator emulator = hs.getEmulator(emu)

        then:
        emulator.emuPath == createResource("/Emulators/MAME/MameUIFX_0.171_64bits_nonag-dinput/mameuifx64.exe")
        emulator.module == "MAME.ahk"
        emulator.romExtensions == ["zip","7z"]
        emulator.name == emu

        where:
        emu << ["mame", "Mame", "MAME"]
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

    @Unroll
    void "list system names and rom names"() {
        setup:
        HyperSpin hs = createDefaultHS()

        expect:
        hs.listSystemNames() == ["AAE", "mame"]

        and:
        hs.listRomNames("aae") == ["alienst", "alphaona", "deep"]

    }


}