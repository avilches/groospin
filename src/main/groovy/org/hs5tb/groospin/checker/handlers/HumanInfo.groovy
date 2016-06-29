package org.hs5tb.groospin.checker.handlers

import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.result.CheckTotalResult
import static org.hs5tb.groospin.common.IOTools.humanReadableByteSize

/**
 * Created by Alberto on 12-Jun-16.
 */
class HumanInfo extends BaseCheckHandler {

    @Override
    void startGroup(String groupName) {
        println "${groupName.center(138, " ")}"
    }

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
    void endGroup(CheckTotalResult checkResult) {
        println "-"*138
        drawLine("Total ${checkResult.group}", checkResult)
        println "-"*138
        println ""
    }

    @Override
    void endCheck(CheckTotalResult checkResult) {
        int totalSecs = (System.currentTimeMillis() - start) / 1000
        int hours = totalSecs / 3600
        int minutes = (totalSecs % 3600) / 60
        int seconds = totalSecs % 60

        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);

        drawLine("TOTAL", checkResult)
        println "Time: ${timeString}"
    }

    void drawLine(String title, CheckTotalResult checkResult) {
        println "${title.padLeft(40, " ")} roms: ${checkResult.totalRoms.toString().padRight(4," ")} w/v/t: ${"${checkResult.wheels}/${checkResult.videos}/${checkResult.themes}".padRight(14," ")} artwork: ${"${checkResult.artwork1}/${checkResult.artwork2}/${checkResult.artwork3}/${checkResult.artwork4}".padRight(19," ")} - roms ${humanReadableByteSize(checkResult.totalRomSize).padLeft(8, " ")} - size ${humanReadableByteSize(checkResult.totalMediaSize).padLeft(8, " ")}"
    }

}
