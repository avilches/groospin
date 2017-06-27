package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.J2K

import static org.hs5tb.groospin.base.MameMapping.Action.*

HyperSpin hs = new HyperSpin("A:/RocketLauncher")

int joystickStartPosition = 1
int player1 = joystickStartPosition
int player2 = joystickStartPosition + 1

/*
J1
ACCION 1 2 3 4 5 6
P1: 7
SALIR: 8
PINBALL 9 10
------------
J2
ACCION 1 2 3 4 9 10
P2: 11
MONEDA: 12
 */

class ArcadeSet {
    J2K.Preset preset
    int player1 = 1

    void p1Action1(String key) { preset.buttonToKey(player1 + 0, 1, key) }
    void p1Action2(String key) { preset.buttonToKey(player1 + 0, 2, key) }
    void p1Action3(String key) { preset.buttonToKey(player1 + 0, 3, key) }
    void p1Action4(String key) { preset.buttonToKey(player1 + 0, 4, key) }
    void p1Action5(String key) { preset.buttonToKey(player1 + 0, 5, key) }
    void p1Action6(String key) { preset.buttonToKey(player1 + 0, 6, key) }
    void p1Start(String key)   { preset.buttonToKey(player1 + 0, 7, key) }
    void exit(String key)      { preset.buttonToKey(player1 + 0, 8, key) }
    void pinballLeft(String key) { preset.buttonToKey(player1 + 0, 9, key) }
    void pinballRight(String key) { preset.buttonToKey(player1 + 0, 10, key) }
    void p2Action1(String key) { preset.buttonToKey(player1 + 1, 1, key) }
    void p2Action2(String key) { preset.buttonToKey(player1 + 1, 2, key) }
    void p2Action3(String key) { preset.buttonToKey(player1 + 1, 3, key) }
    void p2Action4(String key) { preset.buttonToKey(player1 + 1, 4, key) }
    void p2Action5(String key) { preset.buttonToKey(player1 + 1, 9, key) }
    void p2Action6(String key) { preset.buttonToKey(player1 + 1, 10, key) }
    void p2Start(String key)   { preset.buttonToKey(player1 + 1, 11, key) }
    void coin(String key)      { preset.buttonToKey(player1 + 1, 12, key) }
}


println "Resetting all JoyToKey profiles..."
/*
Vaciamos todos los mapeos de JoyToKey
 */
ResetAllMappings.emptyAllJoyToKeyProfiles(hs)

// Configuramos el menu de HyperSpin con teclas
ResetAllMappings.resetHyperSpinMainMenuControls(hs)
/*
d:\Games\HyperSpin-fe\Scripts\HScript\HScript.ahk
CONTROL ALT MAYUSCULAS + F = TEAM VIEWER

!^+f::
	Run, "C:\Program Files (x86)\TeamViewer\TeamViewer.exe"
	Return

 */
new J2K(hs, "HyperSpin").presets.with {
    dPadToCursor(player1)
    dPadToCursor(player2)
    analogToCursor(player1)
    analogToCursor(player2)
    new ArcadeSet(preset: delegate, player1: player1).with {
        exit(ESC)
        p1Action1(RETURN)
        p1Action2(PAGEDOWN)
        p1Action3(PAGEUP)
        p1Action4(KEY_H)
        p1Action5(KEY_G)
        p1Action6(KEY_F)
//        p1Start()
//        coin()

        pinballLeft(LSHIFT)
        pinballRight(RSHIFT)

        p2Action1(ALT)
        p2Action2(CTRL)
        p2Action3(SPACE)
        p2Action4(F3)
        p2Action5(F4)
        p2Action6(F5)
//        p2Start()
    }
    save()
}

// Mapear en JoyToKey la tecla ESCAPE con boton SALIR (player 1, accion 8) en TODOS los sistemas
hs.listAllJoyToKeyProfiles().each { J2K j2k ->
    println "All: Configuring arcade ESC for ${j2k.systemName}"
    j2k.presets.with {
        new ArcadeSet(preset: delegate, player1: player1).with {
            exit(ESC)
        }
        save()
    }
}

