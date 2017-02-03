/*
 * hs5tb
 *
 * @author Alberto Vilches (alberto.vilches@hs5tb)
 * @date 13/6/16
 * Copyright. All Rights Reserved.
 */
package org.hs5tb.groospin.checker

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.base.Rom
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult
import org.hs5tb.groospin.checker.site.RLSystemConfig
import org.hs5tb.groospin.common.IOTools;

class Checker {

    HyperSpin hyperSpin
    Collection<CheckHandler> handlers = []
    Boolean calculateRomSize = null
    Boolean calculateMediaSize = null
    RomChecker romChecker = new RomChecker()
    Closure romFilter

    Checker(HyperSpin hyperSpin) {
        this.hyperSpin = hyperSpin
    }

    Checker(HyperSpin hyperSpin, CheckHandler handler) {
        this.hyperSpin = hyperSpin
        addHandler(handler)
    }

    Checker addHandler(CheckHandler handler) {
        handlers << handler
        resetCachedFlags()
        return this
    }

    Checker setRomFilter(Closure romFilter) {
        this.romFilter = romFilter
        return this
    }

    void resetCachedFlags() {
        calculateRomSize = null
        calculateMediaSize = null
    }

    void checkSystemGroup(Map<String, Collection<RLSystemConfig>> systemGrouped) {
        wrap {
            CheckTotalResult checkResultTotal = new CheckTotalResult()
            systemGrouped.each { String groupName, Collection<RLSystemConfig> systems ->
                handlers*.startGroup(groupName)
                CheckTotalResult checkGroupTotal = new CheckTotalResult(group: groupName)
                systems.each { RLSystemConfig systemConfig ->
                    if (systemConfig.hidden) return
                    handlers*.systemConfig = systemConfig
                    CheckTotalResult checkResultSystem = checkSystemRoms(groupName, systemConfig.name, systemConfig.calculateSpace)
                    if (!systemConfig.calculateSpace) {
                        checkResultSystem.totalRomSize = 0
                    }
                    checkGroupTotal.add(checkResultSystem)
                }
                handlers*.endGroup(checkGroupTotal)
                checkResultTotal.add(checkGroupTotal)
            }
            return checkResultTotal
        }
    }
    void checkSystems(List<String> systems = null) {
        if (!systems) systems = hyperSpin.listSystemNames()
        wrap {
            CheckTotalResult checkResultTotal = new CheckTotalResult()
            systems.each { String systemName ->
                CheckTotalResult checkResultSystem = checkSystemRoms(null, systemName)
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
            return checkSystemRoms(null, systemName, romNames)
        }
    }

    void wrap(Closure<CheckTotalResult> closure) {
        resetCachedFlags()
        handlers*.startCheck()
        CheckTotalResult total = closure.call()
        handlers*.endCheck(total)
    }

    long calculateMediaPathSize(RLSystem system) {
        if (calculateMediaSize == null) {
            CheckHandler checkHandler = handlers.find { it.needsMediaFolderSize() }
            if (checkHandler) {
                // println "Calculate media path size ordered by ${checkHandler}"
                calculateMediaSize = true
            }
        }
        if (calculateMediaSize) {
            return IOTools.folderSize(hyperSpin.newHyperSpinFile("Media/${system.name}"))
        }
        return 0
    }

    long calculateRomPathSize(RLSystem system) {
        if (calculateRomSize == null) {
            CheckHandler checkHandler = handlers.find { it.needsRomFolderSize() }
            if (checkHandler) {
                // println "Calculate rom path size ordered by ${checkHandler}"
                calculateRomSize = true
            }
        }
        long totalSize = 0
        if (calculateRomSize) {
            system.romPathsList?.each { File romFolder -> totalSize += IOTools.folderSize(romFolder) }
        }
        return totalSize

    }

    CheckTotalResult checkSystemRoms(String group, String systemName, boolean calculateRomPath = true, Collection<String> romNames = null) {
        CheckTotalResult checkTotalResult = new CheckTotalResult(group: group, systemName: systemName)
        try {
            RLSystem system = hyperSpin.getSystem(systemName)
            handlers*.startSystem(system)
            checkTotalResult.systemName = system.name
            checkTotalResult.system = system
            checkTotalResult.totalRomSize = calculateRomPath?calculateRomPathSize(system):0
            checkTotalResult.totalMediaSize = calculateMediaPathSize(system)
            if (!system.executable) {
                List<Rom> roms = system.listRoms(romNames)
                if (romFilter) {
                    roms = roms.findAll romFilter
                }
                checkTotalResult.totalRoms = roms.size()

                roms.sort { it.name }.each { Rom rom ->
                    CheckRomResult checkResultRom = romChecker.check(system, rom)
                    checkResultRom.rom = rom
                    checkResultRom.group = group
                    checkTotalResult.add(checkResultRom)
                    handlers*.romChecked(checkResultRom)
                }
            }
            handlers*.endSystem(checkTotalResult)
        } catch (e) {
            handlers*.endSystemWithError(systemName, e)
        }
        return checkTotalResult
    }

}