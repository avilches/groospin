/*
 * @author Alberto Vilches (alberto.vilches@)
 * @date 15/6/16
 * Copyright. All Rights Reserved.
 */
package org.hs5tb.groospin.common;

class Ini {

    static String defaultSection = "#"

    Ini parent
    Map sections = [:]
    boolean dirty = false

    Ini putAll(Map map) {
        return putAll(defaultSection, map)
    }

    Ini putAll(String section, Map map) {
        map.each { key, val ->
            put(section, key as String, val as String)
        }
        return this
    }

    Ini parse(String iniRaw, String section = null, Collection<String> keys = null) {
        return parse(new StringReader(iniRaw), section, keys)
    }

    List<Ini.Data> lines
    static class Data {
        String data
        Type type
        enum Type { property, raw, section }
    }

    /*
    RocketLauncher INI policy is:
    Ignore duplicated sections (only the first section is used)
    Ignore duplicated keys in the same section (only the first key is used)
     */
    static boolean ignoreDuplicatedSections = true
    static boolean ignoreDuplicatedKeys = true
    Ini parse(Reader iniFile, String section = null, Collection<String> includeOnlyKeys = null) {
        Set<String> sectionsParsed = new HashSet<>()
        Set<String> keysParsed  = new HashSet<>()
        boolean ignoreDuplicatedSection = false
        String currentSection = Ini.defaultSection
        section = section?.trim()?: null
        lines = []
        sections = [:]
        includeOnlyKeys = includeOnlyKeys ? includeOnlyKeys.collect { String k -> k?.trim() } : null
        iniFile.eachLine { String originalLine ->
            String line = originalLine.trim()
            if (!line || line.startsWith("#") || line.startsWith(";")) {
                lines << new Ini.Data(data:originalLine, type: Ini.Data.Type.raw)
                return // Ignore comments
            } else if (line.startsWith("[")) {
                int pos = line.indexOf("]")
                if (pos > 1) {
                    currentSection = line.substring(1, pos).trim()
                    lines << new Ini.Data(data:currentSection, type: Ini.Data.Type.section)
                    keysParsed.clear()
                    if (currentSection in sectionsParsed) {
                        ignoreDuplicatedSection = true
                    } else {
                        if (ignoreDuplicatedSections) sectionsParsed << currentSection
                        ignoreDuplicatedSection = false
                    }
                } else {
                    lines << new Ini.Data(data:originalLine, type: Ini.Data.Type.raw)
                }
            } else {
                if (line.contains("=") && !ignoreDuplicatedSection && (section == null || section == currentSection)) {
                    int equalsPos = line.indexOf("=")
                    String key = line.substring(0, equalsPos).trim()
                    if (!keysParsed.contains(key) && (!includeOnlyKeys || key in includeOnlyKeys)) {
                        if (ignoreDuplicatedKeys) keysParsed << key
                        String value = line.substring(equalsPos + 1).trim()
                        lines << new Ini.Data(data:key, type: Ini.Data.Type.property)
                        put(currentSection, key, value)
                    } else {
                        lines << new Ini.Data(data:originalLine, type: Data.Type.raw)
                    }
                } else {
                    lines << new Data(data:originalLine, type: Data.Type.raw)
                }
            }
        }
        return this
    }

    void store(Writer writer) {
        PrintWriter printer = writer.newPrintWriter()
        Map sectionsToStore = sections.clone()
        Map currentSection = sectionsToStore.remove(Ini.defaultSection)?.clone()
        lines.each { Ini.Data data ->
            if (data.type == Ini.Data.Type.raw) {
                writer.println(data.data)
            } else if (data.type == Ini.Data.Type.section) {
                if (currentSection != null) storeSection(printer, currentSection) // Flush pending session (new values..)
                currentSection = sectionsToStore.remove(data.data)?.clone()
                writer.println("[${data.data}]")
            } else if (data.type == Ini.Data.Type.property) {
                if (currentSection != null && currentSection.containsKey(data.data)) {
                    writer.println("${data.data}=${currentSection.remove(data.data)?:""}")
                }
            }
        }
        if (currentSection != null) storeSection(printer, currentSection)
        sectionsToStore.each { String section, Map newSection ->
            writer.println()
            writer.println("[${section}]")
            storeSection(printer, newSection)
        }
        writer.flush()
        writer.close()
    }

    protected void storeSection(PrintWriter printer, Map session) {
        session.each { String key, String value -> printer.println("$key=$value") }
    }

    Object getAt(String property) {
        return get(property)
    }

    String remove(String key) {
        return remove(defaultSection, key)
    }

    String remove(String section, String key) {
        Map sectionValues = sections[section.trim()]
        if (sectionValues?.containsKey(key.trim())) {
            dirty = true
            return sectionValues.remove(key.trim())
        }
        return null
    }

    void putAt(String property, Object newValue) {
        put(property, newValue as String)
    }

    Object put(String key, String value) {
        return put(defaultSection, key, value as String)
    }

    String put(String section, String key, String value) {
        section = section.trim()
        key = key.trim()
        value = value?.trim()?: ""
        Map sectionValues = sections[section]
        if (!sectionValues) {
            dirty = true
            sections[section] = [(key): value]
            return ""
        }
        if (sectionValues.containsKey(key)) {
            String previousValue = sectionValues.get(key)
            if (value == previousValue) return value
        }
        dirty = true
        return sectionValues.put(key, value)
    }

    Object get(String key) {
        return get(defaultSection, key)
    }

    Map getDefaultSection() {
        return getSection(defaultSection)
    }

    Map getSection(String section) {
        section = section.trim()
        Map map = sections[section]?.clone() ?: [:]
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
        section = section.trim()
        key = key.trim()
        Map sectionValues = sections[section]
        if (sectionValues) {
            String value = sectionValues[key.trim()]
            if (value) return value
        }
        // No section or no value in section
        if (parent) {
            return parent.get(section, key)
        }
        return ""
    }

}

