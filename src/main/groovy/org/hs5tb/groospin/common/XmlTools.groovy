package org.hs5tb.groospin.common

/**
 * Created by Alberto on 10-Jul-16.
 */
class XmlTools {
    static boolean updateChild(Node node, String childNodeName, String val) {
        Node child = node."${childNodeName}"[0]
        if (child == null) {
            if (val) {
                node.appendNode(childNodeName, val)
                return true
            }
        } else {
            if (val) {
                if (child.text() != val) {
                    child.value = val
                    return true
                }
            } else {
                node.remove(child)
                return true
            }
        }
        return false
    }
}
