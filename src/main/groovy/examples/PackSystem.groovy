package examples

import operation.Packer

Packer packer = new Packer("D:/Games/RocketLauncher")
packer.simulation = false


//packer.rarTo(["Pinball FX2"],[],
//        "D:/parches/Hyperspin5tb.Pinball.FX2.v-17.01-systems")
packer.rarTo([], ["d:\\Games\\Arcades\\Pinball FX2 Build 030615\\"],
        "D:/parches/Hyperspin5tb.Pinball.FX2.v-17.01-soft")

//packer.rarTo(["KODI", "XBMC", "Plex", "Arcade Music Trivia Challenger", "Rockola", "DWJukebox"],[],
//        "D:/parches/Hyperspin5tb.MediaCenters.v-17.01-systems")
//packer.rarTo([], ["D:/Games/Extra/KODI"],
//        "D:/parches/Hyperspin5tb.MediaCenters.v-17.01-soft")
//
