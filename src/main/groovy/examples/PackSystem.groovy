package examples

import operation.Packer
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem

Packer packer = new Packer("D:/Games/RocketLauncher")
File base = new File("b:\\sync")
base.mkdirs()




packer.simulation = false
// basic1(new Pack(packer: packer, base: base, name: "Basic-FreePack"))
//mame(new Pack(packer: packer, base: base, name: "Mame-Mess-0.180.full"))
//arcade1(new Pack(packer: packer, base: base, name: "Arcade1"))
//arcade2(new Pack(packer: packer, base: base, name: "Arcade2"))
//roms1(new Pack(packer: packer, base: base, name: "Roms1"))
//roms2(new Pack(packer: packer, base: base, name: "Roms2"))
//collections(new Pack(packer: packer, base: base, name: "collections"))
//pc1(new Pack(packer: packer, base: base, name: "PC1"))
//pc2(new Pack(packer: packer, base: base, name: "PC2"))
isos1(new Pack(packer: packer, base: base, name: "Isos1"))
//isos2(new Pack(packer: packer, base: base, name: "Isos2"))


def basic1(Pack pack) {
    def systems =
    [
            "Atari 2600",
    "Nintendo Classic Mini",
    "Nintendo Entertainment System",
    "Sega Master System",
    "Sega Genesis",
    "Super Nintendo Entertainment System",
    "Nintendo 64",
    "Nintendo Game Boy",
    "Nintendo Game Boy Color",
    "Sega Game Gear",
    "Rockola",
    "Vintage Commercials",
    "Future Pinball"]

    pack.addSystem(systems)
    pack.addResource(["D:\\Games\\Arcades\\Future Pinball",
            "D:\\Games\\Extra\\Jukebox"])

    pack.rarTo("13-systems")

}

def arcade1(Pack pack) {
    def systems =
    [
            "Cave 3rd",
            "Gaelco",
            "Sammy Atomiswave",
            "Sega Hikaru",
            "Sega Naomi",
            "Fightcade",
            "HBMAME",
            "AAE",
            "American Laser Games",
            "DICE",
            "Sega Model 2",
            "Sega Model 3"]


    pack.addSystem(systems)
    pack.addResource(["D:\\Games\\Emulators\\Demul\\demul-0.7a",
    "D:\\Games\\Arcades\\Fightcade","D:\\Games\\Arcades\\HBMAME","D:\\Games\\Emulators",
    "D:\\Games\\Roms\\DICE", "D:\\Games\\Emulators\\Sega Model 2", "D:\\Games\\Emulators\\Sega SuperModel"])

    pack.rarTo()

}

def arcade2(Pack pack) {
    def systems =
    [
            "Sega Triforce",
            "Taito Type X",
            "TouchGames",
            "WoW Action Max",
            "Zinc",
            "Pinball Arcade",
            "Pinball FX2",
            "Visual Pinball"]


    pack.addSystem(systems)
    pack.addResource(["D:\\Games\\Arcades\\WoW Action Max",
                      "D:\\Games\\Emulators\\ZiNc\\zinc11-win32",
    "D:\\Games\\Arcades\\Pinball FX2 Build 030615",
            "D:\\Games\\Arcades\\Pinball Arcade",
            "D:\\Games\\Emulators\\Visual Pinball\\VPinball912"
    ])

    pack.rarTo()

}

