package examples.operations

import operation.DatabaseOperations
import operation.Operations


/*
Elimina de las database los juegos clones. No tiene mucho sentido hacerlo porque es posible hacer lo mismo
desde la configuración de HyperSpin para que no salgan los clones en los menus con

        ini.put("filters", "parents_only", "true") // no clones

 */

List systems = ["Namco Classics",
                "Atari Classics", "Capcom Classics", "Cave", "Data East Classics", "MAME",
                "Banpresto", "Kaneko", "Irem Classics", "Williams Classics", "Midway Classics",
                "Sega Classics", "Konami Classics", "Taito Classics", "SNK Classics"]

new DatabaseOperations("D:/Games/HyperSpin-fe").removeFromDatabase("-with-clones", [Operations.IS_CLONE], systems)
