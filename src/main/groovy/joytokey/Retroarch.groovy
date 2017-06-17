package joytokey

import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 17-Jun-17.
 */
class Retroarch {
    IniFile iniFile
    static EMPTY = "\"nul\""
    static PAD_WITH_ANALOG = "\"5\""
    static PAD = "\"1\""

    static ANALOG_TO_DPAD_NONE = "\"0\""
    static ANALOG_TO_DPAD_RIGHT = "\"2\""
    static ANALOG_TO_DPAD_LEFT = "\"1\""

    Retroarch folder(String folder) {
        iniFile = new IniFile().parse(new File(folder, "retroarch.cfg"))
        this
    }

    Retroarch configureKeys(int player, Map<String, String> map) {
        map.each { String key, String val ->
            String used = used(val)
            key = "input_player${player}_${key.toLowerCase()}".toString()
            if (used && used != key) {
                println "Key $val already used by ${used}"
            } else {
                iniFile.put(key, "\"${val.toLowerCase()}\"")
            }
        }
        this
    }
    Retroarch save() {
        iniFile.store()
    }

    Retroarch configureKeyboardPlayer1Default() {
        iniFile.put("input_player1_b", "\"z\"")
        iniFile.put("input_player1_a", "\"x\"")
        iniFile.put("input_player1_y", "\"a\"")
        iniFile.put("input_player1_x", "\"s\"")
        iniFile.put("input_player1_l", "\"q\"")
        iniFile.put("input_player1_r", "\"w\"")
        iniFile.put("input_player1_down", "\"down\"")
        iniFile.put("input_player1_left", "\"left\"")
        iniFile.put("input_player1_right", "\"right\"")
        iniFile.put("input_player1_up", "\"up\"")
        iniFile.put("input_player1_select", "\"rshift\"")
        iniFile.put("input_player1_start", "\"enter\"")
        this
    }

    Retroarch resetKeys() {
        [input_audio_mute : "f9",
         input_cheat_index_minus : "t",
         input_cheat_index_plus : "y",
         input_cheat_toggle : "u",
         input_disk_eject_toggle : "nul",
         input_disk_next : "nul",
         input_disk_prev : "nul",
         input_enable_hotkey : "nul",
         input_frame_advance : "k",
         input_game_focus_toggle : "scroll_lock",
         input_grab_mouse_toggle : "f11",
         input_hold_fast_forward : "l",
         input_load_state : "f4",
         input_menu_toggle : "f1",
         input_movie_record_toggle : "o",
         input_netplay_flip_players_1_2 : "nul",
         input_netplay_game_watch : "i",
         input_osk_toggle : "f12",
         input_overlay_next : "nul",
         input_pause_toggle : "p",
         input_reset : "h",
         input_rewind : "r",
         input_save_state : "f2",
         input_screenshot : "f8",
         input_shader_next : "m",
         input_shader_prev : "n",
         input_slowmotion : "e",
         input_toggle_fast_forward : "space",
         input_exit_emulator: "escape",
         input_toggle_fullscreen : "f",
         input_volume_down : "subtract",
         input_volume_up : "add"].each { key, val ->
            iniFile.put(key, "\"${val}\"")
        }
        this
    }

    String used(k) {
        Map used = [
        "input_audio_mute",
        "input_cheat_index_minus",
        "input_cheat_index_plus",
        "input_cheat_toggle",
        "input_disk_eject_toggle",
        "input_disk_next",
        "input_disk_prev",
        "input_enable_hotkey",
        "input_frame_advance",
        "input_game_focus_toggle",
        "input_grab_mouse_toggle",
        "input_hold_fast_forward",
        "input_load_state",
        "input_menu_toggle",
        "input_movie_record_toggle",
        "input_netplay_flip_players_1_2",
        "input_netplay_game_watch",
        "input_osk_toggle",
        "input_overlay_next",
        "input_pause_toggle",
        "input_reset",
        "input_rewind",
        "input_save_state",
        "input_screenshot",
        "input_shader_next",
        "input_shader_prev",
        "input_slowmotion",
        "input_toggle_fast_forward",
        "input_exit_emulator",
        "input_toggle_fullscreen",
        "input_volume_down",
        "input_volume_up",

         "input_player1_b",
         "input_player1_a",
         "input_player1_y",
         "input_player1_x",
         "input_player1_l",
         "input_player1_r",
         "input_player1_down",
         "input_player1_left",
         "input_player1_right",
         "input_player1_up",
         "input_player1_select",
         "input_player1_start",

         "input_player2_b",
         "input_player2_a",
         "input_player2_y",
         "input_player2_x",
         "input_player2_l",
         "input_player2_r",
         "input_player2_down",
         "input_player2_left",
         "input_player2_right",
         "input_player2_up",
         "input_player2_select",
         "input_player2_start"
        ].inject([:]) { map, kk ->
            String v = iniFile.get(kk)
            if (v && v != EMPTY) map[v] = kk
            map
        }

        return used["\"$k\"".toString()]

    }

