package org.hs5tb.groospin.base

import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 17-Jun-17.
 */
class J2K {
    HyperSpin hs
    String system
    String emulator
    IniFile cfg

    J2K(HyperSpin hs, String system, String emulator = null) {
        this.hs = hs
        this.system = system
        this.emulator = emulator
        File cfgFile
        if (emulator) {
            cfgFile = hs.newRocketLauncherFile("Profiles/JoyToKey/${system}/${emulator}/${emulator}.cfg")
        } else {
            cfgFile = hs.newRocketLauncherFile("Profiles/JoyToKey/${system}/${system}.cfg")
        }
        if (!cfgFile.exists()) {
            cfg = new IniFile(file: cfgFile)
            empty()
        } else {
            cfg = new IniFile().parse(cfgFile)
        }
    }

    Preset presets = new Preset()

    class Preset {
        Preset xbox360Esc() {
            // envia ESC al pulsar SELECT(back) + START (usando mapping boton 32)
            cfg.put("Joystick 1", "Button32", "1, 1B:00:00:00, 0.000, 0, 0")
            cfg.put("ButtonAlias", "Button32", "62, 63")
            return this
        }
        Preset save() {
            cfg.store()
        }
    }

    J2K empty() {
        cfg = new IniFile(file: cfg.file)
        cfg.put("General","FileVersion","57")
        cfg.put("General","NumberOfJoysticks","3")
        cfg.put("General","DisplayMode","2")
        cfg.put("General","UseDiagonalInput","0")
        cfg.put("General","UsePOV8Way","0")
        cfg.put("General","Threshold","20")
        cfg.put("General","Threshold2","20")
        cfg.put("General","KeySendMode","0")
        cfg.put("General","NumberOfButtons","32")
        cfg.store()
        return this
    }


}
