package org.hs5tb.groospin.base

/**
 * Created by Alberto on 12-Jun-16.
 */
class CheckResult {
    String systemName
    String game
    long totalSize
    int games = 0
    int roms = 0
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
        ["\"${systemName}${game ? ":${game}" : ""}\"", totalSize, "\"${humanReadableByteCount(totalSize)}\"", games, roms, wheels, videos].join(delimiter)
    }

    String getLongInfo(String delimiter) {
        getShortInfo(delimiter) + delimiter + [themes, artwork1, artwork2, artwork3, artwork4].join(delimiter)
    }

    String humanReadableByteCount(long bytes, boolean si = true) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = ((si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) as String) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