def roms1(Pack pack) {
    def systems =
    [
            "Atari 2600 - Arcadia Supercharger",
                    "ALF TV Game",
                    "Atari 7800",
                    "Atari XEGS",
                    "Casio PV-2000",
                    "Nintendo Entertainment System Asia",
                    "Nintendo Entertainment System Europe",
                    "Nintendo Famicom",
                    "Nintendo Famicom Disk System",
                    "Sega Mark III",
                    "Sega Master System Japan",
                    "Sega SG-1000",
                    "Technos",
                    "Zemmix",
                    "Zemmix Neo",
                    "Konix Multi-System",
                    "NEC PC Engine",
                    "NEC SuperGrafx",
                    "NEC TurboGrafx-16",
                    "Nintendo Satellaview",
                    "Nintendo Sufami Turbo",
                    "Nintendo Super Famicom",
                    "Nintendo Super Game Boy",
                    "Sega Mega Drive",
                    "Sega Mega Drive Europe",
                    "Super Nintendo Entertainment System Europe",
                    "Super Nintendo Entertainment System Japan"]


    pack.addSystem(systems)
    pack.addResource([
            "D:\\Games\\Emulators\\Universal Emulator",
            "D:\\Games\\Emulators\\Atari800\\atari800-3.0.0-winsdl",
            "D:\\Games\\Emulators\\Nestopia\\Nestopia-1.41.1",
            "D:\\Games\\Emulators\\MSX\\BlueMSX V2.8.2",
            "D:\\Games\\Roms\\Nintendo Entertainment System",
            "D:\\Games\\Roms\\Super Nintendo Entertainment System"

    ])

    pack.rarTo()

}

def roms2(Pack pack) {
    def systems =
    [
            "Atari Jaguar",
                    "Nintendo 64 Europe",
                    "Nintendo 64 Japan",
                    "Nintendo 64DD",
                    "Nintendo Virtual Boy",
                    "Sega 32X",
                    "Atari Lynx",
                    "Bandai WonderSwan",
                    "Bandai WonderSwan Color",
                    "GamePark 32",
                    "Nintendo 3DS",
                    "Nintendo DS",
                    "Nintendo Game & Watch",
                    "Nintendo Game and Watch",
                    "Nintendo Game Boy Advance",
                    "Nintendo Pokemon Mini",
                    "Sega Nomad",
                    "Sega Pico",
                    "SNK Neo Geo Pocket",
                    "SNK Neo Geo Pocket Color",
                    "Acorn BBC Micro",
                    "Amstrad CPC",
                    "Apogee BK-01",
                    "Apple II",
                    "Atari 8-bit",
                    "Atari ST",
                    "Commodore 64",
                    "Commodore Amiga",
                    "Fujitsu FM Towns",
                    "MGT Sam Coupe",
                    "Microsoft MSX",
                    "Microsoft MSX 2 - Discs",
                    "Microsoft MSX2",
                    "Microsoft MSX2+",
                    "MSX Palcom Laserdisc",
                    "Philips VG 5000",
                    "Radio-86RK Mikrosha",
                    "Sega SC-3000",
                    "Sharp X1",
                    "Sharp X68000",
                    "Sinclair ZX Spectrum",
                    "Sord M5",
                    "Texas Instruments TI 99-4A"]


    pack.addSystem(systems)
    pack.addResource([
            "D:\\Games\\Emulators\\Project64\\Project64 DD 2.2.0.9999",
            "D:\\Games\\Emulators\\Geepee32",
            "D:\\Games\\Emulators\\Citra",
            "D:\\Games\\Emulators\\PokeMini",
            "D:\\Games\\Emulators\\Acorn BBC Micro",
            "D:\\Games\\Emulators\\Amstrad",
            "D:\\Games\\Emulators\\Applewin",
            "D:\\Games\\Emulators\\Atari800",
            "D:\\Games\\Emulators\\Atari ST\\",
            "D:\\Games\\Emulators\\Commodore 64",
            "D:\\Games\\Emulators\\Commodore Amiga",
            "D:\\Games\\Emulators\\Fujitsu FM Towns",
            "D:\\Games\\Emulators\\SimCoupe\\",
            "D:\\Games\\Emulators\\MSX",
            "D:\\Games\\Emulators\\DCVG5K",
            "D:\\Games\\Emulators\\Sega\\Fusion364",
            "D:\\Games\\Emulators\\Sharp",
            "D:\\Games\\Emulators\\Sinclair ZX Spectrum\\Spectaculator",
            "D:\\Games\\Roms\\Commodore 64",
            "D:\\Games\\Roms\\Nintendo 64",
    ])

    pack.rarTo()

}

