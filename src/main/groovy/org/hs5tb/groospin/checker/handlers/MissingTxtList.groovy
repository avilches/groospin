package org.hs5tb.groospin.checker.handlers

import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult
import org.hs5tb.groospin.common.FileBuffer

/**
 * Created by Alberto on 12-Jun-16.
 */
class MissingTxtList extends BaseCheckHandler {
    private FileBuffer missingListTxt
    private String separator = ";"

    MissingTxtList(String missingListTxt) {
        this(missingListTxt, ";")
    }

    MissingTxtList(String missingListTxt, String separator) {
        this(new File(missingListTxt), separator)
    }

    MissingTxtList(File missingListTxt, String separator) {
        println("MissingTxtList: " + missingListTxt)
        this.missingListTxt = new FileBuffer(missingListTxt)
        this.separator = separator
    }

    @Override
    void romChecked(CheckRomResult checkResult) {
        if (!checkResult.exes || !checkResult.roms) {
            missingListTxt << "${checkResult.system.name}${separator}${checkResult.romName}"
        }
    }

    @Override
    void endCheck(CheckTotalResult checkResult) {
        missingListTxt.flush()
    }
}
