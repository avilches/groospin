package org.hs5tb.groospin.base

/**
 * Created by Alberto on 12-Jun-16.
 */
class HyperSpin extends Report {
    File hsRoot
    File rlRoot

    String delimiter = ";"

    HyperSpin(String hsRoot, String rlRoot) {
        this.hsRoot = new File(hsRoot).canonicalFile
        this.rlRoot = new File(rlRoot).canonicalFile
        info("HyperSpin root: "+hsRoot)
        info("RocketLauncher root: "+rlRoot)
    }

    HyperSpin(String hsRoot, String rlRoot, String reportRoot) {
        super(new File(reportRoot).canonicalFile)
        this.hsRoot = new File(hsRoot).canonicalFile
        this.rlRoot = new File(rlRoot).canonicalFile
        info("HyperSpin root: "+hsRoot)
        info("RocketLauncher root: "+rlRoot)
    }

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

    EmuConfig getEmuConfig(String systemName) {
        File systemEmulatorConfig = new File(rlRoot, "Settings/${systemName}/Emulators.ini")
        if (!systemEmulatorConfig.file) {
            error "${systemEmulatorConfig} not found! no emulator detected for systemName ${systemName}"
            return null
        }
        def map = ['Rom_Path':null, 'Default_Emulator':null]
        IniTools.fillMap(systemEmulatorConfig, "ROMS", map)
        String romPaths = map['Rom_Path']
        String emulator = map['Default_Emulator']

        EmuConfig emuConfig = new EmuConfig(emulator: emulator)

        List romPathList = romPaths.split("\\|").collect { String romPathString ->
            romPathString = romPathString.replaceAll("\\\\", "/")
            romPathString.startsWith(".") ? new File(rlRoot, romPathString).canonicalFile : new File(romPathString)
        }
        emuConfig.romPaths = romPathList
        return emuConfig
    }



    List getGamesFromSystem(String systemName) {
        File db = new File(hsRoot, "Databases/${systemName}/${systemName}.xml")
        if (!db.exists()) {
            error "${systemName} menu not found in ${db.absolutePath}"
            return []
        }
        def xml = new XmlParser().parseText(db.text)
        return xml.game.collect {
            if (it.@exe=="true") return null
            return it.@name
        }.findAll()
    }

    List getSystems() {
        getGamesFromSystem("Main menu")
    }

    System getSystem(String systemName) {
        System rs = new System(hyperSpin: this, systemName: systemName)
        rs.emuConfig = getEmuConfig(systemName)
        rs.calculateRomPathSizeAndloadRomNames()
        rs.loadExes()
        debug "$systemName romPathFiles " + rs.emuConfig.romPaths
        return rs
    }

    CheckResult checkGame(String systemName, String gameName) {
        CheckResult checkResult = check(systemName, [gameName])
        checkResult.game = gameName
        return checkResult
    }


    CheckResult checkAllGames(String systemName) {
        check(systemName, getGamesFromSystem(systemName))
    }

    private CheckResult check(String systemName, List games) {
        System system = getSystem(systemName)
        // println romFilenames
        CheckResult checkResult = new CheckResult(systemName: systemName, games: games.size(), totalSize: system.totalSize)
        games.sort().each { String game ->

            boolean romFound = system.containsRom(game)
            if (system.mustHaveExe()) {
                romFound = romFound && system.haveExe(game)
            }

            haveRom(game)

            checkResult.roms += romFound ? 1 : 0
            checkResult.wheels += existsInMedia("${systemName}/Images/Wheel/${game}", ["jpg", "png"]) ? 1 : 0
            checkResult.videos += existsInMedia("${systemName}/Video/${game}", ["mp4", "flv"]) ? 1 : 0
            checkResult.themes += existsInMedia("${systemName}/Themes/${game}", ["zip"]) ? 1 : 0
            checkResult.artwork1 += existsInMedia("${systemName}/Images/Artwork1/${game}", ["jpg", "png"]) ? 1 : 0
            checkResult.artwork2 += existsInMedia("${systemName}/Images/Artwork2/${game}", ["jpg", "png"]) ? 1 : 0
            checkResult.artwork3 += existsInMedia("${systemName}/Images/Artwork3/${game}", ["jpg", "png"]) ? 1 : 0
            checkResult.artwork4 += existsInMedia("${systemName}/Images/Artwork4/${game}", ["jpg", "png"]) ? 1 : 0

            if (!romFound) {
                warn "$systemName Missing rom: ${game}"
            }
        }
        return checkResult
    }

    boolean existsInMedia(String path, List extensions) {
        return exists(new File(hsRoot, "Media/${path}"), extensions)
    }

    boolean exists(File f, List extensions) {
        return extensions.any { String extension ->
            File file = new File(f.absolutePath + "." + extension)
            //        println "Checking for ${file}"
            file.exists()
        }
    }

}