def mame(Pack pack) {
    def systems =
    ["Atari Classics",
     "Atlus",
     "Bally",
     "Banpresto",
     "Best of MAME",
     "Capcom Classics",
     "Capcom Play System",
     "Capcom Play System II",
     "Capcom Play System III",
     "Cave",
     "Data East Classics",
     "Gaelco MAME",
     "HyperNeoGeo64",
     "Irem Classics",
     "Jaleco",
     "Kaneko",
     "Konami Classics",
     "MAME",
     "MAME 4 Players",
     "Mature MAME",
     "Midway Classics",
     "Mitchell Corporation",
     "Namco Classics",
     "Namco System 22",
     "Nichibutsu",
     "Nintendo Classics",
     "Psikyo",
     "Sammy",
     "Sega Classics",
     "Sega ST-V",
     "Seibu Kaihatsu",
     "Shotgun Games",
     "SNK Classics",
     "Taito Classics",
     "Technos Classics",
     "Tecmo",
     "Toaplan",
     "Trackball Games",
     "Visco",
     "Williams Classics",
     "Atari 5200",
     "Bally Astrocade",
     "ColecoVision",
     "Emerson Arcadia 2001",
     "Epoch Super Cassette Vision",
     "Fairchild Channel F",
     "GCE Vectrex",
     "Magnavox Odyssey 2",
     "Mattel Intellivision",
     "RCA Studio II",
     "VTech CreatiVision",
     "Amstrad GX4000",
     "Casio PV-1000",
     "VTech Socrates",
     "Funtech Super Acan",
     "SNK Neo Geo AES",
     "Casio Loopy",
     "Creatronic Mega Duck",
     "Entex Adventure Vision",
     "Epoch Game Pocket Computer",
     "Hartung Game Master",
     "Sega VMU",
     "Sony Pocketstation",
     "Tiger Game.com",
     "Watara Supervision",
     "Aamber Pegasus",
     "Exidy Sorcerer",
     "Interton VC 4000",
     "Vector-06C",
     "The King of Fighters Collection"]

    pack.addSystem(systems)
    pack.addResource(["D:\\Games\\Arcades\\MAME"])

    pack.rarTo("13-systems")

}

def collections(Pack pack) {
    def systems = ["Castlevania Collection", "The King of Fighters Collection", "Sonic Mega Collection", "Street Fighter Collection", "Killer Instinct Collection",
                   "Bomberman Collection", "Donkey Kong Collection", "Ghost'n Goblins Collection", "Double Dragon Collection", "Fatal Fury Collection",
                   "Final Fantasy Collection", "Final Fight Collection", "Legend of Zelda Collection", "Mega Man Collection", "Metal Slug Collection",
                   "Mortal Kombat Kollection", "Resident Evil Collection", "Samurai Shodown Collection", "Tekken Collection", "Dragon Warrior Collection",
                   "Shining Force Collection", "Street Fighter Hack Collection", "Super Mario Collection", "World Heroes Collection", "Contra Collection"]

    pack.addSystem(systems)

    String dependencies = listDependencies(systems, pack.hyperSpin)
    pack.createReadme(
            """Descomprimir todos los rars en tu unidad en la carpeta \\Games
Editar el fichero \\Games\\HyperSpin-fe\\Databases\\Main Menu\\Main Menu.xml con un editor de texto (bloc de notas, Notepad++) y añadir las siguientes líneas para dar de alta los nuevos sistemas:

    <game name="Castlevania Collection"/>
    <game name="The King of Fighters Collection"/>
    <game name="Sonic Mega Collection"/>
    <game name="Street Fighter Collection"/>
    <game name="Killer Instinct Collection"/>
    <game name="Bomberman Collection"/>
    <game name="Donkey Kong Collection"/>
    <game name="Ghost'n Goblins Collection"/>
    <game name="Double Dragon Collection"/>
    <game name="Fatal Fury Collection"/>
    <game name="Final Fantasy Collection"/>
    <game name="Final Fight Collection"/>
    <game name="Legend of Zelda Collection"/>
    <game name="Mega Man Collection"/>
    <game name="Metal Slug Collection"/>
    <game name="Mortal Kombat Kollection"/>
    <game name="Resident Evil Collection"/>
    <game name="Samurai Shodown Collection"/>
    <game name="Tekken Collection"/>
    <game name="Dragon Warrior Collection"/>
    <game name="Shining Force Collection"/>
    <game name="Street Fighter Hack Collection"/>
    <game name="Super Mario Collection"/>
    <game name="World Heroes Collection"/>
    <game name="Contra Collection"/>
    
Las colecciones no llevan roms, solo medias, por lo que necesitan tener instalados los siguientes sistemas (al lado, el número de roms que necesitan dicho sistema):

${dependencies}    
""")

    pack.rarTo()

}

