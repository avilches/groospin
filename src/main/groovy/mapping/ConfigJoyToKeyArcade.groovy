package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.J2K
import org.hs5tb.groospin.base.RetroArch
import org.hs5tb.groospin.common.IOTools
import org.hs5tb.groospin.common.IniFile

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

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
    buttonToKey(player1, 8, ESC)

    buttonToKey(player1, 1, RETURN)
    buttonToKey(player1, 2, PAGEDOWN)
    buttonToKey(player1, 3, PAGEUP)

    buttonToKey(player1, 4, KEY_H)
    buttonToKey(player1, 5, KEY_G)
    buttonToKey(player1, 6, KEY_F)

    buttonToKey(player1, 9, SHIFT)

    buttonToKey(player2, 1, ALT)
    buttonToKey(player2, 2, CTRL)
    buttonToKey(player2, 3, SPACE)
    buttonToKey(player2, 4, F3)
    buttonToKey(player2, 9, F4)
    buttonToKey(player2, 10, F5)
    save()
}



// Mapear en JoyToKey la tecla ESCAPE con boton SALIR (player 1, accion 8) en TODOS los sistemas
hs.listAllJoyToKeyProfiles().each { J2K j2k ->
    println "All: Configuring arcade ESC for ${j2k.systemName}"
    j2k.presets.with {
        buttonToKey(player1, 8, ESC)
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

        buttonToKey(player1, 1,  KEY_Z)
        buttonToKey(player1, 2,  KEY_X)
        buttonToKey(player1, 3,  KEY_A)
        buttonToKey(player1, 4,  KEY_S)
        buttonToKey(player1, 5,  KEY_Q)
        buttonToKey(player1, 6,  KEY_W)

        buttonToKey(player1, 7,  KEY_C) // START P1, PLAYER 1
        buttonToKey(player2, 11, KEY_D) // SELECT P1, COIN

        dPadTo(player2, KEY_7, KEY_8, KEY_9, KEY_0)
        analogLeftTo(player2, KEY_7, KEY_8, KEY_9, KEY_0)

        buttonToKey(player2, 1,  KEY_1)
        buttonToKey(player2, 2,  KEY_2)
        buttonToKey(player2, 3,  KEY_3)
        buttonToKey(player2, 4,  KEY_4)
        buttonToKey(player2, 9,  KEY_5)
        buttonToKey(player2, 10, KEY_6)

        buttonToKey(player1, 9,  F2)    // SAVE, PINBALL IZQUIERDO
        buttonToKey(player1, 10, F4)    // LOAD, PINBALL DERECHO

        save()
    }
}

/* Los sistemas MAME ya funcionan con los mandos de 360 si estan conectados como JOYSTICKS 1 Y 2

¿Porque se usa entonces JoyToKey?
- El dpad digital del mando de 360 no funciona, asi que se mapea a los cursores (solo funciona el analogico de la izquierda)
- Se mapean BACK y START para echar moneda y start
TODAS ESTAS CONFIGURACIONES SE PODRIAN HACER MODIFICANDO EL default.cfg pero hay un problema:

SI SE DESENCHUFA EL MANDO (O SI ES INALAMBRICO Y SE APAGA) CUALQUIER CONFIGURACIÓN QUE SE TENGA ECHA EN EL default.cfg
SE BORRA. Por lo tanto, cuando se usan mandos que se pueden enchufar y desenchufar, lo mejor es no editar el default.cfg
y hacer el mapeo en el JoyToKey.
 */

// Se elimina el default.cfg para que se vuelva a generar vacio, haciendo antes una copia de seguridad
hs.mame.backupAndCleanDefaultCfg()

// Mapeos en JoyToKey
(hs.listSystemsMAME()+hs.getSystem("HBMAME"))*.loadJ2KConfig().each { J2K j2k ->
    println "MAME: Configuring 360 for ${j2k.systemName}"

    j2k.presets.with {
        xbox360MameTab(player1)
        xbox360MameTab(player2)
        dPadToCursor(player1)
        dPadTo(player2, KEY_D, KEY_F, KEY_R, KEY_G)
        buttonsTo(player1, [
                (XBOX360_BACK): KEY_5,
                (XBOX360_START): KEY_1
        ])
        buttonsTo(player2, [
                (XBOX360_BACK): KEY_6,
                (XBOX360_START): KEY_2
        ])
        save()
    }
}
println "1 Configuring Future Pinball. RUN THE REG FILE!!!!!!!!!!!"
println "2 Run at least one time Future Pinball as Administrator"
// Future Pinball. Funciona mejor con teclado. Cargar el registro de Windows para que se carguen estas teclas
hs.getSystem("Future Pinball").loadJ2KConfig().presets.with {
    analogLeftTo(player1, KEY_F, RETURN, SPACE, KEY_A)  // abajo sacar, resto golpear
    analogLeftTo(player2, KEY_F, RETURN, SPACE, KEY_A)  // abajo sacar, resto golpear

    buttonToKeys(player1, 9,  [KEY_Z, KEY_X])    // PINBALL IZQUIERDO
    buttonToKeys(player1, 10, [KEY_N, KEY_M])    // PINBALL DERECHO

    buttonToKey(player1, 1,  TAB)
    buttonToKey(player1, 2,  F7)
    buttonToKey(player1, 3,  F8)
    buttonToKey(player1, 4,  KEY_S)
    buttonToKey(player1, 5,  KEY_D)   // SPECIAL

    buttonToKey(player1, 6,  KEY_9)   // SERVICE

    buttonToKey(player1, 7,  KEY_1) // START P1, PLAYER 1
    buttonToKey(player2, 11, KEY_5) // SELECT P1, COIN

    buttonToKey(player2, 1,  F1)
    buttonToKey(player2, 2,  F2)
    buttonToKey(player2, 3,  F3)
    buttonToKey(player2, 4,  F4)
    buttonToKey(player2, 9,  F5)
    buttonToKey(player2, 10, F6)

    save()
}

/*
Pinball FX2. Ya funciona con Xbox 360 directamente
Reset to defaults
pinballs: lshift / rshift
sacar: enter
vista: c
nudge: lctrl, space, rctrl
*/

/*
Pinball Arcade. Ya funciona con Xbox directamente
HKEY_CURRENT_USER\Software\PinballArcade\PinballArcade
Ruta de teclas en: c:\Users\xxxx\My Games\Pinball Arcade\settings.dat
Resetear controles:
pinballs: lshift, lctrl / rshift, rctrl
sacar: space
nudge: AWSD
camara: C, lock: V
hud: H
 */

/*
Sony PlayStation 2
Configuación en:
d:\Games\Emulators\PCSX2\PCXS2.gigapig\inis\LilyPad.ini
Se supone ya está configurado para 360
 */

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

println "Configuring AAE"
// AAE funciona mejor con teclado
hs.getSystem("AAE").loadJ2KConfig().presets.with {
    dPadToCursor(player1)
    dPadToCursor(player2)
    analogToCursor(player1)
    analogToCursor(player2)
    buttonsTo(player1, [
            (XBOX360_A): ALT,
            (XBOX360_B): CTRL,
            (XBOX360_X): SHIFT,
            (XBOX360_Y): SPACE,
            (XBOX360_BACK): KEY_5,
            (XBOX360_START): KEY_1
    ])
    buttonsTo(player2, [
            (XBOX360_A): ALT,
            (XBOX360_B): CTRL,
            (XBOX360_X): SHIFT,
            (XBOX360_Y): SPACE,
            (XBOX360_BACK): KEY_5,
            (XBOX360_START): KEY_2
    ])
    save()
}
