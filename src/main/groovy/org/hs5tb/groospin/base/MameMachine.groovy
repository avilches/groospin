package org.hs5tb.groospin.base

/**
 * Created by Alberto on 01-Sep-16.
 */
class MameMachine extends Rom {

    String status
    int players
    boolean ok = true

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

    List controls

    MameMachine loadFromMameDat(Node machine, boolean imperfect = true) {

        name = machine.@name
        mechanical = machine.@ismechanical == "yes"
        cloneof = machine.@cloneof
        description = machine.description.text()
        manufacturer = machine.manufacturer.text()
        year = machine.year.text()
        players = machine.input[0].@players as int
        status = machine.driver[0].@status
        ok = status == "good" || (imperfect && machine.driver[0].@status == "imperfect")
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

        playable = ok && !mechanical && controls && players  // more than 1 control and 1 player at least
        return this
    }

    @Override
    String toString() {
        "${name}:\"${description}\" ${manufacturer} (${year})"
    }

    static List<MameMachine> parseRoms(File file, boolean imperfect = true, Closure filter = null) {
        return parseRoms(file.newInputStream(), imperfect, filter)
    }

    static List<MameMachine> parseRoms(InputStream is, boolean imperfect = true, Closure filter = null) {
        XmlParser parser = new XmlParser()
        parser.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
        parser.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
        Node mame = parser.parse(is)

        List<MameMachine> machines = []
        mame.machine.each { Node machine ->
            if (machine.@isbios == "yes" || machine.@isdevice == "yes" || machine.@runnable == "no") {
                println "Skipping non-game ${machine.@isbios == "yes" ? "B" : "."}${machine.@isdevice ? "D" : "."}${machine.@runnable == "no" ? "N" : "."} ${machine.@name}: ${machine.description.text()}"
                return
            }
            MameMachine rom = new MameMachine().loadFromMameDat(machine, imperfect)
            if (!filter || filter(rom)) {
                machines << rom
            }
        }
        return machines
    }

}