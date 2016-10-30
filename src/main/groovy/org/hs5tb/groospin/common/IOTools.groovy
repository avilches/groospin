/*
 * hs5tb
 *
 * @author Alberto Vilches (alberto.vilches@hs5tb)
 * @date 13/6/16
 * Copyright. All Rights Reserved.
 */
package org.hs5tb.groospin.common

import groovy.io.FileType

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.security.DigestInputStream
import java.security.MessageDigest
import java.util.zip.CRC32

class IOTools {
    static File tryRelativeFrom(File root, String path) {
        if (File.separator == "/") { // We are running tests in a MacOS/linux environment
            path = path.replaceAll("\\\\", "/")
        }
        if (path.size() > 2 && path.substring(1, 2) == ":") return new File(path)
        File candidate = new File(path)
        if (candidate.exists()) return candidate
        return new File(root, path)
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
            File file = new File(path, fileToFind)
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
            File file = new File(path, fileToFind)
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
            File file = new File(fileBaseWithoutExtension + "." + extension)
            // println "Checking for ${file} exists? ${file.exists()}"
            if (file.exists()) return file
        }
        return null
    }

    static String getExtension(String filename) {
        return filename.lastIndexOf('.').with {it != -1 ? filename.substring(it+1) : filename}
    }
    static String getFilenameWithoutExtension(String filename) {
        return filename.lastIndexOf('.').with {it != -1 ? filename[0..<it] : filename}
    }

    static void copy(File origin, File dst, boolean overwrite = true) {
        if (!overwrite)
            Files.copy(Paths.get(origin.toString()), Paths.get(dst.toString()), java.nio.file.StandardCopyOption.COPY_ATTRIBUTES)
        else
            Files.copy(Paths.get(origin.toString()), Paths.get(dst.toString()), java.nio.file.StandardCopyOption.REPLACE_EXISTING, java.nio.file.StandardCopyOption.COPY_ATTRIBUTES)
    }
    static void move(File origin, File dst) {
        Files.move(Paths.get(origin.toString()), Paths.get(dst.toString()))
        /*
        if (!origin.exists()) return
        if (dst.exists()) {
            if (dst.directory) {
                dst = new File(dst, origin.name)
            }
        } else {
            dst.parentFile.mkdirs()
        }
        if (!origin.renameTo(dst)) {
            origin
            copy(origin, dst)
            if (dst.exists()) {
                origin.delete()
            }
        }
        */
    }

    static String crc(InputStream is) {
        CRC32 crc = new CRC32()
        crc.update(is.bytes)
        return Integer.toHexString((int)crc.value).padLeft(8, "0")

    }

    static String sha1(InputStream is) {
        MessageDigest md = MessageDigest.getInstance("SHA1")
        byte[] buffer = new byte[8192]
        DigestInputStream dis = new DigestInputStream(is, md)
        try {
            while (dis.read(buffer) != -1);
        } finally{
            dis.close()
        }
        return new BigInteger(1, md.digest()).toString(16).toLowerCase()
    }

}