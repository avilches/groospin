package mame

["Namco Classics",
"Atari Classics", "Capcom Classics", "Cave", "Data East Classics", "MAME",
"Kaneko", "Banpresto", "Irem Classics", "Williams Classics", "Midway Classics",
"Sega Classics", "Konami Classics", "Taito Classics", "SNK Classics"].sort().each {
    new Comparer().run(it,
            "d:\\Games\\HyperSpin-fe\\Databases",
            "k:\\HyperSpin\\D\\Games\\HyperSpin-fe\\Databases"
    )
}

/**
 * Created by Alberto on 16-Aug-16.
 */
class Comparer {
    void run(String system, String one, String other) {

        Node on = new XmlParser().parse(new File("${one}\\${system}\\${system}.xml"))
        Node ot = new XmlParser().parse(new File("${other}\\${system}\\${system}.xml"))
        Set onGames = on.game.collect { it.@name }.sort() as LinkedHashSet
        Set otGames = ot.game.collect { it.@name }.sort() as LinkedHashSet

        println "${system} 1+ ${onGames-otGames}"
        println "${system} 2+ ${otGames-onGames}"
    }

}
