/*
* bq.com
*
* @author Alberto Vilches (alberto.vilches@bq.com)
* @date 4/9/16
* Copyright. All Rights Reserved.
*/
package operation

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.common.Ini
import org.hs5tb.groospin.common.IniFile


HyperSpin hs = new HyperSpin(
        "/Users/avilches/Work/Proy/Local/groospin/src/main",
        "D:/Games/RocketLauncher")

hs.changeHyperSpinSettings("AAE", "exe info", "hola", "que tal4")