// Los sistemas RetroArch ya funcionan con los mandos de 360 con la configuración por defecto.
// Para que funcione los mandos arcade, usamos las teclas y mapeamos con JoyToKey ya que RetroArch no te
// permite usar botones de otros player para un player. Insertar moneda pertenece al player 2 por lo que
// no podriamos usarla para SELECT del player 1
ResetAllMappings.resetRetroArch(hs.retroArch)
hs.retroArch.with {
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


hs.listSystemsRetroArch()*.loadJ2KConfig().each { J2K j2k ->
    println "RetroArch: Configuring arcade ${j2k.systemName}"
    j2k.presets.with {
        dPadToCursor(player1)
        analogToCursor(player1)

        dPadTo(player2, KEY_7, KEY_8, KEY_9, KEY_0)
        analogTo(player2, KEY_7, KEY_8, KEY_9, KEY_0)

        new ArcadeSet(preset: delegate, player1: player1).with {
            p1Action1(KEY_Z)
            p1Action2(KEY_X)
            p1Action3(KEY_A)
            p1Action4(KEY_S)
            p1Action5(KEY_Q)
            p1Action6(KEY_W)

            p1Start(KEY_C)
            coin(KEY_D)

            p2Action1(KEY_1)
            p2Action2(KEY_2)
            p2Action3(KEY_3)
            p2Action4(KEY_4)
            p2Action5(KEY_5)
            p2Action6(KEY_6)

            p2Start(KEY_B)

//            pinballLeft(F2)     // SAVE, PINBALL IZQUIERDO
//            pinballRight(F4)    // LOAD, PINBALL DERECHO
        }

        // TODO:
//        buttonToOtherJoy(player1, 9, player2+1) // PINBALL IZQUIERDO

        save()
    }
}

/* Se mapea MAME con teclas y luego se usa JoyToKey

Se usa JoyToKey porque SI SE DESENCHUFA EL JOYSTICK CUALQUIER CONFIGURACIÓN QUE SE TENGA ECHA EN EL default.cfg
SE BORRA. Por lo tanto, cuando se usan mandos que se pueden enchufar y desenchufar, lo mejor es no editar el default.cfg
y hacer el mapeo en el JoyToKey.
*/

hs.getMame().with {
    add(UI_CANCEL, ESC)
    add(COIN1, KEY_5)
    add(COIN2, KEY_6)
    add(COIN2, KEY_5)  // MONEDA P2 TAMBIEN EN LA MISMA TECLA QUE P1
    add(START1, KEY_1)
    add(START2, KEY_2)

    add(J1_BUTTON1, LCONTROL)
    add(J1_BUTTON2, LALT)
    add(J1_BUTTON3, SPACE)
    add(J1_BUTTON4, LSHIFT)
    add(J1_BUTTON5, KEY_Z)
    add(J1_BUTTON6, KEY_X)
    add(J1_UP, CURSOR_UP)
    add(J1_DOWN, CURSOR_DOWN)
    add(J1_LEFT, CURSOR_LEFT)
    add(J1_RIGHT, CURSOR_RIGHT)

    add(J2_BUTTON1, KEY_A)
    add(J2_BUTTON2, KEY_S)
    add(J2_BUTTON3, KEY_Q)
    add(J2_BUTTON4, KEY_W)
    add(J2_BUTTON5, CAPSLOCK)
    add(J2_BUTTON6, KEY_E)
    add(J2_UP, KEY_R)
    add(J2_DOWN, KEY_F)
    add(J2_LEFT, KEY_D)
    add(J2_RIGHT, KEY_G)

    saveCfg("default")
}

// Mapeos en JoyToKey
(hs.listSystemsMAME() + hs.listSystemsMESS() + hs.getSystem("HBMAME"))*.loadJ2KConfig().each { J2K j2k ->
    println "MAME: Configuring 360 for ${j2k.systemName}"

    j2k.presets.with {
        analogTo(player1, CURSOR_LEFT, CURSOR_DOWN, CURSOR_UP, CURSOR_RIGHT)
        analogTo(player2, KEY_D, KEY_F, KEY_R, KEY_G)  // abajo sacar, resto golpear

        new ArcadeSet(preset: delegate, player1: player1).with {
            p1Action1(LCTRL)
            p1Action2(LALT)
            p1Action3(SPACE)
            p1Action4(LSHIFT)
            p1Action5(KEY_Z)
            p1Action6(KEY_X)
            p1Start(KEY_1)
            coin(KEY_5) // MONEDA P1 Y P2

            p2Action1(KEY_A)
            p2Action2(KEY_S)
            p2Action3(KEY_Q)
            p2Action4(KEY_W)
            p2Action5(CAPSLOCK)
            p2Action6(KEY_E)
            p2Start(KEY_2)
        }
        save()

        // TODO:
//        buttonToOtherJoy(player1, 9, player2+1) // PINBALL IZQUIERDO

    }
}
println "1 Configuring Future Pinball. RUN THE REG FILE!!!!!!!!!!!"
println "2 Run at least one time Future Pinball as Administrator"
// Future Pinball. Funciona mejor con teclado. Cargar el registro de Windows para que se carguen estas teclas
hs.getSystem("Future Pinball").loadJ2KConfig().presets.with {
    analogTo(player1, KEY_F, RETURN, SPACE, KEY_A)  // abajo sacar, resto golpear
    analogTo(player2, KEY_F, RETURN, SPACE, KEY_A)  // abajo sacar, resto golpear

    buttonToKeys(player1, 9, [KEY_Z, KEY_X])    // PINBALL IZQUIERDO
    buttonToKeys(player1, 10, [KEY_N, KEY_M])    // PINBALL DERECHO

    new ArcadeSet(preset: delegate, player1: player1).with {
        p1Action1(TAB)
        p1Action2(F7)
        p1Action3(F8)
        p1Action4(KEY_S)
        p1Action5(KEY_D)   // SPECIAL
        p1Action6(KEY_9)   // SERVICE
        p1Start(KEY_1)   // START P1, PLAYER 1
        coin(KEY_5)

        p2Action1(F1)
        p2Action2(F2)
        p2Action3(F3)
        p2Action4(F4)
        p2Action5(F5)
        p2Action6(F6)
    }

    save()
}

/*
Pinball FX2.
Reset to defaults
pinballs: lshift / rshift
sacar: enter
vista: c
nudge: lctrl, space, rctrl
*/

hs.getSystem("Pinball FX2").loadJ2KConfig().presets.with {
    analogTo(player1, LCTRL, RETURN, SPACE, RCTRL)  // abajo sacar, resto golpear
    analogTo(player2, LCTRL, RETURN, SPACE, RCTRL)  // abajo sacar, resto golpear

    new ArcadeSet(preset: delegate, player1: player1).with {
        p1Action1(RETURN)
        p1Action2(RETURN)
        p1Action3(RETURN)
        p1Action4(KEY_C)
        p1Action5(KEY_C)
        p1Action6(KEY_C)
        
        p1Start(RETURN)
        coin(RETURN)

        pinballLeft(LSHIFT)
        pinballRight(RSHIFT)

        p2Action1(RETURN)
        p2Action2(RETURN)
        p2Action3(RETURN)
        p2Action4(KEY_C)
        p2Action5(KEY_C)
        p2Action6(KEY_C)
    }

    save()
}

/*
Pinball Arcade.
HKEY_CURRENT_USER\Software\PinballArcade\PinballArcade
Ruta de teclas en: c:\Users\xxxx\My Games\Pinball Arcade\settings.dat
Resetear controles:
pinballs: lshift, lctrl / rshift, rctrl
sacar: space
nudge: AWSD
camara: C, lock: V
hud: H
 */

hs.getSystem("Pinball FX2").loadJ2KConfig().presets.with {
    analogTo(player1, KEY_A, KEY_S, KEY_W, KEY_D)  // GOLPEAR
    analogTo(player2, KEY_A, KEY_S, KEY_W, KEY_D)  // GOLPEAR

    buttonToKeys(player1, 9, [LCTRL, LSHIFT])    // PINBALL IZQUIERDO
    buttonToKeys(player1, 10, [RCTRL, LCTRL])    // PINBALL DERECHO

    new ArcadeSet(preset: delegate, player1: player1).with {
        p1Action1(SPACE)
        p1Action2(SPACE)
        p1Action3(SPACE)
        p1Action4(KEY_C)
        p1Action5(KEY_V)
        p1Action6(KEY_H)

        p1Start(SPACE)
        coin(SPACE)

        p2Action1(SPACE)
        p2Action2(SPACE)
        p2Action3(SPACE)
        p2Action4(KEY_C)
        p2Action5(KEY_V)
        p2Action6(KEY_H)

    }

    save()
}



/*
Sony PlayStation 2
Configuación en:
d:\Games\Emulators\PCSX2\PCXS2.gigapig\inis\LilyPad.ini
Se supone ya está configurado para 360
 */
/*
IniFile psx2 = new IniFile().parse(new File("d:\\Games\\Emulators\\PCSX2\\PCXS2.gigapig\\inis\\LilyPad.ini"))
if (psx2.get("Device 1", "Display Name") != "WM Keyboard") {
    throw new Exception("ERROR CONFIGURING PSX2")
} else {
    psx2.put("Device 1", "Binding 0", "0x0004000D, 0, 19, 65536, 0, 0, 0")
    psx2.put("Device 1", "Binding 1", "0x00040020, 0, 16, 65536, 0, 0, 0")
    psx2.put("Device 1", "Binding 2", "0x00040025, 0, 23, 65536, 0, 0, 0")
    psx2.put("Device 1", "Binding 3", "0x00040026, 0, 20, 65536, 0, 0, 0")
    psx2.put("Device 1", "Binding 4", "0x00040027, 0, 21, 65536, 0, 0, 0")
    psx2.put("Device 1", "Binding 5", "0x00040028, 0, 22, 65536, 0, 0, 0")
    psx2.put("Device 1", "Binding 6", "0x00040041, 0, 31, 65536, 0, 0, 0")
    psx2.put("Device 1", "Binding 7", "0x00040051, 0, 26, 65536, 0, 0, 0")
    psx2.put("Device 1", "Binding 8", "0x00040053, 0, 28, 65536, 0, 0, 0")
    psx2.put("Device 1", "Binding 9", "0x00040057, 0, 27, 65536, 0, 0, 0")
    psx2.put("Device 1", "Binding 10", "0x00040058, 0, 29, 65536, 0, 0, 0")
    psx2.put("Device 1", "Binding 11", "0x0004005A, 0, 30, 65536, 0, 0, 0")
    psx2.store()
}
*/
println "Configuring AAE"
// AAE funciona mejor con teclado
hs.getSystem("AAE").loadJ2KConfig().presets.with {
    analogToCursor(player1)
    analogToCursor(player2)

    new ArcadeSet(preset: delegate, player1: player1).with {
        p1Action1(ALT)
        p1Action2(CTRL)
        p1Action3(SHIFT)
        p1Action4(SPACE)
        p1Start(KEY_1)   // START P1, PLAYER 1
        coin(KEY_5)

        p2Action1(ALT)
        p2Action2(CTRL)
        p2Action3(SHIFT)
        p2Action4(SPACE)
        p2Start(KEY_2)   // START P1, PLAYER 1
    }
    save()
}
