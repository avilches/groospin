/*
 * @author Alberto Vilches (alberto.vilches@)
 * @date 4/9/16
 * Copyright. All Rights Reserved.
 */
package org.hs5tb.groospin.common;

class IniFile extends Ini {

    File file
    IniFile parse(File iniFile) {
        this.file = iniFile
        if (iniFile.exists()) {
            parse(iniFile.newReader())
            dirty = false
        }
        return this
    }

    void store() {
        store(file)
    }

    void store(File file) {
        store(file.newWriter())
    }

}