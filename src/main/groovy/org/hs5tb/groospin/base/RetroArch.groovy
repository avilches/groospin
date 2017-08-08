package org.hs5tb.groospin.base

import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 17-Jun-17.
 */
class RetroArch {
    boolean override = false
    IniFile iniFile
    IniFile coreIniFile
    HyperSpin hyperSpin
    static String DEVICE_PAD_WITH_ANALOG = "\"5\""
    static String DEVICE_PAD = "\"1\""
    static String DEVICE_NONE = "\"0\""

    static String ANALOG_TO_DPAD_NONE = "\"0\""
    static String ANALOG_TO_DPAD_RIGHT = "\"2\""
    static String ANALOG_TO_DPAD_LEFT = "\"1\""


    static String EMPTY = "\"nul\""

    static String MINUS = "\"subtract\""
    static String PLUS = "\"add\""

    static String KEY_A = "\"a\""
    static String KEY_B = "\"b\""
    static String KEY_C = "\"c\""
    static String KEY_D = "\"d\""
    static String KEY_E = "\"e\""
    static String KEY_F = "\"f\""
    static String KEY_G = "\"g\""
    static String KEY_H = "\"h\""
    static String KEY_I = "\"i\""
    static String KEY_J = "\"j\""
    static String KEY_K = "\"k\""
    static String KEY_L = "\"l\""
    static String KEY_M = "\"m\""
    static String KEY_N = "\"n\""
    static String KEY_O = "\"o\""
    static String KEY_P = "\"p\""
    static String KEY_Q = "\"q\""
    static String KEY_R = "\"r\""
    static String KEY_S = "\"s\""
    static String KEY_T = "\"t\""
    static String KEY_U = "\"u\""
    static String KEY_V = "\"v\""
    static String KEY_W = "\"w\""
    static String KEY_X = "\"x\""
    static String KEY_Y = "\"y\""
    static String KEY_Z = "\"z\""

    static String DOWN = "\"down\""
    static String LEFT = "\"left\""
    static String RIGHT = "\"right\""
    static String UP = "\"up\""


    static String RSHIFT = "\"rshift\""
    static String ENTER = "\"enter\""

    static String F1 = "\"f1\""
    static String F2 = "\"f2\""
    static String F3 = "\"f3\""
    static String F4 = "\"f4\""
    static String F5 = "\"f5\""
    static String F6 = "\"f6\""
    static String F7 = "\"f7\""
    static String F8 = "\"f8\""
    static String F9 = "\"f9\""
    static String F10 = "\"f10\""
    static String F11 = "\"f11\""
    static String F12 = "\"f12\""
    static String SPACE = "\"space\""
    static String ESC = "\"escape\""
    static String SCROLLLOCK = "\"scroll_lock\""

    static String KEY_0 = "\"num0\""
    static String KEY_1 = "\"num1\""
    static String KEY_2 = "\"num2\""
    static String KEY_3 = "\"num3\""
    static String KEY_4 = "\"num4\""
    static String KEY_5 = "\"num5\""
    static String KEY_6 = "\"num6\""
    static String KEY_7 = "\"num7\""
    static String KEY_8 = "\"num8\""
    static String KEY_9 = "\"num9\""

    static String KEY_0_PAD = "\"keypad0\""
    static String KEY_1_PAD = "\"keypad1\""
    static String KEY_2_PAD = "\"keypad2\""
    static String KEY_3_PAD = "\"keypad3\""
    static String KEY_4_PAD = "\"keypad4\""
    static String KEY_5_PAD = "\"keypad5\""
    static String KEY_6_PAD = "\"keypad6\""
    static String KEY_7_PAD = "\"keypad7\""
    static String KEY_8_PAD = "\"keypad8\""
    static String KEY_9_PAD = "\"keypad9\""



