package org.hs5tb.groospin.base

import org.hs5tb.groospin.common.IOTools
import org.hs5tb.groospin.common.IniTools

/**
 * Created by Alberto on 12-Jun-16.
 */
class HyperSpin {
    File hsRoot
    File rlRoot

    HyperSpin(String hsRoot, String rlRoot) {
        this.hsRoot = new File(hsRoot).canonicalFile
        this.rlRoot = new File(rlRoot).canonicalFile
        println("HyperSpin root: "+hsRoot)
        println("RocketLauncher root: "+rlRoot)
    }

/*

    String delimiter = ";"

    void listAllSystems() {
        listSystems(getSystems())
    }

    void listSystems(List systems) {
        log(["systemName","bytes","human size","db xml games","rom files","media:wheels","media:videos","media:themes","media:artwork1","media:artwork2","media:artwork3","media:artwork4"].join(delimiter))

        systems.each { String systemName ->
            haveSystem(systemName)
            CheckResult checkResult = checkAllGames(systemName)
            log(checkResult.getLongInfo(delimiter))
            endHave()
        }
    }

    void listSystem(String systemName) {
        listSystem(systemName, getGamesFromSystem(systemName))
    }

    void listSystem(String systemName, String game) {
        listSystem(systemName, [game])
    }

    void listSystem(String systemName, List games) {
        log(["systemName","bytes","human size","db xml games","rom files","media:wheels","media:videos","media:themes","media:artwork1","media:artwork2","media:artwork3","media:artwork4"].join(delimiter))
        haveSystem(systemName)
        CheckResult checkResult = check(systemName, games)
        log checkResult.getLongInfo(delimiter)
        endHave()
    }
*/
    RLSystem getSystem(String systemName) {
        File systemEmulatorConfig = new File(rlRoot, "Settings/${systemName}/Emulators.ini")
        if (!systemEmulatorConfig.file) {
            return null
        }
        def map = ['rom_path':null, 'default_emulator':null]
        IniTools.fillMap(systemEmulatorConfig, "ROMS", map)
        String rom_Path = map['rom_path']
        String default_Emulator = map['default_emulator']

        List romPathList = rom_Path?.split("\\|")?.collect { String romPathString -> IOTools.tryRelativeFrom(rlRoot, romPathString) }?:[]

        RLSystem system = new RLSystem(hyperSpin: this, systemName: systemName, iniRomPath : rom_Path,
            iniDefaultEmulator : default_Emulator, defaultEmulator : getEmulator(default_Emulator), romPathsList : romPathList)
        system.loadMapping()
//        debug "$systemName romPathFiles " + rs.emuConfig.rom_Path
        return system
    }

    Map getGlobalEmulatorsConfig() {
        File globalEmulatorConfig = new File(rlRoot, "Settings/Global Emulators.ini")
        IniTools.parseIni(globalEmulatorConfig)
    }

    RLEmulator getEmulator(String name) {
        name = name.toLowerCase().trim()
        Map emulatorConfig = getGlobalEmulatorsConfig()[name]

        String iniEmuPath = emulatorConfig['emu_path']
        String iniRomExtension = emulatorConfig['rom_extension']
        String module = emulatorConfig['module']
        File emuPath = IOTools.tryRelativeFrom(rlRoot, iniEmuPath)

        List romExtensions = iniRomExtension?.split("\\|")?.collect { String ext -> ext.trim().toLowerCase() }?:[]

        RLEmulator emulator = new RLEmulator(name: name, iniEmuPath: iniEmuPath,
                iniRomExtension: iniRomExtension, emuPath: emuPath, romExtensions: romExtensions, module: module)
        return emulator
    }

    List listRomNames(String systemName) {
        File db = new File(hsRoot, "Databases/${systemName}/${systemName}.xml")
        if (!db.exists()) {
            error "${systemName} menu not found in ${db.absolutePath}"
            return []
        }
        def xml = new XmlParser().parseText(db.text)
        return xml.game.collect {
            if (it.@exe == "true") return null
            return it.@name
        }.findAll()
    }

    List listSystemNames() {
        listRomNames("Main menu")
    }

                  /*
    CheckResult checkGame(String systemName, String gameName) {
        CheckResult checkResult = check(systemName, [gameName])
        checkResult.game = gameName
        return checkResult
    }


    CheckResult checkAllGames(String systemName) {
        check(systemName, getGamesFromSystem(systemName))
    }

    */

}









