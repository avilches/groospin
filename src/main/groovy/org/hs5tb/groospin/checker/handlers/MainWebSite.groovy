package org.hs5tb.groospin.checker.handlers

import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult
import org.hs5tb.groospin.common.FileBuffer

import static org.hs5tb.groospin.common.IOTools.humanReadableByteSize

/**
 * Created by Alberto on 12-Jun-16.
 */
class MainWebSite extends BaseCheckHandler {
    private File root
    private FileBuffer websiteSystems
    boolean includeMissing = false
    private n = 0

    @Override
    boolean needsRomFolderSize() {
        true
    }

    @Override
    boolean needsMediaFolderSize() {
        true
    }

    MainWebSite(String root, boolean includeMissing) {
        this(new File(root), includeMissing)
    }

    MainWebSite(File root, boolean includeMissing) {
        this.includeMissing = includeMissing
        this.root = root
        this.websiteSystems = new FileBuffer(new File(root, "website-systems.html"))
    }

    @Override
    void startCheck() {
        websiteSystems << "<html><body><table>"

        websiteSystems << """
<style>
table {
\tfont: 10pt Arial;
\tborder-spacing: 0;
    border-collapse: collapse;
\tborder:2px solid #ccc ;
}
thead td {
\tpadding: 3px;
\tbackground:#000000;
\tcolor:white;
\tfont: bold 10pt Arial;
}
tbody td, tfoot td {
\tborder: 1px solid #e5e5e5 ;
\tpadding: 3px;
}
tfoot td, td.n {
\tbackground:#e5e5e5;
}
td.system, td.roms, td.romSize{
\tfont: bold 10pt Arial;
}
table tfoot td {
\tborder-top:2px solid #ccc ;
}
</style>
"""

        websiteSystems <<
                "<thead>\n<tr><td>#</td><td>Tipo</td><td>Sistema</td><td>Roms</td><td>Tamaño</td><td>Emulador</td><td>Wheels</td><td>Video</td><td>Temas</td><td>Tamaño medias</td></tr>\n</thead>\n<tbody>"
    }

    HaveHtmlList haveHtmlList
    @Override
    void startSystem(RLSystem system) {
        haveHtmlList = new HaveHtmlList(new File(root, "system-${system.name}.html"), includeMissing)
        haveHtmlList.startCheck()
        haveHtmlList.startSystem(system)
    }

    @Override
    void romChecked(CheckRomResult checkResult) {
        haveHtmlList.romChecked(checkResult)
    }



    @Override
    void endSystem(CheckTotalResult checkResult) {
        haveHtmlList.endSystem(checkResult)
        haveHtmlList.endCheck(null)
        websiteSystems << "<tr><td class='n'>${++n}</td><td class='group'>${checkResult.group}</td><td class='system'><a href='system-${checkResult.system.name}.html'>${checkResult.system.name}</a></td><td class='roms'>${checkResult.totalRoms}</td><td class='romSize'>${humanReadableByteSize(checkResult.totalRomSize)}</td>" +
                "<td class='emu'>${checkResult.system.defaultEmulator.name}</td><td class='wheels'>${checkResult.wheels}</td><td class='videos'>${checkResult.videos}</td><td class='themes'>${checkResult.themes}</td><td class='mediaSize'>${humanReadableByteSize(checkResult.totalMediaSize)}</td></tr>"
    }

    @Override
    void endCheck(CheckTotalResult checkResult) {
        websiteSystems << "</tbody>\n<tfoot>\n<tr><td></td><td colspan='2' class='total'>Total ${n} sistemas</td><td class='roms'>${checkResult.totalRoms}</td><td class='romSize'>${humanReadableByteSize(checkResult.totalRomSize)}</td>" +
                "<td></td><td class='wheels'>${checkResult.wheels}</td><td class='videos'>${checkResult.videos}</td><td class='themes'>${checkResult.themes}</td><td class='mediaSize'>${humanReadableByteSize(checkResult.totalMediaSize)}</td></tr>"
        websiteSystems << "</tfoot>\n</table>\n</body></html>"
        websiteSystems.flush()
    }
}