String listDependencies(List<String> systems, HyperSpin hyperSpin) {
    String dependencies = ""
    systems.collect {
        RLSystem system = hyperSpin.getSystem(it)
        [system.defaultEmulator?.name] + system.listRoms()*.exe
    }.flatten().findAll().sort().countBy { it }.each { key, value ->
        dependencies += "    - ${key.toString().padRight(36, " ")}: ${value} rom${value > 1 ? "s" : ""}\n"
    }
    return dependencies
}


def pc1(Pack pack) {
    def systems =
            [
                    "Doujin Soft",
                            "Locomalito Games",
                            "MUGEN",
                            "OpenBOR",
                            "Party Games",
                            "PC Games",
                            "ScummVM",
                            "Slot Machines",
                            "Touhou Project",
                            "Daphne",
                            "Pack Remasterizados",
                            "Big Fish Games"]


    pack.addSystem(systems)
    pack.addResource([
            "d:\\Games\\PC\\Doujin Soft",
            "d:\\Games\\PC\\Locomalito Games",
            "d:\\Games\\PC\\MUGEN",
            "d:\\Games\\PC\\OpenBOR",
            "d:\\Games\\PC\\Party Games",
            "d:\\Games\\PC\\Microsoft Windows",
            "D:\\Games\\Emulators\\ScummVM",
            "D:\\Games\\PC\\ScummVM",
            "d:\\Games\\PC\\Systems\\Slots",
            "d:\\Games\\PC\\Touhou",
            "d:\\Games\\Isos\\Daphne",
            "D:\\Games\\Emulators\\Daphne",
            "d:\\Games\\PC\\Pack Remasterizados",
            "d:\\Games\\PC\\BigFish"
    ])

    pack.rarTo()

}

def pc2(Pack pack) {
    def systems =
            [
                    "Flash Games",
                            "Fruit Machine",
                            "Lucasarts Adventure Games",
                            "PopCap",
                            "Pacman Championship Edition DX",
                            "Pacman Museum",
                            "Arcade Music Trivia Challenger",
                            "DWJukebox",
                            "KODI",
                            "Plex",
                            "XBMC",
                            "Microsoft MS-DOS",
                            "Microsoft MS-DOS eXoDOS",
                            "Microsoft Windows 3.x"]


    pack.addSystem(systems)
    pack.addResource([
            "d:\\Games\\PC\\Flash Games",
            "D:\\Games\\Emulators\\Fruit Machines",
            "d:\\Games\\PC\\Fruit Machines",
            "d:\\Games\\PC\\Lucasarts",
            "D:\\Games\\PC\\PopCap",
            "d:\\Games\\Emulators\\ScummVM",
            "d:\\Games\\Extra",
            "d:\\Games\\PC\\Systems",
            "D:\\Games\\Emulators\\Dfend",
            "d:\\Games\\PC\\Microsoft MS-DOS",
            "D:\\Games\\PC\\Microsoft MS-DOS eXoDOS",
            "D:\\Games\\PC\\Microsoft Windows 3.x"
    ])

    pack.rarTo()

}

