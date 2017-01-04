package org.hs5tb.groospin.common

/**
 * Created by Alberto on 12-Jul-16.
 */

import groovy.io.FileType
import groovy.transform.CompileStatic

import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream;

abstract class ZipUtils {

    @CompileStatic
    static OutputStream writeZip(OutputStream os, @DelegatesTo(ZipBuilder) final Closure closure) {
        new ZipOutputStream(os).withStream { OutputStream zipOutputStream ->
            new ZipBuilder(zipOutputStream: (ZipOutputStream)zipOutputStream).with(closure)
        }
        return os
    }

    @CompileStatic
    static OutputStream zip(File origin, OutputStream os, Closure<Boolean> filter = null) {
        zip([origin], os, filter)
    }
    static OutputStream zip(Collection<File> origins, OutputStream os, Closure<Boolean> filter = null) {
        origins.each { File origin -> if (!origin.exists()) throw new FileNotFoundException("File or folder not exists: ${origin}") }

        return writeZip(os) {
            origins.each { File origin ->
                if (origin.isFile() && (!filter || filter.call(origin))) {
                    addFile(origin.name, origin)
                } else {
                    String root = origin.absolutePath + File.separator
                    origin.eachFileRecurse(FileType.FILES) { File fileToCompress ->
                        if (!filter || filter.call(fileToCompress)) {
                            addFile(fileToCompress.absolutePath - root, fileToCompress)
                        }
                    }
                }
            }
        }
    }

    @CompileStatic
    static File zip(Collection<File> origins, File zipFile, Closure<Boolean> filter = null) {
        zipFile.withOutputStream { OutputStream outputStream ->
            zip(origins, outputStream, filter)
        }
        return zipFile
    }
    static File zip(File origin, File zipFile, Closure<Boolean> filter = null) {
        zipFile.withOutputStream { OutputStream outputStream ->
            zip(origin, outputStream, filter)
        }
        return zipFile
    }

    @CompileStatic
    static OutputStream zipSingleStream(String entry, InputStream inputStream, OutputStream os) {
        return writeZip(os) {
            addStream(entry, inputStream)
        }
    }

    @CompileStatic
    static File zipSingleStream(String entry, InputStream inputStream, File zipFile) {
        zipFile.withOutputStream { OutputStream outputStream ->
            zipSingleStream(entry, inputStream, outputStream)
        }
        return zipFile
    }

    static class ZipBuilder {
        ZipOutputStream zipOutputStream

        @CompileStatic
        void addFile(File f) {
            f.withInputStream { InputStream i ->
                addStream(f.name, i)
            }
        }

        @CompileStatic
        void addFile(String entry, File f) {
            f.withInputStream { InputStream i ->
                addStream(entry, i)
            }
        }

        @CompileStatic
        void addStream(String entry, InputStream inputStream) {
            zipOutputStream.putNextEntry(new ZipEntry(entry))
            zipOutputStream << inputStream
            zipOutputStream.closeEntry()
        }

    }


    static List<File> unzip(File zipFile, File destFolder, Closure<Boolean> filter = null) {
        if (!destFolder.exists()) {
            destFolder.mkdirs()
        } else if (!destFolder.isDirectory()) {
            throw new IOException("Destination folder is a file: ${destFolder}")
        }
        List<File> files = []
        new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile))).withStream { InputStream zipStream ->
            ZipEntry entry
            while (entry = zipStream.nextEntry) {
                File destFile = new File(destFolder, entry.name)
                if (!filter || filter.call(destFile)) {
                    if (entry.directory) {
                        destFile.mkdirs()
                    } else {
                        destFile.parentFile?.mkdirs()
                        destFile.withOutputStream { OutputStream outputStream ->
                            outputStream << zipStream
                        }
                        files << destFile
                    }
                }
            }
        }
        return files
    }

}