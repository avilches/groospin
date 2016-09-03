/*
 * bq.com
 *
 * @author Alberto Vilches (alberto.vilches@bq.com)
 * @date 15/6/16
 * Copyright. All Rights Reserved.
 */
package org.hs5tb.groospin.common;

class Ini {

    static String defaultSection = "#"

    Ini parent
    Map sections = [:]

    Ini putAll(Map map) {
        return putAll(defaultSection, map)
    }

    Ini putAll(String section, Map map) {
        map.each { key, val ->
            put(section, key, val)
        }
        return this
    }

    Ini parseText(String iniRaw, String sectionToParse = null, Collection<String> keysToParse = null) {
        return parse(new StringReader(iniRaw), sectionToParse, keysToParse)
    }

    Ini parse(String iniFile, String sectionToParse = null, Collection<String> keys = null) {
        parse(new File(iniFile), sectionToParse, keys)
    }
    Ini parse(File iniFile, String sectionToParse = null, Collection<String> keysToParse = null) {
        return iniFile.exists() ? parse(iniFile.newReader(), sectionToParse, keysToParse) : this
    }
    /*
    RocketLauncher INI policy is:
    Ignore duplicated sections (only the first section is used)
    Ignore duplicated keys in the same section (only the first key is used)
     */
    static boolean ignoreDuplicatedSections = true
    static boolean ignoreDuplicatedKeys = true
    Ini parse(Reader iniFile, String sectionToParse = null, Collection<String> keysToParse = null) {
        Set<String> sectionsParsed = new HashSet<>()
        Set<String> keysParsed  = new HashSet<>()
        boolean ignoreDuplicatedSection = false
        String currentSection = Ini.defaultSection
        sectionToParse = sectionToParse?.trim() ? canonical(sectionToParse) : null
        Ini ini = new Ini()
        keysToParse = keysToParse ? keysToParse.collect { String k -> canonical(k) } : null
        iniFile.eachLine { String line ->
            line = line.trim()
            if (!line || line.startsWith("#") || line.startsWith(";")) {
                return // Ignore comments
            } else if (line.startsWith("[")) {
                int pos = line.indexOf("]")
                if (pos > 1) {
                    currentSection = canonical(line.substring(1, pos))
                    keysParsed.clear()
                    if (currentSection in sectionsParsed) {
                        ignoreDuplicatedSection = true
                    } else {
                        if (ignoreDuplicatedSections) sectionsParsed << currentSection
                        ignoreDuplicatedSection = false
                    }
                }
            } else {
                if (!ignoreDuplicatedSection && (sectionToParse == null || sectionToParse == currentSection)) {
                    String key
                    String value
                    if (line.contains("=")) {
                        int equalsPos = line.indexOf("=")
                        key = canonical(line.substring(0, equalsPos))
                        value = line.substring(equalsPos + 1).trim()
                    } else {
                        key = canonical(line)
                        value = null
                    }
                    if (!keysParsed.contains(key) && (!keysToParse || key in keysToParse)) {
                        if (ignoreDuplicatedKeys) keysParsed << key
                        ini.put(currentSection, key, value)
                    }
                }
            }
        }
        return ini
    }

    Object getAt(String property) {
        return get(property)
    }

    void putAt(String property, Object newValue) {
        put(property, newValue as String)
    }

    Object put(String key, String value) {
        return put(defaultSection, key as String, value as String)
    }

    String put(String section, String key, String value) {
        String canonicalSection = canonical(section)
        Map values = sections[canonicalSection]
        if (!values) {
            values = sections[canonicalSection] = [:]
        }
        return values.put(canonical(key), value)
    }

    Object get(String key) {
        return get(defaultSection, key as String)
    }

    Map getDefaultSection() {
        return getSection(defaultSection)
    }

    Map getSection(String section) {
        Map map = this.sections[canonical(section)] ?: [:]
        if (parent) {
            parent.getSection(section).each { String key, String value ->
                if (!map.containsKey(key)) {
                    map[key] = value
                }
            }
        }
        return map
    }

    String get(String section, String key) {
        Map values = this.sections[canonical(section)]
        if (values) {
            String value = values[canonical(key)]
            if (value) return value
        }
        // No section or no value in section
        if (parent) {
            return parent.get(section, key)
        }
        return null
    }

    static String canonical(String s) {
        s?.trim()?.toLowerCase()
    }

}

