package org.hs5tb.groospin.common

import groovy.transform.CompileStatic

/**
 * Created by Alberto on 23-Jun-16.
 */

@CompileStatic
class FileBuffer {
    static int maxBuffer = 250 * 1024 // 250Kb
    File file
    boolean first = true
    StringBuffer buffer

    FileBuffer(File file) {
        this.file = file
        resetBuffer()
    }

    private void resetBuffer() {
        buffer = new StringBuffer()
    }

    void leftShift(String more) {
        buffer << more
        nextLine()
        if (buffer.size() > maxBuffer) {
            flush()
        }
    }

    void flush() {
        if (first) {
            first = false
            if (!file.parentFile.exists()) {
                file.parentFile.mkdirs()
            }
            file.text = buffer
        } else {
            file << buffer
        }
        resetBuffer()
    }

    void nextLine() {
        buffer << "\n"
    }
}