    Retroarch resetPlayer(player = 1, joypos = 1) {

        iniFile.put("input_player${player}_b", EMPTY)
        iniFile.put("input_player${player}_a", EMPTY)
        iniFile.put("input_player${player}_y", EMPTY)
        iniFile.put("input_player${player}_x", EMPTY)
        iniFile.put("input_player${player}_l", EMPTY)
        iniFile.put("input_player${player}_r", EMPTY)
        iniFile.put("input_player${player}_down", EMPTY)
        iniFile.put("input_player${player}_left", EMPTY)
        iniFile.put("input_player${player}_right", EMPTY)
        iniFile.put("input_player${player}_up", EMPTY)
        iniFile.put("input_player${player}_select", EMPTY)
        iniFile.put("input_player${player}_start", EMPTY)

        iniFile.put("input_libretro_device_p${player}", PAD_WITH_ANALOG)
        iniFile.put("input_player${player}_analog_dpad_mode", ANALOG_TO_DPAD_NONE)
        iniFile.put("input_player${player}_joypad_index", "\"${joypos -1}\"")

        iniFile.put("input_player${player}_a_axis", EMPTY)
        iniFile.put("input_player${player}_a_btn", EMPTY)
        iniFile.put("input_player${player}_b_axis", EMPTY)
        iniFile.put("input_player${player}_b_btn", EMPTY)
        iniFile.put("input_player${player}_down_axis", EMPTY)
        iniFile.put("input_player${player}_down_btn", EMPTY)
        iniFile.put("input_player${player}_l2", EMPTY)
        iniFile.put("input_player${player}_l2_axis", EMPTY)
        iniFile.put("input_player${player}_l2_btn", EMPTY)
        iniFile.put("input_player${player}_l3", EMPTY)
        iniFile.put("input_player${player}_l3_axis", EMPTY)
        iniFile.put("input_player${player}_l3_btn", EMPTY)
        iniFile.put("input_player${player}_left_axis", EMPTY)
        iniFile.put("input_player${player}_left_btn", EMPTY)
        iniFile.put("input_player${player}_l_axis", EMPTY)
        iniFile.put("input_player${player}_l_btn", EMPTY)
        iniFile.put("input_player${player}_l_x_minus", EMPTY)
        iniFile.put("input_player${player}_l_x_minus_axis", EMPTY)
        iniFile.put("input_player${player}_l_x_minus_btn", EMPTY)
        iniFile.put("input_player${player}_l_x_plus", EMPTY)
        iniFile.put("input_player${player}_l_x_plus_axis", EMPTY)
        iniFile.put("input_player${player}_l_x_plus_btn", EMPTY)
        iniFile.put("input_player${player}_l_y_minus", EMPTY)
        iniFile.put("input_player${player}_l_y_minus_axis", EMPTY)
        iniFile.put("input_player${player}_l_y_minus_btn", EMPTY)
        iniFile.put("input_player${player}_l_y_plus", EMPTY)
        iniFile.put("input_player${player}_l_y_plus_axis", EMPTY)
        iniFile.put("input_player${player}_l_y_plus_btn", EMPTY)
        iniFile.put("input_player${player}_r2", EMPTY)
        iniFile.put("input_player${player}_r2_axis", EMPTY)
        iniFile.put("input_player${player}_r2_btn", EMPTY)
        iniFile.put("input_player${player}_r3", EMPTY)
        iniFile.put("input_player${player}_r3_axis", EMPTY)
        iniFile.put("input_player${player}_r3_btn", EMPTY)
        iniFile.put("input_player${player}_right_axis", EMPTY)
        iniFile.put("input_player${player}_right_btn", EMPTY)
        iniFile.put("input_player${player}_r_axis", EMPTY)
        iniFile.put("input_player${player}_r_btn", EMPTY)
        iniFile.put("input_player${player}_r_x_minus", EMPTY)
        iniFile.put("input_player${player}_r_x_minus_axis", EMPTY)
        iniFile.put("input_player${player}_r_x_minus_btn", EMPTY)
        iniFile.put("input_player${player}_r_x_plus", EMPTY)
        iniFile.put("input_player${player}_r_x_plus_axis", EMPTY)
        iniFile.put("input_player${player}_r_x_plus_btn", EMPTY)
        iniFile.put("input_player${player}_r_y_minus", EMPTY)
        iniFile.put("input_player${player}_r_y_minus_axis", EMPTY)
        iniFile.put("input_player${player}_r_y_minus_btn", EMPTY)
        iniFile.put("input_player${player}_r_y_plus", EMPTY)
        iniFile.put("input_player${player}_r_y_plus_axis", EMPTY)
        iniFile.put("input_player${player}_r_y_plus_btn", EMPTY)

        iniFile.put("input_player${player}_select_axis", EMPTY)
        iniFile.put("input_player${player}_select_btn", EMPTY)
        iniFile.put("input_player${player}_start_axis", EMPTY)
        iniFile.put("input_player${player}_start_btn", EMPTY)
        iniFile.put("input_player${player}_turbo", EMPTY)
        iniFile.put("input_player${player}_turbo_axis", EMPTY)
        iniFile.put("input_player${player}_turbo_btn", EMPTY)
        iniFile.put("input_player${player}_up_axis", EMPTY)
        iniFile.put("input_player${player}_up_btn", EMPTY)
        iniFile.put("input_player${player}_x_axis", EMPTY)
        iniFile.put("input_player${player}_x_btn", EMPTY)
        iniFile.put("input_player${player}_y_axis", EMPTY)
        iniFile.put("input_player${player}_y_btn", EMPTY)
        this
    }
}
