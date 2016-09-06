package examples

import mame.DatXmlToHyperSpinXml
import operation.Comparer
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.MameMachine


def dat =         "d:/Games/Roms/HBMAME/0175/dat.xml"
def catver = "d:/Games/Soft/GrooSpin/resources/pS_CatVer/176/catver.ini"
def extraInfo = "d:/Games/Soft/GrooSpin/resources/Official HyperSpin MAME/code/extra_info.txt"

DatXmlToHyperSpinXml.transform(
        dat,
        catver, extraInfo,
        "d:/Games/HyperSpin-fe/Databases/HBMAME/HBMAME.xml",
        [listname: "HBMAME",
         listversion: "0.175",
         lastlistupdate: new Date().format("dd/MM/yyyy HH:mm"),
         exporterversion: "GrooSpin by HS5Tb"])
