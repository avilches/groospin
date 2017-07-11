package mapping

import org.hs5tb.groospin.base.HyperSpin

/**
 * Created by Alberto on 11-Jul-17.
 */
class FourDOMapping {
    static setDefaultKeys(HyperSpin hs) {
        Map p1 = [
                Up: "Up", Down: "Down", Left: "Left", Right: "Right",
                A : "Z",
                B : "X",
                C : "A",
                X : "D5", // STOP
                P : "D1", // PLAY/PAUSE
                L : "Q",
                R : "W"]
        // numeros D1..D9
        // ControlKey, ShiftKey, Space, Return
        Map p2 = [
                Up: "NumPad8", Down: "NumPad2", Left: "NumPad4", Right: "NumPad6",
                A : "C",
                B : "V",
                C : "D",
                X : "D6", // STOP
                P : "D2", // PLAY/PAUSE
                L : "E",
                R : "R"]
        Map console = [ConsoleStateSave           : "F2",
                       ConsoleStateLoad           : "F4",
                       ConsoleStateSlotPrevious   : "F6",
                       ConsoleStateSlotNext       : "F7",
                       ConsoleFullScreen          : "F",
                       ConsoleScreenShot          : "F3",
                       ConsolePause               : "P",
                       ConsoleAdvanceBySingleFrame: "F10",
                       ConsoleReset               : "F12"
        ]
        File settings = new File(hs.fourDOFolder, "Settings\\JohnnyInputBindings.xml")
        settings.text =
                """<InputBindingDevices className="FourDO.Emulation.Plugins.Input.JohnnyInput.InputBindingDevices">
\t<FormatVersion>2</FormatVersion>
\t<Count>7</Count>
\t<System.Collections.IEnumerable>
${inputBindingDevices(console)}
${inputBindingDevices(p1)}
${inputBindingDevices(p2)}
${inputBindingDevices()}
${inputBindingDevices()}
${inputBindingDevices()}
${inputBindingDevices()}
\t</System.Collections.IEnumerable>
</InputBindingDevices>"""


        println "- FourDO reset: keyboard:"
        println settings.absolutePath
    }

    private static String inputBindingDevices(Map config = [:]) {
        String bindings = ""
        if (config) {
            bindings = "\n\t\t\t\t\t<System.Collections.IEnumerable>\n"

            config.each {
                bindings += """\t\t\t\t\t\t<InputBinding className="FourDO.Emulation.Plugins.Input.JohnnyInput.InputBinding">
\t\t\t\t\t\t\t<Button>${it.key}</Button>
\t\t\t\t\t\t\t<Trigger className="FourDO.Emulation.Plugins.Input.JohnnyInput.KeyboardInputTrigger">
\t\t\t\t\t\t\t\t<FriendlyName>${it.value}</FriendlyName>
\t\t\t\t\t\t\t\t<Key>${it.value}</Key>
\t\t\t\t\t\t\t</Trigger>
\t\t\t\t\t\t</InputBinding>
"""
            }
            bindings += "\t\t\t\t\t</System.Collections.IEnumerable>"

        }
        return """\t\t<InputBindingDevice className="FourDO.Emulation.Plugins.Input.JohnnyInput.InputBindingDevice">
\t\t\t<BindingSets className="FourDO.Emulation.Plugins.Input.JohnnyInput.InputBindingSets">
\t\t\t\t<InputBindingSet className="FourDO.Emulation.Plugins.Input.JohnnyInput.InputBindingSet">
\t\t\t\t\t<Count>${config.size()}</Count>${bindings}
\t\t\t\t</InputBindingSet>
\t\t\t</BindingSets>
\t\t</InputBindingDevice>"""
    }

}
