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
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.common.IOTools;

@CompileStatic
class RomChecker {

    CheckRomResult check(RLSystem system, String romName) {
        File romFound = system.findValidRom(romName)

        CheckRomResult checkResultRom = new CheckRomResult(romName: romName)
        if (romFound) {
            if (system.needsExecutable()) {
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

        checkResultRom.wheels = existsInMedia(system, "${system.name}/Images/Wheel/${romName}", ["jpg", "png"]) ? 1 : 0
        checkResultRom.videos = existsInMedia(system, "${system.name}/Video/${romName}", ["mp4", "flv"]) ? 1 : 0
        checkResultRom.themes = existsInMedia(system, "${system.name}/Themes/${romName}", ["zip"]) ? 1 : 0
        checkResultRom.artwork1 = existsInMedia(system, "${system.name}/Images/Artwork1/${romName}", ["jpg", "png"]) ? 1 : 0
        checkResultRom.artwork2 = existsInMedia(system, "${system.name}/Images/Artwork2/${romName}", ["jpg", "png"]) ? 1 : 0
        checkResultRom.artwork3 = existsInMedia(system, "${system.name}/Images/Artwork3/${romName}", ["jpg", "png"]) ? 1 : 0
        checkResultRom.artwork4 = existsInMedia(system, "${system.name}/Images/Artwork4/${romName}", ["jpg", "png"]) ? 1 : 0
        return checkResultRom
    }

    boolean existsInMedia(RLSystem system, String path, List extensions) {
        return IOTools.findFileWithExtensions(new File(system.hyperSpin.hsRoot, "Media/${path}"), extensions)
    }

}