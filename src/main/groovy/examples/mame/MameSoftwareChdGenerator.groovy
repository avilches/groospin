package examples.mame

import org.hs5tb.groospin.base.HyperSpinDatabase
import org.hs5tb.groospin.base.MameMachine
import org.hs5tb.groospin.base.Rom
import org.hs5tb.groospin.common.IOTools

int x = 0
def roms = []
new File("c:/mame/cdimono1.xml").withInputStream {
    List soft = MameMachine.loadSoftware(it)
    soft.each { MameMachine.Software software ->
        File chd = new File("b:\\incoming\\torrent\\MAME 0.180 Software List CHDs\\cdi\\${software.name}\\${software.disks[0]}.chd")
        String cleanName = software.description
        if (cleanName.contains("[")) {
//            cleanName = cleanName.substring(0, cleanName.indexOf("["))

        }
        cleanName = cleanName.replaceAll("/","-")
        File f = new File("d:\\Games\\Isos\\Philips CD-i", cleanName+".chd")
        if (chd.exists() && !f.exists()) {
            x++
            println "copy ${chd} to ${f}"
            try {
                IOTools.copy(chd, f)
            } catch (e) {
                e.printStackTrace()
            }
        }
        if (f.exists()) {
            roms << new Rom(name: software.description,
                    description: software.description,
                    cloneof: software.cloneof,
                    manufacturer: software.publisher, year: software.year)

        }

    }
}
//HyperSpinDatabase.write(roms, "d://games//HyperSpin-fe/Databases/Philips CD-i/Philips CD-i.xml")
//println x