package org.hs5tb.groospin.checker.result

import org.hs5tb.groospin.base.RLSystem

/**
 * Created by Alberto on 12-Jun-16.
 */
class CheckResult {
    String systemName
    RLSystem system
    String group

    int roms = 0
    int exes = 0
    int wheels = 0
    int videos = 0
    int themes = 0
    int artworks1 = 0
    int artworks2 = 0
    int artworks3 = 0
    int artworks4 = 0

    void add(CheckResult checkResult) {
        roms += checkResult.roms
        exes += checkResult.exes
        wheels += checkResult.wheels
        videos += checkResult.videos
        themes += checkResult.themes
        artworks1 += checkResult.artworks1
        artworks2 += checkResult.artworks2
        artworks3 += checkResult.artworks3
        artworks4 += checkResult.artworks4
    }
}
