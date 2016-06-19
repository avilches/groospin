import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.checker.Checker

HyperSpin hs = new HyperSpin(
        "D:/Games/HyperSpin-fe",
        "D:/Games/RocketLauncher")
        //)

Checker checker = new Checker(hs, "D:/Games/Soft/GrooSpin/report")

//hs.listAllSystems()
//hs.listSystems(["Daphne", "Funtech Super Acan"])
//hs.listSystems(["PopCap", "Nintendo Game and Watch", "Cave", "Doujin Soft"])
// println checker.check("PC Games").getLongInfo(";")


StringBuffer txt = new StringBuffer()
txt << """<html><head>
<style>
* { font-family: Arial; }
h3 { margin:15pt 0 3pt; }
</style>
</head><body><div id="data">
"""

hs.listSystem().each { RLSystem system ->
    txt << "<h3>${system.name}</h3>"
    if (system.needsExecutable()) {
        txt << "${system.defaultEmulator.name} (Modulo: ${system.defaultEmulator.module})<br/><ul>"
        system.listRomNames().each { String game ->
            File romFound = system.findValidRom(game)
            if (romFound) {
                File exe = system.findExecutable(game, romFound)
                if (exe) {
                    txt << "<li><strong>${game}</strong><br/>${exe.parent} <a href='file:///${exe.parent}'>abrir carpeta</a></li>\n"
                    txt << "${exe} ${exe.name.toLowerCase().endsWith(".bat")?"(debe abrir la carpeta y lanzarlo a mano)":"<a href='file:///${exe}'>ejecutar</a>"}"
                } else {
                    txt << "<li><strong>${game}</strong><br/>rom ok, pero falta ejecutable!</li>\n"
                }
            } else {
                txt << "<li><strong>${game}</strong><br/>rom no encontrada </li>\n"
            }
        }
        txt << "</ul>"
    } else  {
        txt << """${system.defaultEmulator.name} (Modulo: ${system.defaultEmulator.module})<ul>
<li>${system.defaultEmulator.emuPath.parentFile} <a href='file:///${system.defaultEmulator.emuPath.parent}'>abrir carpeta</a></li>
<li>${system.defaultEmulator.emuPath} <a href='file:///${system.defaultEmulator.emuPath}'>ejecutar</a></li>
</ul>
"""
    }
}
txt << """
</div>
<script>
var isIE = /*@cc_on!@*/false || !!document.documentMode;
if (!isIE) { document.getElementById("data").innerHTML = "<center><h1 style='color:red'><br/>No est&aacute;s usando Internet Explorer</h1><p>Debes usar Internet Explorer para que los enlaces te abran las carpetas y se ejecuten los emuladores!</p></center>"; }
</script>
</body></html>"""

File emus = new File("D:/Games/systems.html")
emus.text = txt