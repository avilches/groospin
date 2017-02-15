package org.hs5tb.groospin.checker.handlers

import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.result.CheckTotalResult
import org.hs5tb.groospin.common.FileBuffer

import java.text.DecimalFormat

import static org.hs5tb.groospin.common.IOTools.humanReadableByteSize

/**
 * Created by Alberto on 12-Jun-16.
 */
class HumanInfo extends BaseCheckHandler {

    private FileBuffer humanReportFile = new FileBuffer()
    Boolean folderSize
    Integer systems = 0
    Type type = Type.missing
    enum Type {
        ok, missing, percentOk
    }

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

    def formatter = new DecimalFormat("###%")
    void drawLine(String title, CheckTotalResult checkResult) {
        String roms
        String wheels
        String themes
        String videos
        String artworks1
        String artworks2
        String artworks3
        String artworks4
        if (type == Type.missing) {
            roms = checkResult.totalRoms - checkResult.exes
            wheels = checkResult.totalRoms - checkResult.wheels
            themes = checkResult.totalRoms - checkResult.themes
            videos = checkResult.totalRoms - checkResult.videos
            artworks1 = checkResult.totalRoms - checkResult.artworks1
            artworks2 = checkResult.totalRoms - checkResult.artworks2
            artworks3 = checkResult.totalRoms - checkResult.artworks3
            artworks4 = checkResult.totalRoms - checkResult.artworks4
        } else if (type == Type.ok) {
            roms = checkResult.exes
            wheels = checkResult.wheels
            themes = checkResult.themes
            videos = checkResult.videos
            artworks1 = checkResult.artworks1
            artworks2 = checkResult.artworks2
            artworks3 = checkResult.artworks3
            artworks4 = checkResult.artworks4
        } else if (type == Type.percentOk) {
            roms = formatter.format(checkResult.exes / checkResult.totalRoms)
            wheels = formatter.format(checkResult.wheels / checkResult.totalRoms)
            themes = formatter.format(checkResult.themes / checkResult.totalRoms)
            videos = formatter.format(checkResult.videos / checkResult.totalRoms)
            artworks1 = formatter.format(checkResult.artworks1 / checkResult.totalRoms)
            artworks2 = formatter.format(checkResult.artworks2 / checkResult.totalRoms)
            artworks3 = formatter.format(checkResult.artworks3 / checkResult.totalRoms)
            artworks4 = formatter.format(checkResult.artworks4 / checkResult.totalRoms)
        }
        humanReportFile << prt("${title.padRight(40, " ")} roms: ${roms.toString().padRight(4," ")}/${checkResult.totalRoms.toString().padRight(4," ")} w/v/t: ${"${wheels}/${videos}/${themes}".padRight(14," ")} artwork: ${"${artworks1}/${artworks2}/${artworks3}/${artworks4}".padRight(19," ")} - roms ${humanReadableByteSize(checkResult.totalRomSize).padLeft(8, " ")} - size ${humanReadableByteSize(checkResult.totalMediaSize).padLeft(8, " ")}")
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
