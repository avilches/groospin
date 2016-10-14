package operation

import org.hs5tb.groospin.base.HyperSpinDatabase

/**
 * Created by Alberto on 16-Aug-16.
 */
class Comparer {
    static void printDifferences(String system, String one, String other) {
        printDifferences(
                "${one}/${system}/${system}.xml",
                "${other}/${system}/${system}.xml")
    }

    static void printDifferences(String one, String other) {
        def fileOne = new File(one)
        def fileOther = new File(other)
        if (!fileOne.exists()) println "* File not found: ${fileOne}"
        if (!fileOther.exists()) println "* File not found: ${fileOther}"
        if (!fileOne.exists() || !fileOther.exists()) return
        Set oneGames = HyperSpinDatabase.listRomNames(fileOne) as LinkedHashSet
        Set otherGames = HyperSpinDatabase.listRomNames(fileOther) as LinkedHashSet
        Set oneMore = oneGames - otherGames
        Set otherMore = otherGames - oneGames
        println "${one} = ${oneGames.size()} (+${oneMore.size()}): ${oneMore}"
        println "${other} = ${otherGames.size()} ( +${otherMore.size()}): ${otherMore}"
        println "-------------------"
    }

}
