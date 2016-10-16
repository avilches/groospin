package examples

import mame.DatXmlToHyperSpinXml
import org.hs5tb.groospin.base.HyperSpinDatabase
import org.hs5tb.groospin.base.MameMachine

def mame171dat = "d:/Games/Emulators/MAME/MameUIFX_0.171_64bits_nonag-dinput/dat.xml"
def catver = "d:/Games/Soft/GrooSpin/resources/pS_CatVer/176/catver.ini"
def extraInfo = "d:/Games/Soft/GrooSpin/resources/Official HyperSpin MAME/code/extra_info.txt"
def header = [listversion    : "0.171",
              lastlistupdate : new Date().format("dd/MM/yyyy"),
              exporterversion: "GrooSpin by HS5Tb"]

def roms = DatXmlToHyperSpinXml.load(mame171dat, catver, extraInfo)

// Base de datos HyperSpin de MAME 178
HyperSpinDatabase best = new HyperSpinDatabase().load(new File("d:\\Games\\Soft\\GrooSpin\\resources\\bestof\\griffin-MAME178.xml"))
Set bestRoms = best.roms*.name

// La reescribimos usando el DAT de la 171 con los filtros que deseemos
DatXmlToHyperSpinXml.store(roms,
        "A:/Hyperspin-fe/Databases/MAME/MAME.xml",
        header + [listname: "MAME only working"]) { MameMachine rom ->
    !rom.cloneof &&
            rom.hasJoystick() && !rom.hasBall() && !rom.hasKeyboard() &&
            !rom.hanafuda && !rom.gambling && !rom.mahjong && rom.working &&
            !rom.disks && rom.name in bestRoms
}
