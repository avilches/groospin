package mapping

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 13-Jul-17.
 */
class PokeMiniMapping {

    static List<File> setDefaultKeys(HyperSpin hs) {
        println "- Nintendo Pokemon Mini keys"
        File iniFile = new File(hs.getPokeMiniFolder(), "pokemini.cfg")
        IniFile cfg = new IniFile().parse(iniFile)

        /*
 Pokémon-Mini     PC Keys
  ----------------------------
  D-PAD Left       Arrow Left
  D-PAD Right      Arrow Right
  D-PAD Up         Arrow Up
  D-PAD Down       Arrow Down
  Key A            Keyboard X
  Key B            Keyboard Z
  Key C            Keyboard S or C
  Power Button     Keyboard E
  Shock Detector   Keyboard A
  ----------------------------
  UI Menu          Keyboard Esc

  F9 will capture the screen and save as "snap_(sequence number).bmp"

  F10 can toggle between Fullscreen and Windowed.

  F11 will disable/enable speed throttle

  TAB can be hold to temporary disable speed throttle
         */


        // Estas son las teclas que guarda en la configuracion si no hay y no tocas nada:

        cfg.put("joyenabled", "no")
        cfg.put("joyid", "0")
        cfg.put("joyaxis_dpad", "yes")
        cfg.put("joyhats_dpad", "yes")
        cfg.put("joyplatform", "default")
        cfg.put("joybutton_menu", "8")
        cfg.put("joybutton_a", "1")
        cfg.put("joybutton_b", "2")
        cfg.put("joybutton_c", "7")
        cfg.put("joybutton_up", "10")
        cfg.put("joybutton_down", "11")
        cfg.put("joybutton_left", "4")
        cfg.put("joybutton_right", "5")
        cfg.put("joybutton_power", "9")
        cfg.put("joybutton_shock", "6")
        cfg.put("keyb_menu", "1")
        cfg.put("keyb_a", "88")
        cfg.put("keyb_b", "90")
        cfg.put("keyb_c", "67")
        cfg.put("keyb_up", "28")
        cfg.put("keyb_down", "29")
        cfg.put("keyb_left", "31")
        cfg.put("keyb_right", "30")
        cfg.put("keyb_power", "69")
        cfg.put("keyb_shock", "65")
        cfg.put("keyb_alt_menu", "81")
        cfg.put("keyb_alt_a", "0")
        cfg.put("keyb_alt_b", "0")
        cfg.put("keyb_alt_c", "68")
        cfg.put("keyb_alt_up", "104")
        cfg.put("keyb_alt_down", "98")
        cfg.put("keyb_alt_left", "100")
        cfg.put("keyb_alt_right", "102")
        cfg.put("keyb_alt_power", "80")
        cfg.put("keyb_alt_shock", "83")
        cfg.store()

        return [iniFile]
    }
}
