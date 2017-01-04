package org.hs5tb.groospin.checker.site

import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult
import org.hs5tb.groospin.common.FileBuffer

import static org.hs5tb.groospin.common.IOTools.humanReadableByteSize

/**
 * Created by Alberto on 12-Jun-16.
 */
class RichRomList extends BaseCheckHandler {
    private FileBuffer haveListHtml
    boolean includeMissing = false
    private String prefix

    RichRomList(String prefix, boolean includeMissing) {
        this.prefix = prefix
        this.includeMissing = includeMissing
    }
    RLSystem currentSystem

    void startSystem(RLSystem system) {
        currentSystem = system
        haveListHtml = new FileBuffer(new File("${prefix}${system.name}.html"))
        haveListHtml << "<table border='0'>"
    }

    void romChecked(CheckRomResult checkResult) {
        if (includeMissing || checkResult.exes) {
            haveListHtml << """
<tr>
    <td><h1>${checkResult.rom.exe?checkResult.rom.exe+": ":""}${checkResult.rom.name}</h1><br/>
        <span class="description"${checkResult.rom.description != checkResult.rom.name?checkResult.rom.description:""}</span>
        <p>${checkResult.rom.exeFileFound?.absolutePath}</p>
</td>
    <td><img class='wheel' src='${checkResult.wheel?.absolutePath}'/></td>
</tr>\n"""
        }
    }

    void endSystem(CheckTotalResult checkResult) {
        haveListHtml << "</table>\n"
        haveListHtml.flush()
    }
}
