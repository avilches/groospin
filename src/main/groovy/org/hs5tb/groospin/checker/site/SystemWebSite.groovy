package org.hs5tb.groospin.checker.site

import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.CheckHandler
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult
import org.hs5tb.groospin.common.FileBuffer

import static org.hs5tb.groospin.common.IOTools.humanReadableByteSize

/**
 * Created by Alberto on 12-Jun-16.
 */
class SystemWebSite extends BaseCheckHandler {
    private FileBuffer haveListHtml
    boolean includeMissing = false
    private String prefix

    SystemWebSite(String prefix, boolean includeMissing) {
        this.prefix = prefix
        this.includeMissing = includeMissing
    }
    void startSystem(RLSystem system) {
        haveListHtml = new FileBuffer(new File("${prefix}${system.name}.html"))
        haveListHtml << "<p>Estas son todas las roms que incluye el sistema ${system.name}. Para ver el listado completo de todos los sistemas mira <a href='/'>nuestra colección</a>. Si lo prefieres puedes descargar el listado completo y con juegos en un solo zip <a rel='nofollow' href='http://bit.ly/hyperspin5tb-romlist'>aquí</a>.</p>\n<ol>"
    }

    void romChecked(CheckRomResult checkResult) {
        if (includeMissing || checkResult.exes) {
            haveListHtml << "<li>"
            if (checkResult.rom.exe) {
                haveListHtml << "${checkResult.rom.exe}: "
            }
            haveListHtml << checkResult.rom.description
            if (checkResult.rom.description != checkResult.rom.name) {
                haveListHtml << "(${checkResult.rom.name})"
            }
            haveListHtml << "</li>"
        }
    }

    void endSystem(CheckTotalResult checkResult) {
        haveListHtml << "</ol>\n"
        if (checkResult) {
            haveListHtml << "<h4>Total roms: <b>${humanReadableByteSize(checkResult.totalRomSize)}</b><br/>Total media (videos, etc): <b>${humanReadableByteSize(checkResult.totalMediaSize)}</b></h4>"
            haveListHtml << "Para ver el listado completo de todos los sistemas mira <a href='/'>nuestra colección</a>. Si lo prefieres puedes descargar el listado completo y con juegos en un solo zip <a rel='nofollow' href='http://bit.ly/hyperspin5tb-romlist'>aquí</a>.</p>"
        }
        haveListHtml.flush()
    }
}
