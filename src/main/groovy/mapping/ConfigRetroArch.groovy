package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RetroArch

class ConfigRetroArch {
    /*
    Configura solo el teclado sin solapes de pulsaciones (respecto a la configuración por defecto)
     */
    static void configureKeys(RetroArch retroArch) {
        retroArch.with {
            configureKeys(1,
                    [b     : "z",
                     a     : "x",
                     y     : "a",
                     x     : "s",
                     l     : "q",
                     r     : "w",
                     left  : "left",
                     down  : "down",
                     up    : "up",
                     right : "right",
                     select: "d",
                     start : "c"]
            )
            configureKeys(2,
                    [b     : "num1",
                     a     : "num2",
                     y     : "num3",
                     x     : "num4",
                     l     : "num5",
                     r     : "num6",
                     left  : "num7",
                     down  : "num8",
                     up    : "num9",
                     right : "num0",
                     select: "g",
                     start : "b"]
            )
            save()
        }
    }

}