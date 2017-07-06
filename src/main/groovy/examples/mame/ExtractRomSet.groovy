package examples.mame

import org.hs5tb.groospin.base.MameMachine
import org.hs5tb.groospin.common.IOTools

MameChecker checker = new MameChecker().loadDat("d:/Games/Arcades/MAME/mame.dat") { MameMachine rom ->
    rom.players >= 3 && rom.working && rom.playable
}

MameChecker.Report report = checker.checkRoms(
        ["d:/Games/Arcades/MAME/ROMs", "d:/Games/Arcades/MAME/chds"])

report.files.each { File file ->
    IOTools.copy(file, new File("D:/export")  )
}
