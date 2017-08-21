package mapping.configs

import org.hs5tb.groospin.base.J2K

/**
 * Created by Alberto on 21-Aug-17.
 */
abstract class ArcadeSet {
    J2K.Preset preset

    abstract void p1Action1(key)
    abstract void p1Action2(key)
    abstract void p1Action3(key)
    abstract void p1Action4(key)
    abstract void p1Action5(key)
    abstract void p1Action6(key)
    abstract void p1Start(key)
    abstract void exit(key)
    abstract void pinballLeft(key)
    abstract void pinballRight(key)
    abstract void p2Action1(key)
    abstract void p2Action2(key)
    abstract void p2Action3(key)
    abstract void p2Action4(key)
    abstract void p2Action5(key)
    abstract void p2Action6(key)
    abstract void p2Start(key)
    abstract void coin(key)

}
