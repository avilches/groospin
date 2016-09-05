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
        Set oneGames = HyperSpinDatabase.listRomNames(new File(one)) as LinkedHashSet
        Set otherGames = HyperSpinDatabase.listRomNames(new File(other)) as LinkedHashSet
        Set oneMore = oneGames - otherGames
        Set otherMore = otherGames - oneGames
        println "${one} = ${oneGames.size()} (+${oneMore.size()}): ${oneMore}"
        println "${other} = ${otherGames.size()} ( +${otherMore.size()}): ${otherMore}"
        println "-------------------"
    }

}
