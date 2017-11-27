package org.hs5tb.groospin.checker.handlers

import groovy.xml.XmlUtil
import operation.Comparer
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.base.Rom
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult

/**
 * Created by Alberto on 10-Jul-16.
 */
abstract class DatabaseTransformer extends BaseCheckHandler {

    File originalDatabaseFile
    Node currentDatabase
    Map<String, XmlRom> gameIndex
    boolean dirty = false

    DatabaseTransformer() {}

    @Override
    void startSystem(RLSystem system) {
        originalDatabaseFile = system.getDatabaseFile()
        currentDatabase = new XmlParser().parseText(originalDatabaseFile.text)
        Map gameIndex = this.gameIndex = [:]
        DatabaseTransformer databaseTransformer = this
        currentDatabase.game.each { Node node ->
            XmlRom romNode = new XmlRom(databaseTransformer, node)
            gameIndex[romNode.name] = romNode
        }
        dirty = false
    }

    @Override
    void romChecked(CheckRomResult checkResult) {
        XmlRom node = findRomNode(checkResult.romName)
        romNodeChecked(checkResult, node)
        dirty = node.update() || dirty
    }

    abstract void romNodeChecked(CheckRomResult checkRomResult, XmlRom romNode)

    void backupOriginalDatabaseTo(String newFileName) {
        File newFile = new File(originalDatabaseFile.parent, newFileName)
        newFile.text = originalDatabaseFile.text
    }

    void backupOriginalDatabaseAndSave(String backupFileName, boolean printDifferences = true) {
        backupOriginalDatabaseTo(backupFileName)
        fixDatabase(currentDatabase)
        originalDatabaseFile.text = XmlUtil.serialize(currentDatabase)

        if (printDifferences) {
            File backupFile = new File(originalDatabaseFile.parent, backupFileName)
            Comparer.printDifferences(originalDatabaseFile.toString(), backupFile.toString())
        }
    }

    void fixDatabase(Node node) {
        // Fix the database. Ensure all the nodes have the cloneof tag even if it's empty
        // The filter "parent_only=true" in HyperSpin will show a black screen if some of the
        // games have cloneof and others don't.
        node.each { Node n ->
            if (n.name() == "game" && n.cloneof.size() == 0) {
                n.appendNode("cloneof")
            }
        }
    }

    void saveDatabaseTo(String newFileName, boolean printDifferences = true) {
        File newFile = new File(originalDatabaseFile.parent, newFileName)
        fixDatabase(currentDatabase)
        newFile.text = XmlUtil.serialize(currentDatabase)

        if (printDifferences) {
            Comparer.printDifferences(originalDatabaseFile.toString(), newFile.toString())
        }

    }
    @Override
    void endSystemWithError(String systemName, Exception e) {
        e.printStackTrace()
    }

    abstract void endDatabaseUpdate(CheckTotalResult checkResult)
    final void endSystem(CheckTotalResult checkResult) {
        if (dirty) {
            try {
                endDatabaseUpdate(checkResult)
            } catch (e) {
                e.printStackTrace()
            }
        }
    }

    XmlRom findRomNode(String name) {
        return gameIndex[name]
    }
}

class XmlRom extends Rom {
    Node node
    DatabaseTransformer databaseTransformer

    XmlRom(DatabaseTransformer databaseTransformer, Node node) {
        super(node)
        this.node = node
        this.databaseTransformer = databaseTransformer
    }

    void remove() {
        databaseTransformer.dirty = true
        databaseTransformer.currentDatabase.children().remove(node)
    }

    boolean update() {
        return super.update(node)
    }
}
