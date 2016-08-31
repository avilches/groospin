package mame

import groovy.xml.MarkupBuilder
import org.hs5tb.groospin.common.IOTools

/**
 * Created by Alberto on 16-Aug-16.

 i've made an automatic script that use catver.ini (http://www.progettosnaps.net/catver/) to keep all the genre exept thoses that contains :
 Paddle,Electromechanical,Utilities,Game Console,Print Club,Multi-cart Board,Machine,Business Computer,Pocket Computer Games,Terminal Games,Notebook,PDA Games,ComputerPhone Games,Workstation,DVD Reader,SCSI Controller,Barcode Printer,Astrological Computer,Drum Machine,Gambling Board,Audio Sequencer,Portable Media Player,Mobile Phone,Development Computer,Electronic Game,Home Computer,Kit Computer,Matrix Printer,Microcomputer,Punched Card Computer,Single Board Computer,Synthesiser,Training Board,Cash Counter,Clock,Document Processors,Electronic Typewriter,EPROM Programmer,Graphics Display Controller,Network Processor,Printer Handbook,Satellite Receiver,Speech Synthesiser,Word-processing Machine,Handheld Child Computers,Dot-Matrix Display,Test ROM,3D Printer,Graphic Tablet,In Circuit Emulator,DVD Player,Robot Control,Thermal Printer,VTR Control.

 my actual script (mame0.175 full lists) use the command : mame -listxml >mamexml.txt
 then i ' ve made filters for machines from this list and check for each filtered machines the genre from catver.ini (that not contains the system list) then write the xml
 I will try to set different order :
 check for all machine from catver.ini (that not contains the system list) the machine tag from mamexml.txt then write the xml
 i think that on this order there will be more results because of less filters.
 ( but the chd will still not appears because they are not listed on catver.ini )
 I will made a test with the mame 0.175 and check the results.If all is ok i will use this method to next release (mame 0.176)
 My script also use nplayer.ini (http://nplayers.arcadebelgium.be/) so i need to wait for both files to create all lists.

 r0man0
 */

class DatXmlToHyperspinXml {

    static run(String slistname, boolean removeClones, String from, String to, String romfolder) {
        run(slistname, removeClones, from, to, [romfolder])
    }

    static run(String slistname, boolean removeClones, String from, String to, List romfolders) {
        def x = new DatXmlToHyperspinXml()
        def roms = x.parseRoms(from)
        if (removeClones) {
            roms = roms.findAll { !it.cloneof }
        }
        Set files = romfolders?.collect { String romFolder ->
            def files = new File(romFolder).listFiles().collect { IOTools.getFilenameWithoutExtension(it.name) }
            println "[+] Loading ${files.size()} roms from $romFolder"
            return files
        }?.flatten()
        roms = roms.findAll { it.name in files }
        x.export(slistname, roms, to)
    }

    List<Rom> parseRoms(String from, boolean imperfect = true, Closure filter = null) {
        XmlParser parser = new XmlParser()
        parser.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
        parser.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
        Node mame = parser.parse(new File(from))

        List<Rom> result = []
        mame.machine.each { machine ->
            if (machine.@isbios == "yes" || machine.@isdevice == "yes" || machine.@runnable == "no") {
                println "Skipping non-game ${machine.@isbios == "yes" ? "B" : "."}${machine.@isdevice ? "D" : "."}${machine.@runnable == "no" ? "N" : "."} ${machine.@name}: ${machine.description.text()}"
                return
            }
            result << new Rom(
                    name: machine.@name,
                    cloneof: machine.@cloneof,
                    description: machine.description.text(),
                    manufacturer: machine.manufacturer.text(),
                    year: machine.year.text(),
                    players: machine.input[0].@players as int,
                    ok: machine.driver[0].@status == "good" || (imperfect && machine.driver[0].@status == "imperfect"),
                    controls: machine.input[0].control.@type
            ).load()
        }
        return result
    }
    void export(String slistname, List<Rom>roms, String to) {
        StringBuilder letters = new StringBuilder()
        new MarkupBuilder(new FileWriter(new File(to))).menu {
            setOmitEmptyAttributes(true)
            header {
                listname(slistname)
                // listversion(mame.@build)
            }
            roms.each { machine ->
                String image = indexImage(machine.name.toUpperCase().charAt(0))
                if (letters.contains(image)) {
                    image = null // Si ya estaba la letra, no la usamos
                } else {
                    letters.append(image) // la marcamos como añadida para la proxima
                }
                println "[+] Adding ${machine.name}"

                game(name: machine.name, index: image ? "true" : "", image: image?.toLowerCase()) {
                    description(machine.description)
                    manufacturer(machine.manufacturer)
                    year(machine.year)
                    if (machine.cloneof) {
                        cloneof(machine.cloneof)
                    }
                }
            }
        }
    }

    private String indexImage(char letter) {
        String image = letter
        if (letter < (char) '0') {
            image = '0'
        } else if (letter > ((char) '9') && letter < ((char) 'A')) {
            image = '9'
        } else if (letter > ((char) 'Z')) {
            image = 'Z'
        }
        return image
    }
}

class Rom {
    String name
    String cloneof
    String description
    String manufacturer
    String year
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

    List controls

    Rom load() {
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
        return this
    }

    @Override
    String toString() {
        "${name}:\"${description}\" ${manufacturer} (${year})"
    }
}
