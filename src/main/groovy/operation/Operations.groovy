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

    static Closure NO_VIDEO = { CheckRomResult checkRomResult, RomDatabase romNode -> !checkRomResult.videos }
    static Closure WITH_VIDEO = { CheckRomResult checkRomResult, RomDatabase romNode -> checkRomResult.videos }

    static Closure NO_WHEEL = { CheckRomResult checkRomResult, RomDatabase romNode -> !checkRomResult.wheels }
    static Closure WITH_WHEEL = { CheckRomResult checkRomResult, RomDatabase romNode -> checkRomResult.wheels }

    static Closure NO_THEME = { CheckRomResult checkRomResult, RomDatabase romNode -> !checkRomResult.themes }
    static Closure WITH_THEME = { CheckRomResult checkRomResult, RomDatabase romNode -> checkRomResult.themes }

    void extractFromDatabase(String newFileSuffix, List<Closure> conditions, List systems = null) {
        new Checker(hs).
                addHandler(new HumanInfo(false)).
                addHandler(new DatabaseTransformer() {
                    @Override
                    void romNodeChecked(CheckRomResult checkRomResult, RomDatabase romNode) {
                        // limpia los juegos que no cumplen la condicion
                        if (!conditions.any { Closure condition ->
                            if (condition.maximumNumberOfParameters == 2) {
                                return condition.call(checkRomResult, romNode)
                            } else if (condition.maximumNumberOfParameters == 1) {
                                return condition.call(checkRomResult)
                            } else {
                                throw new IllegalArgumentException("Condition must have one or two parameters")
                            }
                        }) {
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
                        // limpia los juegos que no cumplen la condicion
                        if (!conditions.any { call(checkRomResult, romNode) }) {
                            romNode.remove()
                        }
                    }

                    @Override
                    void endDatabaseUpdate(CheckTotalResult checkResult) {
                        backupOriginalDatabaseAndSave("${checkResult.systemName}${backupSuffix}.xml")
                    }
                }).
                checkSystems(systems)
    }

    void moveRomsTo(List<Closure> conditions, String dst, List systems = null) {
        new Checker(hs).
                addHandler(new HumanInfo(false)).
                addHandler(new BaseCheckHandler() {
                    @Override
                    void romChecked(CheckRomResult checkResult) {
                        if (!conditions.any { call(checkResult) }) {
                            File exe = checkResult.rom.exeFileFound
                            if (exe && exe.exists()) {
                                IOTools.move(exe, new File(dst, exe.name))
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
                        if (!conditions.any{it.call(checkRomResult)}) {
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
                        if (conditions.any{it.call(checkRomResult)}) {
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