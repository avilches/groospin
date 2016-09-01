package examples

import operation.Comparer

["Namco Classics",
"Atari Classics", "Capcom Classics", "Cave", "Data East Classics", "MAME",
"Kaneko", "Banpresto", "Irem Classics", "Williams Classics", "Midway Classics",
"Sega Classics", "Konami Classics", "Taito Classics", "SNK Classics"].sort().each {
    Comparer.printDifferences(it,
            "d:/Games/HyperSpin-fe/Databases",
            "k:/HyperSpin/D/Games/HyperSpin-fe/Databases"
    )
}


