package org.hs5tb.groospin.checker.handlers

import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.result.CheckTotalResult
import org.hs5tb.groospin.common.FileBuffer

import static org.hs5tb.groospin.common.IOTools.humanReadableByteSize
import static org.hs5tb.groospin.common.IOTools.escapeCsv

/**
 * Created by Alberto on 12-Jun-16.
 */
class SystemCsvList extends BaseCheckHandler {
    private FileBuffer csvList
    private String separator = ";"

    @Override
    boolean needsRomFolderSize() {
        true
    }

    @Override
    boolean needsMediaFolderSize() {
        true
    }

    SystemCsvList(String csvList) {
        this(csvList, ";")
    }

    SystemCsvList(String csvList, String separator) {
        this(new File(csvList), separator)
    }

    SystemCsvList(File csvList, String separator) {
        println("SystemCsvList: " + csvList)
        this.csvList = new FileBuffer(csvList)
        this.separator = separator
    }

    @Override
    void startCheck() {
        csvList << ["group",
                    "system",
                    "totalRomSize",
                    "totalMediaSize",
                    "totalRoms",
                    "roms",
                    "wheels",
                    "videos",
                    "themes"].join(separator)
    }

    @Override
    void endSystem(CheckTotalResult checkResult) {
        csvList << [escapeCsv(checkResult.group, separator),
                    escapeCsv(checkResult.systemName, separator),
                    humanReadableByteSize(checkResult.totalRomSize),
                    humanReadableByteSize(checkResult.totalMediaSize),
                    checkResult.totalRoms,
                    checkResult.exes,
                    checkResult.wheels,
                    checkResult.videos,
                    checkResult.themes].join(separator)
    }

    @Override
    void endCheck(CheckTotalResult checkResult) {
        csvList.flush()
    }
}
