package org.hs5tb.groospin.base

/**
 * Created by Alberto on 12-Jun-16.
 */
class Report {
    boolean first = true
    private File haveList
    private File csv
    private File file
    private File warning
    private File debug

    Report() {
    }

    Report(File reportRoot) {
        info("Report root: "+reportRoot)
        csv = new File(reportRoot, "hs.csv")
        haveList = new File(reportRoot, "have-list.html")
        file = new File(reportRoot, "errors.log")
        warning = new File(reportRoot, "warning.log")
        debug = new File(reportRoot, "debug.log")
    }

    void haveSystem(String system) {
        if (haveList) haveList << "${first?"":"</ol>\n"}<h2>${system}</h2><ol>\n"
        first = false
    }
    void haveRom(String title) {
        if (haveList) haveList << "<li>${title}</li>\n"
    }
    void endHave() {
        if (haveList) haveList << "</ol>\n"
        first = true
    }
    void info(String it) {
        println it
    }
    void log(String it) {
        println("$it")
        if (csv) csv << it.toString()+"\n"
    }

    void error(String it) {
        println("Error: $it")
        if (file) file << it.toString()+"\n"
    }

    void warn(String it) {
        println("Warning: $it")
        if (warning) warning << it.toString()+"\n"
    }

    void debug(String it) {
        if (debug) debug << it.toString()+"\n"
    }

    void startReport() {
        if (csv) csv.text = ""
        if (file) file.text = ""
        if (warning) warning.text = ""
        if (debug) debug.text = ""
        if (haveList) haveList.text = ""
        first = true
    }

}
