package examples

import mame.DatXmlToHyperspinXml
import operation.Comparer
import org.hs5tb.groospin.base.HyperSpinDatabase
import org.hs5tb.groospin.base.MameMachine


new DatXmlToHyperspinXml().run(
        "HBMAME",
        true,
        "d:/Games/Roms/HBMAME/0175/dat.xml",
        "d:/Games/HyperSpin-fe/Databases/HBMAME/HBMAME.xml",
        "d:/Games/Roms/HBMAME/0175/roms")

return

String dat = "d:/Games/Emulators/MAME/MameUIFX_0.171_64bits_nonag-dinput/dat.xml"
def roms = MameMachine.parseRoms(new File(dat), true) { MameMachine rom ->
    !rom.cloneof && rom.ok && !rom.mechanical && rom.manufacturer.contains("Sega")
}
HyperSpinDatabase.write(roms,
        new File("d:/Games/HyperSpin-fe/Databases/Sega Classics/Sega Classics MIO.xml"),
        [listname: "0.171 Sega/No Clones"])

new Comparer().run(
        "d:/Games/HyperSpin-fe/Databases/Sega Classics/Sega Classics MIO.xml",
"c:\\Users\\Alberto\\Downloads\\mame0.171\\Manufacturers\\Sega Classics\\Working Games\\Sega Classics.xml")
        // "d:/Games/HyperSpin-fe/Databases/Sega Classics/Sega Classics.xml")