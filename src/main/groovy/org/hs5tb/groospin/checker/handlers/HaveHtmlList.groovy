package org.hs5tb.groospin.checker.handlers

import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult
import org.hs5tb.groospin.common.FileBuffer

import static org.hs5tb.groospin.common.IOTools.humanReadableByteSize
import static org.hs5tb.groospin.common.IOTools.humanReadableByteSize

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
    void startGroup(String groupName) {
        haveListHtml << "<h1>${groupName}</h1>"
    }

    @Override
    void startSystem(RLSystem system) {
        haveListHtml << "<h2>${system.name}</h2>\n<ol>"
    }

    @Override
    void romChecked(CheckRomResult checkResult) {
        if (includeMissing || checkResult.exes) {
            haveListHtml << "<li>${checkResult.rom.description}</li>"
        }
    }

    @Override
    void endSystem(CheckTotalResult checkResult) {
        haveListHtml << "</ol>\n"
        if (checkResult) {
            haveListHtml << "<ul><li>Total ${checkResult.system.name} roms: ${humanReadableByteSize(checkResult.totalRomSize)}</li><li>Total media (videos, etc): ${humanReadableByteSize(checkResult.totalMediaSize)}</ul></li>"
        }
    }

    @Override
    void endGroup(CheckTotalResult checkResult) {
        if (checkResult) haveListHtml << "<p>${checkResult.group} roms: ${humanReadableByteSize(checkResult.totalRomSize)}</li><li>Total media (videos, etc): ${humanReadableByteSize(checkResult.totalMediaSize)}</p>"
    }

    @Override
    void endCheck(CheckTotalResult checkResult) {
        if (checkResult) haveListHtml << "<h3>Total all systems roms: ${humanReadableByteSize(checkResult.totalRomSize)}</li><li>Total media (videos, etc): ${humanReadableByteSize(checkResult.totalMediaSize)}</h3>"
        haveListHtml << "</body></html>"
        haveListHtml.flush()
    }
}
