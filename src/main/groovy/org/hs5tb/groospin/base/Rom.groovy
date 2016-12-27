package org.hs5tb.groospin.base

import org.hs5tb.groospin.common.XmlTools

/**
 * Created by Alberto on 19-Jun-16.
 */
class Rom {
    String name
    String description
    String cloneof
    String manufacturer
    String crc
    String year
    String genre
    String rating
    String enabled
    String exe

    File romFileFound
    File exeFileFound

    Rom() {}

    Rom(Node node) {
        loadFromHyperSpinDatabase(node)
    }

    Rom loadFromHyperSpinDatabase(Node node) {
        name = node.@name
        description = node.description.text()
        cloneof = node.cloneof.text()
        manufacturer = node.manufacturer.text()
        year = node.year.text()
        crc = node.crc.text()
        genre = node.genre.text()
        rating = node.rating.text()
        enabled = node.enabled.text()
        exe = node.exe.text()
        this
    }

    boolean update(Node node) {
        boolean dirty = false
        if (node.@name != name) {
            node.@name = name
            dirty = true
        }

        ["description", "cloneof", "manufacturer", "year", "genre", "rating", "enabled"].each { String childNodeName ->
            String val = this."${childNodeName}"
            dirty = XmlTools.updateChild(node, childNodeName, val) || dirty
        }
        dirty
    }

    boolean containsTexts(List s) {
        s.any { containsText(it) }

    }
    boolean containsText(String s) {
        s = s.toLowerCase()
        return name.toLowerCase().contains(s) || description.toLowerCase().contains(s)
    }

    void exportAdditional(d) {
    }

}
