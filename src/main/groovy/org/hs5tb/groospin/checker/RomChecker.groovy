/*
 * hs5tb
 *
 * @author Alberto Vilches (alberto.vilches@hs5tb)
 * @date 13/6/16
 * Copyright. All Rights Reserved.
 */
package org.hs5tb.groospin.checker

import groovy.transform.CompileStatic
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
        CheckRomResult checkResultRom = new CheckRomResult(romName: romName, system: system)

        File romFound = system.findValidRom(romName)
        if (romFound) {
            if (system.romsIsExecutable(romName)) {
                File exe = system.findExecutable(romName, romFound)
                if (exe) {
                    checkResultRom.roms = checkResultRom.exes = 1
                } else {
                    checkResultRom.roms = 1
                    checkResultRom.exes = 0
                }
            } else {
                checkResultRom.roms = checkResultRom.exes = 1
            }
        } else {
            checkResultRom.roms = checkResultRom.exes = 0
        }

        checkResultRom.wheels = existsInMedia(originalSystem, "Images/Wheel/${romName}", ["jpg", "png"]) ? 1 : 0
        checkResultRom.videos = existsInMedia(originalSystem, "Video/${romName}", ["mp4", "flv"]) ? 1 : 0
        checkResultRom.themes = existsInMedia(originalSystem, "Themes/${romName}", ["zip"]) ? 1 : 0
        checkResultRom.artwork1 = existsInMedia(originalSystem, "Images/Artwork1/${romName}", ["jpg", "png"]) ? 1 : 0
        checkResultRom.artwork2 = existsInMedia(originalSystem, "Images/Artwork2/${romName}", ["jpg", "png"]) ? 1 : 0
        checkResultRom.artwork3 = existsInMedia(originalSystem, "Images/Artwork3/${romName}", ["jpg", "png"]) ? 1 : 0
        checkResultRom.artwork4 = existsInMedia(originalSystem, "Images/Artwork4/${romName}", ["jpg", "png"]) ? 1 : 0
        return checkResultRom
    }

    boolean existsInMedia(RLSystem system, String path, List extensions) {
        boolean exists = IOTools.findFileWithExtensions(system.hyperSpin.findHyperSpinFile("Media/${system.name}/${path}"), extensions)
        if (!exists) {
            // println "Missing ${system.name}:$path.[${extensions.join("|")}]"
        }
        return exists
    }

}