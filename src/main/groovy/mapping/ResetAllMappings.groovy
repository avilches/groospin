package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.J2K
import org.hs5tb.groospin.base.MameIni
import org.hs5tb.groospin.base.RetroArch
import org.hs5tb.groospin.common.IniFile

class ResetAllMappings {

    /*
    Vacia todos los mapeos de JoyToKey
     */

    static void emptyAllJoyToKeyProfiles(HyperSpin hs) {
        println "- JoyToKey: Empty all profiles:"
        println hs.newRocketLauncherFile("Profiles/JoyToKey").absolutePath + "\\**\\*"
        (hs.listAllJoyToKeyProfiles() + new J2K(hs, "HyperSpin")).each { it.empty() }
    }

    static void resetHyperSpinMainMenuControls(HyperSpin hs) {
        println "- HyperSpin: Setting default keys:"
        println hs.newHyperSpinFile("Settings/Settings.ini").absolutePath
        hs.withHyperSpinSettings("Settings") { String filename, IniFile ini ->
            ini.put("P1 Joystick", "Enabled", "false")
            ini.put("P2 Joystick", "Enabled", "false")
            ini.put("Keyboard", "Key_Delay", "true")

            ini.put("P1 Controls", "Start", "13")          // enter
            ini.put("P1 Controls", "Exit", "27")           // esc
            ini.put("P1 Controls", "Up", "38")             // cursor arriba
            ini.put("P1 Controls", "Down", "40")           // cursor abajo
            ini.put("P1 Controls", "SkipUp", "37")         // derecha
            ini.put("P1 Controls", "SkipDown", "39")       // izquierda
            ini.put("P1 Controls", "SkipUpNumber", "33")   // pg up
            ini.put("P1 Controls", "SkipDownNumber", "34") // pg down
            ini.put("P1 Controls", "HyperSpin", "72")      // H
            ini.put("P1 Controls", "Genre", "71")          // G
            ini.put("P1 Controls", "Favorites", "70")      // F

            ini.put("P2 Controls", "Start", "32")          // espacio
            ini.put("P2 Controls", "Exit", "8")            // backspace
            ini.put("P2 Controls", "Up", "87")             // W
            ini.put("P2 Controls", "Down", "83")           // S
            ini.put("P2 Controls", "SkipUp", "65")         // A
            ini.put("P2 Controls", "SkipDown", "68")       // D
            ini.put("P2 Controls", "SkipUpNumber", "74")   // J
            ini.put("P2 Controls", "SkipDownNumber", "77") // M
            ini.put("P2 Controls", "HyperSpin", "72")      // H
            ini.put("P2 Controls", "Genre", "71")          // G
            ini.put("P2 Controls", "Favorites", "70")      // F
            ini.store()
        }
    }

    /*
    Resetea los joystick 1 y 2 para que funcionen de fabrica u TODAS las teclas de sistema.
    */

    static void resetMameCtrl(HyperSpin hs) {
        MameIni mameIni = hs.getMameIni("ini/presets/mame.ini")
        println "- MAME: reset ctrlr to none:"
        println mameIni.file.absolutePath
        mameIni.set("keyboardprovider", "dinput")  // ensure MAME can read JoyToKey mappings
        mameIni.set("ctrlr", "")
        mameIni.save()

        hs.mameMapping.backupAndCleanDefaultCfg()
    }

    static void resetRetroArch(RetroArch retroArch) {
        println "- Retroarch reset: empty players button&joystick, force default keys for extra):"
        println retroArch.iniFile.file.absolutePath

        retroArch.iniFile.put("system_directory", ":\\system")
        retroArch.save()

        retroArch.with {
            resetKeys()
            resetPlayer(1, 1)
            resetPlayer(2, 2)
            save()
        }
    }

