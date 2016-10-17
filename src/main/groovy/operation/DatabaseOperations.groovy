package operation

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.checker.Checker
import org.hs5tb.groospin.checker.handlers.DatabaseTransformer
import org.hs5tb.groospin.checker.handlers.HumanInfo
import org.hs5tb.groospin.checker.handlers.RomDatabase
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult

/**
 * Created by Alberto on 01-Sep-16.
 */
class DatabaseOperations extends Operations {

    DatabaseOperations(String hs) {
        super(hs)
    }

    DatabaseOperations(HyperSpin hs) {
        super(hs)
    }

    void extractFromDatabase(String newFileSuffix, List<Closure> conditions, List systems = null) {
        new Checker(hyperSpin).
                addHandler(new DatabaseTransformer() {
                    @Override
                    void startSystem(RLSystem system) {
                        super.startSystem(system)
                        log(system.name)
                    }

                    @Override
                    void romNodeChecked(CheckRomResult checkRomResult, RomDatabase romNode) {
                        // elimina los juegos que no cumplen ninguna condicion para dejar en
                        // la base de datos que se va a salvar con el nuevo nombre los que si la cumplen
                        if (none(conditions, checkRomResult, romNode)) {
                            romNode.remove()
                        } else {
                            log("Deleting from db ${checkRomResult.romName}...")
                        }
                    }

                    @Override
                    void endDatabaseUpdate(CheckTotalResult checkResult) {
                        String newDatabase = "${checkResult.systemName}${newFileSuffix}.xml"
                        log("Saved new database ${newDatabase}")
                        saveDatabaseTo(newDatabase, verbose)
                    }
                }).
                checkSystems(systems)
    }

    void removeFromDatabase(String backupSuffix, List<Closure> conditions, List systems = null) {
        new Checker(hyperSpin).
                addHandler(new DatabaseTransformer() {
                    @Override
                    void startSystem(RLSystem system) {
                        super.startSystem(system)
                        log(system.name)
                    }

                    @Override
                    void romNodeChecked(CheckRomResult checkRomResult, RomDatabase romNode) {
                        // elimina los juegos que cumplen la condicion para luego salvarla (creando un backup)
                        if (any(conditions, checkRomResult, romNode)) {
                            println "Deleting from db ${checkRomResult.romName}..."
                            romNode.remove()
                        }
                    }

                    @Override
                    void endDatabaseUpdate(CheckTotalResult checkResult) {
                        String newDatabase = "${checkResult.systemName}${backupSuffix}.xml"
                        log("Database saved. Backup: ${newDatabase}")
                        backupOriginalDatabaseAndSave(newDatabase, verbose)
                    }
                }).
                checkSystems(systems)
    }

    void split(String suffixYes, String suffixNo, List<Closure> conditions, List systems = null) {
        new Checker(hyperSpin).
                addHandler(new HumanInfo(false)).
                addHandler(new DatabaseTransformer() {
                    @Override
                    void romNodeChecked(CheckRomResult checkRomResult, RomDatabase romNode) {
                        // Borro las que no cumplen la condición para quedarme con las que la cumplen y guardarlas
                        if (none(conditions, checkRomResult, romNode)) {
                            romNode.remove()
                            log("${suffixNo}: ${romNode.name}")
                        }
                    }

                    @Override
                    void endDatabaseUpdate(CheckTotalResult checkResult) {
                        saveDatabaseTo("${checkResult.systemName}${suffixYes}.xml", verbose)
                    }

                }).
                addHandler(new DatabaseTransformer() {
                    @Override
                    void romNodeChecked(CheckRomResult checkRomResult, RomDatabase romNode) {
                        // Borro las que si cumplen la condición para quedarme con las que no la cumplen y guardarlas
                        if (any(conditions, checkRomResult, romNode)) {
                            romNode.remove()
                            log("${suffixYes}: ${romNode.name}")
                        }
                    }

                    @Override
                    void endDatabaseUpdate(CheckTotalResult checkResult) {
                        saveDatabaseTo("${checkResult.systemName}${suffixNo}.xml", verbose)
                    }

                }).
                checkSystems(systems)
    }


}