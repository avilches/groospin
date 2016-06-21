package org.hs5tb.groospin.checker

import org.hs5tb.groospin.base.RLSystem

/**
 * Created by Alberto on 12-Jun-16.
 */
class Report {
    private File haveList
    private File missingList

    private File csv
    private File errors
    private File warning
    private File debug

    Report(File reportRoot) {
        println("Report root: " + reportRoot)
        if (!reportRoot.exists()) {
            reportRoot.mkdirs()
        }
        csv = new File(reportRoot, "hs.csv")
        haveList = new File(reportRoot, "have-list.html")
        missingList = new File(reportRoot, "missing-list.html")

        errors = new File(reportRoot, "errors.log")
        warning = new File(reportRoot, "warning.log")
        debug = new File(reportRoot, "debug.log")
    }

    void startReport() {
        haveList << "<html><body>"
    }


    void startSystem(RLSystem system) {
        haveList << "<h2>${system.name}</h2>\n<ol>\n"
    }

    void romChecked(RLSystem system, CheckResult checkResult) {
        haveList << "<li>${checkResult.romName}</li>\n"
    }

    void endSystem(RLSystem system, CheckResult checkResult) {
        haveList << "</ol>\n"
    }

    void endReport(CheckResult checkResult) {
        haveList << "</body></html>"
    }

    void endSystemWithError(Exception e) {

    }
}
