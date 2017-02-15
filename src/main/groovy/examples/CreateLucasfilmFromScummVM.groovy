package examples

import operation.DatabaseOperations
import operation.Operations
import org.hs5tb.groospin.checker.handlers.XmlRom
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.common.Ini
import org.hs5tb.groospin.common.IniFile


Ini ini = new IniFile().parse(new File("d:\\Games\\Emulators\\ScummVM\\scummvm-lucasfilm.ini"))
String romNames = ini.getSections().keySet()

List systems = ["ScummVM"]

def operations = new DatabaseOperations("D:/Games/RocketLauncher")
operations.simulation = false
operations.removeFromDatabase("-backup", [{ CheckRomResult checkRomResult, XmlRom romNode ->
    !romNames.contains(checkRomResult.rom.name) }], systems)


