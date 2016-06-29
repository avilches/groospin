package org.hs5tb.groospin.checker.site

import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult
import org.hs5tb.groospin.common.FileBuffer

import static org.hs5tb.groospin.common.IOTools.humanReadableByteSize
import static org.hs5tb.groospin.common.IOTools.sanitize

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
        println ("MainWebSite root: "+root)
        this.includeMissing = includeMissing
        this.root = root
        this.websiteSystems = new FileBuffer(new File(root, "systems.html"))
    }

    @Override
    void startCheck() {

        websiteSystems << """
<style>
table#systems {
    font: 11pt Arial;
    border-spacing: 0;
    border-collapse: collapse;
    border:2px solid #ccc ;
}
table#systems thead td {
    padding: 3px;
    background:#000000;
    color:white;
    font: bold 11pt Arial;
}
table#systems tbody td, table#systems tfoot td {
    border: 1px solid #e5e5e5 ;
    padding: 3px;
}
tfoot td, td.n {
    background:#e5e5e5;
}
table#systems tbody td .emu {
    font-size: 8pt;
}
td.system, td.roms, td.romSize{
    font: bold 11pt Arial;
}
table tfoot td {
    border-top:2px solid #ccc ;
}
</style>
"""

        websiteSystems << "<table id='systems'><thead>\n<tr>\n    <td>#</td><td>Tipo</td><td>Sistema</td><td></td><td>Roms</td><td>Tamaño</td><td>Wheels</td><td>Video</td><td>Temas</td><td>Tamaño medias</td>\n</tr>\n</thead>\n<tbody>"
    }

    SystemWebSite haveHtmlList
    @Override
    void startSystem(RLSystem system) {
        haveHtmlList = new SystemWebSite(new File(root, "system-${system.name}.html"), includeMissing)
        haveHtmlList.startSystem(system)
    }

    @Override
    void romChecked(CheckRomResult checkResult) {
        haveHtmlList.romChecked(checkResult)
    }



    @Override
    void endSystem(CheckTotalResult checkResult) {
        haveHtmlList.endSystem(checkResult)
        websiteSystems << "<tr>\n    <td class='n'>${++n}</td><td class='group'>${checkResult.group}</td><td><img src='static/icons/${checkResult.system.name}.png'/></td><td class='system'><a href='/sistema/${sanitize(checkResult.system.name)}/'>${checkResult.system.name}</a><div class='emu'>${checkResult.system.defaultEmulator.name?:""}</div></td><td class='roms'>${checkResult.totalRoms}</td><td class='romSize'>${humanReadableByteSize(checkResult.totalRomSize)}</td>" +
                "<td class='wheels'>${checkResult.wheels}</td><td class='videos'>${checkResult.videos}</td><td class='themes'>${checkResult.themes}</td><td class='mediaSize'>${humanReadableByteSize(checkResult.totalMediaSize)}</td></tr>"
    }

    @Override
    void endCheck(CheckTotalResult checkResult) {
        websiteSystems << "</tbody>\n<tfoot>\n<tr>\n    <td></td><td colspan='3' class='total'>Total ${n} sistemas</td><td class='roms'>${checkResult.totalRoms}</td><td class='romSize'>${humanReadableByteSize(checkResult.totalRomSize)}</td>" +
                "<td class='wheels'>${checkResult.wheels}</td><td class='videos'>${checkResult.videos}</td><td class='themes'>${checkResult.themes}</td><td class='mediaSize'>${humanReadableByteSize(checkResult.totalMediaSize)}</td></tr>"
        websiteSystems << "</tfoot>\n</table>"
        websiteSystems.flush()
    }
}
