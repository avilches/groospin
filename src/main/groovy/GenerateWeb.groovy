import org.hs5tb.groospin.base.*
import org.hs5tb.groospin.checker.*
import org.hs5tb.groospin.checker.handlers.*

HyperSpin hs = new HyperSpin(
        "D:/Games/HyperSpin-fe",
        "D:/Games/RocketLauncher")

def myConfig = ["Arcade":["AAE","American Laser Games","Atari Classics","Capcom Classics","Capcom Play System","Capcom Play System II","Capcom Play System III","Data East Classics","DICE","Fightcade","Irem Classics","Konami Classics","MAME","Midway Classics","Namco Classics","Namco System 22","Nintendo Classics","Sammy Atomiswave","Sega Classics","Sega Hikaru","Sega Model 2","Sega Model 3","Sega Naomi","Sega ST-V",/*"Sega Triforce",*/"Shotgun Games","SNK Classics","Taito Classics","Taito Type X","TouchGames","Visual Pinball","Williams Classics","WoW Action Max","Zinc"],
                "Computer":["Acorn BBC Micro","Amstrad CPC","Apple II","Atari ST","Atari XEGS","Commodore 64","Commodore Amiga","Commodore Amiga CD32","Commodore CDTV","Fruit Machine","Fujitsu FM Towns","Microsoft MSX",/*"Microsoft MSX 2 - Discs",*/"Microsoft MSX2","Microsoft MSX2+","MSX Palcom Laserdisc",/*"Philips CD-i",*/"Philips VG 5000","Sega SC-3000","Sharp X1","Sharp X68000","Sinclair ZX Spectrum","Sord M5",/*"Texas Instruments TI 99-4A",*/"Zemmix","Zemmix Neo"],
                "Console":["Amstrad GX4000","Atari 2600","Atari 2600 - Arcadia Supercharger","Atari 5200","Atari 7800",/*"Atari 8-bit",*/"Atari Jaguar","Atari Jaguar CD","Bally Astrocade","Casio PV-1000","Cave","ColecoVision","Daphne","Emerson Arcadia 2001","Epoch Super Cassette Vision","Fairchild Channel F","Funtech Super Acan","Future Pinball","GCE Vectrex","Magnavox Odyssey 2","Mattel Intellivision","NEC PC Engine","NEC PC Engine-CD","NEC PC-FX","NEC SuperGrafx","NEC TurboGrafx-16","NEC TurboGrafx-CD",       "Nintendo 64","Nintendo Entertainment System","Nintendo Famicom","Nintendo Famicom Disk System","Nintendo Game and Watch","Nintendo GameCube","Nintendo Super Famicom","Nintendo Virtual Boy","Nintendo Wii","Nintendo WiiWare","Panasonic 3DO","RCA Studio II","Sega 32X","Sega CD","Sega Dreamcast","Sega Master System","Sega Mega Drive","Sega Saturn","Sega SG-1000","SNK Neo Geo AES","SNK Neo Geo CD","Sony PlayStation","Sony PlayStation 2","Sony PSP Minis","Super Nintendo Entertainment System","Technos","VTech CreatiVision"],
                "Multiple": ["Castlevania Collection", "The King of Fighters Collection"],
                "PC":["Doujin Soft", "Microsoft MS-DOS", "MUGEN","OpenBOR","PC Games","PopCap","Rockola","ScummVM","Touhou Project"],
                "Portable": ["Atari Lynx","Bandai WonderSwan","Bandai WonderSwan Color","Creatronic Mega Duck","Entex Adventure Vision","Epoch Game Pocket Computer","Nintendo DS","Nintendo Game Boy","Nintendo Game Boy Advance","Nintendo Game Boy Color","Nintendo Pokemon Mini","Sega Game Gear","Sega Pico","SNK Neo Geo Pocket","SNK Neo Geo Pocket Color","Sony PSP","Tiger Game.com","Watara Supervision"]]

/*

Collection<String> configSystems = myConfig.values().flatten()
Collection<String> hsSystems = hs.listSystemNames(true)
Collection<String> faltan = (hsSystems-configSystems)
Collection<String> sobran = (configSystems-hsSystems)

if (faltan) {
    println "Faltan por configurar ${faltan}"
    System.exit(1)
}
if (sobran) {
    println "Sobran de configurar ${sobran}"
    System.exit(1)
}
*/

def myConfig2 = ["Arcade":["AAE","American Laser Games"],
                "Computer":["Acorn BBC Micro","Apple II"]]



new Checker(hs).
        validateSystemGroup(myConfig).
        addHandler(new HumanInfo()).
        addHandler(new HaveHtmlList("D:/Games/Soft/GrooSpin/report/all.html", true)).
        addHandler(new HaveHtmlList("D:/Games/Soft/GrooSpin/report/have-list.html", false)).
        addHandler(new MissingTxtList("D:/Games/Soft/GrooSpin/report/missing.csv", ";")).
        addHandler(new AllRomsCsvList("D:/Games/Soft/GrooSpin/report/roms.csv", ";")).
        addHandler(new SystemCsvList("D:/Games/Soft/GrooSpin/report/systems.csv", ";")).
        checkSystemGroup(myConfig2)


