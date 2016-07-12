package org.hs5tb.groospin.checker

import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult
import org.hs5tb.groospin.checker.site.RLSystemConfig

/**
 * Created by Alberto on 12-Jun-16.
 */
interface CheckHandler {
    void startCheck()
    void startGroup(String groupName)
    void startSystem(RLSystem system)
    void romChecked(CheckRomResult checkResult)
    void endSystem(CheckTotalResult checkResult)
    void endSystemWithError(String systemName, Exception e)
    void endCheck(CheckTotalResult checkResult)
    void endGroup(CheckTotalResult checkResult)
    void setSystemConfig(RLSystemConfig systemConfig)

    boolean needsRomFolderSize()
    boolean needsMediaFolderSize()
}
