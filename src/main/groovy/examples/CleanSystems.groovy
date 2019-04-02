package examples

import groovy.xml.XmlUtil
import operation.DatabaseOperations
import operation.Operations
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.base.Rom
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.Checker
import org.hs5tb.groospin.checker.handlers.HumanInfo
import org.hs5tb.groospin.checker.handlers.PrintMissing
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult
import org.hs5tb.groospin.common.IOTools

/**
 * Created by Alberto on 23-Dec-16.
 * Elimina los sistemas que tienen 0 juegos o son ejecutables de HyperSpin que no existen.
 * Ideal para discos de menos capacidad a los que se han quitado carpetas.
 */


HyperSpin hs = new HyperSpin("F:/Games/RocketLauncher")
boolean simulation = false


DatabaseOperations operations = new DatabaseOperations(hs)
operations.simulation = simulation
operations.removeFromDatabase("-with-missing", [Operations.MISSING, Operations.IS_CLONE]) /// , systems)


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
println "${systemsOk} systems ok"

if (!simulation) {
    println "Media folders renamed. Delete these to free space:"
    toDelete.each {
        File mediaFolder = hs.getSystem(it).getMediaFolder()
        File newName = new File(mediaFolder.parentFile, mediaFolder.name + ".delete")
        println "${newName}"
        mediaFolder.renameTo(newName)
    }

    Node mainMenu = new XmlParser().parseText(hs.getMainMenuFile().text)
    mainMenu.game.each { Node node ->
        if (node.@name in toDelete) {
            mainMenu.children().remove(node)
        }
    }

    File backup = new File(hs.getMainMenuFile().parentFile, "Main Menu.backup-${new Date().format("yyyy-MM-dd HH-mm")}.xml")
    IOTools.copy(hs.getMainMenuFile(), backup)
    hs.getMainMenuFile().text = XmlUtil.serialize(mainMenu)
    println "New main menu with created with ${systemsOk} systems only"
    println "You have a backup in ${backup.absolutePath}"
}

