package examples

import groovy.xml.XmlUtil
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.base.Rom
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.Checker
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult

/**
 * Created by Alberto on 23-Dec-16.
 * Elimina los sistemas que tienen 0 juegos (y no son ejecutables)
 */


HyperSpin hs = new HyperSpin("G:/Games/RocketLauncher")

Set toDelete = []
hs.listSystems(true).each { RLSystem system ->
    if (system.isExecutable()) {
        if (!system.executableExe.exists()) {
            toDelete << system.name
            println "[${system.name}] Executable ${system.executableExe} missing"
        }
    } else {
        new Checker(hs).addHandler(new BaseCheckHandler() {
            @Override
            void endSystem(CheckTotalResult checkResult) {
                if (checkResult.roms == 0) {
                    toDelete << checkResult.systemName
                    println "[${system.name}] 0 roms"
                }
            }
        }).checkSystem(system.name)
    }
}

Node mainMenu = new XmlParser().parseText(hs.getMainMenuFile().text)
mainMenu.game.each { Node node ->
    if (node.@name in toDelete) {
        mainMenu.children().remove(node)
    }
}

new File(hs.getMainMenuFile().parentFile, "Main Menu.xml.clean").text = XmlUtil.serialize(mainMenu)

