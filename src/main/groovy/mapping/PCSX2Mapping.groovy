package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 11-Jul-17.
 */
class PCSX2Mapping {
    static void setPPSSPP360AndKeys(HyperSpin hs) {
        println "- PCSX2 keys: setting LilyPad and set some default keys for P1 & P2: (YOU HAVE TO CONFIGURE 360 or other pads YOURSELF!)"
        IniFile psx2 = new IniFile().parse(new File(hs.getPCSX2Folder(), "inis\\PCSX2_ui.ini"))
        IniFile lilyPad = new IniFile().parse(new File(hs.getPCSX2Folder(), "inis\\LilyPad.ini"))

        println psx2.file.absolutePath
        println lilyPad.file.absolutePath

        psx2.put("Filenames", "PAD", "LilyPad.dll")
        psx2.store()
        if (lilyPad.get("Device 1", "Display Name") != "WM Keyboard") {
            throw new Exception("ERROR CONFIGURING PSX2")
        } else {

            // PLAYER 1
            // CURSORES
            // A -> CUADRADO, S -> TRIANGULO, Z -> CRUZ, X -> CIRCULO,
            // Q -> L1, W -> R1, ESPACIO -> SELECT, ENTER -> START
            lilyPad.put("Device 1", "Binding 0", "0x0004000D, 0, 19, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 1", "0x00040020, 0, 16, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 2", "0x00040025, 0, 23, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 3", "0x00040026, 0, 20, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 4", "0x00040027, 0, 21, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 5", "0x00040028, 0, 22, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 6", "0x00040041, 0, 31, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 7", "0x00040051, 0, 26, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 8", "0x00040053, 0, 28, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 9", "0x00040057, 0, 27, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 10", "0x00040058, 0, 29, 65536, 0, 0, 0")
            lilyPad.put("Device 1", "Binding 11", "0x0004005A, 0, 30, 65536, 0, 0, 0")
            // PLAYER 2
            // IJKL
            // CVDFER
            // SELECT N, START M
            lilyPad.put("Device 1", "Binding 12", "0x00040043, 1, 30, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 13", "0x00040044, 1, 31, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 14", "0x00040045, 1, 26, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 15", "0x00040046, 1, 28, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 16", "0x00040049, 1, 20, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 17", "0x0004004A, 1, 23, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 18", "0x0004004B, 1, 22, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 19", "0x0004004C, 1, 21, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 20", "0x0004004D, 1, 19, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 21", "0x0004004E, 1, 16, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 22", "0x00040052, 1, 27, 65536, 0, 0, 0");
            lilyPad.put("Device 1", "Binding 23", "0x00040056, 1, 29, 65536, 0, 0, 0");

            lilyPad.store()
        }

    }

}