def isos1(Pack pack) {
    def systems =
            [
                    "Atari Jaguar CD",
                            "Commodore Amiga CD32",
                            "Commodore CDTV",
                            "NEC PC Engine-CD",
                            "NEC PC-FX",
                            "NEC TurboGrafx-CD",
                            "Panasonic 3DO",
                            "Philips CD-i",
                            "Sony PlayStation",
                            "Nintendo Wii",
                            "Nintendo WiiWare",
                            "Sony PSP",
                            "Sony PSP Minis"]


    pack.addSystem(systems, false)
    pack.addResource([
            "D:\\Games\\Emulators\\Project Tempest\\Project Tempest 0.95",
            "D:\\Games\\Emulators\\Commodore Amiga\\WinUAE 3.0.0",
            "D:\\Games\\Emulators\\Panasonic 3DO\\FourDO_1.3.2.3",
            "D:\\Games\\Emulators\\Nintendo Wii\\Dolphin 5",
            "D:\\Games\\Emulators\\PPSSPP\\ppsspp v1.4.2"
    ])


    pack.rarTo()

}

def isos2(Pack pack) {
    def systems =
            [
                    "Sega CD",
                            "Sega Mega-CD",
                            "Sega Saturn",
                            "Sega Saturn Japan",
                            "SNK Neo Geo CD",
                            "Nintendo GameCube",
                            "Sega Ages",
                            "Sega Dreamcast",
                            "Sony PlayStation 2"]


    pack.addSystem(systems, false)
    pack.addResource([
            "D:\\Games\\Emulators\\Sega\\Fusion364",
            "D:\\Games\\Emulators\\NeoRaine\\NeoRaine 1.4.3",
            "D:\\Games\\Emulators\\Nintendo GameCube\\Dolphin 5",
            "D:\\Games\\Emulators\\PCSX2\\PCXS2.gigapig",
            "D:\\Games\\Emulators\\nullDC\\EmuCR-nullDC-r150"
    ])


    pack.rarTo()

}
/*
mkdir "/volume1/store/sync/Isos1/Isos/Atari Jaguar CD"
mkdir "/volume1/store/sync/Isos1/Isos/Commodore Amiga CD32"
mkdir "/volume1/store/sync/Isos1/Isos/Commodore Amiga CD32.others"
mkdir "/volume1/store/sync/Isos1/Isos/Commodore CDTV"
mkdir "/volume1/store/sync/Isos1/Isos/NEC PC Engine-CD"
mkdir "/volume1/store/sync/Isos1/Isos/NEC PC Engine-CD.others"
mkdir "/volume1/store/sync/Isos1/Isos/NEC PC-FX"
mkdir "/volume1/store/sync/Isos1/Isos/NEC TurboGrafx-CD"
mkdir "/volume1/store/sync/Isos1/Isos/NEC TurboGrafx-CD.others"
mkdir "/volume1/store/sync/Isos1/Isos/Panasonic 3DO"
mkdir "/volume1/store/sync/Isos1/Isos/Philips CD-i"
mkdir "/volume1/store/sync/Isos1/Isos/Sony Playstation"
mkdir "/volume1/store/sync/Isos1/Isos/Sony PlayStation.DDR"
mkdir "/volume1/store/sync/Isos1/Isos/Sony Playstation.renamed"
mkdir "/volume1/store/sync/Isos1/Isos/Nintendo Wii"
mkdir "/volume1/store/sync/Isos1/Isos/Nintendo WiiWare"
mkdir "/volume1/store/sync/Isos1/Isos/Sony PSP"
mkdir "/volume1/store/sync/Isos1/Isos/Sony Playstation Minis"
mkdir "/volume1/store/sync/Isos2/Isos/Sega CD"
mkdir "/volume1/store/sync/Isos2/Isos/Sega Mega-CD"
mkdir "/volume1/store/sync/Isos2/Isos/Sega Saturn"
mkdir "/volume1/store/sync/Isos2/Isos/Sega Saturn Japan"
mkdir "/volume1/store/sync/Isos2/Isos/SNK Neo Geo CD"
mkdir "/volume1/store/sync/Isos2/Isos/SNK Neo Geo CD.others"
mkdir "/volume1/store/sync/Isos2/Isos/Nintendo GameCube"
mkdir "/volume1/store/sync/Isos2/Isos/Nintendo GameCube.renamed"
mkdir "/volume1/store/sync/Isos2/Isos/Sega Dreamcast"
mkdir "/volume1/store/sync/Isos2/Isos/Sega Dreamcast.more"
mkdir "/volume1/store/sync/Isos2/Isos/Sony PlayStation 2"
mkdir "/volume1/store/sync/Isos2/Isos/Sony PlayStation 2.DDR"
mkdir "/volume1/store/sync/Isos2/Isos/Sony PlayStation 2.Lucasarts"
mkdir "/volume1/store/sync/Isos2/Isos/Sony PlayStation 2.renamed"
mkdir "/volume1/store/sync/Isos2/Isos/Sony PlayStation 2.Sega Ages"



sudo mount -o bind "/volume1/store/Games/Isos/Atari Jaguar CD"               "/volume1/store/sync/Isos1/Isos/Atari Jaguar CD"
sudo mount -o bind "/volume1/store/Games/Isos/Commodore Amiga CD32"          "/volume1/store/sync/Isos1/Isos/Commodore Amiga CD32"
sudo mount -o bind "/volume1/store/Games/Isos/Commodore Amiga CD32.others"   "/volume1/store/sync/Isos1/Isos/Commodore Amiga CD32.others"
sudo mount -o bind "/volume1/store/Games/Isos/Commodore CDTV"                "/volume1/store/sync/Isos1/Isos/Commodore CDTV"
sudo mount -o bind "/volume1/store/Games/Isos/NEC PC Engine-CD"              "/volume1/store/sync/Isos1/Isos/NEC PC Engine-CD"
sudo mount -o bind "/volume1/store/Games/Isos/NEC PC Engine-CD.others"       "/volume1/store/sync/Isos1/Isos/NEC PC Engine-CD.others"
sudo mount -o bind "/volume1/store/Games/Isos/NEC PC-FX"                     "/volume1/store/sync/Isos1/Isos/NEC PC-FX"
sudo mount -o bind "/volume1/store/Games/Isos/NEC TurboGrafx-CD"             "/volume1/store/sync/Isos1/Isos/NEC TurboGrafx-CD"
sudo mount -o bind "/volume1/store/Games/Isos/NEC TurboGrafx-CD.others"      "/volume1/store/sync/Isos1/Isos/NEC TurboGrafx-CD.others"
sudo mount -o bind "/volume1/store/Games/Isos/Panasonic 3DO"                 "/volume1/store/sync/Isos1/Isos/Panasonic 3DO"
sudo mount -o bind "/volume1/store/Games/Isos/Philips CD-i"                  "/volume1/store/sync/Isos1/Isos/Philips CD-i"
sudo mount -o bind "/volume1/store/Games/Isos/Sony Playstation"              "/volume1/store/sync/Isos1/Isos/Sony Playstation"
sudo mount -o bind "/volume1/store/Games/Isos/Sony PlayStation.DDR"          "/volume1/store/sync/Isos1/Isos/Sony PlayStation.DDR"
sudo mount -o bind "/volume1/store/Games/Isos/Sony Playstation.renamed"      "/volume1/store/sync/Isos1/Isos/Sony Playstation.renamed"
sudo mount -o bind "/volume1/store/Games/Isos/Nintendo Wii"                  "/volume1/store/sync/Isos1/Isos/Nintendo Wii"
sudo mount -o bind "/volume1/store/Games/Isos/Nintendo WiiWare"              "/volume1/store/sync/Isos1/Isos/Nintendo WiiWare"
sudo mount -o bind "/volume1/store/Games/Isos/Sony PSP"                      "/volume1/store/sync/Isos1/Isos/Sony PSP"
sudo mount -o bind "/volume1/store/Games/Isos/Sony Playstation Minis"        "/volume1/store/sync/Isos1/Isos/Sony Playstation Minis"
sudo mount -o bind "/volume1/store/Games/Isos/Sega CD"                       "/volume1/store/sync/Isos2/Isos/Sega CD"
sudo mount -o bind "/volume1/store/Games/Isos/Sega Mega-CD"                  "/volume1/store/sync/Isos2/Isos/Sega Mega-CD"
sudo mount -o bind "/volume1/store/Games/Isos/Sega Saturn"                   "/volume1/store/sync/Isos2/Isos/Sega Saturn"
sudo mount -o bind "/volume1/store/Games/Isos/Sega Saturn Japan"             "/volume1/store/sync/Isos2/Isos/Sega Saturn Japan"
sudo mount -o bind "/volume1/store/Games/Isos/SNK Neo Geo CD"                "/volume1/store/sync/Isos2/Isos/SNK Neo Geo CD"
sudo mount -o bind "/volume1/store/Games/Isos/SNK Neo Geo CD.others"         "/volume1/store/sync/Isos2/Isos/SNK Neo Geo CD.others"
sudo mount -o bind "/volume1/store/Games/Isos/Nintendo GameCube"             "/volume1/store/sync/Isos2/Isos/Nintendo GameCube"
sudo mount -o bind "/volume1/store/Games/Isos/Nintendo GameCube.renamed"     "/volume1/store/sync/Isos2/Isos/Nintendo GameCube.renamed"
sudo mount -o bind "/volume1/store/Games/Isos/Sega Dreamcast"                "/volume1/store/sync/Isos2/Isos/Sega Dreamcast"
sudo mount -o bind "/volume1/store/Games/Isos/Sega Dreamcast.more"           "/volume1/store/sync/Isos2/Isos/Sega Dreamcast.more"
sudo mount -o bind "/volume1/store/Games/Isos/Sony PlayStation 2"            "/volume1/store/sync/Isos2/Isos/Sony PlayStation 2"
sudo mount -o bind "/volume1/store/Games/Isos/Sony PlayStation 2.DDR"        "/volume1/store/sync/Isos2/Isos/Sony PlayStation 2.DDR"
sudo mount -o bind "/volume1/store/Games/Isos/Sony PlayStation 2.Lucasarts"  "/volume1/store/sync/Isos2/Isos/Sony PlayStation 2.Lucasarts"
sudo mount -o bind "/volume1/store/Games/Isos/Sony PlayStation 2.renamed"    "/volume1/store/sync/Isos2/Isos/Sony PlayStation 2.renamed"
sudo mount -o bind "/volume1/store/Games/Isos/Sony PlayStation 2.Sega Ages"  "/volume1/store/sync/Isos2/Isos/Sony PlayStation 2.Sega Ages"

 */



