package examples

import mame.DatXmlToHyperSpinXml
import operation.Comparer
import org.hs5tb.groospin.base.MameMachine

def mame171dat = "d:/Games/Emulators/MAME/MameUIFX_0.171_64bits_nonag-dinput/dat.xml"
def catver = "d:/Games/Soft/GrooSpin/resources/pS_CatVer/176/catver.ini"
def extraInfo = "d:/Games/Soft/GrooSpin/resources/Official HyperSpin MAME/code/extra_info.txt"
def header = [listversion    : "0.171",
              lastlistupdate : new Date().format("dd/MM/yyyy HH:mm"),
              exporterversion: "GrooSpin by HS5Tb"]
def databaseRoot = "d:/Games/HyperSpin-fe/Databases"

def roms = DatXmlToHyperSpinXml.load(mame171dat, catver, extraInfo)
/*
debugRoms.removeAll { it.mahjong || it.hanafuda }
debugRoms.removeAll { it.catVerCat?.contains("Casino") || it.genre?.contains("Casino") }
debugRoms.removeAll { it.catVerCat?.contains("Tabletop") || it.genre?.contains("Tabletop") }
*/

// MAME
DatXmlToHyperSpinXml.store(roms, "${databaseRoot}/MAME/MAME.xml",
        header + [listname: "MAME only working"])
Comparer.printDifferences(
        "${databaseRoot}/MAME/MAME.xml",
        "c:/Users/Alberto/Downloads/mame0.171/Mame/Working Games/Mame.xml")
Comparer.printDifferences("MAME", databaseRoot, "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")

DatXmlToHyperSpinXml.store(roms,
        "${databaseRoot}/MAME 4 Players/MAME 4 Players.xml",
        header + [listname: "MAME 4 Players only working with clones (mechanical removed)"]) { MameMachine rom ->
    rom.working && rom.players >= 4
}
Comparer.printDifferences("MAME 4 Players", databaseRoot, "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")

// Otros sistemas


DatXmlToHyperSpinXml.store(roms,
        "${databaseRoot}/SNK Classics/SNK Classics.xml",
        header + [listname: "SNK Classics (NeoGeo roms removed)"]) { MameMachine rom ->
    // SNK Classics needs an special condition to remove the neogeo roms
    rom.working && rom.manufacturer.contains("SNK") && rom.romof != "neogeo" && rom.romof != "hng64"
}
Comparer.printDifferences("SNK Classics", databaseRoot, "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")

DatXmlToHyperSpinXml.store(roms,
        "${databaseRoot}/Nintendo Classics/Nintendo Classics.xml",
        header + [listname: "Nintendo Classics"]) { MameMachine rom ->
    rom.working && (rom.manufacturer.contains("Nintendo") || rom.sourcefile == "vsnes.cpp")
}
Comparer.printDifferences("Nintendo Classics", databaseRoot, "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")

DatXmlToHyperSpinXml.store(roms,
        "${databaseRoot}/SNK Neo Geo AES/SNK Neo Geo AES.xml",
        header + [listname: "SNK Neo Geo AES"]) { MameMachine rom ->
    // NeoGeo don't remove the non-working games
    rom.romof == "neogeo"
}
Comparer.printDifferences("SNK Neo Geo AES", databaseRoot, "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")

DatXmlToHyperSpinXml.store(roms,
        "${databaseRoot}/HyperNeoGeo64/HyperNeoGeo64.xml",
        header + [listname: "HyperNeoGeo64"]) { MameMachine rom ->
    // HyperNeoGeo64 don't remove the non-working games
    rom.romof == "hng64"
}
Comparer.printDifferences("HyperNeoGeo64", databaseRoot, "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")

DatXmlToHyperSpinXml.store(roms,
        "${databaseRoot}/Capcom Play System/Capcom Play System.xml",
        header + [listname: "Capcom Play System"]) { MameMachine rom ->
    rom.working && rom.sourcefile == "cps1.cpp"
}
Comparer.printDifferences("Capcom Play System", databaseRoot, "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")


DatXmlToHyperSpinXml.store(roms,
        "${databaseRoot}/Capcom Play System II/Capcom Play System II.xml",
        header + [listname: "Capcom Play System II"]) { MameMachine rom ->
    rom.working && rom.sourcefile == "cps2.cpp"
}
Comparer.printDifferences("Capcom Play System II", databaseRoot, "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")

DatXmlToHyperSpinXml.store(roms,
        "${databaseRoot}/Capcom Play System III/Capcom Play System III.xml",
        header + [listname: "Capcom Play System III"]) { MameMachine rom ->
    rom.working && rom.sourcefile == "cps3.cpp"
}
Comparer.printDifferences("Capcom Play System III", databaseRoot, "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")

DatXmlToHyperSpinXml.store(roms,
        "${databaseRoot}/Sega ST-V/Sega ST-V.xml",
        header + [listname: "Sega ST-V"]) { MameMachine rom ->
    rom.romof == "stvbios"
}
Comparer.printDifferences("Sega ST-V", databaseRoot, "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")

DatXmlToHyperSpinXml.store(roms,
        "${databaseRoot}/Namco System 22/Namco System 22.xml",
        header + [listname: "Namco System 22"]) { MameMachine rom ->
    rom.working && rom.sourcefile == "namcos22.cpp"
}
Comparer.printDifferences("Namco System 22", databaseRoot, "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")


["Namco Classics",
 "Atari Classics", "Capcom Classics", "Cave", "Data East Classics",
 "Banpresto", "Kaneko", "Irem Classics", "Williams Classics", "Midway Classics",
 "Sega Classics", "Konami Classics", "Taito Classics"].sort().each {

    String find = it - " Classics"

    DatXmlToHyperSpinXml.store(roms,
            "${databaseRoot}/${it}/${it}.xml",
            header + [listname: "${it}"]) { MameMachine rom ->
        rom.working && rom.manufacturer.contains(find)
    }

    Comparer.printDifferences(it, databaseRoot, "k:/HyperSpin/D/Games/HyperSpin-fe/Databases")
}



