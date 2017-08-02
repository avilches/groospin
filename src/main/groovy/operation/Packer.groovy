package operation

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
import org.hs5tb.groospin.checker.handlers.HumanInfo
import org.hs5tb.groospin.common.IOTools

/**
 * Created by Alberto on 18-Oct-16.
 */
class Packer extends Operations {

    static RAR_EXE = "\"c:\\Program Files\\WinRAR\\Rar.exe\""

    String root // "D:\Games"
    Packer(String hs) {
        super(hs)
        root = hyperSpin.hsRoot.parentFile.canonicalPath + "\\"
    }

    Packer(HyperSpin hs) {
        super(hs)
        root = hyperSpin.hsRoot.parentFile.canonicalPath + "\\"
    }

    void copyTo(List resources, String dst) {
        combineAndOptimizeResourceList(resources).each { File originFile ->
            File dstFile = new File(dst, relativeToRoot(originFile))
            log("(${simulation?"simulation":"real"}) Copy to ${dstFile}")
            if (!simulation) IOTools.copy(originFile, dstFile, false)
        }
    }

    String relativeToRoot(File originFile) {
        if (originFile.canonicalPath.toLowerCase().startsWith(root.toLowerCase())) {
            return originFile.absolutePath.substring(root.length())
        }
        throw new RuntimeException("${originFile} is not relative to ${root}")
    }

    void rarTo(List resources, String rar) {
        if (IOTools.getExtension(rar) != "rar") {
            rar = rar+".rar"
        }
        String txt = ""
        resources = combineAndOptimizeResourceList(resources)
        listFiles(resources, rar - ".rar")

        resources.each { File originFile ->
            txt += "${relativeToRoot(originFile)}\n"
        }
        File f = File.createTempFile("groospin-packer-rar", "lst")
        try {
            f.text = txt
            String cmd = "${RAR_EXE} a -r0 -v500000 \"${new File(rar).absolutePath}\" @${f.absolutePath}"
            println "---------------------------------------------------------------------"
            println "cwd $root"
            println cmd
            if (!simulation) {
                Process p = cmd.execute([], new File(root))
                p.waitForProcessOutput(System.out, System.err)
            }
        } finally {
            f.delete()
        }
    }

    void listFiles(List resources, String file) {
        Set<File> files = explodeToSingleFileList(resources)
        new File(file+".txt").text = files.collect{
            relativeToRoot(it)
        }.join("\n")
        new File(file+".csv").text = "size,content\n${files.collect{ "${it.file?it.size():0},${relativeToRoot(it)}" }.join("\n")}\n${files.sum { it.file?it.size():0}}"
    }

    Set<File> explodeToSingleFileList(List resources) {
        Set<File> files = new HashSet()
        long total = 0
        resources.each { File originFile ->
            if (originFile.directory) {
                long dirsize = 0
                originFile.eachFileRecurse { File contentFile ->
                    // Se incluyen también los directorios
                    dirsize += contentFile.size()
                    files << contentFile
                }
                println "${IOTools.humanReadableByteSize(dirsize).padLeft(10, " ")} ${originFile.absolutePath}"
                total += dirsize
            } else {
                files << originFile
                println "${IOTools.humanReadableByteSize(originFile.size()).padLeft(10, " ")} ${originFile.absolutePath}"
                total += originFile.size()
            }
        }
        println "${IOTools.humanReadableByteSize(total)}"
        return files.sort { it.absolutePath.toLowerCase()}
    }

    List<File> combineAndOptimizeResourceList(List resources) {
        List dirs = []
        List files = []
        resources.each { o ->
            File originFile = o instanceof File ? o : new File(o.toString())
            if (originFile.directory) {
                dirs << originFile
            } else if (originFile.file) {
                files << originFile
            } else {
                println "[-] Not found ${originFile}"
            }
        }
        List<File> bestDirs = []
        dirs.sort{ it.absolutePath.toLowerCase() }.each { File current ->
            File include = bestDirs.find { File other -> current.absolutePath.toLowerCase().startsWith(other.absolutePath.toLowerCase() + "\\") }
            if (!include) {
                bestDirs << current
            } else {
                println "[!] Ignoring folder ${current.absolutePath} (included in ${include.absolutePath})"
            }
        }
        List<File> filesNotIncludedInDirs = []
        files.sort{ it.absolutePath.toLowerCase() }.each { File current ->
            File include = bestDirs.find { File other -> current.absolutePath.toLowerCase().startsWith(other.absolutePath.toLowerCase())}
            if (!include) {
                filesNotIncludedInDirs << current
            } else {
                println "Ignoring file ${current.absolutePath} (included in ${include.absolutePath})"
            }
        }
        println "* FILES *"
        println filesNotIncludedInDirs.join("\n")
        println "* FOLDERS *"
        println bestDirs.join("\n")
        return bestDirs + filesNotIncludedInDirs
    }

    List listSystemResources(RLSystem system) {
        File mainMenu = hyperSpin.newHyperSpinMediaFile("Main Menu")
        [system.getMediaFolder(),
         new File(mainMenu, "Images/Wheel/${system.name}.png"),
         new File(mainMenu, "Themes/${system.name}.zip"),
         new File(mainMenu, "Video/${system.name}.mp4"),
         new File(mainMenu, "Video/${system.name}.flv"),
         hyperSpin.newHyperSpinFile("Settings/${system.name}.ini"),
         hyperSpin.newHyperSpinFile("Databases/${system.name}")
        ] + system.romPathsList

    }
}
