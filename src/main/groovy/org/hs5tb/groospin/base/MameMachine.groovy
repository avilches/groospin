package org.hs5tb.groospin.base

import org.hs5tb.groospin.common.Ini

/**
 * Created by Alberto on 01-Sep-16.
 */
class MameMachine extends Rom {

    String driverStatus
    String emulationStatus
    String colorStatus
    String soundStatus
    String graphicStatus
    String sourcefile

    String catVerCat
    String catVerVersion
    boolean catVerMature

    String romof

    Set roms = new HashSet()
    Set disks = new HashSet()

    int players

    boolean working = true

    boolean joystick
    boolean doublejoy
    boolean lightgun

    boolean paddle
    boolean dial
    boolean pedal

    boolean trackball
    boolean mouse


    boolean keypad
    boolean keyboard

    boolean gambling
    boolean mahjong
    boolean hanafuda

    boolean mechanical

    boolean playable

    boolean vertical = false

    List controls

    MameMachine loadFromMameDat(Node machine) {

        name = machine.@name
        mechanical = machine.@ismechanical == "yes"
        cloneof = machine.@cloneof
        romof = machine.@romof
        sourcefile = machine.@sourcefile
        description = machine.description.text()
        manufacturer = machine.manufacturer.text()
        year = machine.year.text()
        players = (machine.input[0].@players?:0) as int
        driverStatus = machine.driver[0].@status
        emulationStatus = machine.driver[0].@emulation
        colorStatus = machine.driver[0].@color
        soundStatus = machine.driver[0].@sound
        graphicStatus = machine.driver[0].@graphic

        controls = machine.input[0].control.@type

        joystick = controls.contains("joy") || controls.contains("stick") || controls.contains("doublejoy")
        doublejoy = controls.contains("doublejoy")

        lightgun = controls.contains("lightgun")

        paddle = controls.contains("paddle")
        dial = controls.contains("dial")
        pedal = controls.contains("pedal")

        trackball = controls.contains("trackball")
        mouse = controls.contains("mouse")

        keypad = controls.contains("keypad")
        keyboard = controls.contains("keyboard")

        gambling = controls.contains("gambling")
        mahjong = controls.contains("mahjong")
        hanafuda = controls.contains("hanafuda")

        int rotate = (machine.display[0]?.@rotate?:0) as int
        vertical = rotate == 90 || rotate == 270

        playable = working && !mechanical && controls && players  // more than 1 control and 1 player at least

        working = machineIsWorkingCondition.call(this)

        if (!manufacturer || manufacturer.startsWith("?") || manufacturer.startsWith("<")) {
            manufacturer = "Unknown"
        }

        if (machine.rom.size() > 0) {
            roms.addAll(machine.rom.@name)
        }
        if (machine.disk.size() > 0) {
            disks.addAll(machine.disk.@name)
        }

        return this
    }

    boolean isArcadeCabinetControls() {
        joystick && // josytick enabled
                !lightgun && !gambling && !hanafuda && !mahjong && !paddle && !dial && !pedal && !keyboard && !keypad && !mouse && !trackball
    }

    static Closure machineIsWorkingCondition = { MameMachine rom ->
        // Rules as offical MAME.xml from HyperSpin
        /*
        rom.driverStatus != "preliminary" &&
        rom.emulationStatus == "good" &&
        rom.colorStatus == "good" &&
        rom.soundStatus == "good"
        */

        rom.emulationStatus == "good" &&
        rom.colorStatus != "preliminary"
    }


    @Override
    String toString() {
        "${name}:\"${description}\" ${manufacturer} (${year})"
    }

    static Node parseDat(String file) {
        return parseDat(new File(file).newInputStream())
    }

    static Node parseDat(File file) {
        return parseDat(file.newInputStream())
    }

    static Node parseDat(InputStream is) {
        XmlParser parser = new XmlParser()
        parser.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
        parser.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
        Node mame = parser.parse(is)

        Map machineRomOf = [:]
        mame.machine.each { Node machine ->
            if (machineNotRunnableCondition.call(machine)) {
                mame.children().remove(machine)
            } else {
                // Mame stores the roms in this way:
                // <machine name="2020bb" romof="neogeo">
                // <machine name="2020bba" cloneof="2020bb" romof="2020bb">
                // But this is wrong because we loose the romof="neogeo" in the 2020bba clone,
                // so we fix the xml putting the right romof in the clones also

                if (machine.@romof && !machine.@cloneof) {
                    machineRomOf[machine.@name] = machine
                } else if (machine.@cloneof) {
                    if (machineRomOf[machine.@cloneof]) {
                        machine.@romof = machineRomOf[machine.@cloneof].@romof
                    }
                }
            }
        }

        return mame
    }

    static Closure machineNotRunnableCondition = { Node machine ->
        boolean notRunnable = machine.@isbios == "yes" || machine.@isdevice == "yes" || machine.@runnable == "no"
        if (notRunnable) {
            // println "Skipping non-game ${machine.@isbios == "yes" ? "B" : "."}${machine.@isdevice ? "D" : "."}${machine.@runnable == "no" ? "N" : "."} ${machine.@name}: ${machine.description.text()}"
        }
        return notRunnable
    }

    static List<MameMachine> loadRoms(String file, Closure filter = null) {
        loadRoms(new File(file).newInputStream(), filter)
    }

    static List<MameMachine> loadRoms(File file, Closure filter = null) {
        return loadRoms(file.newInputStream(), filter)
    }

    static List<MameMachine> loadRoms(InputStream is, Closure filter = null) {
        Node mame = parseDat(is)
        loadRoms(mame, filter)
    }

    static List<MameMachine> loadRoms(Node mame, Closure filter = null) {
        List<MameMachine> machines = []
        mame.machine.each { Node machine ->
            MameMachine rom = new MameMachine().loadFromMameDat(machine)
            if (!filter || filter(rom)) {
                machines << rom
            }
        }
        return machines
    }

}