    static void resetPS2Keys(HyperSpin hs) {
        println "- PCSX2 keys: setting LilyPad and set some default keys for P1 & P2: (YOU HAVE TO CONFIGURE 360 or other pads YOURSELF!)"
        IniFile psx2 = new IniFile().parse(new File(hs.getPCSX2Folder(),"inis\\PCSX2_ui.ini"))
        IniFile lilyPad = new IniFile().parse(new File(hs.getPCSX2Folder(),"inis\\LilyPad.ini"))

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
    static void resetPPSSPP360AndKeys(HyperSpin hs) {
        File iniFile = new File(hs.getPPSSPPFolder(), "memstick\\PSP\\SYSTEM\\controls.ini")

        println "- PPSSPP: Reseting 360 + keys"
        println iniFile.absolutePath

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
    }

    private static String dolphin360(section, port) {
"""[${section}]
Device = XInput/${port}/Gamepad
Buttons/A = `Button A`
Buttons/B = `Button B`
Buttons/X = `Button X`
Buttons/Y = `Button Y`
Buttons/Z = Back
Buttons/Start = Start
Main Stick/Up = `Left Y+`
Main Stick/Down = `Left Y-`
Main Stick/Left = `Left X-`
Main Stick/Right = `Left X+`
C-Stick/Up = `Right Y+`
C-Stick/Down = `Right Y-`
C-Stick/Left = `Right X-`
C-Stick/Right = `Right X+`
Triggers/L = `Shoulder L`
Triggers/R = `Shoulder R`
Triggers/L-Analog = `Trigger L`
Triggers/R-Analog = `Trigger R`
Rumble/Motor = `Motor L` | `Motor R`
D-Pad/Up = `Pad N`
D-Pad/Down = `Pad S`
D-Pad/Left = `Pad W`
D-Pad/Right = `Pad E`
"""
    }
    static void resetDolphins360(HyperSpin hs) {
        [hs.getDolphinGameCubeFolder(),
         hs.getDolphinWiiFolder(),
         hs.getDolphinTriforceFolder()
        ].each { File dolphin ->
            new File(dolphin, "User\\Config\\Profiles\\GCPad\\Xbox 360 port 1.ini").text = dolphin360("Profile", "0")
            new File(dolphin, "User\\Config\\Profiles\\GCPad\\Xbox 360 port 2.ini").text = dolphin360("Profile", "1")
            new File(dolphin, "User\\Config\\Profiles\\GCPad\\Xbox 360 port 3.ini").text = dolphin360("Profile", "2")
            new File(dolphin, "User\\Config\\Profiles\\GCPad\\Xbox 360 port 4.ini").text = dolphin360("Profile", "3");


            File iniDolphinFile = new File(dolphin, "User\\Config\\Dolphin.ini")
            println " - Dolphin: set P1 + P2 + P3 + P4 to standard controller"
            println iniDolphinFile.absolutePath
            IniFile cfgDolphin = new IniFile(equals: " = ").parse(iniDolphinFile)
            cfgDolphin.put("Core", "SIDevice0", "6") // Standard controller
            cfgDolphin.put("Core", "SIDevice1", "6") // Standard controller
            cfgDolphin.put("Core", "SIDevice2", "6") // Standard controller
            cfgDolphin.put("Core", "SIDevice3", "6") // Standard controller
            cfgDolphin.store()

            File iniPadFile = new File(dolphin, "User\\Config\\GCPadNew.ini")
            println " - Dolphin: set P1 + P2 to standard controller"
            println iniPadFile.absolutePath
            iniPadFile.text =
"""
${dolphin360("GCPad1", "0")}
${dolphin360("GCPad2", "1")}
${dolphin360("GCPad3", "2")}
${dolphin360("GCPad4", "3")}
"""
        }

    }

    static void resetSuperModel3KeysAndJoy(HyperSpin hs) {
        File iniFile = new File(hs.superModelFolder, "Config\\Supermodel.ini")
        println "- Super Model 0.3a: joystick and keys"
        println iniFile.absolutePath
        IniFile cfg = new IniFile(equals: " = ").parse(iniFile)
        // common
        cfg.put("Global", "InputStart1", "\"KEY_1\"")
        cfg.put("Global", "InputStart2", "\"KEY_2\"")
        cfg.put("Global", "InputCoin1", "\"KEY_5\"")
        cfg.put("Global", "InputCoin2", "\"KEY_4\"")
        cfg.put("Global", "InputServiceA", "\"KEY_6\"")
        cfg.put("Global", "InputServiceB", "\"KEY_7\"")
        cfg.put("Global", "InputTestA", "\"KEY_8\"")
        cfg.put("Global", "InputTestB", "\"KEY_9\"")
        // joy
        cfg.put("Global", "InputJoyDown", "\"KEY_DOWN,JOY1_DOWN\"")
        cfg.put("Global", "InputJoyDown2", "\"KEY_KEYPAD2,JOY2_DOWN\"")
        cfg.put("Global", "InputJoyLeft", "\"KEY_LEFT,JOY1_LEFT\"")
        cfg.put("Global", "InputJoyLeft2", "\"KEY_KEYPAD4,JOY2_LEFT\"")
        cfg.put("Global", "InputJoyRight", "\"KEY_RIGHT,JOY1_RIGHT\"")
        cfg.put("Global", "InputJoyRight2", "\"KEY_KEYPAD6,JOY2_RIGHT\"")
        cfg.put("Global", "InputJoyUp", "\"KEY_UP,JOY1_UP\"")
        cfg.put("Global", "InputJoyUp2", "\"KEY_KEYPAD8,JOY2_UP\"")

        // Fighting game
        cfg.put("Global", "InputEscape", "\"KEY_F,JOY1_BUTTON4\"")
        cfg.put("Global", "InputEscape2", "\"KEY_R,JOY2_BUTTON4\"")
        cfg.put("Global", "InputGuard", "\"KEY_D,JOY1_BUTTON3\"")
        cfg.put("Global", "InputGuard2", "\"KEY_E,JOY2_BUTTON3\"")
        cfg.put("Global", "InputKick", "\"KEY_S,JOY1_BUTTON2\"")
        cfg.put("Global", "InputKick2", "\"KEY_W,JOY2_BUTTON2\"")
        cfg.put("Global", "InputPunch", "\"KEY_A,JOY1_BUTTON1\"")
        cfg.put("Global", "InputPunch2", "\"KEY_Q,JOY2_BUTTON1\"")

        // Spikeout
        cfg.put("Global", "InputShift", "\"KEY_A,JOY1_BUTTON1\"")
        cfg.put("Global", "InputBeat", "\"KEY_S,JOY1_BUTTON2\"")
        cfg.put("Global", "InputCharge", "\"KEY_D,JOY1_BUTTON3\"")
        cfg.put("Global", "InputJump", "\"KEY_F,JOY1_BUTTON4\"")

        // Virtua Striker
        cfg.put("Global", "InputLongPass", "\"KEY_S,JOY1_BUTTON2\"")
        cfg.put("Global", "InputLongPass2", "\"KEY_W,JOY2_BUTTON2\"")
        cfg.put("Global", "InputShortPass", "\"KEY_A,JOY1_BUTTON1\"")
        cfg.put("Global", "InputShortPass2", "\"KEY_Q,JOY2_BUTTON1\"")
        cfg.put("Global", "InputShoot", "\"KEY_D,JOY1_BUTTON3\"")
        cfg.put("Global", "InputShoot2", "\"KEY_E,JOY2_BUTTON3\"")

        // Volante
        cfg.put("Global", "InputSteering", "\"JOY1_XAXIS\"")
        cfg.put("Global", "InputSteeringLeft", "\"KEY_LEFT,JOY1_LEFT\"")
        cfg.put("Global", "InputSteeringRight", "\"KEY_RIGHT,JOY1_RIGHT\"")

        // pedal
        cfg.put("Global", "InputBrake", "\"KEY_DOWN,JOY1_BUTTON2\"")
        cfg.put("Global", "InputAccelerator", "\"KEY_UP,JOY1_BUTTON1\"")

        // ; Handbrake (Dirt Devils, Sega Rally 2)
        cfg.put("Global", "InputHandBrake", "\"KEY_S,JOY1_BUTTON3\"")

        // ; 4-Speed manual transmission (Daytona 2, Sega Rally 2, Scud Race)
        cfg.put("Global", "InputGearShift1", "\"KEY_1\"")
        cfg.put("Global", "InputGearShift2", "\"KEY_2\"")
        cfg.put("Global", "InputGearShift3", "\"KEY_3\"")
        cfg.put("Global", "InputGearShift4", "\"KEY_4\"")
        cfg.put("Global", "InputGearShiftN", "\"KEY_0\"")

        // Up/down shifter manual transmission (all racers)
        cfg.put("Global", "InputGearShiftDown", "\"KEY_Q\"")
        cfg.put("Global", "InputGearShiftUp", "\"KEY_W\"")

        // VR4 view change buttons (Daytona 2, Le Mans 24, Scud Race)
        cfg.put("Global", "InputVR1", "\"KEY_A\"")
        cfg.put("Global", "InputVR2", "\"KEY_S\"")
        cfg.put("Global", "InputVR3", "\"KEY_D\"")
        cfg.put("Global", "InputVR4", "\"KEY_F\"")

//        Single view change button (Dirt Devils, ECA, Harley-Davidson, Sega Rally 2)
        cfg.put("Global", "InputViewChange", "\"KEY_A,JOY1_BUTTON4\"")
        
        
        
        /////////////////// SIN DEFINIR!!!!!!!!!!! POR DEFECTO

        // Harley-Davidson controls
        cfg.put("Global", "InputRearBrake", "\"KEY_S,JOY1_BUTTON2\"")
        cfg.put("Global", "InputMusicSelect", "\"KEY_D,JOY1_BUTTON3\"")

        // Virtual On macros
        cfg.put("Global", "InputTwinJoyTurnLeft", "\"KEY_Q,JOY1_RXAXIS_NEG\"")
        cfg.put("Global", "InputTwinJoyTurnRight", "\"KEY_W,JOY1_RXAXIS_POS\"")
        cfg.put("Global", "InputTwinJoyForward", "\"KEY_UP,JOY1_YAXIS_NEG\"")
        cfg.put("Global", "InputTwinJoyReverse", "\"KEY_DOWN,JOY1_YAXIS_POS\"")
        cfg.put("Global", "InputTwinJoyStrafeLeft", "\"KEY_LEFT,JOY1_XAXIS_NEG\"")
        cfg.put("Global", "InputTwinJoyStrafeRight", "\"KEY_RIGHT,JOY1_XAXIS_POS\"")
        cfg.put("Global", "InputTwinJoyJump", "\"KEY_E,JOY1_BUTTON1\"")
        cfg.put("Global", "InputTwinJoyCrouch", "\"KEY_R,JOY1_BUTTON2\"")

        // Virtual On individual joystick mapping
        cfg.put("Global", "InputTwinJoyLeft1", "\"NONE\"")
        cfg.put("Global", "InputTwinJoyLeft2", "\"NONE\"")
        cfg.put("Global", "InputTwinJoyRight1", "\"NONE\"")
        cfg.put("Global", "InputTwinJoyRight2", "\"NONE\"")
        cfg.put("Global", "InputTwinJoyUp1", "\"NONE\"")
        cfg.put("Global", "InputTwinJoyUp2", "\"NONE\"")
        cfg.put("Global", "InputTwinJoyDown1", "\"NONE\"")
        cfg.put("Global", "InputTwinJoyDown2", "\"NONE\"")

        // Virtual On buttons
        cfg.put("Global", "InputTwinJoyShot1", "\"KEY_A,JOY1_BUTTON5\"")
        cfg.put("Global", "InputTwinJoyShot2", "\"KEY_S,JOY1_BUTTON6\"")
        cfg.put("Global", "InputTwinJoyTurbo1", "\"KEY_Z,JOY1_BUTTON7\"")
        cfg.put("Global", "InputTwinJoyTurbo2", "\"KEY_X,JOY1_BUTTON8\"")

        // Analog joystick (Star Wars Trilogy)
        cfg.put("Global", "InputAnalogJoyLeft", "\"KEY_LEFT\"")             // digital, move left
        cfg.put("Global", "InputAnalogJoyRight", "\"KEY_RIGHT\"")           // digital, move right
        cfg.put("Global", "InputAnalogJoyUp", "\"KEY_UP\"")                 // digital, move up
        cfg.put("Global", "InputAnalogJoyDown", "\"KEY_DOWN\"")             // digital, move down
        cfg.put("Global", "InputAnalogJoyX", "\"JOY_XAXIS,MOUSE_XAXIS\"")   // analog, full X axis
        cfg.put("Global", "InputAnalogJoyY", "\"JOY_YAXIS,MOUSE_YAXIS\"")   // analog, full Y axis
        cfg.put("Global", "InputAnalogJoyTrigger", "\"KEY_A,JOY_BUTTON1,MOUSE_LEFT_BUTTON\"")
        cfg.put("Global", "InputAnalogJoyEvent", "\"KEY_S,JOY_BUTTON2,MOUSE_RIGHT_BUTTON\"")
        cfg.put("Global", "InputAnalogJoyTrigger2", "\"KEY_D,JOY_BUTTON2\"")
        cfg.put("Global", "InputAnalogJoyEvent2", "\"NONE\"")

        // Light guns (Lost World)
        cfg.put("Global", "InputGunLeft", "\"KEY_LEFT\"")               // digital, move gun left
        cfg.put("Global", "InputGunRight", "\"KEY_RIGHT\"")             // digital, move gun right
        cfg.put("Global", "InputGunUp", "\"KEY_UP\"")                   // digital, move gun up
        cfg.put("Global", "InputGunDown", "\"KEY_DOWN\"")               // digital, move gun down
        cfg.put("Global", "InputGunX", "\"MOUSE_XAXIS,JOY1_XAXIS\"")    // analog, full X axis
        cfg.put("Global", "InputGunY", "\"MOUSE_YAXIS,JOY1_YAXIS\"")    // analog, full Y axis
        cfg.put("Global", "InputTrigger", "\"KEY_A,JOY1_BUTTON1,MOUSE_LEFT_BUTTON\"")
        cfg.put("Global", "InputOffscreen", "\"KEY_S,JOY1_BUTTON2,MOUSE_RIGHT_BUTTON\"")    // point off-scree\"n)
        cfg.put("Global", "InputAutoTrigger", "0")                    // automatic reload when off-scree\"n)
        cfg.put("Global", "InputGunLeft2", "\"NONE\"")
        cfg.put("Global", "InputGunRight2", "\"NONE\"")
        cfg.put("Global", "InputGunUp2", "\"NONE\"")
        cfg.put("Global", "InputGunDown2", "\"NONE\"")
        cfg.put("Global", "InputGunX2", "\"JOY2_XAXIS\"")
        cfg.put("Global", "InputGunY2", "\"JOY2_YAXIS\"")
        cfg.put("Global", "InputTrigger2", "\"JOY2_BUTTON1\"")
        cfg.put("Global", "InputOffscreen2", "\"JOY2_BUTTON2\"")
        cfg.put("Global", "InputAutoTrigger2", "0")

        // Analog guns (Ocean Hunter, LA Machineguns
        cfg.put("Global", "InputAnalogGunLeft", "\"KEY_LEFT\"")               // digital, move gun left
        cfg.put("Global", "InputAnalogGunRight", "\"KEY_RIGHT\"")             // digital, move gun right
        cfg.put("Global", "InputAnalogGunUp", "\"KEY_UP\"")                   // digital, move gun up
        cfg.put("Global", "InputAnalogGunDown", "\"KEY_DOWN\"")               // digital, move gun down
        cfg.put("Global", "InputAnalogGunX", "\"MOUSE_XAXIS\",JOY1_XAXIS")    // analog, full X axis
        cfg.put("Global", "InputAnalogGunY", "\"MOUSE_YAXIS\",JOY1_YAXIS")    // analog, full Y axis
        cfg.put("Global", "InputAnalogTriggerLeft", "\"KEY_A,JOY1_BUTTON1,MOUSE_LEFT_BUTTON\"")
        cfg.put("Global", "InputAnalogTriggerRight", "\"KEY_S,JOY1_BUTTON2,MOUSE_RIGHT_BUTTON\"")
        cfg.put("Global", "InputAnalogGunLeft2", "\"NONE\"")
        cfg.put("Global", "InputAnalogGunRight2", "\"NONE\"")
        cfg.put("Global", "InputAnalogGunUp2", "\"NONE\"")
        cfg.put("Global", "InputAnalogGunDown2", "\"NONE\"")
        cfg.put("Global", "InputAnalogGunX2", "\"NONE\"")
        cfg.put("Global", "InputAnalogGunY2", "\"NONE\"")
        cfg.put("Global", "InputAnalogTriggerLeft2", "\"NONE\"")
        cfg.put("Global", "InputAnalogTriggerRight2", "\"NONE\"")
        // Ski Champ controls
        cfg.put("Global", "InputSkiLeft", "\"KEY_LEFT\"")
        cfg.put("Global", "InputSkiRight", "\"KEY_RIGHT\"")
        cfg.put("Global", "InputSkiUp", "\"KEY_UP\"")
        cfg.put("Global", "InputSkiDown", "\"KEY_DOWN\"")
        cfg.put("Global", "InputSkiX", "\"JOY1_XAXIS\"")
        cfg.put("Global", "InputSkiY", "\"JOY1_YAXIS\"")
        cfg.put("Global", "InputSkiPollLeft", "\"KEY_A,JOY1_BUTTON1\"")
        cfg.put("Global", "InputSkiPollRight", "\"KEY_S,JOY1_BUTTON2\"")
        cfg.put("Global", "InputSkiSelect1", "\"KEY_Q,JOY1_BUTTON3\"")
        cfg.put("Global", "InputSkiSelect2", "\"KEY_W,JOY1_BUTTON4\"")
        cfg.put("Global", "InputSkiSelect3", "\"KEY_E,JOY1_BUTTON5\"")

        cfg.store()




    }

    static void resetWinViceKeys(HyperSpin hs) {
        File iniFile = new File(hs.winViceFolder, "vice.ini")
        println "- WinVICE reset: P1:AWSD + Q/ P2:IJKL + U):"
        println iniFile.absolutePath
        IniFile cfg = new IniFile().parse(iniFile)

// http://vice-emu.pokefinder.org/index.php/Hotkey_cleanup
// http://vice-emu.pokefinder.org/index.php/Keymaps
        ["C64", "C64DTV", "C128", "VIC20", "PET", "CBM-II-5x0", "CBM-II", "C64SC", "VSID"].each { String machine ->

            cfg.put(machine, "SaveResourcesOnExit", "1")
            cfg.put(machine, "ConfirmOnExit", "0")

            final String JOY_DEVICE_NONE = "0"
            final String JOY_DEVICE_NUMPAD_CTRL = "1"
            final String JOY_DEVICE_KEYSET_A = "2"
            final String JOY_DEVICE_KEYSET_B = "3"
            final def reset = { p ->
                cfg.put(machine, "KeySet${p}North", "0")
                cfg.put(machine, "KeySet${p}East", "0")
                cfg.put(machine, "KeySet${p}South", "0")
                cfg.put(machine, "KeySet${p}West", "0")
                cfg.put(machine, "KeySet${p}NorthWest", "0")
                cfg.put(machine, "KeySet${p}NorthEast", "0")
                cfg.put(machine, "KeySet${p}SouthWest", "0")
                cfg.put(machine, "KeySet${p}SouthEast", "0")
                cfg.put(machine, "KeySet${p}Fire", "0")
            }

            reset(1)
            reset(2)

            cfg.put(machine, "KeySetEnable", "1")
            cfg.put(machine, "JoyDevice1", JOY_DEVICE_KEYSET_A)
            cfg.put(machine, "JoyDevice2", JOY_DEVICE_KEYSET_B)

            cfg.put(machine, "KeySet1North", "17") // W
            cfg.put(machine, "KeySet1East", "32")  // A
            cfg.put(machine, "KeySet1South", "31") // S
            cfg.put(machine, "KeySet1West", "30")  // D
            cfg.put(machine, "KeySet1Fire", "16")  // Q

            cfg.put(machine, "KeySet2North", "23") // I
            cfg.put(machine, "KeySet2East", "38")  // L
            cfg.put(machine, "KeySet2South", "37") // K
            cfg.put(machine, "KeySet2West", "36")  // J
            cfg.put(machine, "KeySet2Fire", "22")  // U
            cfg.store()
        }
    }

}

