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
        RLEmulator emulator = hs.getEmulator("MAME")

        then:
        emulator.emuPath == createResource("/Emulators/MAME/MameUIFX_0.171_64bits_nonag-dinput/mameuifx64.exe")
        emulator.romExtensions == ["zip","7z"]
        emulator.name == "mame"
    }

    @Unroll
    void "get system"() {
        setup:
        HyperSpin hs = createDefaultHS()

        when:
        RLSystem system = hs.getSystem("AAE")

        then:
        system.defaultEmulator.name == "aae"
        system.hyperSpin == hs

        system.romPathsList == [createResource("/Roms/AAE")]
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