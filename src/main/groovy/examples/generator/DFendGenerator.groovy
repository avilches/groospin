package examples.generator

import org.hs5tb.groospin.base.HyperSpinDatabase
import org.hs5tb.groospin.base.Rom
import org.hs5tb.groospin.common.Ini
import org.hs5tb.groospin.common.IniFile


def proofRoot = "d:\\Games\\Emulators\\Dfend\\D-Fend Reloaded v1.2.1\\Confs\\"

def romNames = findAndValidateRomNamesFromProofRoot(proofRoot)
println romNames.join(",")

HyperSpinDatabase.write(romNames.collect { new Rom(name: it, description: it)}, "d:\\Games\\HyperSpin-fe\\Databases\\Microsoft MS-DOS\\Microsoft MS-DOS.xml")


def findAndValidateRomNamesFromProofRoot(def proofRoot) {
    return new File(proofRoot).listFiles().collect {
        if (!it.name.endsWith(".prof")) return null
        Ini conf = new IniFile().parse(it)
        String exePath = conf.get("Extra", "Exe")
        File exe = new File(exePath)
        if (!exe.file) {
            println "Error en ${it}. Exe no encontrado : ${exePath}"
            return null
        }

        String setupPath = conf.get("Extra", "Setup")
        if (setupPath) {
            File setup = new File(exePath)
            if (!setup.file) {
                println "Error en ${it}. Setup no encontrado : ${setupPath}"
                return null
            }
        }
        return it.name - ".prof"
    }.findAll()

}