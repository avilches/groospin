package org.hs5tb.groospin.checker.handler

import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult

/**
 * Created by Alberto on 12-Jun-16.
 */
class Report implements CheckHandler {
    private File haveListHtml
//    private File missingListHtml

    Report(String reportRoot) {
        this(new File(reportRoot))
    }

    Report(File reportRoot) {
        println("Report root: " + reportRoot)
        if (!reportRoot.exists()) {
            reportRoot.mkdirs()
        }
        haveListHtml = new File(reportRoot, "have-list.html")
//        missingListHtml = new File(reportRoot, "missing-list.html")
    }

    void startCheck() {
        haveListHtml << "<html><body>"
//        missingListHtml << "<html><body>"
    }


    void startSystem(RLSystem system) {
        haveListHtml << "<h2>${system.name}</h2>\n<ol>\n"
//        missingListHtml << "<h2>${system.name}</h2>\n<ol>\n"
    }

    void romChecked(CheckRomResult checkResult) {
        if (checkResult.exes && checkResult.roms) {
            haveListHtml << "<li>${checkResult.romName}</li>\n"
        } else {
            haveListHtml << "<li style='color:red'>${checkResult.romName} missing!</li>\n"
        }
    }

    void endSystem(CheckTotalResult checkResult) {
        haveListHtml << "</ol>\n"
    }

    void endCheck(CheckTotalResult checkResult) {
        haveListHtml << "</body></html>"
    }

    void endSystemWithError(String systemName, Exception e) {

    }
}
