/*
* bq.com
*
* @author Alberto Vilches (alberto.vilches@bq.com)
* @date 12/7/16
* Copyright. All Rights Reserved.
*/
package org.hs5tb.groospin.checker.site

import com.xlson.groovycsv.PropertyMapper;

class RLSystemConfig {
    //    [0:0, "Romset origen":1, Fecha revisión BD:2, Estado:3, Missing:4, Origen:5, Tipo:6, Sistema:7, Padre:8, roms tamaño:9, database:10, roms db (real/exclud):11, roms folder:12, wheel:13, video:14, themes:15, Emu:16, estado:17, Comentarios emu:18, controles:19, controles p2:20, urls:21, config:22]

    String name
    boolean hidden
    String state
    String type
    boolean calculateSpace
    boolean stable
    boolean perfect

    RLSystemConfig() {
    }
    RLSystemConfig(PropertyMapper map) {
        name = map[7].trim()
        hidden = map[2].trim() == "OCULTO"
        state = map[3].trim()
        type = map[6].trim()
        stable = !state.contains("R")
        perfect = state == "OK"
        calculateSpace = !map[8] // Si no vacio, tiene padre, luego no se calcula espacio

    }
}