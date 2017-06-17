package examples.generator

import org.hs5tb.groospin.base.HyperSpinDatabase
import org.hs5tb.groospin.base.Rom
import org.hs5tb.groospin.common.IOTools

def romNames = new File("d:\\Games\\HyperSpin-fe\\Media\\Vintage Commercials\\Video").listFiles(new FileFilter() {
    boolean accept(File pathname) {
        return pathname.file
    }
}).collect { IOTools.getFilenameWithoutExtension(it)}

def fileName = "d:/Games/HyperSpin-fe/Databases/Vintage Commercials/Vintage Commercials.xml"


new HyperSpinDatabase().addOrOverwrite(romNames.collect { new Rom(name: it, description: it)}).export(new File(fileName))