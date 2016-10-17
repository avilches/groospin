package operation

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.Checker
import org.hs5tb.groospin.checker.handlers.DatabaseTransformer
import org.hs5tb.groospin.checker.handlers.HumanInfo
import org.hs5tb.groospin.checker.handlers.RomDatabase
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult
import org.hs5tb.groospin.common.IOTools

/**
 * Created by Alberto on 01-Sep-16.
 */
class Operations {
    HyperSpin hs

    Operations(HyperSpin hs) {
        this.hs = hs
    }

    static Closure IS_CLONE = { CheckRomResult checkRomResult, RomDatabase romNode -> romNode.cloneof }
    static Closure MISSING = { CheckRomResult checkRomResult, RomDatabase romNode -> !checkRomResult.rom.exeFileFound }
    static Closure EXISTS = { CheckRomResult checkRomResult, RomDatabase romNode -> checkRomResult.rom.exeFileFound }

    static Closure NO_VIDEO = { CheckRomResult checkRomResult, RomDatabase romNode ->
        !checkRomResult.videos
    }
    static Closure WITH_VIDEO = { CheckRomResult checkRomResult, RomDatabase romNode -> checkRomResult.videos }

    static Closure NO_WHEEL = { CheckRomResult checkRomResult, RomDatabase romNode ->
        !checkRomResult.wheels
    }
    static Closure WITH_WHEEL = { CheckRomResult checkRomResult, RomDatabase romNode -> checkRomResult.wheels }

    static Closure NO_THEME = { CheckRomResult checkRomResult, RomDatabase romNode -> !checkRomResult.themes }
    static Closure WITH_THEME = { CheckRomResult checkRomResult, RomDatabase romNode -> checkRomResult.themes }

    private boolean none(List<Closure> conditions, CheckRomResult checkRomResult, RomDatabase romNode = null) {
        !any(conditions, checkRomResult, romNode)
    }

    private boolean any(List<Closure> conditions, CheckRomResult checkRomResult, RomDatabase romNode = null) {
        return conditions.any { Closure condition ->
            if (condition.maximumNumberOfParameters == 2) {
                return condition.call(checkRomResult, romNode)
            } else if (condition.maximumNumberOfParameters == 1) {
                return condition.call(checkRomResult)
            } else {
                throw new IllegalArgumentException("Condition must have one or two parameters")
            }
        }
    }

    void extractFromDatabase(String newFileSuffix, List<Closure> conditions, List systems = null) {
        new Checker(hs).
                addHandler(new HumanInfo(false)).
                addHandler(new DatabaseTransformer() {
                    @Override
                    void romNodeChecked(CheckRomResult checkRomResult, RomDatabase romNode) {
                        // elimina los juegos que no cumplen ninguna condicion para dejar en
                        // la base de datos que se va a salvar con el nuevo nombre los que si la cumplen
                        if (none(conditions, checkRomResult, romNode)) {
                            romNode.remove()
                        }
                    }

                    @Override
                    void endDatabaseUpdate(CheckTotalResult checkResult) {
                        saveDatabaseTo("${checkResult.systemName}${newFileSuffix}.xml")
                    }
                }).
                checkSystems(systems)
    }

    void removeFromDatabase(String backupSuffix, List<Closure> conditions, List systems = null) {
        new Checker(hs).
                addHandler(new HumanInfo(false)).
                addHandler(new DatabaseTransformer() {
                    @Override
                    void romNodeChecked(CheckRomResult checkRomResult, RomDatabase romNode) {
                        // elimina los juegos que cumplen la condicion
                        if (any(conditions, checkRomResult, romNode)) {
                            println "Deleteing ${checkRomResult.romName}" // romNode.remove()
                        }
                    }

                    @Override
                    void endDatabaseUpdate(CheckTotalResult checkResult) {
                        backupOriginalDatabaseAndSave("${checkResult.systemName}${backupSuffix}.xml")
                    }
                }).
                checkSystems(systems)
    }

    void addSuffixToRomName(List<Closure> conditions, String suffix, List systems = null) {
        Closure<File> addSuffix = { File exe ->
            exe.renameTo(new File(exe.toString()+suffix))
        }
        executeRomAction(conditions, addSuffix, systems)
    }

    void deleteRoms(List<Closure> conditions, List systems = null) {
        Closure<File> delete = { File exe ->
            exe.delete()
        }
        executeRomAction(conditions, delete, systems)
    }

    void moveRomsTo(List<Closure> conditions, String dst, List systems = null) {
        Closure<File> moveTo = { File exe ->
            IOTools.move(exe, new File(dst, exe.name))
        }
        executeRomAction(conditions, moveTo, systems)
    }

    void executeRomAction(List<Closure> conditions, Closure<File> action, List systems = null) {
        new Checker(hs).
                addHandler(new HumanInfo(false)).
                addHandler(new BaseCheckHandler() {
                    @Override
                    void romChecked(CheckRomResult checkRomResult) {
                        if (any(conditions, checkRomResult)) {
                            File exe = checkRomResult.rom.exeFileFound
                            if (exe && exe.exists()) {
                                action.call(exe)
                            }
                        }
                    }
                }).
                checkSystems(systems)
    }

    void split(List<Closure> conditions, String suffixYes, String suffixNo, List systems = null) {
        new Checker(hs).
                addHandler(new HumanInfo(false)).
                addHandler(new DatabaseTransformer() {
                    @Override
                    void romNodeChecked(CheckRomResult checkRomResult, RomDatabase romNode) {
                        if (none(conditions, checkRomResult, romNode)) {
                            romNode.remove()
                        }
                    }

                    @Override
                    void endDatabaseUpdate(CheckTotalResult checkResult) {
                        saveDatabaseTo("${checkResult.systemName}${suffixYes}.xml")
                    }

                }).
                addHandler(new DatabaseTransformer() {
                    @Override
                    void romNodeChecked(CheckRomResult checkRomResult, RomDatabase romNode) {
                        if (any(conditions, checkRomResult, romNode)) {
                            romNode.remove()
                        }
                    }

                    @Override
                    void endDatabaseUpdate(CheckTotalResult checkResult) {
                        saveDatabaseTo("${checkResult.systemName}${suffixNo}.xml")
                    }

                }).
                checkSystems(systems)
    }


}