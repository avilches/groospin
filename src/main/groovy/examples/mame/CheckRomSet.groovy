package examples.mame

MameChecker checker180 = new MameChecker().loadDat("d:/Games/Roms/MAME/mame.dat")

MameChecker.Report report = checker180.checkRoms(
        ["d:/Games/Roms/MAME/ROMs", "d:/Games/Roms/MAME/chds"])

println report.missing
