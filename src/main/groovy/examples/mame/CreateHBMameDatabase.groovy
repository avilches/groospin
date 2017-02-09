package examples.mame

import mame.DatXmlToHyperSpinXml
import org.hs5tb.groospin.base.MameMachine


def dat = "d:/Games/Arcades/HBMAME/0175/dat.xml"
def catver = "d:/Games/Soft/GrooSpin/resources/pS_CatVer/176/catver.ini"
def extraInfo = "d:/Games/Soft/GrooSpin/resources/Official HyperSpin MAME/177/code/extra_info.txt"
def romFolders = ["d:\\Games\\Arcades\\HBMAME\\0175\\roms\\"]

def roms = DatXmlToHyperSpinXml.load(dat, catver, extraInfo)

MameChecker.Report report = new MameChecker().loadDat(dat).checkRoms(romFolders)
def missing = report.missing
if (missing) {
    println "*** PAY ATTENTION!!! Missing: ${missing}"
    return
}
roms.removeAll { it.name in missing }

roms.each { MameMachine rom ->
    rom.filesUsed = report.filesUsed[rom.name]
}

File f = new File("d:/Games/Arcades/HBMAME/0175/romsfake")
f.mkdirs()
roms.each {
    new File(f, it.name+".txt").text = ""
}



DatXmlToHyperSpinXml.store(
        roms,
        "d:/Games/HyperSpin-fe/Databases alternativas/HBMAME/0175/todo/HBMAME.xml",
        [listname: "HBMAME all",
         listversion: "0.175",
         lastlistupdate: new Date().format("dd/MM/yyyy HH:mm"),
         exporterversion: "GrooSpin by HS5Tb"]) { MameMachine rom -> true
}

DatXmlToHyperSpinXml.store(
        roms,
        "d:/Games/HyperSpin-fe/Databases alternativas/HBMAME/0175/sin clones/HBMAME.xml",
        [listname: "HBMAME no clones",
         listversion: "0.175",
         lastlistupdate: new Date().format("dd/MM/yyyy HH:mm"),
         exporterversion: "GrooSpin by HS5Tb"]) { MameMachine rom -> !rom.cloneof
}

DatXmlToHyperSpinXml.store(
        roms,
        "d:/Games/HyperSpin-fe/Databases alternativas/HBMAME/0175/sin clones solo joystick/HBMAME.xml",
        [listname: "HBMAME no clones only joystick",
         listversion: "0.175",
         lastlistupdate: new Date().format("dd/MM/yyyy HH:mm"),
         exporterversion: "GrooSpin by HS5Tb"]) { MameMachine rom ->
    !rom.cloneof &&
            rom.hasJoystick() && !rom.hasBall() && !rom.hasKeyboard() &&
            !rom.hanafuda && !rom.gambling && !rom.mahjong

}
