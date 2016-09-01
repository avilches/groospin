package org.hs5tb.groospin.base

import groovy.xml.MarkupBuilder

/**
 * Created by Alberto on 01-Sep-16.
 */
class HyperSpinDatabase {

    String listname
    String lastlistupdate
    String listversion
    String exporterversion
    List<Rom> roms

    HyperSpinDatabase load(File db, Closure filter = null) {
        Node menu = new XmlParser().parse(db.newReader())
        roms = loadRoms(menu, filter)
        listname = menu.listname.text()
        lastlistupdate = menu.lastlistupdate.text()
        listversion = menu.listversion.text()
        exporterversion = menu.exporterversion.text()
    }

    void export(File file) {
        write(roms, file, properties)
    }

    static List<String> listRomNames(File db) {
        new XmlParser().parse(db).game.collect { it.@name }
    }

    static List<Rom> loadRoms(File db, Closure filter = null) {
        Node menu = new XmlParser().parseText(db.text)
        return loadRoms(menu)
    }

    static List<Rom> loadRoms(Node menu, Closure filter = null) {
        List<Rom> roms = []
        menu.game.each { Node node ->
            Rom rom = new Rom().loadFromHyperspinDatabase(node)
            if (!filter || filter(rom)) {
                roms << rom
            }
        }
        return roms
    }

    static void write(List<Rom> roms, File file, Map header = [:]) {
        write(roms, file.newWriter(), header)
    }

    static void write(List<Rom> roms, Writer writer, Map headers = [:]) {
        StringBuilder lettersAlreadyUsed = new StringBuilder()
        new MarkupBuilder(writer).menu {
            setOmitEmptyAttributes(true)
            header {
                if (headers.listname) listname(headers.listname)
                if (headers.listversion) listversion(headers.listversion)
                if (headers.lastlistupdate) lastlistupdate(headers.lastlistupdate)
                if (headers.exporterversion) exporterversion(headers.exporterversion)
            }
            roms.each { Rom rom ->
                String image = indexImage(rom.name.toUpperCase().charAt(0))
                if (lettersAlreadyUsed.contains(image)) {
                    image = null // Si ya estaba la letra, no la usamos
                } else {
                    lettersAlreadyUsed.append(image) // la marcamos como añadida para la proxima
                }
                println "[+] Adding ${rom.name}"

                game(name: rom.name, index: image ? "true" : "", image: image?.toLowerCase()) {
                    description(rom.description)
                    if (rom.cloneof) cloneof(rom.cloneof)
                    if (rom.manufacturer) manufacturer(rom.manufacturer)
                    if (rom.year) year(rom.year)
                    if (rom.genre) genre(rom.genre)
                    if (rom.rating) rating(rom.rating)
                    if (rom.enabled) enabled(rom.enabled)
                    if (rom.exe) exe(rom.exe)
                }
            }
        }
    }

    static private String indexImage(char letter) {
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
