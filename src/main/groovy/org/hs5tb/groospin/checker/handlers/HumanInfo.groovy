package org.hs5tb.groospin.checker.handlers

import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.result.CheckTotalResult
import org.hs5tb.groospin.common.FileBuffer

import static org.hs5tb.groospin.common.IOTools.humanReadableByteSize

/**
 * Created by Alberto on 12-Jun-16.
 */
class HumanInfo extends BaseCheckHandler {

    private FileBuffer humanReportFile = new FileBuffer()
    Boolean folderSize
    Integer systems = 0
    boolean inverse = false
    boolean absolute = true

    HumanInfo() {}

    HumanInfo(boolean folderSize) {
        this.folderSize = folderSize
    }

    HumanInfo(String humanReportFile, boolean folderSize) {
        this(new File(humanReportFile), folderSize)
    }
    HumanInfo(File humanReportFile, boolean folderSize) {
        this.humanReportFile = new FileBuffer(humanReportFile)
        this.folderSize = folderSize
    }

    @Override
    void startGroup(String groupName) {
        humanReportFile << prt("${groupName.center(143, " ")}")
    }

    String prt(String x = "") {
        println x
        return x
    }

    long start
    @Override
    void startCheck() {
        start = System.currentTimeMillis()
    }

    @Override
    void startSystem(RLSystem system) {
        systems ++
    }

    @Override
    void endSystem(CheckTotalResult checkResult) {
        drawLine("${systems.toString().padLeft(3, " ")}-${checkResult.systemName}", checkResult)
        if (checkResult.system.defaultEmulator?.emuPath && !checkResult.system.defaultEmulator?.emuPath?.exists()) {
            println "WARNING: Emulator ${checkResult.system.defaultEmulator.name} executable not found ${checkResult.system.defaultEmulator.emuPath}"
        }
        checkResult.system.romPathsList.each {
            if (!it.directory) {
                println "WARNING: Emulator ${checkResult.system.defaultEmulator.name} rom path not found ${it}"
            }
        }
    }

    @Override
    void endGroup(CheckTotalResult checkResult) {
        humanReportFile << prt("-"*143)
        drawLine("Total ${checkResult.group}", checkResult)
        humanReportFile << prt("-"*143)
        humanReportFile << prt()
    }

    @Override
    void endCheck(CheckTotalResult checkResult) {
        int totalSecs = (System.currentTimeMillis() - start) / 1000
        int hours = totalSecs / 3600
        int minutes = (totalSecs % 3600) / 60
        int seconds = totalSecs % 60

        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);

        drawLine("TOTAL $systems systems", checkResult)
        humanReportFile << prt("Time: ${timeString}")
        humanReportFile.flush()
    }

    void drawLine(String title, CheckTotalResult checkResult) {
        if (inverse) {
            humanReportFile << prt("${title.padRight(40, " ")} roms: ${(checkResult.totalRoms-checkResult.exes).toString().padRight(4," ")}/${checkResult.totalRoms.toString().padRight(4," ")} w/v/t: ${"${checkResult.totalRoms-checkResult.wheels}/${checkResult.totalRoms-checkResult.videos}/${checkResult.totalRoms-checkResult.themes}".padRight(14," ")} artwork: ${"${checkResult.totalRoms-checkResult.artworks1}/${checkResult.totalRoms-checkResult.artworks2}/${checkResult.totalRoms-checkResult.artworks3}/${checkResult.totalRoms-checkResult.artworks4}".padRight(19," ")} - roms ${humanReadableByteSize(checkResult.totalRomSize).padLeft(8, " ")} - size ${humanReadableByteSize(checkResult.totalMediaSize).padLeft(8, " ")}")
        } else {
            if (absolute) {
                humanReportFile << prt("${title.padRight(40, " ")} roms: ${checkResult.exes.toString().padRight(4," ")}/${checkResult.totalRoms.toString().padRight(4," ")} w/v/t: ${"${checkResult.wheels}/${checkResult.videos}/${checkResult.themes}".padRight(14," ")} artwork: ${"${checkResult.artworks1}/${checkResult.artworks2}/${checkResult.artworks3}/${checkResult.artworks4}".padRight(19," ")} - roms ${humanReadableByteSize(checkResult.totalRomSize).padLeft(8, " ")} - size ${humanReadableByteSize(checkResult.totalMediaSize).padLeft(8, " ")}")
            } else {
                humanReportFile << prt("${title.padRight(40, " ")} roms: ${checkResult.exes.toString().padRight(4," ")}/${checkResult.totalRoms.toString().padRight(4," ")} w/v/t: ${"${checkResult.wheels}/${checkResult.videos}/${checkResult.themes}".padRight(14," ")} artwork: ${"${checkResult.artworks1}/${checkResult.artworks2}/${checkResult.artworks3}/${checkResult.artworks4}".padRight(19," ")} - roms ${humanReadableByteSize(checkResult.totalRomSize).padLeft(8, " ")} - size ${humanReadableByteSize(checkResult.totalMediaSize).padLeft(8, " ")}")
            }
        }
    }

    @Override
    boolean needsRomFolderSize() {
        folderSize != null?folderSize:super.needsRomFolderSize()
    }

    @Override
    boolean needsMediaFolderSize() {
        folderSize != null?folderSize:super.needsMediaFolderSize()
    }

}
