package mapping.configs

import mapping.MappingManager
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.J2K

class ConfigJoyToKeyArcade {
    MappingManager mappingManager

    int joystickStartPosition = 1
    int player1 = joystickStartPosition
    int player2 = joystickStartPosition + 1
    int player3 = joystickStartPosition + 2
    int player4 = joystickStartPosition + 3
    HyperSpin hs
    ArcadeSet arcadeSet

    ConfigJoyToKeyArcade(HyperSpin hs) {
        this.hs = hs
    }

    void execute(ArcadeSet arcadeSet, File mirrorPath = null) {
        this.arcadeSet = arcadeSet
        mappingManager = new MappingManager(hs)
        mappingManager.setupAllJoyToKeyProfiles(true)
        hyperSpin()
        mapEscapeExit()
        retroArch()
        mame()
        pinballs()
        aae()
        winvice()
        ps2()
        ppsspp()
        dolphins()
        superModel3()
        daphne()
        fourDO()
        zinc()
        dice()
        neoRaine()
        pokeMini()
        demul()
        nullDC()

        mappingManager.mirrorUpdatedFiles(mirrorPath)
    }

    void withArcadeSet(J2K.Preset preset, @DelegatesTo(ArcadeSet) Closure closure) {
        arcadeSet.preset = preset
        arcadeSet.with(closure)
    }

