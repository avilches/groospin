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
    private n = 0
    private nn = 0

    @Override
    boolean needsRomFolderSize() {
        true
    }

    @Override
    boolean needsMediaFolderSize() {
        true
    }

    MainWebSite(String root) {
        this(new File(root))
    }

    MainWebSite(File root) {
        println ("MainWebSite root: "+root)
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
    table-layout: auto;
}
table#systems .head td {
    padding: 3px;
    background:#000000;
    color:white;
    font: 10pt Arial;
}
table#systems tbody td, table#systems tfoot td {
    border-bottom: 1px solid #e5e5e5 ;
    padding: 3px;
}
.wheels, .videos, .themes, .mediaSize {
    font: 9pt Arial;
    color: #999;
    text-align: right;
}
tfoot td, td.n, tr.n {
    background:#e5e5e5;
}
table#systems tbody td .emu {
    font-size: 8pt;
}
td.system{
    font: 12pt Arial;
}
td.roms {
    font: bold 13pt monospace, "Courier New", Courier;
    text-align:right;
}
td.romSize {
    font: bold 13pt monospace, "Courier New", Courier;
    text-align:right;
    color:#999;
}
td.romSize, td.mediaSize {
    white-space: nowrap;
}

td.group {
    font: bold 14pt/30pt Arial;
}
table tfoot td {
    border-top:2px solid #ccc ;
}
table#systems tbody td.instable {
    font: normal 9pt arial;
    color: #F00;
    xxbackground: #ffd966;
    text-align:center;
}
table#systems tbody td.ok {
    font: normal 9pt arial;
    text-align:center;
}
table#systems tbody td.perfect {
    xxxbackground: #93c47d;
    text-align:center;
}
table#systems tbody td.state {
}
.toolttip {
    position: relative;
    display: inline-block;
}

.toolttip .toolttiptext {
    visibility: hidden;
    width: 200px;
    background-color: #666;
    color: #fff;

    border:0;
    text-align: center;
    border-radius: 6px;
    padding: 10px;

    /* Position the toolttip */
    position: absolute;
    z-index: 1;
    bottom: 100%;
    left: 50%;
    margin-left: -60px;
    opacity: 0;
    transition: opacity 0.5s;
}

