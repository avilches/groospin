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
import org.hs5tb.groospin.common.IOTools;

@CompileStatic
public class Checker {

    HyperSpin hyperSpin
    Report report
    boolean calculateSize = true

    Checker(HyperSpin hyperSpin) {
        this.hyperSpin = hyperSpin
    }

    Checker(HyperSpin hyperSpin, String reportRoot) {
        this(hyperSpin, new File(reportRoot))
    }

    Checker(HyperSpin hyperSpin, File reportRoot) {
        this(hyperSpin, new Report(reportRoot))
    }

    Checker(HyperSpin hyperSpin, Report report) {
        this.hyperSpin = hyperSpin
        this.report = report
    }

    void checkSystems(List<String> systems = null) {
        if (!systems) systems = hyperSpin.listSystemNames()
        report {
            CheckResult checkResultTotal = new CheckResult()
            systems.each { String systemName ->
                CheckResult checkResultSystem = checkSystemRoms(systemName)
                checkResultTotal.sum(checkResultSystem)
            }
            return checkResultTotal
        }
    }

    void checkSystem(String systemName, String rom) {
        checkSystem(systemName, [rom])
    }

    void checkSystem(String systemName, List<String> romNames = null) {
        report {
            return checkSystemRoms(systemName, romNames)
        }
    }

    void report(Closure<CheckResult> closure) {
        report?.startReport()
        CheckResult total = closure.call()
        report?.endReport(total)
    }

    long calculateRomPathSize(RLSystem system) {
        long totalSize = 0
        system.romPathsList?.each { File romFolder -> totalSize += IOTools.folderSize(romFolder) }
        return totalSize
    }

    CheckResult checkSystemRoms(String systemName, List<String> romsNames = null) {
        CheckResult checkResultSystem = new CheckResult(systemName: systemName)
        try {
            RLSystem system = hyperSpin.getSystem(systemName)
            report?.startSystem(system)
            if (!romsNames) romsNames = system.listRomNames()
            checkResultSystem.systemName = system.name
            checkResultSystem.system = system
            print "Checking ${systemName}"
            checkResultSystem.totalSize = calculateSize ? calculateRomPathSize(system) : 0
            checkResultSystem.totalRoms = romsNames.size()
            checkResultSystem.romName =  romsNames.size() == 1 ? romsNames.first() : null

            romsNames.sort().each { String romName ->

                CheckResult checkResultRom = checkRom(system, romName)

                checkResultSystem.sum(checkResultRom)

                report?.romChecked(system, checkResultRom)
            }
            report?.endSystem(system, checkResultSystem)
        } catch (e) {
            report?.endSystemWithError(e)
        }
        println ""
        return checkResultSystem
    }

    private CheckResult checkRom(RLSystem system, String romName) {
        File romFound = system.findValidRom(romName)

        CheckResult checkResultRom = new CheckResult(totalRoms: 1, romName: romName)
        if (romFound) {
            if (system.needsExecutable()) {
                File exe = system.findExecutable(romName, romFound)
                if (exe) {
                    checkResultRom.roms = checkResultRom.exes = 1
                } else {
                    checkResultRom.roms = 1
                    checkResultRom.exes = 0
                }
            } else {
                checkResultRom.roms = checkResultRom.exes = 1
            }
        } else {
            checkResultRom.roms = checkResultRom.exes = 0
        }

        checkResultRom.wheels = existsInMedia("${system.name}/Images/Wheel/${romName}", ["jpg", "png"]) ? 1 : 0
        checkResultRom.videos = existsInMedia("${system.name}/Video/${romName}", ["mp4", "flv"]) ? 1 : 0
        checkResultRom.themes = existsInMedia("${system.name}/Themes/${romName}", ["zip"]) ? 1 : 0
        checkResultRom.artwork1 = existsInMedia("${system.name}/Images/Artwork1/${romName}", ["jpg", "png"]) ? 1 : 0
        checkResultRom.artwork2 = existsInMedia("${system.name}/Images/Artwork2/${romName}", ["jpg", "png"]) ? 1 : 0
        checkResultRom.artwork3 = existsInMedia("${system.name}/Images/Artwork3/${romName}", ["jpg", "png"]) ? 1 : 0
        checkResultRom.artwork4 = existsInMedia("${system.name}/Images/Artwork4/${romName}", ["jpg", "png"]) ? 1 : 0
        return checkResultRom
    }

    boolean existsInMedia(String path, List extensions) {
        return IOTools.findFileWithExtensions(new File(hyperSpin.hsRoot, "Media/${path}"), extensions)
    }

/*
    void listSystems(List systems) {
        log(["systemName","bytes","human size","db xml games","rom files","media:wheels","media:videos","media:themes","media:artwork1","media:artwork2","media:artwork3","media:artwork4"].join(delimiter))

        systems.each { String systemName ->
            haveSystem(systemName)
            CheckResult checkResult = checkAllGames(systemName)
            log(checkResult.getLongInfo(delimiter))
            endHave()
        }
    }

 */

}