package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 11-Jul-17.
 */
class SuperModelMapping {
    static void setDefaultKeysAndJoy(HyperSpin hs) {
        File iniFile = new File(hs.superModelFolder, "Config\\Supermodel.ini")
        println "- Super Model 0.3a: joystick and keys"
        println iniFile.absolutePath
        IniFile cfg = new IniFile(equals: " = ").parse(iniFile)
        // common
        cfg.put("Global", "InputStart1", "\"KEY_1\"")
        cfg.put("Global", "InputStart2", "\"KEY_2\"")
        cfg.put("Global", "InputCoin1", "\"KEY_5\"")
        cfg.put("Global", "InputCoin2", "\"KEY_6\"")
        cfg.put("Global", "InputServiceA", "\"KEY_7\"")
        cfg.put("Global", "InputServiceB", "\"KEY_8\"")
        cfg.put("Global", "InputTestA", "\"KEY_9\"")
        cfg.put("Global", "InputTestB", "\"KEY_0\"")
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


}
