package org.hs5tb.groospin.base

import groovy.xml.MarkupBuilder
import org.hs5tb.groospin.common.StringHelper

/**
 * Created by Alberto on 01-Sep-16.
 */
class HyperSpinDatabase {

    String listname
    String lastlistupdate
    String listversion
    String exporterversion
    List<Rom> roms
    Map<String, List<Rom>> romsByGenre
    File db

    HyperSpinDatabase load(File db, Closure filter = null) {
        this.db = db
        Node menu = new XmlParser().parse(db.newReader())
        listname = menu.header.listname.text()
        lastlistupdate = menu.header.lastlistupdate.text()
        listversion = menu.header.listversion.text()
        exporterversion = menu.header.exporterversion.text()

        roms = loadRoms(menu, filter)
        return this
    }

    HyperSpinDatabase loadGenres() {
        if (db) {
            romsByGenre = loadGenres(db.parentFile)
        }
        return this
    }

    HyperSpinDatabase splitAndWriteGenres(String folder, Map headers = this.properties) {
        splitAndWriteGenres(folder, { Rom rom -> rom.genre }, headers)
        return this
    }

    HyperSpinDatabase splitAndWriteGenres(String folder, Closure condition, Map headers = this.properties) {
        writeGenres(roms.groupBy(condition), new File(folder), headers)
        return this
    }

    HyperSpinDatabase export(File file = db, Map headers = this.properties) {
        write(roms, file, headers)
        return this
    }

    static void writeGenres(Map<String, List<Rom>> romsByGenre, String folder, Map headers = [:]) {
        writeGenres(romsByGenre, new File(folder), headers)
    }

    static Closure<String> cleanGenre = { String genre ->
        genre = genre?.trim()?:"" // No nulls or empty strings
        if (genre.size() <= 1) { // No one letter genres
            genre = "Unknown"
        }
        genre = genre.replaceAll("[/\\\\]", "-"). // remove slashes to avoid problems saving file
                split("\\b"). // split in words
                collect { it.toLowerCase().capitalize() }.join("") // capitalize

        return genre
    }

    void validateGenres() {
        if (romsByGenre == null) {
            println "Validating: ${db.parent}/Genre.xml not found."
            return
        } else {
            println "Validating: ${db.parent}/Genre.xml..."
        }
        Set<String> allRoms = roms.name as Set
        romsByGenre.each { String genre, List<Rom> roms ->
            if (roms == null) {
                println "Error: genre ${genre}.xml is not found"
                return
            }
            Set<String> genreRoms = roms.name as Set
            Set<String> unknownGenreRoms = (genreRoms - allRoms)
            if (unknownGenreRoms) {
                println "Error: genre ${genre} use these unknown roms: ${unknownGenreRoms}"
            }
        }
        Set<String> allGenreRoms = romsByGenre.values().name.flatten() as Set
        Set<String> romsWithoutGenre = allRoms - allGenreRoms
        if (romsWithoutGenre) {
            println "Warning: these roms are not in any genre: ${romsWithoutGenre}"
        }
    }

    static void writeGenres(Map<String, List<Rom>> romsByGenre, File folder, Map headers = [:]) {
        Map<String, List<Rom>> romsByGenreClean = new LinkedHashMap<>()

        romsByGenre.each { Map.Entry<String, List<Rom>> entry ->
            if (entry.value) {
                // Clean the empty genres, if any
                String genreGood = cleanGenre.call(entry.key)
                if (romsByGenreClean[genreGood]) {
                    romsByGenreClean[genreGood].addAll(entry.value)
                } else {
                    romsByGenreClean[genreGood] = entry.value
                }
            }
        }
        if (!romsByGenreClean || romsByGenreClean.size() == 1) {
            // Only write genres if we have more then one...
            return
        }
        StringBuffer genreContent = new StringBuffer()
        romsByGenreClean.keySet().sort().each { String genre ->
            genreContent << "  <game name=\"${StringHelper.escapeXmlValue(genre, true, true)}\"/>\n"
        }
        if (!folder.exists()) folder.mkdirs()
        new File(folder, "Genre.xml").text = "<menu>\n${genreContent}</menu>"

        romsByGenreClean.each { String genre, List<Rom> genreRoms ->
            write(genreRoms, new File(folder, "${StringHelper.unescapeHTML(genre)}.xml"), headers)
        }
    }

    static HyperSpinDatabase rewriteDatabase(File db, File dst = null, Closure genreCondition = null) {
        HyperSpinDatabase hyperSpinDatabase = new HyperSpinDatabase()
        hyperSpinDatabase.load(db)
        rewriteDatabase(hyperSpinDatabase, dst, genreCondition)
        return hyperSpinDatabase
    }

