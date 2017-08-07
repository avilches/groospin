package org.hs5tb.groospin.base

import org.hs5tb.groospin.common.IOTools

/**
 * Created by Alberto on 12-Jun-16.
 */
class RLEmulator {

    static Set modulesWithZip = ["AAE", "MAME", "DOSBox",
                                 "Demul (v0.7)", "WinUAE",
                                 "Sega Model 2 Emulator", "SuperModel", "ZiNc"]

    static Set modulesExecutables = ["MUGEN", "OpenBOR", "Casual Games",
                                     "PCLauncher", "DFend"]

    String name

    String iniEmuPath
    File emuPath
    String iniRomExtension
    List romExtensions
    String module

    String _moduleName

    boolean romsAreExecutable() {
        getModuleName() in modulesExecutables
    }

    boolean acceptZip() {
        getModuleName() in modulesWithZip
    }

    String getModuleName() {
        return _moduleName = _moduleName ? _moduleName : IOTools.getFilenameWithoutExtension(new File(module).name)
    }
}
