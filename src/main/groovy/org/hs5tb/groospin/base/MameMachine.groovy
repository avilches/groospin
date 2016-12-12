package org.hs5tb.groospin.base
/**
 * Created by Alberto on 01-Sep-16.
 */
class MameMachine extends Rom {

    static class RomBin {
        String name
        int size
        String merge
        String sha1
        String crc
        Status status = Status.good
        boolean optional = false

        enum Status { nodump, baddump, good
            static names = values()*.name() as Set
        }

        RomBin load(Node node) {
            name = node.@name.trim()
            merge = node.@merge?.trim()
            size = (node.@size?:0) as int
            sha1 = node.@sha1
            crc = node.@crc
            if (node.@status && node.@status in RomBin.Status.names) {
                status = RomBin.Status."${node.@status}"
            }
            optional = node.@optional == "true"
            return this
        }

    }

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

    Set<RomBin> roms = new HashSet<>()
    Set<RomBin> disks = new HashSet<>()

    int players
    int buttons

    boolean working = true

    boolean joy
    boolean stick
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

    Set softwareNames

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
        players = (machine.input[0]?.@players?:0) as int
        buttons = (machine.input[0]?.@buttons?:0) as int
        driverStatus = machine.driver[0]?.@status
        emulationStatus = machine.driver[0]?.@emulation
        colorStatus = machine.driver[0]?.@color
        soundStatus = machine.driver[0]?.@sound
        graphicStatus = machine.driver[0]?.@graphic

        controls = machine.input[0]?.control?.@type

        joy = controls?.contains("joy")
        stick = controls?.contains("stick")
        doublejoy = controls?.contains("doublejoy")

        lightgun = controls?.contains("lightgun")

        paddle = controls?.contains("paddle")
        dial = controls?.contains("dial")
        pedal = controls?.contains("pedal")

        trackball = controls?.contains("trackball")
        mouse = controls?.contains("mouse")

        keypad = controls?.contains("keypad")
        keyboard = controls?.contains("keyboard")

        gambling = controls?.contains("gambling")
        mahjong = controls?.contains("mahjong")
        hanafuda = controls?.contains("hanafuda")

        int rotate = (machine.display[0]?.@rotate?:0) as int
        vertical = rotate == 90 || rotate == 270

        playable = !mechanical && players && (controls || buttons)

        working = machineIsWorkingCondition.call(this)

        if (!manufacturer || manufacturer.startsWith("?") || manufacturer.startsWith("<")) {
            manufacturer = "Unknown"
        }

        machine.rom.each {
            roms << new RomBin().load(it)
        }
        machine.disk.each {
            disks << new RomBin().load(it)
        }

        softwareNames = machine.softwarelist.collect {
            it.@name
        }

        return this
    }

    boolean hasJoystick() {
        (joy || stick || doublejoy || dial)
    }

    boolean hasBall() {
        mouse || trackball
    }

    boolean hasKeyboard() {
        keyboard || keypad
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

    static Node parseDat(InputStream is, boolean fixClones = true, Closure filter = mameMachineRunnableArcadeOnlyCondition) {
        XmlParser parser = new XmlParser()
        parser.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
        parser.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
        Node mame = parser.parse(is)

        Map machineRomOf = [:]
        mame.machine.each { Node machine ->
            if (filter == null || filter.call(machine)) {
                if (fixClones) {
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
            } else {
                mame.children().remove(machine)
            }
        }

        return mame
    }

    static Closure mameMachineRunnableArcadeOnlyCondition = { Node machine ->
        boolean runnable =
                machine.@isbios != "yes" &&
                machine.@isdevice != "yes" &&
                machine.@runnable != "no" &&
                machine.softwarelist.size() == 0
        if (!runnable) {
            println "Skipping non-game ${machine.@isbios == "yes" ? "B" : "."}${machine.@isdevice ? "D" : "."}${machine.@runnable == "no" ? "N" : "."}-${machine.softwarelist.size()} [${machine.@name}]: \"${machine.description.text()}\""
        }
        return runnable
    }

    static List<MameMachine> loadRoms(String file, boolean fixClones = true, Closure filter = mameMachineRunnableArcadeOnlyCondition) {
        loadRoms(new File(file).newInputStream(), fixClones, filter)
    }

    static List<MameMachine> loadRoms(File file, boolean fixClones = true, Closure filter = mameMachineRunnableArcadeOnlyCondition) {
        return loadRoms(file.newInputStream(), fixClones, filter)
    }

    static List<MameMachine> loadRoms(InputStream is, boolean fixClones = true, Closure filter = mameMachineRunnableArcadeOnlyCondition) {
        Node mame = parseDat(is, fixClones, filter)
        createMameMachinesFromDat(mame)
    }

    static List<MameMachine> createMameMachinesFromDat(Node mame) {
        return mame.machine.collect { Node machine -> new MameMachine().loadFromMameDat(machine) }
    }

}