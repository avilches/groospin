/*
 * hs5tb
 *
 * @author Alberto Vilches (alberto.vilches@hs5tb)
 * @date 13/6/16
 * Copyright. All Rights Reserved.
 */
package org.hs5tb.groospin.checker

import groovy.transform.CompileStatic
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.base.Rom
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.common.IOTools;

@CompileStatic
class RomChecker {

    CheckRomResult check(RLSystem originalSystem, Rom rom) {
        RLSystem system = originalSystem
        if (rom.exe) {
            system = originalSystem.hyperSpin.getSystem(rom.exe)
        }
        String romName = rom.name
        CheckRomResult checkResultRom = new CheckRomResult(romName: romName, system: system, systemName: system.name)

        File romFound = system.findValidRom(romName)
        if (romFound) {
            rom.romFileFound = romFound
            if (system.romsIsExecutable(romName)) {
                File exe = system.findExecutable(romName, romFound)
                if (exe && exe.exists()) {
                    rom.exeFileFound = romFound
                    checkResultRom.roms = checkResultRom.exes = 1
                } else {
                    checkResultRom.roms = 1
                    checkResultRom.exes = 0
                }
            } else {
                rom.exeFileFound = romFound
                checkResultRom.roms = checkResultRom.exes = 1
            }
        } else {
            checkResultRom.roms = checkResultRom.exes = 0
        }

        checkResultRom.wheels = findMedia(true, originalSystem, "Images/Wheel/${romName}", HyperSpin.IMAGE_EXTENSIONS) ? 1 : 0
        checkResultRom.videos = findMedia(true, originalSystem, "Video/${romName}", HyperSpin.VIDEO_EXTENSIONS) ? 1 : 0
        checkResultRom.themes = findMedia(false, originalSystem, "Themes/${romName}", ["zip"]) ? 1 : 0
        checkResultRom.artwork1 = findMedia(false, originalSystem, "Images/Artwork1/${romName}", HyperSpin.IMAGE_EXTENSIONS) ? 1 : 0
        checkResultRom.artwork2 = findMedia(false, originalSystem, "Images/Artwork2/${romName}", HyperSpin.IMAGE_EXTENSIONS) ? 1 : 0
        checkResultRom.artwork3 = findMedia(false, originalSystem, "Images/Artwork3/${romName}", HyperSpin.IMAGE_EXTENSIONS) ? 1 : 0
        checkResultRom.artwork4 = findMedia(false, originalSystem, "Images/Artwork4/${romName}", HyperSpin.IMAGE_EXTENSIONS) ? 1 : 0
        return checkResultRom
    }

    File findMedia(boolean log, RLSystem system, String path, List extensions) {
        File mediaFile = IOTools.findFileWithExtensions(system.newMediaPath(path), extensions)
        if (!mediaFile && system.defaultEmulator?.name?.startsWith("MAME")) {
            mediaFile = IOTools.findFileWithExtensions(system.hyperSpin.newHyperSpinMediaFile("_MAME/$path"), extensions)
        }
        if (!mediaFile && log) {
            //println "Missing ${system.name}:$path.[${extensions.join("|")}]"
        }
        return mediaFile
    }

}