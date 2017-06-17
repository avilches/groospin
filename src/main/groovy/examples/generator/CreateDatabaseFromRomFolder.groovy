package examples.generator

import org.hs5tb.groospin.base.HyperSpinDatabase
import org.hs5tb.groospin.base.Rom
import org.hs5tb.groospin.common.IOTools

def folder = "d:\\Games\\Isos\\Sega Saturn Japan"
def database = "d:\\Games\\HyperSpin-fe\\Databases\\Sega Saturn Japan\\Sega Saturn Japan.xml"

def roms = new File(folder).listFiles().collect {
    it.file ? IOTools.getFilenameWithoutExtension(it) : null
}.findAll().collect {
    new Rom(name: it, description: it)
}

HyperSpinDatabase.write(roms, database)
