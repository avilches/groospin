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
        haveListHtml << """
<style>
table.roms {
    font: 11pt Arial;
    border-spacing: 0;
    border-collapse: collapse;
    border:2px solid #ccc ;
    table-layout: auto;
}
.romName {
    font-weight:bold;
}
.desc {
    font-size:9pt;
    font-weight:normal;
}
table.roms tr TD {
    border-bottom: 1px solid #e5e5e5 ;
    padding: 3px;
}

img.wheel {
    height: 32px;
}
.roms td.yes {
    width:20px;
    background:green;
    border:1px solid gray;
}
.roms td.no {
    width:20px;
    background:red;
    border:1px solid gray;
}
span.exe {
    font-size:9pt;
    display:none;
}
td.wheel {
    display:none;
}
.dbexe {
    color: gray;
    margin-right:5px;
    margin-right:5px;
}
</style>

<script
  src="https://code.jquery.com/jquery-3.1.1.slim.js"
  integrity="sha256-5i/mQ300M779N2OVDrl16lbohwXNUdzL/R2aVUXyXWA="
  crossorigin="anonymous"></script>
  
  
<input type="checkbox" onclick="\$('.exe').css('display', this.checked?'block':'none')"/> Exes
<input type="checkbox" onclick="\$('.wheel').css('display', this.checked?'block':'none')"/> Wheel
<table border='0' class="roms">"""
    }

    void romChecked(CheckRomResult checkResult) {
        if (includeMissing || checkResult.exes) {
            haveListHtml << """
<tr>
    <td class="romName">${checkResult.rom.exe?"<span class='dbexe'>${checkResult.rom.exe}</span>":""}${checkResult.rom.name}
    ${checkResult.rom.description != checkResult.rom.name?"<div class='desc'>${checkResult.rom.description}</div>":""}</td>
    <td class="${checkResult.rom.exeFileFound?"yes":"no"}"><span class="exe">${checkResult.rom.exeFileFound}</span></td>
    <td class="${checkResult.wheel?"yes":"no"}">W</td>
    <td class="wheel">${checkResult.wheel?"<img class='wheel' src='${checkResult.wheel?.absolutePath}'/>":""}</td>
    <td class="${checkResult.theme?"yes":"no"}">T</td>
    <td class="${checkResult.video?"yes":"no"}">V</td>
    <!--td><video class='video' height='200' controls><source src='${checkResult.video?.absolutePath}'  type="video/mp4"/></video></td-->
</tr>\n"""
        }
    }

    void endSystem(CheckTotalResult checkResult) {
        haveListHtml << "</table>\n"
        haveListHtml.flush()
    }
}
