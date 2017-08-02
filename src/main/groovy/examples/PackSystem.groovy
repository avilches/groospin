package examples

import operation.Packer
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem

Packer packer = new Packer("D:/Games/RocketLauncher")
packer.simulation = false
File base = new File("d:\\parches")
base.mkdirs()



basic1(new Pack(packer: packer, base: base, name: "Basic-FreePack"))
// nintendo3DS(packer)
// pinballFX2(packer)
// mediaCenters(packer)


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

def collection1(Pack pack) {
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

    pack.rarTo("systems")

}

def pinballFX2(Packer packer) {
    String pck = "Hyperspin5tb.Pinball.FX2.VERSION-17.01"
    File base = createPack(pck)
    packer.rarTo(["Pinball FX2"], [],
            new File(base, "${pck}-systems").toString())
    packer.rarTo([], ["d:\\Games\\Arcades\\Pinball FX2 Build 030615\\"],
            new File(base, "${pck}-soft").toString())
    new File(base, "${pck}.readme.txt").text =
            """Descomprimir todos los rars en tu unidad en la carpeta \\Games
Editar el fichero \\Games\\HyperSpin-fe\\Databases\\Main Menu\\Main Menu.xml con un editor de texto (bloc de notas, Notepad++) y añadir la siguiente línea para dar de alta el nuevo sistema:

    <game name="Pinball FX2" />
"""
}

def nintendo3DS(Packer packer) {
    String pck = "Hyperspin5tb.Nintendo.3DS.VERSION-17.01"
    File base = createPack(pck)
    packer.rarTo(["Nintendo 3DS"], ["d:\\Games\\Emulators\\Citra", "d:\\Games\\RocketLauncher\\Settings\\Nintendo 3DS" ],
            new File(base, "${pck}-systems").toString())
    packer.rarTo([], ["d:\\Games\\Roms\\Nintendo 3DS"],
            new File(base, "${pck}-roms").toString())
    new File(base, "${pck}.readme.txt").text =
            """Descomprimir todos los rars en tu unidad en la carpeta \\Games
Editar el fichero \\Games\\HyperSpin-fe\\Databases\\Main Menu\\Main Menu.xml con un editor de texto (bloc de notas, Notepad++) y añadir la siguiente línea para dar de alta el nuevo sistema:

    <game name="Nintendo 3DS" />
"""
}

def mediaCenters(Packer packer) {
    String pck = "Hyperspin5tb.MediaCenters.VERSION-17.01"
    File base = createPack(pck)
    packer.rarTo(["KODI", "XBMC", "Plex", "Arcade Music Trivia Challenger", "Rockola", "DWJukebox"], [],
            new File(base, "${pck}-systems").toString())
    packer.rarTo([], ["D:/Games/Extra/KODI"],
            new File(base, "${pck}-soft").toString())
    new File(base, "${pck}.readme.txt").text =
            """Descomprimir todo en tu unidad en la carpeta \\Games
Editar el fichero \\Games\\HyperSpin-fe\\Databases\\Main Menu\\Main Menu.xml con un editor de texto (bloc de notas, Notepad++) y añadir las siguientes líneas para dar de alta los nuevos sistemas:

  <game name="Arcade Music Trivia Challenger" exe="true" />
  <game name="XBMC" exe="true" />
  <game name="Plex" exe="true" />
  <game name="KODI" exe="true" />
  <game name="Rockola" exe="true" />
  <game name="DWJukebox" exe="true" />
  
Solo para usuarios que tengan la colección en una unidad que no sea D: deben editar los siguientes ficheros de texto y cambiar la letra D: por la que corresponda:
                                       
  \\Games\\HyperSpin-fe\\Settings\\Arcade Music Trivia Challenger.ini
  \\Games\\HyperSpin-fe\\Settings\\XBMC.ini
  \\Games\\HyperSpin-fe\\Settings\\Plex.ini
  \\Games\\HyperSpin-fe\\Settings\\KODI.ini
  \\Games\\HyperSpin-fe\\Settings\\Rockola.ini
  \\Games\\HyperSpin-fe\\Settings\\DWJukebox.ini
"""
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


class Pack {
    Packer packer
    String name
    File base

    HyperSpin getHyperSpin() {
        return packer.hyperSpin
    }

    File createFile(String filename) {
        new File(base, name).mkdirs()
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
    void addSystem(String systemName) {
        RLSystem system = hyperSpin.getSystem(systemName)
        if (system.executable) {
            println "${system.executableExe.absolutePath} [${system.name}] "
        } else {
            println "${system.defaultEmulator?.emuPath} [${system.name}] "
        }


        resources.addAll(packer.listSystemResources(system))
    }
    void addSystem(List r) {
        r.each { String it -> addSystem(it) }
    }

    void rarTo(String rar) {
        packer.rarTo(resources, createFile(rar).toString())
    }

}

