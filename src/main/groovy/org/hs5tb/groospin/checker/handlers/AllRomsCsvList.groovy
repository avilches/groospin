package org.hs5tb.groospin.checker.handlers

import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult
import org.hs5tb.groospin.common.FileBuffer

import static org.hs5tb.groospin.common.IOTools.escapeCsv

/**
 * Created by Alberto on 12-Jun-16.
 */
class AllRomsCsvList extends BaseCheckHandler {
    private FileBuffer csvList
    private String separator = ";"

    AllRomsCsvList(String csvList) {
        this(csvList, ";")
    }

    AllRomsCsvList(String csvList, String separator) {
        this(new File(csvList), separator)
    }

    AllRomsCsvList(File csvList, String separator) {
        println("AllRomsCsvList: " + csvList)
        this.csvList = new FileBuffer(csvList)
        this.separator = separator
    }

    @Override
    void startCheck() {
        csvList << ["group",
                    "system",
                    "name",
                    "description",
                    "roms",
                    "exes",
                    "wheels",
                    "videos",
                    "themes",
                    "artwork1",
                    "artwork2",
                    "artwork3",
                    "artwork4"
        ].join(separator)
    }

    @Override
    void romChecked(CheckRomResult checkResult) {
        csvList << [escapeCsv(checkResult.group, separator),
                    escapeCsv(checkResult.system.name, separator),
                    escapeCsv(checkResult.rom.name, separator),
                    escapeCsv(checkResult.rom.description, separator),
                    checkResult.roms,
                    checkResult.exes,
                    checkResult.wheels,
                    checkResult.videos,
                    checkResult.themes,
                    checkResult.artwork1,
                    checkResult.artwork2,
                    checkResult.artwork3,
                    checkResult.artwork4
        ].join(separator)
    }


    @Override
    void endCheck(CheckTotalResult checkResult) {
        csvList.flush()
    }
}