    RetroArch folder(String folder) {
        iniFile = new IniFile(equals: " = ").parse(new File(folder, "retroarch.cfg"))
        iniFile.put("system_directory", "\":\\system\"")
        iniFile.store()

        coreIniFile = new IniFile(equals: " = ").parse(new File(folder, "retroarch-core-options.cfg"))
        coreIniFile.put("beetle_psx_analog_toggle", "\"enabled\"")
        coreIniFile.store()

        File bettle = new File(folder, "config\\Beetle PSX\\Beetle PSX.cfg")
        if (!bettle.exists()) {
            bettle.parentFile.mkdirs()
            bettle.text = ""
        }
        this
    }

    RetroArch configureKeys(int player, Map<String, String> map) {
        map.each { String key, String val ->
            String used = used(val)
            key = "input_player${player}_${key.toLowerCase()}".toString()
            if (used && used != key) {
                if (override) {
                    println("    RetroArch warning: key $val already used by ${used}... removing")
                    iniFile.put(used, EMPTY)
                } else {
                    throw new Exception("Key $val already used by ${used}")
                }
            } else {
                iniFile.put(key, val)
            }
        }
        this
    }
    RetroArch save() {
        iniFile.store()
    }

    RetroArch setDefaultKeysFor2Players() {
        configureKeys(1,
                [b     : KEY_Z,
                 a     : KEY_X,
                 y     : KEY_A,
                 x     : KEY_S,
                 l     : KEY_Q,
                 r     : KEY_W,
                 left  : LEFT,
                 down  : DOWN,
                 up    : UP,
                 right : RIGHT,
                 select: KEY_D,
                 start : KEY_C]
        )
        configureKeys(2,
                [b     : KEY_1,
                 a     : KEY_2,
                 y     : KEY_3,
                 x     : KEY_4,
                 l     : KEY_5,
                 r     : KEY_6,
                 left  : KEY_4_PAD,
                 down  : KEY_2_PAD,
                 up    : KEY_8_PAD,
                 right : KEY_6_PAD,
                 select: KEY_G,
                 start : KEY_B]
        )
        this
    }

    RetroArch resetKeysToSystemDefault() {
        [input_audio_mute : F9,
         input_cheat_index_minus : KEY_T,
         input_cheat_index_plus : KEY_Y,
         input_cheat_toggle : KEY_U,
         input_disk_eject_toggle : EMPTY,
         input_disk_next : EMPTY,
         input_disk_prev : EMPTY,
         input_enable_hotkey : EMPTY,
         input_frame_advance : KEY_K,
         input_game_focus_toggle : SCROLLLOCK,
         input_grab_mouse_toggle : F11,
         input_hold_fast_forward : KEY_L,
         input_load_state : F4,
         input_menu_toggle : F1,
         input_movie_record_toggle : KEY_O,
         input_netplay_flip_players_1_2 : EMPTY,
         input_netplay_game_watch : KEY_I,
         input_osk_toggle : F12,
         input_overlay_next : EMPTY,
         input_pause_toggle : KEY_P,
         input_reset : KEY_H,
         input_rewind : KEY_R,
         input_save_state : F2,
         input_screenshot : F8,
         input_shader_next : KEY_M,
         input_shader_prev : KEY_N,
         input_slowmotion : KEY_E,
         input_toggle_fast_forward : SPACE,
         input_exit_emulator: ESC,
         input_toggle_fullscreen : KEY_F,
         input_volume_down : MINUS,
         input_volume_up : PLUS].each { key, val ->
            iniFile.put(key, val)
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

    RetroArch setDeviceKeyboardForPlayer(player) {
        iniFile.put("input_libretro_device_p${player}", DEVICE_NONE)
        this
    }

    RetroArch setDevicePadForPlayer(player) {
        iniFile.put("input_libretro_device_p${player}", DEVICE_PAD)
        this
    }

    RetroArch setDevicePadWithAnalogForPlayer(player) {
        iniFile.put("input_libretro_device_p${player}", DEVICE_PAD_WITH_ANALOG)
        this
    }

    RetroArch emptyKeysForPlayer(player = 1, joypos = 1) {

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

        iniFile.put("input_libretro_device_p${player}", DEVICE_PAD_WITH_ANALOG)
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
