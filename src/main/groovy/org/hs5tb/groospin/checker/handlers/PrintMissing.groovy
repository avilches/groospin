package org.hs5tb.groospin.checker.handlers

import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult
import org.hs5tb.groospin.common.FileBuffer

/**
 * Created by Alberto on 12-Jun-16.
 */
class PrintMissing extends BaseCheckHandler {

    @Override
    void romChecked(CheckRomResult checkResult) {
        if (!checkResult.exes) {
            println "${checkResult.originalSystem.name != checkResult.systemName?checkResult.originalSystem.name+":":""}${checkResult.systemName}: rom \"${checkResult.romName}\" missing${checkResult.roms?" but rom found in ${checkResult.rom.romFileFound.canonicalPath}":""}"
        }
    }
}
