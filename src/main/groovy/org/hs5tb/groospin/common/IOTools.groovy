/*
 * hs5tb
 *
 * @author Alberto Vilches (alberto.vilches@hs5tb)
 * @date 13/6/16
 * Copyright. All Rights Reserved.
 */
package org.hs5tb.groospin.common

import groovy.io.FileType

class IOTools {
    static File tryRelativeFrom(File root, String path) {
        path = path.replaceAll("\\\\", "/")
        (path.startsWith(".") ? new File(root, path) : new File(path)).canonicalFile
    }

    static long folderSize(File folder) {
        long totalSize = 0
        if (folder.directory)
            folder.eachFileRecurse(FileType.FILES) { File file -> totalSize += file.size() }
        return totalSize
    }

    static String humanReadableByteCount(long bytes, boolean si = true) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = ((si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) as String) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    static File matchesAnyExtension(File fileBaseWithoutExtension, List extensions) {
        return matchesAnyExtension(fileBaseWithoutExtension.absolutePath, extensions)
    }

    static File matchesAnyExtension(String fileBaseWithoutExtension, List extensions) {
        for (String extension in extensions) {
            File file = new File(fileBaseWithoutExtension + "." + extension)
            println "Checking for ${file} exists? ${file.exists()}"
            if (file.exists()) return file
        }
        return null
    }
}