package mame

["Namco Classics",
"Atari Classics", "Capcom Classics", "Cave", "Data East Classics", "MAME",
"Kaneko", "Banpresto", "Irem Classics", "Williams Classics", "Midway Classics",
"Sega Classics", "Konami Classics", "Taito Classics", "SNK Classics"].sort().each {
    new Comparer().run(it,
            "d:/Games/HyperSpin-fe/Databases",
            "k:/HyperSpin/D/Games/HyperSpin-fe/Databases"
    )
}

/**
 * Created by Alberto on 16-Aug-16.
 */
class Comparer {
    void run(String system, String one, String other) {
        run(  "${one}/${system}/${system}.xml",
            "${other}/${system}/${system}.xml")
    }

    void run(String one, String other) {

        Node on = new XmlParser().parse(new File(one))
        Node ot = new XmlParser().parse(new File(other))
        Set onGames = on.game.collect { it.@name }.sort() as LinkedHashSet
        Set otGames = ot.game.collect { it.@name }.sort() as LinkedHashSet

        def oneMas = onGames-otGames
        def otMas = otGames-onGames
        println "${one} +${oneMas.size()}: ${oneMas}"
        println "${other} +${otMas.size()}: ${otMas}"
    }

}
