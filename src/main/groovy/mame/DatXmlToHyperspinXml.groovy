package mame

import groovy.xml.MarkupBuilder
import org.hs5tb.groospin.common.IOTools

new Transform().run("HBMAME",
        true,
        "d:\\Games\\Roms\\HBMAME\\0175\\dat.xml",
        "d:\\Games\\HyperSpin-fe\\Databases\\HBMAME\\HBMAME.xml",
        "d:\\Games\\Roms\\HBMAME\\0175\\roms\\")

/**
 * Created by Alberto on 16-Aug-16.
 */
class Transform {
    void run(String slistname, boolean removeClones, String from, String to, String romfolder) {
        run(slistname, removeClones, from, to, [romfolder])
    }
    void run(String slistname, boolean removeClones, String from, String to, List romfolder = null) {

        Set roms = romfolder?.collect {
            new File(it).listFiles().collect { IOTools.getFilenameWithoutExtension(it.name) }
        }?.flatten()

        XmlParser parser = new XmlParser()
        parser.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
        parser.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
        Node mame = parser.parse(new File(from))
        int total = 0
        int processed = 0
        int ignored = 0
        int missing = 0
        StringBuilder letters = new StringBuilder()
        new MarkupBuilder(new FileWriter(new File(to))).menu {
            setOmitEmptyAttributes(true)
            header {
                listname(slistname)
                listversion(mame.@build)
            }
            mame.machine.each { machine ->
                total ++
                if (removeClones && machine.@cloneof) {
                    ignored ++
                    return
                }
                if (roms != null && !roms.contains(machine.@name)) {
                    missing ++
                    return
                }
                processed ++
                char letter = machine.@name.toUpperCase().charAt(0)
                String image = letter
                if (letter < (char)'0') {
                    image = '0'
                } else if (letter > ((char)'9') && letter < ((char)'A')) {
                    image = '9'
                } else if (letter > ((char)'Z')) {
                    image = 'Z'
                }
                if (letters.contains(image)) {
                    image = ""
                } else {
                    letters.append(image)
                }

                game(name:machine.@name, index:image?"true":"", image:image?.toLowerCase()) {
                    description(machine.description.text())
                    manufacturer(machine.manufacturer.text())
                    year(machine.year.text())
                    if (machine.@cloneof) {
                        cloneof(machine.@cloneof)
                    }
                }
                roms?.remove(machine.@name)
            }
        }
        println "Total: ${total} | Processed: ${processed} | Ignored: ${ignored} | Missing: ${missing} | Not used: ${roms?.size()?:0} ${roms?roms.join(", "):""}"
    }

}
