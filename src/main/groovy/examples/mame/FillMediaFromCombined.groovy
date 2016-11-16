package examples.mame

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.Checker
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.common.IOTools

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

// Se procesan todos los sistemas basados en MAME, menos MAME
// Se supone que antes se ha ejecutado CombineMediaMame y tenemos en MAME todos los medias mezclados de todos
// los sistemas

def systems = (hs.listSystems().findAll { it.defaultEmulator.name.startsWith("MAME") }*.name) - "MAME"
RLSystem mame = hs.getSystem("MAME")
boolean simulation = false
new Checker(hs).
        addHandler(new BaseCheckHandler() {
            @Override
            void startSystem(RLSystem system) {
                println("Processing ${system.name}. Simulation: ${simulation}")
            }

            @Override
            void romChecked(CheckRomResult checkRomResult) {
                if (checkRomResult.videos == 0) {
                    File mameMedia = IOTools.findFileWithExtensions(mame.getMediaPath("Video/${checkRomResult.romName}"), ["mp4", "flv"])
                    if (mameMedia) {
                        File systemMissingVideo = checkRomResult.system.getMediaPath("Video/${mameMedia.name}")
                        println "${mameMedia} -> ${systemMissingVideo}"
                        if (!simulation) {
                            IOTools.copy(mameMedia, systemMissingVideo)
                        }

                    }
                }
                if (checkRomResult.wheels == 0) {
                    File mameMedia = IOTools.findFileWithExtensions(mame.getMediaPath("Images/Wheel/${checkRomResult.romName}"), ["png", "jpg"])
                    if (mameMedia) {
                        File systemMissing = checkRomResult.system.getMediaPath("Images/Wheel/${mameMedia.name}")
                        println "${mameMedia} -> ${systemMissing}"
                        if (!simulation) {
                            IOTools.copy(mameMedia, systemMissing)
                        }
                    }
                }
            }

        }).
        checkSystems(systems)
