package org.hs5tb.groospin.checker

import org.hs5tb.groospin.common.IOTools

/**
 * Created by Alberto on 12-Jun-16.
 */
class CheckResult {
    String systemName
    String game
    long totalSize
    int games = 0
    int roms = 0
    int works = 0
    int exes = 0
    int wheels = 0
    int videos = 0
    int themes = 0
    int artwork1 = 0
    int artwork2 = 0
    int artwork3 = 0
    int artwork4 = 0

    String toString() {
        getLongInfo(", ")
    }

    String getQuickInfo(String delimiter) {
        ["\"${systemName}${game ? ":${game}" : ""}\"", games, roms, wheels, videos].join(delimiter)
    }

    String getShortInfo(String delimiter) {
        ["\"${systemName}${game ? ":${game}" : ""}\"", totalSize, "\"${IOTools.humanReadableByteCount(totalSize)}\"", games, roms, exes, works, wheels, videos].join(delimiter)
    }

    String getLongInfo(String delimiter) {
        getShortInfo(delimiter) + delimiter + [themes, artwork1, artwork2, artwork3, artwork4].join(delimiter)
    }

}