.toolttip:hover .toolttiptext {
    visibility: visible;
    opacity: 1;
}
</style>
"""

        websiteSystems << "<table id='systems'>"
    }

    static String header = "<tr class='head'>\n    <td>#</td>" +
            "<td colspan='2'>Sistema</td>" +
            "<td colspan='2'>Estado</td>" +
            "<td style='text-align: right;font: 8pt Arial'>Roms</td>" +
            "<td style='text-align: right;font: 8pt Arial'>Tamaño</td>" +
            "<td style='text-align: right;font: 8pt Arial'>Wheels</td>" +
            "<td style='text-align: right;font: 8pt Arial'>Video</td>" +
            "<td style='text-align: right;font: 8pt Arial'>Temas</td>" +
            "<td style='text-align: right;font: 8pt Arial'>Roms/medias</td>\n</tr>"

    @Override
    void startSystem(RLSystem system) {
    }

    @Override
    void romChecked(CheckRomResult checkResult) {
    }

    @Override
    void startGroup(String groupName) {
        nn = 0
        websiteSystems << "<tr>\n    <td></td><td class='group' colspan='10'>${groupName}</td>\n</tr>\n${header}"
    }

    String baseImg = "../static/icons/"
    String httpBaseImg = "http://hyperspin5tb.com/static/icons/"

    String arcadeIcon    = "<span class='toolttip'><img src='${httpBaseImg}arcade-flat-icon.png' width='10'/><span class=\"toolttiptext\">Ideal para recreativa</span></span>"
    String excellentIcon = "<span class='toolttip'><img src='${httpBaseImg}icon-certified.png' width='11'/><span class=\"toolttiptext\">¡Emulación excelente!</span></span>"
    String dangerIcon    = "<span class='toolttip'><img src='${httpBaseImg}danger-icon.png' width='14'/><span class=\"toolttiptext\">El sistema es inestable, no está bien emulado o presenta dificultades para jugar</span></span>"
    @Override
    void endSystem(CheckTotalResult checkResult) {
        n++
        nn++
        websiteSystems << "<tr>\n    <td class='n'>${n}</td>" +
                "<td>${icon(checkResult.system.name)}</td>" +
                "<td class='system'><a href='/sistemas/${sanitize(checkResult.system.name)}/'>${checkResult.system.name}</a><div class='emu'>${checkResult.system.defaultEmulator?.name ?: ""}</div></td>"+
                "<td class='${systemConfig.arcade?'arcadeYes':'arcadeNo'}'>${systemConfig.arcade?arcadeIcon:""}</td>" +
                "<td class='state ${systemConfig.perfect ? "perfect" : !systemConfig.stable ? "instable" : ""}'>${systemConfig.perfect ? excellentIcon : !systemConfig.stable ? dangerIcon : ""}</td>" +
                "<td class='roms'>${checkResult.totalRoms}</td>" +
                "<td class='romSize'>${humanReadableByteSize(checkResult.totalRomSize+checkResult.totalMediaSize)}</td>" +
                "<td class='wheels'>${checkResult.wheels}</td>" +
                "<td class='videos'>${checkResult.videos}</td>" +
                "<td class='themes'>${checkResult.themes}</td>" +
                "<td class='mediaSize'>${humanReadableByteSize(checkResult.totalRomSize)}/${humanReadableByteSize(checkResult.totalMediaSize)}</td></tr>"
    }

    String icon(String name) {
        if (new File("d:\\Games\\RocketLauncher\\RocketLauncherUI\\Media\\Icons\\${name}.png").exists()) {
            return "<img src='${httpBaseImg}${name}.png' onerror=\"this.style.display='none'\"/>"
        }
        ""
    }

    @Override
    void endGroup(CheckTotalResult checkResult) {
        websiteSystems << "<tr class='n'>\n    <td></td>" +
                "<td colspan='2' class='total'>Total ${nn} sistemas de ${checkResult.group}</td>" +
                "<td colspan='2'></td>"+
                "<td class='roms'>${checkResult.totalRoms}</td>" +
                "<td class='romSize'>${humanReadableByteSize(checkResult.totalRomSize+checkResult.totalMediaSize)}</td>" +
                "<td class='wheels'>${checkResult.wheels}</td>" +
                "<td class='videos'>${checkResult.videos}</td>" +
                "<td class='themes'>${checkResult.themes}</td>" +
                "<td class='mediaSize'>${humanReadableByteSize(checkResult.totalMediaSize)}/${humanReadableByteSize(checkResult.totalMediaSize)}</td></tr>"
        websiteSystems.flush()
    }

    @Override
    void endCheck(CheckTotalResult checkResult) {
        websiteSystems << "<tfoot>\n<tr>\n    <td></td>" +
                "<td colspan='2' class='total'>Total ${n} sistemas</td>" +
                "<td colspan='2'></td>"+
                "<td class='roms'>${checkResult.totalRoms}</td>" +
                "<td class='romSize'>${humanReadableByteSize(checkResult.totalRomSize+checkResult.totalMediaSize)}</td>" +
                "<td class='wheels'>${checkResult.wheels}</td>" +
                "<td class='videos'>${checkResult.videos}</td>" +
                "<td class='themes'>${checkResult.themes}</td>" +
                "<td class='mediaSize'>${humanReadableByteSize(checkResult.totalMediaSize)}/${humanReadableByteSize(checkResult.totalMediaSize)}</td></tr>"
        websiteSystems << "</tfoot>\n</table>"
        websiteSystems.flush()
    }
}
