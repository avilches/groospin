package examples

import operation.DatabaseOperations
import operation.Operations
import org.hs5tb.groospin.base.HyperSpin

List systems = ["Namco Classics",
                "Atari Classics", "Capcom Classics", "Cave", "Data East Classics", "MAME",
                "Banpresto", "Kaneko", "Irem Classics", "Williams Classics", "Midway Classics",
                "Sega Classics", "Konami Classics", "Taito Classics", "SNK Classics"]

new DatabaseOperations("D:/Games/HyperSpin-fe").removeFromDatabase("-with-clones", [Operations.IS_CLONE], systems)
