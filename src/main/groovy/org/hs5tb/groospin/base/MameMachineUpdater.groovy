package org.hs5tb.groospin.base

import org.hs5tb.groospin.common.Ini
import org.hs5tb.groospin.common.IniFile

/**
 * Created by Alberto on 02-Sep-16.
 */
class MameMachineUpdater {

    static loadExtraInfo(List<MameMachine> roms, File extraInfoFile, boolean fixGenres = true) {
        Map extraInfo = [:]
        String currentName
        extraInfoFile.eachLine { String line ->
            line = line.trim()
            if (!line) return
            String[] parts = line.split("=")
            if (parts.size() < 2) return // empty field
            String key = parts[0].trim()
            String value = parts[1].trim()
            if (key == "name") {
                currentName = value
                extraInfo[value] = [:]
            } else {
                extraInfo[currentName][key] = value
            }
        }

        roms.each { MameMachine rom ->
            Map romExtraInfo = extraInfo[rom.name]
            if (!romExtraInfo && rom.cloneof) {
                romExtraInfo = extraInfo[rom.cloneof]
            }
            if (romExtraInfo) {
                rom.genre = romExtraInfo.genre
                rom.rating = romExtraInfo.rating
            } else {
                println "No extra info for ${rom.name} \"${rom.description}\""
            }

            if (fixGenres) {
                if (!rom.genre) rom.genre = "Unknown"
                if (rom.genre.startsWith("Quiz")) rom.genre = "Quiz"
                if (rom.genre == "Sports/Compilation") rom.genre = "Sports"
                if (rom.genre.startsWith("Sports/")) rom.genre = rom.genre - "Sports/"
                if (rom.genre == "Fighter / Versus") rom.genre = "Fighter"
            }
        }
    }

    static loadCatVer(List<MameMachine> roms, File catVerFile) {
        Ini catVer = new IniFile().parse(catVerFile)
        loadCatVer(roms, catVer)
    }

    static loadCatVer(List<MameMachine> roms, Ini catVer, boolean fixCategories = true) {
        roms.each { MameMachine rom ->
            String category = catVer.get("Category", rom.name)
            if (category?.contains("* Mature *")) {
                category = (category - "* Mature *").trim()
                rom.catVerMature = true
            }
            rom.catVerCat = category
            String version = catVer.get("VerAdded", rom.name)
            rom.catVerVersion = version
        }
        if (fixCategories) {
            roms.each { MameMachine rom ->
                if (!rom.catVerCat) {
                    if (rom.cloneof) {
                        String cloneOfCategory = catVer.get("Category", rom.cloneof)
                        if (cloneOfCategory?.contains("* Mature *")) {
                            cloneOfCategory = (cloneOfCategory - "* Mature *").trim()
                        }
                        if (cloneOfCategory != rom.catVerCat) {
                            println "No category for ${rom.name} \"${rom.description})\", taking \"${cloneOfCategory}\" from his clone ${rom.cloneof}"
                            rom.catVerCat = cloneOfCategory
                        }
                    } else {
                        rom.catVerCat = "Unknown"
                    }
                }
            }
        }
    }

}
