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

    boolean deep = true

    CheckRomResult check(RLSystem originalSystem, Rom rom) {
        RLSystem system = originalSystem
        if (rom.exe) {
            system = originalSystem.hyperSpin.getSystem(rom.exe)
        }
        String romName = rom.name
        CheckRomResult checkResultRom = new CheckRomResult(romName: romName, originalSystem: originalSystem, system: system, systemName: system.name)

        File romFound = system.findValidRom(romName)
        if (romFound) {
            rom.romFileFound = romFound.canonicalFile
            if (system.romIsExecutable(romName)) {
                File exe = system.findExecutable(romName, romFound)
                if (exe && exe.exists()) {
                    rom.exeFileFound = exe.canonicalFile
                    checkResultRom.roms = checkResultRom.exes = 1
                } else {
                    checkResultRom.roms = 1
                    checkResultRom.exes = 0
                }
            } else {
                if (deep && IOTools.getExtension(romFound) in ["7z", "zip"] && !system.acceptZip(romName)) {
                    String pathInside = system.findDeepRom(romName, romFound)
                    if (pathInside) {
                        rom.exeFileFound = romFound.canonicalFile
                        checkResultRom.roms = checkResultRom.exes = 1
                    } else {
                        checkResultRom.roms = 1
                        checkResultRom.exes = 0
                    }
                } else {
                    rom.exeFileFound = romFound.canonicalFile
                    checkResultRom.roms = checkResultRom.exes = 1
                }
            }
        } else {
            checkResultRom.roms = checkResultRom.exes = 0
        }

        checkResultRom.wheel = findMedia(true, originalSystem, "Images/Wheel/${romName}", HyperSpin.IMAGE_EXTENSIONS)
        checkResultRom.video = findMedia(true, originalSystem, "Video/${romName}", HyperSpin.VIDEO_EXTENSIONS)
        checkResultRom.theme = findMedia(false, originalSystem, "Themes/${romName}", ["zip"])
        checkResultRom.artwork1 = findMedia(false, originalSystem, "Images/Artwork1/${romName}", HyperSpin.IMAGE_EXTENSIONS)
        checkResultRom.artwork2 = findMedia(false, originalSystem, "Images/Artwork2/${romName}", HyperSpin.IMAGE_EXTENSIONS)
        checkResultRom.artwork3 = findMedia(false, originalSystem, "Images/Artwork3/${romName}", HyperSpin.IMAGE_EXTENSIONS)
        checkResultRom.artwork4 = findMedia(false, originalSystem, "Images/Artwork4/${romName}", HyperSpin.IMAGE_EXTENSIONS)

        checkResultRom.wheels = checkResultRom.wheel? 1 : 0
        checkResultRom.videos = checkResultRom.video? 1 : 0
        checkResultRom.themes = checkResultRom.theme? 1 : 0
        checkResultRom.artworks1 = checkResultRom.artwork1? 1 : 0
        checkResultRom.artworks2 = checkResultRom.artwork2? 1 : 0
        checkResultRom.artworks3 = checkResultRom.artwork3? 1 : 0
        checkResultRom.artworks4 = checkResultRom.artwork4? 1 : 0
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