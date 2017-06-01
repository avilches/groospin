package operation

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.checker.Checker
import org.hs5tb.groospin.checker.handlers.DatabaseTransformer
import org.hs5tb.groospin.checker.handlers.HumanInfo
import org.hs5tb.groospin.checker.handlers.XmlRom
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
                        log("Processing ${system.name}. (Simulation: ${simulation})")
                    }

                    @Override
                    void romNodeChecked(CheckRomResult checkRomResult, XmlRom romNode) {
                        // elimina los juegos que no cumplen ninguna condicion para dejar en
                        // la base de datos que se va a salvar con el nuevo nombre los que si la cumplen
                        if (none(conditions, checkRomResult, romNode)) {
                            romNode.remove()
                        } else {
                            log("(${simulation?"simulation":"real"}) Deleting from db ${checkRomResult.romName}...")
                        }
                    }

                    @Override
                    void endDatabaseUpdate(CheckTotalResult checkResult) {
                        if (dirty) {
                            String newDatabase = "${checkResult.systemName}${newFileSuffix}.xml"
                            log("(${simulation ? "simulation" : "real"}) Saved new database ${newDatabase}")
                            if (!simulation) saveDatabaseTo(newDatabase, verbose)
                        }
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
                        log("Processing ${system.name}. (Simulation: ${simulation})")
                    }

                    @Override
                    void romNodeChecked(CheckRomResult checkRomResult, XmlRom romNode) {
                        // elimina los juegos que cumplen la condicion para luego salvarla (creando un backup)
                        if (any(conditions, checkRomResult, romNode)) {
                            log("(${simulation?"simulation":"real"}) Deleting from db ${checkRomResult.romName}...")
                            romNode.remove()
                        }
                    }

                    @Override
                    void endDatabaseUpdate(CheckTotalResult checkResult) {
                        if (dirty) {
                            String newDatabase = "${checkResult.systemName}${backupSuffix}.xml"
                            log("(${simulation ? "simulation" : "real"}) Database saved. Backup: ${newDatabase}")
                            if (!simulation) backupOriginalDatabaseAndSave(newDatabase, verbose)
                        }
                    }
                }).
                checkSystems(systems)
    }

    void split(String suffixYes, String suffixNo, List<Closure> conditions, List systems = null) {
        new Checker(hyperSpin).
                addHandler(new HumanInfo(false)).
                addHandler(new DatabaseTransformer() {
                    @Override
                    void startSystem(RLSystem system) {
                        super.startSystem(system)
                        log("Processing ${system.name}. (Simulation: ${simulation})")
                    }
                    @Override
                    void romNodeChecked(CheckRomResult checkRomResult, XmlRom romNode) {
                        // Borro las que no cumplen la condición para quedarme con las que la cumplen y guardarlas
                        if (none(conditions, checkRomResult, romNode)) {
                            romNode.remove()
                            log("(${simulation?"simulation":"real"}) ${suffixNo}: ${romNode.name}")
                        }
                    }

                    @Override
                    void endDatabaseUpdate(CheckTotalResult checkResult) {
                        if (!simulation) {
                            String newDatabase = "${checkResult.systemName}${suffixYes}.xml"
                            log("(${simulation?"simulation":"real"}) Saved new database ${newDatabase}")
                            saveDatabaseTo(newDatabase, verbose)
                        }
                    }

                }).
                addHandler(new DatabaseTransformer() {
                    @Override
                    void romNodeChecked(CheckRomResult checkRomResult, XmlRom romNode) {
                        // Borro las que si cumplen la condición para quedarme con las que no la cumplen y guardarlas
                        if (any(conditions, checkRomResult, romNode)) {
                            romNode.remove()
                            log("(${simulation?"simulation":"real"}) ${suffixYes}: ${romNode.name}")
                        }
                    }

                    @Override
                    void endDatabaseUpdate(CheckTotalResult checkResult) {
                        if (!simulation) {
                            String newDatabase = "${checkResult.systemName}${suffixNo}.xml"
                            log("(${simulation?"simulation":"real"}) Saved new database ${newDatabase}")
                            saveDatabaseTo(newDatabase, verbose)
                        }
                    }

                }).
                checkSystems(systems)
    }


}