class Pack {
    Packer packer
    String name
    File base

    HyperSpin getHyperSpin() {
        return packer.hyperSpin
    }

    File createFile(String filename) {
        new File(base, "${name}/${filename}").parentFile.mkdirs()
        new File(base, "${name}/${filename}")
    }

    void createReadme(String content) {
        File readme = createFile("readme.txt")
        println readme.absolutePath
        println "-----------------------"
        println content
        if (!packer.simulation) {
            readme.text = content
        }
    }

    List resources = []

    void addResource(List l) {
        resources.addAll(l)
    }
    void addResource(File r) {
        resources << r
    }
    void addResource(String r) {
        resources << r
    }

    String xml = ""
    void addSystem(String systemName, boolean includeRoms = true) {
        RLSystem system = hyperSpin.getSystem(systemName)
        if (system.executable) {
            xml = xml + "    <game name=\"${systemName}\" exe=\"true\"/>\n"
            println "${system.executableExe.absolutePath} [${system.name}] "
        } else {
            xml = xml + "    <game name=\"${systemName}\">\n"
            println "${system.defaultEmulator?.emuPath} [${system.name}] "
        }
        resources.addAll(packer.listSystemResources(system))
        if (includeRoms) {
            resources.addAll(system.romPathsList)
        }
    }
    void addSystem(List r, boolean includeRoms = true) {
        r.each { String it -> addSystem(it, includeRoms) }
    }

    void rarTo(String rar = name) {
        println xml
        packer.rarTo(resources, createFile(name+"/"+rar).toString())
    }

}

