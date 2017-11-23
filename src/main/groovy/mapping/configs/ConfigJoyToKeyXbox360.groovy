package mapping.configs

import mapping.MappingManager
import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.J2K

class ConfigJoyToKeyXbox360 {
    MappingManager mappingManager

    int joystickStartPosition = 1
    int player1 = joystickStartPosition
    int player2 = joystickStartPosition + 1
    int player3 = joystickStartPosition + 2
    int player4 = joystickStartPosition + 3
    HyperSpin hs

    ConfigJoyToKeyXbox360(HyperSpin hs) {
        this.hs = hs
    }

    void execute(File mirrorPath = null) {
        this.hs = hs
        mappingManager = new MappingManager(hs)
        mappingManager.setupAllJoyToKeyProfiles(true)

        hyperSpin()
        mapEscapeExit()
        retroArch()
        mame()
        pinballs()
        aae()
        winvice()
        superModel3()
        daphne()
        fourDO()
        zinc()
        dice()
        neoRaine()
        pokeMini()

        mappingManager.configPS2KeysAnd360()
        mappingManager.configPSPKeysAnd360()
        mappingManager.configGamecube360()
        mappingManager.configWii360()

        mappingManager.configNullDc360()
        mappingManager.configDemul360()

        mappingManager.mirrorUpdatedFiles(mirrorPath)

/*
DONE:
AAE: [AAE]

RetroArch: [Atari 2600, Atari 7800, Atari 2600 - Arcadia Supercharger, Atari Jaguar, Atari Lynx,
Bandai WonderSwan, Bandai WonderSwan Color,
NEC PC Engine, NEC PC Engine-CD, NEC PC-FX, NEC SuperGrafx, NEC TurboGrafx-16, NEC TurboGrafx-CD,
Nintendo 64, Nintendo DS, Nintendo Entertainment System, Super Nintendo Entertainment System, Nintendo Famicom, Nintendo Famicom Disk System, Nintendo Game & Watch, Nintendo Game Boy, Nintendo Game Boy Advance, Nintendo Game Boy Color, Nintendo Satellaview, Nintendo Sufami Turbo, Nintendo Super Famicom, Nintendo Virtual Boy,
Sega 32X, Sega Genesis, Sega Game Gear, Sega Mark III, Sega Master System, Sega Mega Drive, Sega Nomad, Sega Pico, Sega Saturn, Sega Saturn Japan, Sega SG-1000,
SNK Neo Geo Pocket, SNK Neo Geo Pocket Color,
Sony PlayStation]
RetroArch Extended: [Nintendo 64 Japan, Nintendo 64 Europe, Nintendo Entertainment System Europe, Nintendo Entertainment System Asia, Super Nintendo Entertainment System Europe, Super Nintendo Entertainment System Japan, Sega Master System Japan, Sega Mega Drive Europe]

MAME: [Atari Classics, Capcom Classics, Capcom Play System, Capcom Play System II, Capcom Play System III, Data East Classics, HyperNeoGeo64, Irem Classics, Kaneko, Konami Classics, Atlus, Banpresto, Cave, Gaelco MAME, Bally, Sammy, Nichibutsu, Seibu Kaihatsu, Jaleco, Mature MAME, Best of MAME, MAME, Tecmo, Toaplan, Mitchell Corporation, Visco, SNK Classics, MAME 4 Players, Midway Classics, Namco Classics, Namco System 22, Nintendo Classics, Psikyo, Sega Classics, Sega ST-V, Shotgun Games, SNK Neo Geo AES, Technos Classics, Taito Classics, Trackball Games, Williams Classics]

WinVICE: [Commodore 64]

Future Pinball: [Future Pinball]
Pinball FX2: [Pinball FX2]
Pinball Arcade DX11: [Pinball Arcade]
ZiNc: [Zinc]

PPSSPP: [Sony PSP, Sony PSP Minis]
PCSX2: [Sega Ages, Sony PlayStation 2]
Dolphin5: [Nintendo Wii, Nintendo WiiWare]
Dolphin GC: [Nintendo GameCube]
Dolphin Triforce: [Sega Triforce]
SuperModel: [Sega Model 3]
Daphne: [Daphne]
FourDO: [Panasonic 3DO]
DICE: [DICE]
NeoRaine: [SNK Neo Geo CD]
PokeMini: [Nintendo Pokemon Mini]
HBMAME: [HBMAME]
NullDC: [Sega Dreamcast]
Demul70: [Cave 3rd, Gaelco, Sammy Atomiswave, Sega Hikaru, Sega Naomi]

PENDING:

Project64 DD: [Nintendo 64DD]
Fusion: [Sega CD, Sega Mega-CD, Sega SC-3000]
Nestopia: [Technos]

Sega Model 2 Emulator: [Sega Model 2]
CPCE: [Amstrad CPC]
Sinclair ZX Spectrum
Project Tempest: [Atari Jaguar CD]
PCLauncher: [American Laser Games, Big Fish Games, Doujin Soft, Flash Games, Lucasarts Adventure Games, Locomalito Games, Nintendo Game and Watch, Party Games, PC Games, Taito Type X, TouchGames, Touhou Project, Pack Remasterizados]


Spectaculator: [Sinclair ZX Spectrum]
MESS: [Amstrad GX4000, Atari 5200, Bally Astrocade, Casio PV-1000, Casio PV-2000, ColecoVision, Creatronic Mega Duck, Emerson Arcadia 2001, Entex Adventure Vision, Epoch Game Pocket Computer, Epoch Super Cassette Vision, Exidy Sorcerer, Fairchild Channel F, Funtech Super Acan, GCE Vectrex, Interton VC 4000, Magnavox Odyssey 2, Mattel Intellivision, Nintendo Super Game Boy, Philips CD-i, RCA Studio II, Sony Pocketstation, Texas Instruments TI 99-4A, Tiger Game.com, VTech CreatiVision, Watara Supervision, Aamber Pegasus, Hartung Game Master]
MESScart1: [Sord M5]
MESSApogee: [Apogee BK-01, Sega VMU, Vector-06C, VTech Socrates, Casio Loopy]
BeebEm: [Acorn BBC Micro]
Universal Emualator Alf: [ALF TV Game]
AppleWin: [Apple II]
Atari800: [Atari 8-bit, Atari XEGS]
Hatari: [Atari ST]
WinUAE: [Commodore Amiga, Commodore Amiga CD32, Commodore CDTV]
MFME v10.1a: [Fruit Machine]
UNZ: [Fujitsu FM Towns]
GeePee32: [GamePark 32]

BlueMSX: [Microsoft MSX, Microsoft MSX2, Microsoft MSX2+]
OpenMSXPalm: [MSX Palcom Laserdisc]
MUGEN: [MUGEN]
SimCoupe: [MGT Sam Coupe]
Citra: [Nintendo 3DS]
CEMU162: [Nintendo Wii U]
OpenBOR: [OpenBOR]
DCVG5K: [Philips VG 5000]
Casual Games: [PopCap]
Xmillennium: [Sharp X1]
XM6: [Sharp X68000]
BlueMSX Zemmix: [Zemmix, Zemmix Neo]


ScummVM: [ScummVM]
Daphen WoW Action Max: [WoW Action Max]
Visual Pinball 9: [Visual Pinball]
DFend: [Microsoft MS-DOS]
DOSBox eXoDOS: [Microsoft MS-DOS eXoDOS]
DOSBox Win3XO: [Microsoft Windows 3.x]
video dummy: [Vintage Commercials]
 */

    }

