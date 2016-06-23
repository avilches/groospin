package org.hs5tb.groospin.checker

import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult

/**
 * Created by Alberto on 12-Jun-16.
 */
class BaseCheckHandler implements CheckHandler {
    @Override
    void startCheck() {
    }

    @Override
    void startSystem(RLSystem system) {
    }

    @Override
    void romChecked(CheckRomResult checkResult) {
    }

    @Override
    void endSystem(CheckTotalResult checkResult) {
    }

    @Override
    void endSystemWithError(String systemName, Exception e) {
        throw e
    }

    @Override
    void endCheck(CheckTotalResult checkResult) {
    }

    @Override
    boolean needsRomFolderSize() {
        return false
    }

    @Override
    boolean needsMediaFolderSize() {
        return false
    }
}