    void hyperSpin() {
// Configuramos el menu de HyperSpin con teclas
        mappingManager.configureHyperSpinKeys()
/*
d:\Games\HyperSpin-fe\Scripts\HScript\HScript.ahk
CONTROL ALT MAYUSCULAS + F = TEAM VIEWER

!^+f::
	Run, "C:\Program Files (x86)\TeamViewer\TeamViewer.exe"
	Return

 */

        println "JoyToKey HyperSpin: Configuring profile for Arcade"
        new J2K(hs, "HyperSpin").presets.with {
            analogToCursor(player1)
            analogToCursor(player2)
            withArcadeSet(delegate) {
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

            // player 3 y 4 con mando de xbox 360
            xbox360Esc(player3)
            xbox360Esc(player4)
            dPadToCursor(player3)
            dPadToCursor(player4)
            Map mapping = [
                    (XBOX360_A)        : RETURN,
                    (XBOX360_B)        : ESC,
                    (XBOX360_X)        : KEY_F,
                    (XBOX360_Y)        : KEY_G,
                    (XBOX360_BACK)     : F5,  // GENERO
                    (XBOX360_START)    : KEY_H,
                    (XBOX360_LB)       : PAGEDOWN,
                    (XBOX360_LT_ANALOG): F3,   // SEARCH
                    (XBOX360_RB)       : PAGEUP,
                    (XBOX360_RT_ANALOG): F4 // FAVORITES
            ]
            buttonsTo(player3, mapping)
            buttonsTo(player4, mapping)
            save()
        }
    }

    void mapEscapeExit() {

// Mapear en JoyToKey la tecla ESCAPE con boton SALIR (player 1, accion 8) en TODOS los sistemas
        println "JoyToKey all: [exit button] -> ESC....."
        hs.listAllJoyToKeyProfiles().each { J2K j2k ->
            j2k.presets.with {
                withArcadeSet(delegate) {
                    exit(ESC)
                }
                xbox360Esc(player1)
                xbox360Esc(player2)
                xbox360Esc(player3)
                xbox360Esc(player4)
                save()
            }
        }
    }

    void retroArch() {

// Los sistemas RetroArch ya funcionan con los mandos de 360 con la configuración por defecto.
// Para que funcione los mandos arcade, usamos las teclas y mapeamos con JoyToKey ya que RetroArch no te
// permite usar botones de otros player para un player. Insertar moneda pertenece al player 2 por lo que
// no podriamos usarla para SELECT del player 1
        mappingManager.resetRetroArch()

        println "- RetroArch: configure keys "
        hs.retroArch.with {
            setDevicePadForPlayer(1)
            // no meter analogico, a retroarch resetea las teclas, borrando las del player2 y dejando en P1 select rshift y start return
            setDevicePadForPlayer(2)

            setDefaultKeysFor2Players()
            save()
        }

        println "JoyToKey RetroArch: joysticks and button -> RetroArch keys"
        hs.listSystemsRetroArch()*.loadJ2KConfig().each { J2K j2k ->
            j2k.presets.with {
                analogToCursor(player1)
                analogToNumpad(player2)

                withArcadeSet(delegate) {
                    p1Action1(KEY_Z)
                    p1Action2(KEY_X)
                    p1Action3(KEY_W)
                    p1Action4(KEY_A)
                    p1Action5(KEY_S)
                    p1Action6(KEY_Q)

                    p1Start(KEY_C)
                    coin(KEY_D)

                    p2Action1(KEY_1)
                    p2Action2(KEY_2)
                    p2Action3(KEY_6)
                    p2Action4(KEY_3)
                    p2Action5(KEY_4)
                    p2Action6(KEY_5)

                    p2Start(KEY_B)

//            pinballLeft(F2)     // SAVE, PINBALL IZQUIERDO
//            pinballRight(F4)    // LOAD, PINBALL DERECHO
                }

                // TODO:
//        buttonToOtherJoy(player1, 9, player2+1) // PINBALL IZQUIERDO

                save()
            }
        }

    }

    void mame() {

// Reseteamos MAME y apuntamos a controller "arcadeAT"
        mappingManager.resetMameConfig()

/* Se mapea MAME con teclas y luego se usa JoyToKey*/
        mappingManager.configMameCtrlrKeys()

// Mapeos en JoyToKey
        println "JoyToKey MAME: joysticks and button -> keys"
        (hs.listSystemsMAME() + hs.listSystemsMESS() + hs.getSystem("HBMAME"))*.loadJ2KConfig().each { J2K j2k ->

            j2k.presets.with {
                analogTo(player1, CURSOR_LEFT, CURSOR_DOWN, CURSOR_UP, CURSOR_RIGHT)
                analogTo(player2, KEY_D, KEY_F, KEY_R, KEY_G)

                withArcadeSet(delegate) {
                    p1Action1(LCTRL)
                    p1Action2(LALT)
                    p1Action3(SPACE)
                    p1Action4(LSHIFT)
                    p1Action5(KEY_Z)
                    p1Action6(KEY_X)
                    p1Start(KEY_1)
                    coin(KEY_5) // MONEDA P1, P2, P3 Y P4

                    p2Action1(KEY_A)
                    p2Action2(KEY_S)
                    p2Action3(KEY_Q)
                    p2Action4(KEY_W)
                    p2Action5(CAPSLOCK)
                    p2Action6(KEY_E)
                    p2Start(KEY_2)
                }

                analogTo(player3, KEY_J, KEY_K, KEY_I, KEY_L)
                dPadTo(player3, KEY_J, KEY_K, KEY_I, KEY_L)

                buttonsTo(player3, [
                        (XBOX360_A)    : KEY_C,
                        (XBOX360_B)    : KEY_V,
                        (XBOX360_X)    : KEY_B,
                        (XBOX360_Y)    : KEY_N,
                        (XBOX360_LB)   : KEY_M,
                        (XBOX360_RB)   : KEY_T,
                        (XBOX360_BACK) : KEY_5,
                        (XBOX360_START): KEY_3
                ])

                analogToNumpad(player4)
                dPadToNumpad(player4)
                buttonsTo(player4, [
                        (XBOX360_A)    : KEY_1_PAD,
                        (XBOX360_B)    : KEY_3_PAD,
                        (XBOX360_X)    : KEY_5_PAD,
                        (XBOX360_Y)    : KEY_7_PAD,
                        (XBOX360_LB)   : KEY_9_PAD,
                        (XBOX360_RB)   : KEY_0_PAD,
                        (XBOX360_BACK) : KEY_5,
                        (XBOX360_START): KEY_4
                ])

                // TODO:
//        buttonToOtherJoy(player1, 9, player2+1) // PINBALL IZQUIERDO
                save()

            }
        }

    }

    void pinballs() {
        mappingManager.configPinballs()
        println "JoyToKey Future Pinball"
// Future Pinball. Funciona mejor con teclado. Cargar el registro de Windows para que se carguen estas teclas
        hs.getSystem("Future Pinball").loadJ2KConfig().presets.with {
            analogTo(player1, KEY_F, RETURN, SPACE, KEY_A)  // abajo sacar, resto golpear
            analogTo(player2, KEY_F, RETURN, SPACE, KEY_A)  // abajo sacar, resto golpear


            withArcadeSet(delegate) {
                pinballLeft([KEY_Z, KEY_X])    // PINBALL IZQUIERDO
                pinballRight([KEY_N, KEY_M])    // PINBALL DERECHO

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

        println "JoyToKey Pinball FX2"
        hs.getSystem("Pinball FX2").loadJ2KConfig().presets.with {
            analogTo(player1, LCTRL, RETURN, SPACE, RCTRL)  // abajo sacar, resto golpear
            analogTo(player2, LCTRL, RETURN, SPACE, RCTRL)  // abajo sacar, resto golpear

            withArcadeSet(delegate) {
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

        println "JoyToKey Pinball Arcade"
        hs.getSystem("Pinball Arcade").loadJ2KConfig().presets.with {
            analogTo(player1, [KEY_A, CURSOR_LEFT], [KEY_S, CURSOR_DOWN], [KEY_W, CURSOR_UP], [KEY_D, CURSOR_UP])
            // GOLPEAR Y CURSORES DEL MENU
            analogTo(player2, [KEY_A, CURSOR_LEFT], [KEY_S, CURSOR_DOWN], [KEY_W, CURSOR_UP], [KEY_D, CURSOR_UP])
            // GOLPEAR Y CURSORES DEL MENU

            withArcadeSet(delegate) {
                pinballLeft([LCTRL, LSHIFT])    // PINBALL IZQUIERDO
                pinballRight([RCTRL, RSHIFT])    // PINBALL DERECHO

                p1Action1(RETURN) // ENTER PARA SELECCIONAR LAS OPCIONES
                p1Action2(SPACE)
                p1Action3(SPACE)
                p1Action4(KEY_C)
                p1Action5(KEY_V)
                p1Action6(KEY_H)

                p1Start(SPACE)
                coin(SPACE)

                p2Action1(RETURN)
                p2Action2(SPACE)
                p2Action3(SPACE)
                p2Action4(KEY_C)
                p2Action5(KEY_V)
                p2Action6(KEY_H)

            }

            save()
        }
    }

    void aae() {

        println "JoyToKey AAE"
// AAE funciona mejor con teclado
        hs.getSystem("AAE").loadJ2KConfig().presets.with {
            analogToCursor(player1)
            analogToCursor(player2)

            withArcadeSet(delegate) {
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

    }

    void winvice() {

        mappingManager.configCommodoreWinViceKeys()
        println "JoyToKey WinVICE: configuring ${hs.listSystemsWinVICE()*.name}"
        hs.listSystemsWinVICE()*.loadJ2KConfig().with { J2K j2k ->
            j2k.presets.with {

                analogTo(player1, KEY_A, KEY_S, KEY_W, KEY_D)
                analogTo(player2, KEY_J, KEY_K, KEY_I, KEY_L)

                withArcadeSet(delegate) {
                    p1Action1(KEY_Q) // DISPARO P1

                    p1Action2(ENTER)
                    p1Action3(TAB)
                    p1Action4(SPACE)
                    p1Action5(CURSOR_UP)
                    p1Action6(CURSOR_DOWN)
                    coin(CAPSLOCK)
                    p1Start(KEY_1)

                    pinballLeft(KEY_N)
                    pinballRight(KEY_Y)

                    p2Action1(KEY_U) // DISPARO P2

                    p2Action2(ENTER)
                    p2Action3(TAB)
                    p2Action4(SPACE)
                    p2Action5(CURSOR_LEFT)
                    p2Action6(CURSOR_RIGHT)
                    p2Start(KEY_2)

                }
                save()
            }
        }

    }

    void ps2() {
        /*
        Sony PlayStation 2
        Configuación en:
        d:\Games\Emulators\PCSX2\PCXS2.gigapig\inis\LilyPad.ini
         */
        mappingManager.configPS2KeysAnd360()

        println "JoyToKey Sony PlayStation 2 + Sega Ages"
// AAE funciona mejor con teclado
        [hs.getSystem("Sony PlayStation 2"), hs.getSystem("Sega Ages")]*.loadJ2KConfig().each { J2K j2k ->
            j2k.presets.with {
                analogToCursor(player1)
                analogTo(player2, KEY_J, KEY_K, KEY_I, KEY_L)

                withArcadeSet(delegate) {
                    p1Action1(KEY_Z)
                    p1Action2(KEY_X)
                    p1Action3(KEY_W)
                    p1Action4(KEY_A)
                    p1Action5(KEY_S)
                    p1Action6(KEY_Q)
                    coin(SPACE)
                    p1Start(RETURN)   // START P1, PLAYER 1
                    p2Action1(KEY_C)
                    p2Action2(KEY_V)
                    p2Action3(KEY_E)
                    p2Action4(KEY_D)
                    p2Action5(KEY_F)
                    p2Action6(KEY_R)
                    p2Start(KEY_M)   // START P2, PLAYER 2
                }
                save()
            }
        }
    }

    void ppsspp() {
        mappingManager.configPSPKeysAnd360()

        println "JoyToKey Sony PSP + Sony PSP Minis"
// AAE funciona mejor con teclado
        [hs.getSystem("Sony PSP"), hs.getSystem("Sony PSP Minis")]*.loadJ2KConfig().each { J2K j2k ->
            j2k.presets.with {
                analogToCursor(player1)
                withArcadeSet(delegate) {
                    p1Action1(KEY_Z)
                    p1Action2(KEY_X)
                    p1Action3(KEY_W)
                    p1Action4(KEY_A)
                    p1Action5(KEY_S)
                    p1Action6(KEY_Q)
                    coin(SPACE)
                    p1Start(RETURN)   // START P1, PLAYER 1
                }
                save()
            }
        }
    }

    void dolphins() {
        mappingManager.configWii360()
        mappingManager.configGamecubeKeyboard()
        println "JoyToKey Dolphin keys"
// AAE funciona mejor con teclado
        [hs.getSystem("Sega Triforce"), hs.getSystem("Nintendo WiiWare"), hs.getSystem("Nintendo Wii"), hs.getSystem("Nintendo GameCube")]*.loadJ2KConfig().each { J2K j2k ->
            j2k.presets.with {
                analogToCursor(player1)
                analogTo(player2, KEY_J, KEY_K, KEY_I, KEY_L)
                withArcadeSet(delegate) {
                    p1Action1(KEY_Z)
                    p1Action2(KEY_X)
                    p1Action3(KEY_W)
                    p1Action4(KEY_A)
                    p1Action5(KEY_S)
                    p1Action6(KEY_Q)
                    coin(KEY_D)
                    p1Start(KEY_C)   // START P1, PLAYER 1

                    p2Action1(KEY_F)
                    p2Action2(KEY_G)
                    p2Action3(KEY_H)
//            p2Action4(KEY_S)
                    p2Action5(KEY_T)
//            p2Action6(KEY_W)

                }
                save()
            }
        }
    }

    void superModel3() {
        mappingManager.configSuperModel3KeysAndJoy()

        println "JoyToKey Super Model 3"
        hs.getSystem("Sega Model 3").loadJ2KConfig().presets.with {

            withArcadeSet(delegate) {
                coin(KEY_5)
                p1Start(KEY_1)   // START P1
                p2Start(KEY_2)   // START P2

                p1Action5(KEY_Q) // baja marcha
                p1Action6(KEY_W)  // sube marcha

                p2Action1(KEY_Q) // baja marcha
                p2Action2(KEY_W)  // sube marcha

                pinballLeft(KEY_6) // service
                pinballRight(KEY_8) // test
            }

            analogToCursor(player3)
            dPadToCursor(player3)
            buttonsTo(player3, [(XBOX360_BACK)     : KEY_5,  // coin
                                (XBOX360_START)    : KEY_1, // start

                                (XBOX360_A)        : KEY_A,
                                (XBOX360_B)        : KEY_S,
                                (XBOX360_X)        : KEY_D,
                                (XBOX360_Y)        : KEY_F,

                                (XBOX360_L3)       : KEY_7,  // service
                                (XBOX360_R3)       : KEY_9,  // test
                                (XBOX360_RB)       : KEY_W, // sube marcha
                                (XBOX360_LB)       : KEY_Q, // baja marcha
                                (XBOX360_RT_ANALOG): CURSOR_UP, // frena
                                (XBOX360_LT_ANALOG): CURSOR_DOWN, // acelera
            ])

            analogToCursor(player4)
            dPadToNumpad(player4)
            buttonsTo(player4, [(XBOX360_BACK) : KEY_6,  // coin
                                (XBOX360_START): KEY_2,  // start

                                (XBOX360_A)    : KEY_Q,
                                (XBOX360_B)    : KEY_W,
                                (XBOX360_X)    : KEY_E,
                                (XBOX360_Y)    : KEY_R,

                                (XBOX360_L3)   : KEY_8,  // service
                                (XBOX360_R3)   : KEY_0,  // test
            ])

            save()
        }

    }

    void daphne() {
        mappingManager.configDaphneKeys()
        println "JoyToKey Daphne"
        hs.getSystem("Daphne").loadJ2KConfig().presets.with {

            analogToCursor(player1)
            analogToCursor(player2)

            withArcadeSet(delegate) {
                pinballLeft(KEY_S) // service
                pinballRight(KEY_T) // TILT

                coin(KEY_5) // coin

                p1Start(KEY_1)  // start
                p1Action1(LCTRL)
                p1Action2(LALT)
                p1Action3(LSHIFT)
                p1Action4(KEY_Z) // skill 1
                p1Action5(KEY_X) // skill 2
                p1Action6(KEY_C) // skill 3

                p2Start(KEY_2)  // start
                p2Action1(LCTRL)
                p2Action2(LALT)
                p2Action3(LSHIFT)
                p2Action4(KEY_Z) // skill 1
                p2Action5(KEY_X) // skill 2
                p2Action6(KEY_C) // skill 3

                save()
            }
        }
    }

    void fourDO() {

        mappingManager.configFourDOKeys()
        println "JoyToKey Panasonic 3DO"
        hs.getSystem("Panasonic 3DO").loadJ2KConfig().presets.with {

            analogToCursor(player1)
            analogToNumpad(player2)

            withArcadeSet(delegate) {
                coin(KEY_5) // STOP

                p1Start(KEY_1)  // play/pause
                p1Action1(KEY_Z)
                p1Action2(KEY_X)
                p1Action3(KEY_A)
                p1Action4(KEY_A)
                p1Action5(KEY_Q)
                p1Action6(KEY_W)

                p2Start(KEY_2)  // play/pause
                p2Action1(KEY_C)
                p2Action2(KEY_V)
                p2Action3(KEY_D)
                p2Action4(KEY_C)
                p2Action5(KEY_E)
                p2Action6(KEY_R)
            }

            analogToCursor(player3)
            dPadToCursor(player3)
            buttonsTo(player3, [
                    (XBOX360_A)        : KEY_Z,
                    (XBOX360_B)        : KEY_X,
                    (XBOX360_X)        : KEY_A,
//            (XBOX360_Y)        : KEY_Z, // skill 1
                    (XBOX360_BACK)     : KEY_5, // stop
                    (XBOX360_START)    : KEY_1,  // play/pause
                    (XBOX360_LB)       : KEY_Q, //
                    (XBOX360_LT_ANALOG): KEY_Q, //
                    (XBOX360_RB)       : KEY_W, //
                    (XBOX360_RT_ANALOG): KEY_W, //
            ])

            analogToNumpad(player4)
            dPadToNumpad(player4)
            buttonsTo(player4, [
                    (XBOX360_A)        : KEY_C,
                    (XBOX360_B)        : KEY_V,
                    (XBOX360_X)        : KEY_D,
//            (XBOX360_Y)        : KEY_Z, // skill 1
                    (XBOX360_BACK)     : KEY_6, // stop
                    (XBOX360_START)    : KEY_2,  // play/pause
                    (XBOX360_LB)       : KEY_E, //
                    (XBOX360_LT_ANALOG): KEY_E, //
                    (XBOX360_RB)       : KEY_R, //
                    (XBOX360_RT_ANALOG): KEY_R, //
            ])

            save()
        }
    }

    void zinc() {
        mappingManager.configZincKeys()
        println "JoyToKey Zinc"
        hs.getSystem("Zinc").loadJ2KConfig().presets.with {

            analogToCursor(player1)
            dPadToCursor(player1)

            analogToNumpad(player2)
            dPadToNumpad(player2)

            withArcadeSet(delegate) {
                coin(KEY_5)

                p1Start(KEY_1)
                p1Action1(KEY_Z)
                p1Action2(KEY_X)
                p1Action3(KEY_C)
                p1Action4(KEY_V)
                p1Action5(KEY_B)
                p1Action6(KEY_N)

                p2Start(KEY_2)
                p2Action1(KEY_A)
                p2Action2(KEY_S)
                p2Action3(KEY_D)
                p2Action4(KEY_F)
                p2Action5(KEY_G)
                p2Action6(KEY_H)
            }

            analogToCursor(player3)
            dPadToCursor(player3)
            buttonsTo(player3, [
                    (XBOX360_A)        : KEY_Z,
                    (XBOX360_B)        : KEY_X,
                    (XBOX360_X)        : KEY_C,
                    (XBOX360_Y)        : KEY_V,
                    (XBOX360_BACK)     : KEY_5,
                    (XBOX360_START)    : KEY_1,
                    (XBOX360_LB)       : KEY_B,
                    (XBOX360_LT_ANALOG): KEY_B,
                    (XBOX360_RB)       : KEY_N,
                    (XBOX360_RT_ANALOG): KEY_N,
            ])

            analogToNumpad(player4)
            dPadToNumpad(player4)
            buttonsTo(player4, [
                    (XBOX360_A)        : KEY_A,
                    (XBOX360_B)        : KEY_S,
                    (XBOX360_X)        : KEY_D,
                    (XBOX360_Y)        : KEY_F,
                    (XBOX360_BACK)     : KEY_6,
                    (XBOX360_START)    : KEY_2,
                    (XBOX360_LB)       : KEY_G,
                    (XBOX360_LT_ANALOG): KEY_G,
                    (XBOX360_RB)       : KEY_H,
                    (XBOX360_RT_ANALOG): KEY_H,
            ])
            save()
        }
    }

    void dice() {

        mappingManager.configDICE()
        println "JoyToKey DICE"
        hs.getSystem("DICE").loadJ2KConfig().presets.with {

            analogToCursor(player1)
            dPadToCursor(player1)

            analogTo(player2, KEY_A, KEY_S, KEY_W, KEY_D)
            dPadTo(player2, KEY_A, KEY_S, KEY_W, KEY_D)

            withArcadeSet(delegate) {
                coin(KEY_5)

                p1Start(KEY_1)
                p1Action1(LCONTROL)
                p1Action2(LALT)
                p1Action3(SPACE)
                p1Action4(LCONTROL)
                p1Action5(LALT)
                p1Action6(SPACE)

                p2Start(KEY_2)
                p2Action1(KEY_G)
                p2Action2(KEY_H)
                p2Action3(KEY_J)
                p2Action4(KEY_G)
                p2Action5(KEY_H)
                p2Action6(KEY_J)

            }
        }

    }

    void neoRaine() {

        mappingManager.configNeoRaineKeys()
        println "JoyToKey SNK Neo Geo CD"
        hs.getSystem("SNK Neo Geo CD").loadJ2KConfig().presets.with {

            analogToCursor(player1)
            dPadToCursor(player1)

            analogToNumpad(player2)
            dPadToNumpad(player2)
            withArcadeSet(delegate) {
                coin(KEY_5)

                p1Start(KEY_1)
                p1Action1(KEY_Z)
                p1Action2(KEY_X)
                p1Action3(KEY_C)
                p1Action4(KEY_V)
                p1Action5(KEY_B)
                p1Action6(KEY_N)

                p2Start(KEY_2)
                p2Action1(KEY_A)
                p2Action2(KEY_S)
                p2Action3(KEY_D)
                p2Action4(KEY_F)
                p2Action5(KEY_G)
                p2Action6(KEY_H)
            }

            analogToCursor(player3)
            dPadToCursor(player3)
            buttonsTo(player3, [
                    (XBOX360_A)        : KEY_Z,
                    (XBOX360_B)        : KEY_X,
                    (XBOX360_X)        : KEY_C,
                    (XBOX360_Y)        : KEY_V,
                    (XBOX360_BACK)     : KEY_5,
                    (XBOX360_START)    : KEY_1,
                    (XBOX360_LB)       : KEY_B,
                    (XBOX360_LT_ANALOG): KEY_M,
                    (XBOX360_RB)       : KEY_N,
                    (XBOX360_RT_ANALOG): KEY_L,
            ])

            analogToNumpad(player4)
            dPadToNumpad(player4)
            buttonsTo(player4, [
                    (XBOX360_A)        : KEY_A,
                    (XBOX360_B)        : KEY_S,
                    (XBOX360_X)        : KEY_D,
                    (XBOX360_Y)        : KEY_F,
                    (XBOX360_BACK)     : KEY_6,
                    (XBOX360_START)    : KEY_2,
                    (XBOX360_LB)       : KEY_G,
                    (XBOX360_LT_ANALOG): KEY_J,
                    (XBOX360_RB)       : KEY_H,
                    (XBOX360_RT_ANALOG): KEY_K,
            ])


            save()
        }

    }

    void pokeMini() {

        mappingManager.configPokeMiniKeys()
        println "JoyToKey Nintendo Pokemon Mini"
        hs.getSystem("Nintendo Pokemon Mini").loadJ2KConfig().presets.with {

            analogToCursor(player1)
            dPadToCursor(player1)

            withArcadeSet(delegate) {
                coin(TAB)

                p1Start(KEY_E)
                p1Action1(KEY_X)
                p1Action2(KEY_Z)
                p1Action3(KEY_S)
                p1Action4(KEY_C)
                p1Action6(KEY_A)
            }

            buttonsTo(player3, [
                    (XBOX360_A)        : KEY_X,
                    (XBOX360_B)        : KEY_Z,
                    (XBOX360_X)        : KEY_S,
                    (XBOX360_Y)        : KEY_C,
                    (XBOX360_BACK)     : KEY_A,
                    (XBOX360_START)    : KEY_E,
                    (XBOX360_LT_ANALOG): TAB,
                    (XBOX360_RT_ANALOG): TAB,
            ])

            save()
        }
    }

    void demul() {
        mappingManager.configDemulKeysAndOnlyAnalogTo360()
        println "JoyToKey Demul"
        hs.listSystemsDemul()*.loadJ2KConfig().each { J2K j2k ->
            j2k.presets.with {
                analogToCursor(player1)
                dPadToCursor(player1)

                analogToNumpad(player2)
                dPadToNumpad(player2)

                withArcadeSet(delegate) {
                    coin(KEY_5)

                    p1Start(KEY_1)
                    p1Action1(KEY_Z)
                    p1Action2(KEY_X)
                    p1Action3(KEY_C)
                    p1Action4(KEY_V)
                    p1Action5(KEY_A)
                    p1Action6(KEY_S)

                    p2Start(KEY_2)
                    p2Action1(KEY_Q)
                    p2Action2(KEY_W)
                    p2Action3(KEY_E)
                    p2Action4(KEY_R)
                    p2Action5(KEY_3)
                    p2Action6(KEY_4)
                }

                save()
            }
        }
    }

    void nullDC() {
        mappingManager.configNullDcKeys()
        println "JoyToKey NullDC"
        hs.getSystem("Sega Dreamcast").loadJ2KConfig().presets.with {
            analogToCursor(player1)
            dPadToCursor(player1)

            analogToNumpad(player2)
            dPadToNumpad(player2)

            withArcadeSet(delegate) {
                coin(TAB)
                p1Start(KEY_1)
                p1Action1(KEY_X)
                p1Action2(KEY_Z)
                p1Action3(KEY_C)
                p1Action4(KEY_V)
                p1Action5(KEY_B)
                p1Action6(KEY_N)

                p2Start(KEY_2)
                p2Action1(KEY_A)
                p2Action2(KEY_S)
                p2Action3(KEY_U)
                p2Action4(KEY_Y)
                p2Action5(KEY_Q)
                p2Action6(KEY_W)
            }
            save()
        }

    }
}