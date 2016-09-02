package examples

import mame.DatXmlToHyperSpinXml
import operation.Comparer
import org.hs5tb.groospin.base.MameMachine


DatXmlToHyperSpinXml.transform(
        "d:/Games/Roms/HBMAME/0175/dat.xml",
        "d:/Games/HyperSpin-fe/Databases/HBMAME/HBMAME.xml",
        [listname: "HBMAME only working with clones",
         listversion: "0.175",
         lastlistupdate: new Date().format("dd/MM/yyyy HH:mm"),
         exporterversion: "GrooSpin by HS5Tb"])
