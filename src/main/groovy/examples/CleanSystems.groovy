package examples

import groovy.xml.XmlUtil
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.base.Rom
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.Checker
import org.hs5tb.groospin.checker.handlers.HumanInfo
import org.hs5tb.groospin.checker.handlers.PrintMissing
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult

/**
 * Created by Alberto on 23-Dec-16.
 * Elimina los sistemas que tienen 0 juegos (y no son ejecutables)
 */


HyperSpin hs = new HyperSpin("G:/Games/RocketLauncher")
boolean simulation = true

Set toDelete = []
new Checker(hs).
        addHandler(new HumanInfo(true)).
        addHandler(new BaseCheckHandler() {
    @Override
    void endSystem(CheckTotalResult checkResult) {
        if (checkResult.system.isExecutable()) {
            if (!checkResult.system.executableExe.exists()) {
                toDelete << checkResult.systemName
                println "[${checkResult.systemName}] Executable ${checkResult.system.executableExe} missing"
            }
        } else if (checkResult.roms == 0 && checkResult.systemName != "Pinball Arcade") {
            toDelete << checkResult.systemName
            println "[${checkResult.systemName}] 0 roms"
        }

    }
}).checkSystems()


println "0 roms systems: ${toDelete.join(", ")}"
toDelete.each {
    File mediaFolder = hs.getSystem(it).getMediaFolder()
    File newName = new File(mediaFolder.parentFile, mediaFolder.name + ".delete")
    println "Renaming ${mediaFolder} -> ${newName}"
    if (!simulation) {
        mediaFolder.renameTo(newName)
    }
}

Node mainMenu = new XmlParser().parseText(hs.getMainMenuFile().text)
mainMenu.game.each { Node node ->
    if (node.@name in toDelete) {
        mainMenu.children().remove(node)
    }
}

new File(hs.getMainMenuFile().parentFile, "Main Menu.xml.clean").text = XmlUtil.serialize(mainMenu)

