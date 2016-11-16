package operation

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.common.IOTools

/**
 * Created by Alberto on 18-Oct-16.
 */
class RomMediaOperations extends Operations {

    RomMediaOperations(String hs) {
        super(hs)
    }

    RomMediaOperations(HyperSpin hs) {
        super(hs)
    }

    void copyMedia(String rom, String systemFromName, String systemToName, boolean overwrite = false) {
        RLSystem systemFrom = hyperSpin.getSystem(systemFromName)
        RLSystem systemTo = hyperSpin.getSystem(systemToName)
        copyMedia(rom, systemFrom, systemTo, overwrite)
    }

    void copyMedia(String rom, RLSystem systemFrom, RLSystem systemTo, boolean overwrite = false) {

        ["Video"].each { String path ->
            copyMedia(rom, systemFrom, systemTo, path, HyperSpin.VIDEO_EXTENSIONS, overwrite)
        }

        ["Images/Wheel", "Images/Gamestart",
         "Images/Artwork1", "Images/Artwork2", "Images/Artwork3", "Images/Artwork4"].each { String path ->
            copyMedia(rom, systemFrom, systemTo, path, HyperSpin.IMAGE_EXTENSIONS, overwrite)
        }

        ["Themes"].each { String path ->
            copyMedia(rom, systemFrom, systemTo, path, HyperSpin.THEME_EXTENSIONS, overwrite)
        }

        ["Sound/Background Music"].each { String path ->
            copyMedia(rom, systemFrom, systemTo, "Sound/Background Music", HyperSpin.MUSIC_EXTENSIONS, overwrite)
        }

    }
    void copyMedia(String rom, RLSystem systemFrom, RLSystem systemTo, String path, List extensions, boolean overwrite = false) {
        if (!overwrite) {
            File dst = IOTools.findFileWithExtensions(systemTo.getMediaPath("${path}/${rom}"), extensions)
            if (dst) {
                return
            }
        }

        File originMedia = IOTools.findFileWithExtensions(systemFrom.getMediaPath("${path}/${rom}"), extensions)
        if (originMedia) {
            File missingMedia = systemTo.getMediaPath("${path}/${originMedia.name}")
            log("(${simulation?"simulation":"real"}) ${originMedia} -> ${missingMedia}")
            if (!simulation) {
                IOTools.copy(originMedia, missingMedia)
            }

        }

    }
}
