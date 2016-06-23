package org.hs5tb.groospin.checker.handlers

import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult
import org.hs5tb.groospin.common.FileBuffer

/**
 * Created by Alberto on 12-Jun-16.
 */
class HaveHtmlList extends BaseCheckHandler {
    private FileBuffer haveListHtml
    boolean includeMissing = false

    HaveHtmlList(String haveListHtml, boolean includeMissing) {
        this(new File(haveListHtml), includeMissing)
    }

    HaveHtmlList(File haveListHtml, boolean includeMissing) {
        println("HaveHtmlList: " + haveListHtml)
        this.includeMissing = includeMissing
        this.haveListHtml = new FileBuffer(haveListHtml)
    }

    @Override
    void startCheck() {
        haveListHtml << "<html><body>"
    }

    @Override
    void startSystem(RLSystem system) {
        haveListHtml << "<h2>${system.name}</h2>\n<ol>\n"
    }

    @Override
    void romChecked(CheckRomResult checkResult) {
        if (includeMissing || (checkResult.exes && checkResult.roms)) {
            haveListHtml << "<li>${checkResult.romName}</li>\n"
        }
    }

    @Override
    void endSystem(CheckTotalResult checkResult) {
        haveListHtml << "</ol>\n"
    }

    @Override
    void endCheck(CheckTotalResult checkResult) {
        haveListHtml << "</body></html>"
        haveListHtml.flush()
    }
}