    void hyperSpin() {
        mappingManager.configureHyperSpinKeys()
        println "JoyToKey HyperSpin: Configuring profile for 360"
        new J2K(hs, "HyperSpin").presets.with {
            dPadToCursor(player1)
            dPadToCursor(player2)
            xbox360Esc(player1)
            xbox360Esc(player2)
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
            buttonsTo(player1, mapping)
            buttonsTo(player2, mapping)
            save()
        }
    }

    void mapEscapeExit() {

// Mapear en JoyToKey la tecla ESCAPE con BACK+START (Xbox 360) en TODOS los sistemas
        println "JoyToKey all: BACK+START -> ESC....."
        hs.listAllJoyToKeyProfiles().each { J2K j2k ->
            j2k.presets.with {
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
// Para dejar la configuración por defecto: podemos borrar el retroarch.cfg o
// ejecutar el script ConfigRetroarch que se encarga de borrar los comandos de JoyStick de los dos players y, además,
// de configurar teclas (también borra todas las acciones de sistema que haya configurados para que funcione todo
// por defecto)
        mappingManager.resetRetroArch()

        println "JoyToKey RetroArch: Configuring 360 BACK+RB = F1"
// Después, se mapea en JoyToKey la tecla F1 con BACK+RB
        hs.listSystemsRetroArch()*.loadJ2KConfig().each { J2K j2k ->
            j2k.presets.with {
                xbox360RetroArchF1(player1)
                xbox360RetroArchF1(player2)
                save()
            }
        }
    }

    void mame() {

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
        mappingManager.resetMameConfig()

        println "JoyToKey MAME: Configuring 360 additional buttons (coin, start, dpad): ${(hs.listSystemsMAME() + hs.getSystem("HBMAME"))*.name}"
// Mapeos en JoyToKey
        (hs.listSystemsMAME() + hs.getSystem("HBMAME"))*.loadJ2KConfig().each { J2K j2k ->
            j2k.presets.with {
                xbox360MameTab(player1)
                xbox360MameTab(player2)
                dPadToCursor(player1)
                dPadTo(player2, KEY_D, KEY_F, KEY_R, KEY_G)
                buttonsTo(player1, [
                        (XBOX360_BACK) : KEY_5,
                        (XBOX360_START): KEY_1
                ])
                buttonsTo(player2, [
                        (XBOX360_BACK) : KEY_6,
                        (XBOX360_START): KEY_2
                ])
                save()
            }
        }
    }
    void pinballs() {

        mappingManager.configPinballs()
        println "JoyToKey Future Pinball"
// Future Pinball. Funciona mejor con teclado. Cargar el registro de Windows para que se carguen estas teclas
        hs.getSystem("Future Pinball").loadJ2KConfig().presets.with {
            analogLeftTo(player1, F1, F4, F3, F2)  // vistas
            analogRightTo(player1, F5, F8, F7, F6)  // vistas
            dPadTo(player1, KEY_F, RETURN, SPACE, KEY_A)  // abajo sacar, resto golpear
            buttonsTo(player1, [
                    (XBOX360_A)    : RETURN, // sacar
                    (XBOX360_Y)    : TAB, // mirar arriba
                    (XBOX360_BACK) : KEY_5, // start
                    (XBOX360_START): KEY_1, // moneda
                    (XBOX360_LB)   : [KEY_Z, KEY_X],  // pinballs izquierdo
                    (XBOX360_RB)   : [KEY_N, KEY_M],  // pinballs derecho
            ])
            save()
        }
    }

    void aae() {

        println "JoyTokey AAE"
// AAE funciona mejor con teclado
        hs.getSystem("AAE").loadJ2KConfig().presets.with {
            dPadToCursor(player1)
            dPadToCursor(player2)
            analogToCursor(player1)
            analogToCursor(player2)
            buttonsTo(player1, [
                    (XBOX360_A)    : ALT,
                    (XBOX360_B)    : CTRL,
                    (XBOX360_X)    : SHIFT,
                    (XBOX360_Y)    : SPACE,
                    (XBOX360_BACK) : KEY_5,
                    (XBOX360_START): KEY_1
            ])
            buttonsTo(player2, [
                    (XBOX360_A)    : ALT,
                    (XBOX360_B)    : CTRL,
                    (XBOX360_X)    : SHIFT,
                    (XBOX360_Y)    : SPACE,
                    (XBOX360_BACK) : KEY_5,
                    (XBOX360_START): KEY_2
            ])
            save()
        }
    }

    void winvice() {
        mappingManager.configCommodoreWinViceKeys()
        println "JoyToKey WinVICE: configuring ${hs.listSystemsWinVICE()*.name}"
        hs.listSystemsWinVICE()*.loadJ2KConfig().with { J2K j2k ->
            j2k.presets.with {
                Map mapping = [
                        (XBOX360_B)        : SPACE,
                        (XBOX360_X)        : ENTER,
                        (XBOX360_Y)        : TAB,
                        (XBOX360_BACK)     : F1,
                        (XBOX360_START)    : CAPSLOCK,  // RUN/STOP
                        (XBOX360_LB)       : KEY_N,
                        (XBOX360_LT_ANALOG): KEY_1,
                        (XBOX360_RB)       : KEY_Y,
                        (XBOX360_RT_ANALOG): KEY_2,
                ]
                buttonsTo(player1, mapping)
                buttonToKey(player1, XBOX360_A, KEY_Q)

                buttonsTo(player2, mapping)
                buttonToKey(player2, XBOX360_A, KEY_U)

                dPadTo(player1, KEY_A, KEY_S, KEY_W, KEY_D)
                analogToCursor(player1)

                dPadTo(player2, KEY_J, KEY_K, KEY_I, KEY_L)
                analogToCursor(player2)
                save()
            }
        }
    }

    void superModel3() {


        mappingManager.configSuperModel3KeysAndJoy()
        println "JoyToKey Super Model 3"
        hs.getSystem("Sega Model 3").loadJ2KConfig().presets.with {
            dPadToCursor(player1)
            buttonsTo(player1, [(XBOX360_BACK)     : KEY_5,  // coin
                                (XBOX360_START)    : KEY_1, // start
                                (XBOX360_L3)       : KEY_7,  // service
                                (XBOX360_R3)       : KEY_9,  // test
                                (XBOX360_RB)       : KEY_W, // sube marcha
                                (XBOX360_LB)       : KEY_Q, // baja marcha
                                (XBOX360_RT_ANALOG): CURSOR_UP, // frena
                                (XBOX360_LT_ANALOG): CURSOR_DOWN, // acelera
            ])

            dPadToNumpad(player1)
            buttonsTo(player2, [(XBOX360_BACK) : KEY_6,  // coin
                                (XBOX360_START): KEY_2,  // start
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
            dPadToCursor(player1)

            analogToCursor(player2)
            dPadToCursor(player2)
            Map mapping = [
                    (XBOX360_A)        : LCTRL,
                    (XBOX360_B)        : LALT,
                    (XBOX360_X)        : LSHIFT,
                    (XBOX360_Y)        : KEY_Z, // skill 1
                    (XBOX360_BACK)     : KEY_5, // coin
                    (XBOX360_START)    : KEY_1,  // start
                    (XBOX360_LB)       : KEY_X, // skill 2
                    (XBOX360_LT_ANALOG): KEY_T, // tilt
                    (XBOX360_RB)       : KEY_C, // skill 3
                    (XBOX360_RT_ANALOG): KEY_P, // pause
            ]
            buttonsTo(player1, mapping)
            mapping[XBOX360_BACK] = KEY_6
            mapping[XBOX360_START] = KEY_2
            buttonsTo(player2, mapping)

            save()
        }
    }

    void fourDO() {


        mappingManager.configFourDOKeys()
        println "JoyToKey Panasonic 3DO"
        hs.getSystem("Panasonic 3DO").loadJ2KConfig().presets.with {

            analogToCursor(player1)
            dPadToCursor(player1)
            buttonsTo(player1, [
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

            analogToNumpad(player2)
            dPadToNumpad(player2)
            buttonsTo(player2, [
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
            buttonsTo(player1, [
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

            analogToNumpad(player2)
            dPadToNumpad(player2)
            buttonsTo(player2, [
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

            buttonsTo(player1, [
                    (XBOX360_A)    : LCONTROL,
                    (XBOX360_B)    : LALT,
                    (XBOX360_X)    : SPACE,
                    (XBOX360_Y)    : SPACE,
                    (XBOX360_BACK) : KEY_6,
                    (XBOX360_START): KEY_2
            ])

            buttonsTo(player2, [
                    (XBOX360_A)    : KEY_G,
                    (XBOX360_B)    : KEY_H,
                    (XBOX360_X)    : KEY_J,
                    (XBOX360_Y)    : KEY_J,
                    (XBOX360_BACK) : KEY_6,
                    (XBOX360_START): KEY_2
            ])
        }
    }

    void neoRaine() {

        mappingManager.configNeoRaineKeys()
        println "JoyToKey SNK Neo Geo CD"
        hs.getSystem("SNK Neo Geo CD").loadJ2KConfig().presets.with {

            analogToCursor(player1)
            dPadToCursor(player1)
            buttonsTo(player1, [
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

            analogToNumpad(player2)
            dPadToNumpad(player2)
            buttonsTo(player2, [
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
            buttonsTo(player1, [
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
}