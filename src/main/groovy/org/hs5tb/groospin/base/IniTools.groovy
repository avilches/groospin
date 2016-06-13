package org.hs5tb.groospin.base

/**
 * Created by Alberto on 12-Jun-16.
 */
class IniTools {

    static Map parseIni(File ini, def map = [:].withDefault { k -> [:]}) {
        String currentSection
        ini.eachLine { String line ->
            line = line.trim()
            if (!line || line.startsWith("#")) return
            if (line.startsWith("[") && line.endsWith("]")) {
                currentSection = line.substring(1, line.size() - 1).trim().toLowerCase()
            } else {
                if (line.contains("=")) {
                    int equalsPos = line.indexOf("=")
                    map[currentSection][line.substring(0, equalsPos).trim()] = line.substring( equalsPos+ 1).trim()
                }
            }
        }
        return map
    }

    static Map parseIni(File ini, String section) {
        String currentSection
        section = section.trim().toLowerCase()
        Map map = [:]
        ini.eachLine { String line ->
            line = line.trim()
            if (!line || line.startsWith("#")) return
            if (line.startsWith("[") && line.endsWith("]")) {
                currentSection = line.substring(1, line.size() - 1).trim().toLowerCase()
            } else {
                if ((section == null || section == currentSection) && line.contains("=")) {
                    int equalsPos = line.indexOf("=")
                    map[line.substring(0, equalsPos).trim()] = line.substring( equalsPos+ 1).trim()
                }
            }
        }
        return map
    }


    static void fillMap(File ini, String section, Map values) {
        String currentSection
        section = section.trim().toLowerCase()
        ini.eachLine { String line ->
            line = line.trim()
            if (!line || line.startsWith("#")) return
            if (line.startsWith("[") && line.endsWith("]")) {
                currentSection = line.substring(1, line.size() - 1).trim().toLowerCase()
            } else {
                if ((section == null || section == currentSection) && line.contains("=")) {
                    values.keySet().find { String key ->
                        if (line.toLowerCase().startsWith(key.toLowerCase())) {
                            values[key] = line.substring(line.indexOf("=") + 1).trim()
                            return true
                        } else return false
                    }
                }
            }
        }
    }

}
