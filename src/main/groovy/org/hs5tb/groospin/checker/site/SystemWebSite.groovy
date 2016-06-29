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
class SystemWebSite {
    private FileBuffer haveListHtml
    boolean includeMissing = false

    SystemWebSite(String haveListHtml, boolean includeMissing) {
        this(new File(haveListHtml), includeMissing)
    }

    SystemWebSite(File haveListHtml, boolean includeMissing) {
        this.includeMissing = includeMissing
        this.haveListHtml = new FileBuffer(haveListHtml)
    }

    void startSystem(RLSystem system) {
        haveListHtml << "<p>Estas son todas las roms que incluye el sistema ${system.name}. Para ver el listado completo de todos los sistemas mira <a href='/'>nuestra colección</a>. Si lo prefieres puedes descargar el listado completo y con juegos en un solo zip <a rel='nofollow' href='http://bit.ly/hyperspin5tb-listado'>aquí</a>.</p>\n<ol>"
    }

    void romChecked(CheckRomResult checkResult) {
        if (includeMissing || (checkResult.exes && checkResult.roms)) {
            haveListHtml << "<li>${checkResult.rom.description}</li>"
        }
    }

    void endSystem(CheckTotalResult checkResult) {
        haveListHtml << "</ol>\n"
        if (checkResult) {
            haveListHtml << "<h4>Total roms: <b>${humanReadableByteSize(checkResult.totalRomSize)}</b><br/>Total media (videos, etc): <b>${humanReadableByteSize(checkResult.totalMediaSize)}</b></h4>"
            haveListHtml << "Para ver el listado completo de todos los sistemas mira <a href='/'>nuestra colección</a>. Si lo prefieres puedes descargar el listado completo y con juegos en un solo zip <a rel='nofollow' href='http://bit.ly/hyperspin5tb-listado'>aquí</a>.</p>"
        }
        haveListHtml.flush()
    }
}
