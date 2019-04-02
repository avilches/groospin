package mapping.configs

import org.hs5tb.groospin.base.J2K

/**
 * Created by Alberto on 21-Aug-17.
 */
class ArcadeSetJulioPandoraBox4S extends ArcadeSet {
    J2K.Preset preset
    int player1 = 1
    int player2 = 2

    void p1Action1(key) { preset.buttonToKey(player1, 3, key) }

    void p1Action2(key) { preset.buttonToKey(player1, 2, key) }

    void p1Action3(key) { preset.buttonToKey(player1, 4, key) }

    void p1Action4(key) { preset.buttonToKey(player1, 1, key) }

    void p1Action5(key) { preset.buttonToKey(player1, 6, key) }

    void p1Action6(key) { preset.buttonToKey(player1, 7, key) }

    void p1Start(key) { preset.buttonToKey(player1, 10, key) }

    void exit(key) { preset.buttonToKey(player2, 11, key) }

    void pinballLeft(key) { preset.buttonToKey(player1, 13, key) }

    void pinballRight(key) { preset.buttonToKey(player1, 14, key) }

    void p2Action1(key) { preset.buttonToKey(player2, 3, key) }

    void p2Action2(key) { preset.buttonToKey(player2, 2, key) }

    void p2Action3(key) { preset.buttonToKey(player2, 4, key) }

    void p2Action4(key) { preset.buttonToKey(player2, 1, key) }

    void p2Action5(key) { preset.buttonToKey(player2, 6, key) }

    void p2Action6(key) { preset.buttonToKey(player2, 7, key) }

    void p2Start(key) { preset.buttonToKey(player2, 10, key) }

    void coin(key) { preset.buttonToKey(player1, 11, key) }

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


}
