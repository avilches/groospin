package org.hs5tb.groospin.checker.result

import org.hs5tb.groospin.base.RLSystem

/**
 * Created by Alberto on 12-Jun-16.
 */
class CheckTotalResult extends CheckResult {
    String systemName
    RLSystem system

    long totalSize
    int totalRoms = 0

    @Override
    void add(CheckResult checkResult) {
        if (checkResult instanceof CheckTotalResult) {
            totalRoms += checkResult.totalRoms
            totalSize += checkResult.totalSize
        }
        super.add(checkResult)
    }
}
