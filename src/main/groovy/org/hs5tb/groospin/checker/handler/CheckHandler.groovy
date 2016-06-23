package org.hs5tb.groospin.checker.handler

import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult

/**
 * Created by Alberto on 12-Jun-16.
 */
interface CheckHandler {
    void startCheck()
    void startSystem(RLSystem system)
    void romChecked(CheckRomResult checkResult)
    void endSystem(CheckTotalResult checkResult)
    void endSystemWithError(String systemName, Exception e)
    void endCheck(CheckTotalResult checkResult)
}
