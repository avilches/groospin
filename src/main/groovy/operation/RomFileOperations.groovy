package operation

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.Checker
import org.hs5tb.groospin.checker.handlers.HumanInfo
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.common.IOTools

/**
 * Created by Alberto on 01-Sep-16.
 */
class RomFileOperations extends Operations {

    RomFileOperations(String hs) {
        super(hs)
    }
    RomFileOperations(HyperSpin hs) {
        super(hs)
    }

    void addSuffixToRomName(String suffix, List<Closure> conditions, List systems = null) {
        executeRomAction(conditions, systems) { File exe ->
            String newFileName = exe.toString()+suffix
            log("Renaming to ${newFileName}")
            //exe.renameTo(new File(newFileName))
        }
    }

    void deleteRoms(List<Closure> conditions, List systems = null) {
        executeRomAction(conditions, systems) { File exe ->
            log("Deleting ${exe.absolutePath}")
            exe.delete()
        }
    }

    void moveRomsTo(String dst, List<Closure> conditions, List systems = null) {
        executeRomAction(conditions, systems) { File exe ->
            log("Moving ${exe.absolutePath} to ${dst}")
            IOTools.move(exe, new File(dst, exe.name))
        }
    }

    void executeRomAction(List<Closure> conditions, List systems = null, Closure<File> action) {
        new Checker(hyperSpin).
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

}