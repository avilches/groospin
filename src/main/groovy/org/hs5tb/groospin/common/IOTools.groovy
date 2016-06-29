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
        if (File.separator == "/") { // We are running tests in a MacOS/linux environment
            path = path.replaceAll("\\\\", "/")
        }
        File candidate = new File(path).canonicalFile
        if (candidate.exists()) return candidate
        return new File(root, path).canonicalFile
    }

    static long folderSize(File folder) {
        long totalSize = 0
        if (folder.directory)
            folder.eachFileRecurse(FileType.FILES) { File file -> totalSize += file.size() }
        return totalSize
    }

    static String sanitize(String s) {
        return s.toLowerCase().replaceAll(" ", "-")
    }


    static String escapeCsv(String string, String delimiter) {
        if (!string) return '""'
        if (string.contains('"') || string.contains(delimiter)) {
            string = string.replaceAll(/"/, /""/)
            StringBuffer row = new StringBuffer()
            row.append("\"")
            row.append(string)
            row.append("\"")
            string = row.toString()
        }
        return string
    }

    static String humanReadableByteSize(long bytes, boolean si = true) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = ((si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) as String) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    static File findFilesInFolder(File path, List<String> filesToFind) {
        for (String fileToFind in filesToFind) {
            File file = new File(path, fileToFind).canonicalFile
            // println "Checking for ${file} exists? ${file.exists()}"
            if (file.exists()) return file
        }
        return null
    }

    static File findFilesInFolders(List<File> paths, List<String> filesToFind) {
        for (String fileToFind in filesToFind) {
            File file = findFileInFolders(paths, fileToFind)
            if (file) return file
        }
        return null
    }

    static File findFileInFolders(List<File> paths, String fileToFind) {
        for (File path in paths) {
            File file = new File(path, fileToFind).canonicalFile
            // println "Checking for ${file} exists? ${file.exists()}"
            if (file.exists()) return file
        }
        return null
    }

    static File findFileWithExtensions(File fileBaseWithoutExtension, List extensions) {
        return findFileWithExtensions(fileBaseWithoutExtension.absolutePath, extensions)
    }

    static File findFileWithExtensions(String fileBaseWithoutExtension, List extensions) {
        for (String extension in extensions) {
            File file = new File(fileBaseWithoutExtension + "." + extension).canonicalFile
            // println "Checking for ${file} exists? ${file.exists()}"
            if (file.exists()) return file
        }
        return null
    }
}