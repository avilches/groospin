package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.J2K
import org.hs5tb.groospin.base.MameIni

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")
int joystickStartPosition = 1
int player1 = joystickStartPosition
int player2 = joystickStartPosition + 1

/*
Vaciamos todos los mapeos de JoyToKey
 */
ResetAllMappings.emptyAllJoyToKeyProfiles(hs)

// Configuramos el menu de HyperSpin con teclas
ResetAllMappings.resetHyperSpinMainMenuControls(hs)

println "JoyToKey HyperSpin: Configuring profile for 360"
new J2K(hs, "HyperSpin").presets.with {
    dPadToCursor(player1)
    dPadToCursor(player2)
    analogToCursor(player1)
    analogToCursor(player2)
    xbox360Esc(player1)
    xbox360Esc(player2)
    def mapping = [
            (XBOX360_A): RETURN,
            (XBOX360_B): ESC,
            (XBOX360_X): KEY_F,
            (XBOX360_Y): KEY_G,
            (XBOX360_BACK): F5,  // GENERO
            (XBOX360_START): KEY_H,
            (XBOX360_LB): PAGEDOWN,
            (XBOX360_LT_ANALOG): F3,   // SEARCH
            (XBOX360_RB): PAGEUP,
            (XBOX360_RT_ANALOG): F4 // FAVORITES
    ]
    buttonsTo(player1, mapping)
    buttonsTo(player2, mapping)
    save()
}



// Mapear en JoyToKey la tecla ESCAPE con BACK+START (Xbox 360) en TODOS los sistemas
println "JoyToKey all: BACK+START -> ESC....."
hs.listAllJoyToKeyProfiles().each { J2K j2k ->
    j2k.presets.with {
        xbox360Esc(player1)
        xbox360Esc(player2)
        save()
    }
}

// Los sistemas RetroArch ya funcionan con los mandos de 360 con la configuración por defecto.
// Para dejar la configuración por defecto: podemos borrar el retroarch.cfg o
// ejecutar el script ConfigRetroarch que se encarga de borrar los comandos de JoyStick de los dos players y, además,
// de configurar teclas (también borra todas las acciones de sistema que haya configurados para que funcione todo
// por defecto)
ResetAllMappings.resetRetroArch(hs.retroArch)

println "JoyToKey RetroArch: Configuring 360 BACK+RB = F1"
// Después, se mapea en JoyToKey la tecla F1 con BACK+RB
hs.listSystemsRetroArch()*.loadJ2KConfig().each { J2K j2k ->
    j2k.presets.with {
        xbox360RetroArchF1(player1)
        xbox360RetroArchF1(player2)
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
hs.mameMapping.backupAndCleanDefaultCfg()

println "JoyToKey MAME: Configuring 360 additional buttons (coin, start, dpad)"
// Mapeos en JoyToKey
(hs.listSystemsMAME()+hs.getSystem("HBMAME"))*.loadJ2KConfig().each { J2K j2k ->
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

MameIni mameIni = hs.getMameIni("ini/presets/mame.ini")
println "MAME: Set dinput keyboard and no ctrlr: ${mameIni.file.absolutePath}"
mameIni.set("keyboardprovider", "dinput")  // ensure MAME can read JoyToKey mappings
mameIni.set("ctrlr", "")
mameIni.save()

println "JoyToKey Future Pinball. (RUN THE REG FILE!!!!!!!!!!!)"
// Future Pinball. Funciona mejor con teclado. Cargar el registro de Windows para que se carguen estas teclas
hs.getSystem("Future Pinball").loadJ2KConfig().presets.with {
    analogLeftTo(player1, F1, F4, F3, F2)  // vistas
    analogRightTo(player1, F5, F8, F7, F6)  // vistas
    dPadTo(player1, KEY_F, RETURN, SPACE, KEY_A)  // abajo sacar, resto golpear
    buttonsTo(player1, [
            (XBOX360_A): RETURN, // sacar
            (XBOX360_Y): TAB, // mirar arriba
            (XBOX360_BACK): KEY_5, // start
            (XBOX360_START): KEY_1, // moneda
            (XBOX360_LB): [KEY_Z, KEY_X],  // pinballs izquierdo
            (XBOX360_RB): [KEY_N, KEY_M],  // pinballs derecho
    ])
    save()
}

/*
Pinball FX2. Ya funciona con Xbox 360 directamente
*/

/*
Pinball Arcade. Ya funciona con Xbox directamente
HKEY_CURRENT_USER\Software\PinballArcade\PinballArcade
 */

/*
Sony PlayStation 2
Configuación en:
d:\Games\Emulators\PCSX2\PCXS2.gigapig\inis\LilyPad.ini
Se supone ya está configurado para 360
 */

println "JoyTokey AAE special keys"
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