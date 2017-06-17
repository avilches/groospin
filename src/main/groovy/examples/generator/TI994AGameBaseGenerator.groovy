package examples.generator

import groovy.io.FileType
import org.hs5tb.groospin.base.HyperSpinDatabase
import org.hs5tb.groospin.base.Rom
import org.hs5tb.groospin.common.IOTools

String gamebase = "d:\\Games\\Roms\\Texas Instruments TI 99-4A\\gamebase"
String romFolder = "d:\\Games\\Roms\\Texas Instruments TI 99-4A"
String database = "d:\\Games\\HyperSpin-fe\\Databases\\Texas Instruments TI 99-4A\\Texas Instruments TI 99-4A.xml"

def filenames = new File(gamebase).listFiles().collect { File rpk ->
    return rpk.name.toLowerCase().endsWith("rpk")?IOTools.getFilenameWithoutExtension(rpk.name):null
}.findAll() as Set


def roms = []
new File(gamebase).eachFile(FileType.DIRECTORIES) { File fold ->
    File rpk = new File(fold.parentFile, fold.name+".rpk")
    File meta = new File(fold, "meta-inf.xml")
    if (!meta.file || !rpk.file) {
        return
    }
    if (meta.file && rpk.file) {
        String romName = fold.name
        filenames.remove(romName)
        XmlParser parser = new XmlParser()
        Node node = parser.parse(meta)
        String desc = node.name.text()
        String year = node.year.text()
        String dist = node.dist.text()
        desc = desc.replaceAll(":","-").replaceAll("\\\\","-").replaceAll("/","-")
        roms << new Rom(name:desc, description: desc, year: year, manufacturer: dist)
        try {
            IOTools.copy(rpk, new File(romFolder, "${desc}.rpk"))
        } catch(e) {
            println e
        }
    }
}

filenames.each {
    String desc = it.replaceAll("_"," ").split(' ').collect{ it.toLowerCase().capitalize() }.join(' ')
    roms << new Rom(name:desc, description: desc)
    IOTools.copy(new File(gamebase,"${it}.rpk"), new File(romFolder, "${desc}.rpk"))

}

HyperSpinDatabase.write(roms, database)
