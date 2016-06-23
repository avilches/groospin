/*
 * hs5tb
 *
 * @author Alberto Vilches (alberto.vilches@hs5tb)
 * @date 13/6/16
 * Copyright. All Rights Reserved.
 */
package org.hs5tb.groospin.checker

import groovy.transform.CompileStatic
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.base.Rom
import org.hs5tb.groospin.checker.handler.CheckHandler
import org.hs5tb.groospin.checker.result.CheckResult
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult
import org.hs5tb.groospin.common.IOTools;

@CompileStatic
class Checker {

    HyperSpin hyperSpin
    CheckHandler handler
    boolean calculateSize = true
    RomChecker romChecker = new RomChecker()

    Checker(HyperSpin hyperSpin) {
        this.hyperSpin = hyperSpin
    }

    Checker(HyperSpin hyperSpin, CheckHandler handler) {
        this.hyperSpin = hyperSpin
        this.handler = handler
    }

    void checkSystems(List<String> systems = null) {
        if (!systems) systems = hyperSpin.listSystemNames()
        wrap {
            CheckTotalResult checkResultTotal = new CheckTotalResult()
            systems.each { String systemName ->
                CheckTotalResult checkResultSystem = checkSystemRoms(systemName)
                checkResultTotal.add(checkResultSystem)
            }
            return checkResultTotal
        }
    }

    void checkSystem(String systemName, String rom) {
        checkSystem(systemName, [rom])
    }

    void checkSystem(String systemName, List<String> romNames = null) {
        wrap {
            return checkSystemRoms(systemName, romNames)
        }
    }

    void wrap(Closure<CheckTotalResult> closure) {
        handler?.startCheck()
        CheckTotalResult total = closure.call()
        handler?.endCheck(total)
    }

    long calculateRomPathSize(RLSystem system) {
        long totalSize = 0
        system.romPathsList?.each { File romFolder -> totalSize += IOTools.folderSize(romFolder) }
        return totalSize
    }

    CheckTotalResult checkSystemRoms(String systemName, Collection<String> romNames = null) {
        CheckTotalResult checkTotalResult = new CheckTotalResult(systemName: systemName)
        try {
            RLSystem system = hyperSpin.getSystem(systemName)
            handler?.startSystem(system)
            List<Rom> roms = system.listRoms(romNames)
            checkTotalResult.systemName = system.name
            checkTotalResult.system = system
            println "Checking ${systemName}..."
            checkTotalResult.totalRoms = roms.size()
            checkTotalResult.totalSize = calculateSize ? calculateRomPathSize(system) : 0

            roms.sort{ it.name }.each { Rom rom ->
                CheckResult checkResultRom = romChecker.check(system, rom.name)
                checkResultRom.romName = rom.name
                checkTotalResult.add(checkResultRom)
                handler?.romChecked(checkResultRom)
            }
            handler?.endSystem(checkTotalResult)
        } catch (e) {
            handler?.endSystemWithError(systemName, e)
        }
        return checkTotalResult
    }

    CheckRomResult checkRom(RLSystem system, String rom) {
        romChecker.check(system, rom)
    }
}