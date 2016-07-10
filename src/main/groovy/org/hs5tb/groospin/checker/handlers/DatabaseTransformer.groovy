package org.hs5tb.groospin.checker.handlers

import groovy.xml.XmlUtil
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.base.Rom
import org.hs5tb.groospin.checker.BaseCheckHandler
import org.hs5tb.groospin.checker.CheckHandler
import org.hs5tb.groospin.checker.result.CheckRomResult
import org.hs5tb.groospin.checker.result.CheckTotalResult

/**
 * Created by Alberto on 10-Jul-16.
 */
abstract class DatabaseTransformer extends BaseCheckHandler {

    File originalDatabaseFile
    Node currentDatabase
    Map<String, RomNode> gameIndex
    File folder
    boolean dirty = false

    DatabaseTransformer(String folder) {
        this(new File(folder))
    }

    DatabaseTransformer(File folder) {
        this.folder = folder
    }

    @Override
    void startSystem(RLSystem system) {
        originalDatabaseFile = system.findSystemDatabaseFile()
        currentDatabase = new XmlParser().parseText(originalDatabaseFile.text)
        Map gameIndex = this.gameIndex = [:]
        DatabaseTransformer databaseTransformer = this
        currentDatabase.game.each { Node node ->
            RomNode romNode = new RomNode(databaseTransformer, node)
            gameIndex[romNode.name] = romNode
        }
        dirty = false
    }

    @Override
    void romChecked(CheckRomResult checkResult) {
        RomNode node = findRomNode(checkResult.romName)
        romNodeChecked(checkResult, node)
        dirty = node.update() || dirty
    }

    abstract void romNodeChecked(CheckRomResult checkRomResult, RomNode romNode)

    void backupOriginalDatabaseTo(String newFileName) {
        File newFile = new File(originalDatabaseFile.parent, newFileName)
        newFile.text = originalDatabaseFile.text
    }

    void backupOriginalDatabaseAndSave(String newFileName) {
        backupOriginalDatabaseTo(newFileName)
        originalDatabaseFile.text = XmlUtil.serialize(currentDatabase)
    }

    @Override
    void endSystemWithError(String systemName, Exception e) {
        e.printStackTrace()
    }

    void saveDatabaseTo(String newFileName) {
        File newFile = new File(originalDatabaseFile.parent, newFileName)
        newFile.text = XmlUtil.serialize(currentDatabase)
    }

    RomNode findRomNode(String name) {
        return gameIndex[name]
    }
}

class RomNode extends Rom {
    Node node
    DatabaseTransformer databaseTransformer

    RomNode(DatabaseTransformer databaseTransformer, Node node) {
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
