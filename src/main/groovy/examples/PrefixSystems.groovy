package examples

import groovy.xml.XmlUtil
import org.hs5tb.groospin.base.HyperSpin

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

Set retroarch = hs.listSystemsRetroArch()*.name
Set mame = hs.listSystemsMAME()*.name
Set mess = hs.listSystemsMESS()*.name
Set exes = hs.listSystemNames(true) - hs.listSystemNames()
Set packs = hs.listSystems().findAll{ !it.defaultEmulator?.name }*.name
Set others = hs.listSystemNames() - (retroarch + mame + mess + packs)

mainMenu(hs, retroarch, "Main Menu - RetroArch.xml")
mainMenu(hs, mame, "Main Menu - MAME.xml")
mainMenu(hs, mess, "Main Menu - MESS.xml")
mainMenu(hs, exes, "Main Menu - Executables.xml")
mainMenu(hs, packs, "Main Menu - Packs.xml")
mainMenu(hs, others, "Main Menu - Others.xml")
mainMenu(hs, retroarch + mame + packs, "Main Menu - RetroArch MAME packs.xml")

def mainMenu(HyperSpin hs, Set systems, String file) {
    Node mainMenu = new XmlParser().parseText( hs.getMainMenuFile().text)
    mainMenu.game.each { Node node ->
        if (!(node.@name in systems)) {
            mainMenu.children().remove(node)
        }
    }

    File f = new File(hs.getMainMenuFile().parentFile, file)
    println "Creating ${f} with ${mainMenu.children().size()} systems"
    f.text = XmlUtil.serialize(mainMenu)
}

