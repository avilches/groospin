/*
 * hs5tb
 *
 * @author Alberto Vilches (alberto.vilches@hs5tb)
 * @date 13/6/16
 * Copyright. All Rights Reserved.
 */
package org.hs5tb.groospin.checker

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.common.IOTools;

public class Checker {

    HyperSpin hyperSpin
    Report report

    Checker(HyperSpin hyperSpin) {
        this.hyperSpin = hyperSpin
        report = new Report()

    }

    Checker(HyperSpin hyperSpin, File reportRoot) {
        this.hyperSpin = hyperSpin
        report = new Report(reportRoot)
    }

    CheckResult check(String systemName, List games = null) {
        RLSystem system = hyperSpin.getSystem(systemName)
        // println romFilenames
        int totalSize = system.calculateRomPathSize()
        if (!games) games = hyperSpin.getGamesFromSystem(systemName)
        CheckResult checkResult = new CheckResult(systemName: system.systemName, games: games.size(), totalSize: totalSize)
        games.sort().each { String game ->
            report.haveRom(game)
            boolean romFound = system.getRomFilePath(game)
//            if (system.mustHaveExe()) {
//                romFound = romFound && system.haveExe(game)
//            }
            checkResult.roms += romFound ? 1 : 0
            checkResult.wheels += existsInMedia("${systemName}/Images/Wheel/${game}", ["jpg", "png"]) ? 1 : 0
            checkResult.videos += existsInMedia("${systemName}/Video/${game}", ["mp4", "flv"]) ? 1 : 0
            checkResult.themes += existsInMedia("${systemName}/Themes/${game}", ["zip"]) ? 1 : 0
            checkResult.artwork1 += existsInMedia("${systemName}/Images/Artwork1/${game}", ["jpg", "png"]) ? 1 : 0
            checkResult.artwork2 += existsInMedia("${systemName}/Images/Artwork2/${game}", ["jpg", "png"]) ? 1 : 0
            checkResult.artwork3 += existsInMedia("${systemName}/Images/Artwork3/${game}", ["jpg", "png"]) ? 1 : 0
            checkResult.artwork4 += existsInMedia("${systemName}/Images/Artwork4/${game}", ["jpg", "png"]) ? 1 : 0
//            if (!romFound) {
//                warn "$systemName Missing rom: ${game}"
//            }
        }
        return checkResult
    }

    boolean existsInMedia(String path, List extensions) {
        return IOTools.matchesAnyExtension(new File(hyperSpin.hsRoot, "Media/${path}"), extensions)
    }



}