package org.hs5tb.groospin.base

/**
 * Created by Alberto on 12-Jun-16.
 */
class RLEmulator {
    String name

    String iniEmuPath
    File emuPath
    String iniRomExtension
    List romExtensions
    String module

    boolean romsAreExecutable() {
        module in ["MUGEN.ahk", "OpenBOR.ahk", "Casual Games.ahk", "PCLauncher.ahk", "DFend.ahk", "DOSBox.ahk"]
    }
}
