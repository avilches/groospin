import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.HyperSpinDatabase
import org.hs5tb.groospin.base.Rom


HyperSpinDatabase mainDb = new HyperSpinDatabase().load(
        new File("d:\\Games\\HyperSpin-fe\\Databases\\Atari 5200\\Atari 5200.xml")).loadGenres()

HyperSpinDatabase other = new HyperSpinDatabase().load(
        new File("m:\\Arcade\\Databases\\Atari 5200\\\\Atari 5200.xml"))


addOnlyNews(mainDb.roms, other.roms)
mainDb.export()


void addOnlyNews(List<Rom> roms, List<Rom> toAdd) {
    Set<String> avoidTheseRomNames = roms*.name
    toAdd.each { Rom rom ->
        if (!(rom.name in avoidTheseRomNames)) {
            roms << rom
        }
    }
}

void appendAll(List<Rom> roms, List<Rom> toAdd) {
    roms.addAll(toAdd)
}

void addOrOverwrite(List<Rom> roms, List<Rom> toAdd) {
    Set<String> overwrite = roms*.name.intersect(toAdd*.name)
    roms.removeAll { Rom rom -> rom.name in overwrite}
    appendAll(roms, toAdd)
}

