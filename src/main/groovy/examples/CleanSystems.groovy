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
 * Elimina los sistemas que tienen 0 juegos o son ejecutables de HyperSpin que no existen.
 * Ideal para discos de menos capacidad a los que se han quitado carpetas.
 */


HyperSpin hs = new HyperSpin("I:/Games/RocketLauncher")
boolean simulation = false

Set toDelete = []
int systemsOk = 0
new Checker(hs).
        addHandler(new HumanInfo(true)).
        addHandler(new BaseCheckHandler() {
    @Override
    void endSystem(CheckTotalResult checkResult) {
        if (checkResult.system.isExecutable()) {
            if (!checkResult.system.executableExe.exists()) {
                toDelete << checkResult.systemName
                println "[${checkResult.systemName}] Executable ${checkResult.system.executableExe} missing"
            } else {
                systemsOk++
            }
        } else if (checkResult.roms == 0 && checkResult.systemName != "Pinball Arcade") {
            toDelete << checkResult.systemName
            println "----- 0 roms: ${checkResult.systemName}"
        } else {
            systemsOk++
        }
    }
}).checkSystems()
//}).checkSystems(["Apple II"])

println "0 roms systems: ${toDelete.join(", ")}"
println "${systemsOk} roms"

if (!simulation) {
    toDelete.each {
        File mediaFolder = hs.getSystem(it).getMediaFolder()
        File newName = new File(mediaFolder.parentFile, mediaFolder.name + ".delete")
        println "Renaming ${mediaFolder} -> ${newName}"
        mediaFolder.renameTo(newName)
    }

    Node mainMenu = new XmlParser().parseText(hs.getMainMenuFile().text)
    mainMenu.game.each { Node node ->
        if (node.@name in toDelete) {
            mainMenu.children().remove(node)
        }
    }

    new File(hs.getMainMenuFile().parentFile, "Main Menu.xml.clean").text = XmlUtil.serialize(mainMenu)
}

