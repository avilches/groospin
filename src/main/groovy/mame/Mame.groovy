package mame


/*new DatXmlToHyperspinXml().run(
        "HBMAME",
        true,
        "d:/Games/Roms/HBMAME/0175/dat.xml",
        "d:/Games/HyperSpin-fe/Databases/HBMAME/HBMAME.xml",
        "d:/Games/Roms/HBMAME/0175/roms")

return
*/
def x = new DatXmlToHyperspinXml()
String dat = "d:/Games/Emulators/MAME/MameUIFX_0.171_64bits_nonag-dinput/dat.xml"
def roms = x.parseRoms(dat)
x.export("0.171 Sega/No Clones",
        roms.findAll { !it.cloneof && it.manufacturer.contains("Sega")},
        "d:/Games/HyperSpin-fe/Databases/Sega Classics/Sega Classics MIO.xml")

new Comparer().run(
        "d:/Games/HyperSpin-fe/Databases/Sega Classics/Sega Classics MIO.xml",
        "d:/Games/HyperSpin-fe/Databases/Sega Classics/Sega Classics.xml")