    static void rewriteDatabase(HyperSpinDatabase hyperSpinDatabase, File dst = null, Closure genreCondition = null) {
        if (!dst) dst = hyperSpinDatabase.db.parentFile
        hyperSpinDatabase.export(new File(dst, hyperSpinDatabase.db.name))
        if (genreCondition) {
            hyperSpinDatabase.splitAndWriteGenres(dst as String, genreCondition)
        }
    }

    static List<String> listRomNames(File db) {
        new XmlParser().parse(db).game.collect { it.@name }
    }

    static List<Rom> loadRoms(File db, Closure filter = null) {
        Node menu = new XmlParser().parse(db)
        return loadRoms(menu, filter)
    }

    static List<Rom> loadRoms(Node menu, Closure filter = null) {
        List<Rom> roms = []
        menu.game.each { Node node ->
            Rom rom = new Rom().loadFromHyperSpinDatabase(node)
            if (!filter || filter(rom)) {
                roms << rom
            }
        }
        return roms
    }

    static void write(List<Rom> roms, String file, Map header = [:]) {
        write(roms, new File(file).newWriter(), header)
    }

    static void write(List<Rom> roms, File file, Map header = [:]) {
        file.parentFile.mkdirs()
        write(roms, file.newWriter(), header)
    }

    static Map<String, List<Rom>> loadGenres(File folder) {
        File mainGenreFile = new File(folder, "Genre.xml")
        if (!mainGenreFile.exists()) return null
        Map<String, List<Rom>> romsByGenre = new LinkedHashMap<>()
        listRomNames(mainGenreFile).each { String genre ->
            File genreFile = new File(folder, "${genre}.xml")
            romsByGenre[genre] = genreFile.exists() ? loadRoms(genreFile) : null
        }
        return romsByGenre
    }

    static void write(List<Rom> roms, Writer writer, Map headers = [:]) {
        StringBuilder lettersAlreadyUsed = new StringBuilder()
        MarkupBuilder mb = new MarkupBuilder(writer)
        mb.mkp.xmlDeclaration(version: "1.0")
        mb.menu {
            setOmitNullAttributes(true)
            setDoubleQuotes(true)
            setEscapeAttributes(true)
            header {
                if (headers.listname) listname(headers.listname)
                if (headers.listversion) listversion(headers.listversion)
                if (headers.lastlistupdate) lastlistupdate(headers.lastlistupdate)
                if (headers.exporterversion) exporterversion(headers.exporterversion)
            }
            roms.sort { it.description }.each { Rom rom ->
                String image = getLetter(lettersAlreadyUsed, rom)
                game(name: rom.name, index: image ? "true" : "", image: image?.toLowerCase() ?: "") {
                    description(rom.description)
                    cloneof(rom.cloneof) // clone of is mandatory, even if it's empty! HS fails if you use parents_only=true and there is no <cloneof> tag!
                    if (rom.crc) crc(rom.crc)
                    if (rom.manufacturer) manufacturer(rom.manufacturer)
                    if (rom.year) year(rom.year)
                    if (rom.genre) genre(rom.genre)
                    if (rom.rating) rating(rom.rating)
                    if (rom.enabled) enabled(rom.enabled)
                    if (rom.exe) exe(rom.exe)
                    rom.exportAdditional(delegate)
                }
            }
        }
    }

    void addOnlyNews(List<Rom> toAdd) {
        RomListMerger.addOnlyNews(roms, toAdd)
    }

    void appendAll(List<Rom> toAdd) {
        RomListMerger.appendAll(roms, toAdd)
    }

    void addOrOverwrite(List<Rom> toAdd) {
        RomListMerger.addOrOverwrite(roms, toAdd)
    }

    static class RomListMerger {
        static void addOnlyNews(List<Rom> roms, List<Rom> toAdd) {
            Set<String> avoidTheseRomNames = roms*.name
            toAdd.each { Rom rom ->
                if (!(rom.name in avoidTheseRomNames)) {
                    roms << rom
                }
            }
        }

        static void appendAll(List<Rom> roms, List<Rom> toAdd) {
            roms.addAll(toAdd)
        }

        static void addOrOverwrite(List<Rom> roms, List<Rom> toAdd) {
            Set<String> overwrite = roms*.name.intersect(toAdd*.name)
            roms.removeAll { Rom rom -> rom.name in overwrite }
            appendAll(roms, toAdd)
        }
    }



    private static String getLetter(StringBuilder lettersAlreadyUsed, Rom rom) {
        String image
        if (!rom.cloneof) {
            // Only the parents have letter because if you use the parents_only=true flag and
            // choose a letter which belongs to a clone, HS will jump to the beginning
            image = fixLetter(rom.description.toUpperCase().charAt(0))
            if (lettersAlreadyUsed.contains(image)) {
                image = null // Si ya estaba la letra, no la usamos
            } else {
                lettersAlreadyUsed << image // la marcamos como añadida para la proxima
            }
        }
        return image
    }

    static private String fixLetter(char letter) {
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
