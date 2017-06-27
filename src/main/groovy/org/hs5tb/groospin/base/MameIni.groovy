package org.hs5tb.groospin.base

/**
 * Created by Alberto on 28-Jun-17.
 */
class MameIni {

    File file
    List<String> lines = []
    Map<String, String> values = [:]
    Map<String, Integer> mapping = [:]

    MameIni parse(File file) {
        this.file = file
        int lineNumber = 0
        file.eachLine { String line ->
            line = line.trim()
            lines << line
            if (line && !line.startsWith("#")) {
                int pos = line.indexOf(" ")
                String key = line
                String value = ""
                if (pos > 0) {
                    key = line.substring(0, pos)
                    value = line.substring(pos).trim()
                }
                values[key] = value
                mapping[key] = lineNumber
            }
            lineNumber++
        }
        this
    }

    String get(String key) {
        values[key.trim()]
    }

    void set(String key, String value) {
        key = key.trim()
        value = value?.trim()?:""
        String newLine = "${key.padRight(26, " ")}${value}"
        if (values.containsKey(key)) {
            int lineNumber = mapping[key]
            lines[lineNumber] = newLine
        } else {
            lines << newLine
        }
        values[key] = value
    }

    MameIni save(File file = this.file) {
        file.text = lines.join("\n")
        this
    }
}
