import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.base.Rom

HyperSpin hs = new HyperSpin(
        "D:/Games/HyperSpin-fe",
        "D:/Games/RocketLauncher")

File emus = new File("D:/Games/roms.html")

StringBuffer txt = new StringBuffer()
int all = 0
hs.listSystems().each { RLSystem system ->
    println system.name
    StringBuffer romSet = new StringBuffer()
    int roms = 0
    system.listRoms().each { Rom rom ->
        File romFound = system.findValidRom(rom.name)
        roms++
        all++
        if (romFound) {
            romSet << "<li>${rom.description}</li>\n"
        } else {
            romSet << "<li><strong>${rom.description}</strong><br/>rom no encontrada </li>\n"
        }
    }
    txt << "<h3>${system.name}</h3>${roms} roms <ol>${romSet}</ol>"
}
emus.text = """
<html><head>
<style>
* { font-family: Arial; }
h3 { margin:15pt 0 3pt; }
</style>
</head><body><div id="data">
${all} roms total
${txt}
</div>
</body></html>"""
