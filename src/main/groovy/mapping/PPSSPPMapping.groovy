package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 11-Jul-17.
 */
class PPSSPPMapping {
    static List<File> setDefault360AndKeys(HyperSpin hs) {
        File iniFile = new File(hs.getPPSSPPFolder(), "memstick\\PSP\\SYSTEM\\controls.ini")

        println "- PPSSPP: Reseting 360 + keys"

        IniFile cfg = new IniFile(equals: " = ").parse(iniFile)
        // Cursores, DPAD y XBOX
        cfg.put("ControlMapping", "Up", "1-19,20-19,10-19")
        cfg.put("ControlMapping", "Down", "1-20,20-20,10-20")
        cfg.put("ControlMapping", "Left", "1-21,20-21,10-21")
        cfg.put("ControlMapping", "Right", "1-22,20-22,10-22")

        // XBOX 360
        // A -> CUADRADO, S -> TRIANGULO, Z -> CRUZ, X -> CIRCULO,
        // Q -> L1, W -> R1, ESPACIO -> SELECT, ENTER -> START
        cfg.put("ControlMapping", "Circle", "1-52,20-97")
        cfg.put("ControlMapping", "Cross", "1-54,20-96")
        cfg.put("ControlMapping", "Square", "1-29,20-99")
        cfg.put("ControlMapping", "Triangle", "1-47,20-100")
        cfg.put("ControlMapping", "Start", "1-66,20-108")
        cfg.put("ControlMapping", "Select", "1-62,20-109")
        cfg.put("ControlMapping", "L", "1-45,20-102")
        cfg.put("ControlMapping", "R", "1-51,20-103")

        // AnalogicPAD, 360 stick y IJKL teclas
        cfg.put("ControlMapping", "An.Up", "1-37,20-4002,10-4003")
        cfg.put("ControlMapping", "An.Down", "1-39,20-4003,10-4002")
        cfg.put("ControlMapping", "An.Left", "1-38,20-4001,10-4001")
        cfg.put("ControlMapping", "An.Right", "1-40,20-4000,10-4000")

        // LSHIFT
        cfg.put("ControlMapping", "RapidFire", "1-59")
        cfg.put("ControlMapping", "Unthrottle", "1-61,20-4036")
        cfg.put("ControlMapping", "SpeedToggle", "1-68,20-107")

        // P, home (360),
        cfg.put("ControlMapping", "Pause", "1-44,20-4034,20-3")

        // BACKSPACE
        cfg.put("ControlMapping", "Rewind", "1-67")

        // F2 SAVE, F4 LOAD
        cfg.put("ControlMapping", "Save State", "1-132")
        cfg.put("ControlMapping", "Load State", "1-134")

        // 360 solo
        cfg.put("ControlMapping", "RightAn.Up", "20-4028")
        cfg.put("ControlMapping", "RightAn.Down", "20-4029")
        cfg.put("ControlMapping", "RightAn.Left", "20-4023")
        cfg.put("ControlMapping", "RightAn.Right", "20-4022")

        // RSHIFT
        cfg.put("ControlMapping", "Analog limiter", "1-60")
        cfg.store()

        return [iniFile]
    }

}
