package org.hs5tb.groospin.checker.handlers

import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.result.CheckTotalResult
import org.hs5tb.groospin.common.FileBuffer

import static org.hs5tb.groospin.common.IOTools.escapeCsv
import static org.hs5tb.groospin.common.IOTools.humanReadableByteSize

/**
 * Created by Alberto on 12-Jun-16.
 */
class HumanInfo extends BaseCheckHandler {

    long start
    @Override
    void startCheck() {
        start = System.currentTimeMillis()
    }

    @Override
    void endSystem(CheckTotalResult checkResult) {
        drawLine(checkResult.systemName, checkResult)
    }

    @Override
    void endCheck(CheckTotalResult checkResult) {
        long elapsed = System.currentTimeMillis() - start
        drawLine("TOTAL", checkResult)
        println "Time: ${(elapsed/1000) as int}s"
    }

    @Override
    boolean needsRomFolderSize() {
        true
    }

    @Override
    boolean needsMediaFolderSize() {
        true
    }

    void drawLine(String title, CheckTotalResult checkResult) {
        println "${title.padLeft(40, " ")} roms: ${checkResult.totalRoms.toString().padRight(4," ")} w/v/t: ${"${checkResult.wheels}/${checkResult.videos}/${checkResult.themes}".padRight(14," ")} artwork: ${"${checkResult.artwork1}/${checkResult.artwork2}/${checkResult.artwork3}/${checkResult.artwork4}".padRight(19," ")} - roms ${humanReadableByteSize(checkResult.totalRomSize).padLeft(8, " ")} - size ${humanReadableByteSize(checkResult.totalMediaSize).padLeft(8, " ")}"
    }

}
