package examples

import mame.DatXmlToHyperSpinXml
import operation.Comparer
import org.hs5tb.groospin.base.MameMachine

String mame171dat = "d:/Games/Emulators/MAME/MameUIFX_0.171_64bits_nonag-dinput/dat.xml"
Node dat = MameMachine.parseDat(mame171dat)
def debugRoms = MameMachine.loadRoms(dat)// ; return

def header = [listversion:"0.171",
              lastlistupdate: new Date().format("dd/MM/yyyy HH:mm"),
              exporterversion: "GrooSpin by HS5Tb"]


DatXmlToHyperSpinXml.transform(dat,
        "d:/Games/HyperSpin-fe/Databases/SNK Classics/SNK Classics.xml",
        header + [listname: "SNK Classics (NeoGeo roms removed)"]) { MameMachine rom ->
    // SNK Classics needs an special condition to remove the neogeo roms
    rom.working && !rom.mechanical && rom.manufacturer.contains("SNK") && rom.romof != "neogeo" && rom.romof != "hng64"
}
Comparer.printDifferences("SNK Classics", "d:/Games/HyperSpin-fe/Databases", "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")

DatXmlToHyperSpinXml.transform(dat,
        "d:/Games/HyperSpin-fe/Databases/Nintendo Classics/Nintendo Classics.xml",
        header + [listname: "Nintendo Classics"]) { MameMachine rom ->
    rom.working && !rom.mechanical && (rom.manufacturer.contains("Nintendo") || rom.sourcefile == "vsnes.cpp")
}
Comparer.printDifferences("Nintendo Classics", "d:/Games/HyperSpin-fe/Databases", "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")

DatXmlToHyperSpinXml.transform(dat,
        "d:/Games/HyperSpin-fe/Databases/SNK Neo Geo AES/SNK Neo Geo AES.xml",
        header + [listname: "SNK Neo Geo AES"]) { MameMachine rom ->
    // NeoGeo don't remove the non-working games
    rom.romof == "neogeo"
}
Comparer.printDifferences("SNK Neo Geo AES", "d:/Games/HyperSpin-fe/Databases", "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")

DatXmlToHyperSpinXml.transform(dat,
        "d:/Games/HyperSpin-fe/Databases/HyperNeoGeo64/HyperNeoGeo64.xml",
        header + [listname: "HyperNeoGeo64"]) { MameMachine rom ->
    // HyperNeoGeo64 don't remove the non-working games
    rom.romof == "hng64"
}
Comparer.printDifferences("HyperNeoGeo64", "d:/Games/HyperSpin-fe/Databases", "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")

DatXmlToHyperSpinXml.transform(dat,
    "d:/Games/HyperSpin-fe/Databases/MAME 4 Players/MAME 4 Players.xml",
        header + [listname: "MAME 4 Players only working with clones (mechanical removed)"]) { MameMachine rom ->
    rom.working && !rom.mechanical && rom.players >= 4
}
Comparer.printDifferences("MAME 4 Players", "d:/Games/HyperSpin-fe/Databases", "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")

DatXmlToHyperSpinXml.transform(dat,
    "d:/Games/HyperSpin-fe/Databases/MAME/MAME.xml",
        header + [listname: "MAME only working with clones (mechanical removed)"]) { MameMachine rom ->
    rom.working && !rom.mechanical
}

Comparer.printDifferences(
        "d:/Games/HyperSpin-fe/Databases/MAME/MAME.xml",
        "c:/Users/Alberto/Downloads/mame0.171/Mame/Working Games/Mame.xml")

Comparer.printDifferences("MAME", "d:/Games/HyperSpin-fe/Databases", "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")

DatXmlToHyperSpinXml.transform(dat,
        "d:/Games/HyperSpin-fe/Databases/Capcom Play System/Capcom Play System.xml",
        header + [listname: "Capcom Play System"]) { MameMachine rom ->
    rom.working && !rom.mechanical && rom.sourcefile == "cps1.cpp"
}
Comparer.printDifferences("Capcom Play System", "d:/Games/HyperSpin-fe/Databases", "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")


DatXmlToHyperSpinXml.transform(dat,
        "d:/Games/HyperSpin-fe/Databases/Capcom Play System II/Capcom Play System II.xml",
        header + [listname: "Capcom Play System II"]) { MameMachine rom ->
    rom.working && !rom.mechanical && rom.sourcefile == "cps2.cpp"
}
Comparer.printDifferences("Capcom Play System II", "d:/Games/HyperSpin-fe/Databases", "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")

DatXmlToHyperSpinXml.transform(dat,
        "d:/Games/HyperSpin-fe/Databases/Capcom Play System III/Capcom Play System III.xml",
        header + [listname: "Capcom Play System III"]) { MameMachine rom ->
    rom.working && !rom.mechanical && rom.sourcefile == "cps3.cpp"
}
Comparer.printDifferences("Capcom Play System III", "d:/Games/HyperSpin-fe/Databases", "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")

DatXmlToHyperSpinXml.transform(dat,
        "d:/Games/HyperSpin-fe/Databases/Sega ST-V/Sega ST-V.xml",
        header + [listname: "Sega ST-V"]) { MameMachine rom ->
    !rom.mechanical && rom.romof =="stvbios"
}
Comparer.printDifferences("Sega ST-V", "d:/Games/HyperSpin-fe/Databases", "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")

DatXmlToHyperSpinXml.transform(dat,
        "d:/Games/HyperSpin-fe/Databases/Namco System 22/Namco System 22.xml",
        header + [listname: "Namco System 22"]) { MameMachine rom ->
    rom.working && !rom.mechanical && rom.sourcefile == "namcos22.cpp"
}
Comparer.printDifferences("Namco System 22", "d:/Games/HyperSpin-fe/Databases", "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")


["Namco Classics",
 "Atari Classics", "Capcom Classics", "Cave", "Data East Classics",
 "Banpresto", "Kaneko", "Irem Classics", "Williams Classics", "Midway Classics",
 "Sega Classics", "Konami Classics", "Taito Classics"].sort().each {

   String find = it-" Classics"

   DatXmlToHyperSpinXml.transform(dat,
            "d:/Games/HyperSpin-fe/Databases/${it}/${it}.xml",
           header + [listname: "${it}/No Clones"]) { MameMachine rom ->
       rom.working && !rom.mechanical && rom.manufacturer.contains(find)
    }

    Comparer.printDifferences(it, "d:/Games/HyperSpin-fe/Databases", "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")
}



