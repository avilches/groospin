package examples.old

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLEmulator
import org.hs5tb.groospin.base.RLSystem

HyperSpin hs = new HyperSpin("D:/Games/RocketLauncher")

File emus = new File("D:/Games/Emuladores.html")

StringBuffer txt = new StringBuffer()
txt << """<html><head>
<style>
* { font-family: Arial; }
h3 { margin:15pt 0 3pt; }
</style>
</head><body><div id="data">
"""

hs.listSystems().findAll { RLSystem system ->
    !system.romsAreExecutable() && system.defaultEmulator?.emuPath
}.groupBy() { RLSystem system ->
    system.defaultEmulator
}.each { RLEmulator emulator, List<RLSystem> systems ->
    txt << "<h3>${emulator.name}</h3>"
    println emulator.name
    txt << """Modulo: ${emulator.module} - Sistemas: ${systems*.name.join(", ")} <ul>
<li>${emulator.emuPath.parentFile} <a href='file:///${emulator.emuPath.parent}'>Abrir carpeta</a></li>
<li>${emulator.emuPath.name} <a href='file:///${emulator.emuPath}'>Ejecutar emulador</a></li>
</ul>
"""
}
txt << """
</div>
<script>
var isIE = /*@cc_on!@*/false || !!document.documentMode;
if (!isIE) { document.getElementById("data").innerHTML = "<center><h1 style='color:red'><br/>No est&aacute;s usando Internet Explorer</h1><p>Debes usar Internet Explorer para que los enlaces te abran las carpetas y se ejecuten los emuladores!</p></center>"; }
</script>
</body></html>"""

emus.text = txt
