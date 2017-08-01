package operation

import org.hs5tb.groospin.base.HyperSpin
import org.hs5tb.groospin.base.RLSystem
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
        combineAndOptimizeResourceList(resources).each { File originFile ->
            txt += "${relativeToRoot(originFile)}\n"
        }
        File f = File.createTempFile("groospin-packer-rar", "lst")
        try {
            f.text = txt
            String cmd = "${RAR_EXE} a -r0 -v500000 \"${new File(rar).absolutePath}\" @${f.absolutePath}"
            println "cwd $root"
            println cmd
            if (!simulation) {
                Process p = cmd.execute([], new File(root))
                p.waitForProcessOutput(System.out, System.err)
            }
        } finally {
            f.delete()
        }
        listFiles(resources, rar - ".rar")
    }

    void listFiles(List resources, String file) {
        Set<File> files = explodeToSingleFileList(resources)
        new File(file+".txt").text = files.collect{ relativeToRoot(it) }.join("\n")
        new File(file+".csv").text = "size,content\n${files.collect{ "${it.file?it.size():0},${relativeToRoot(it)}" }.join("\n")}\n${files.sum { it.file?it.size():0}}"
    }

    Set<File> explodeToSingleFileList(List resources) {
        Set<File> files = new HashSet()
        combineAndOptimizeResourceList(resources).each { File originFile ->
            if (originFile.directory) {
                originFile.eachFileRecurse { File contentFile ->
                    // Se incluyen también los directorios
                    files << contentFile
                }
            } else {
                files << originFile
            }
        }
        return files.sort { it.absolutePath.toLowerCase()}
    }

    List<String> combineAndOptimizeResourceList(List resources) {
        List dirs = []
        List files = []
        resources.each { o ->
            File originFile = o instanceof File ? o : new File(o.toString())
            if (originFile.directory) {
                dirs << originFile
            } else if (originFile.file) {
                files << originFile
            }
        }
        List bestDirs = []
        dirs.sort{ it.absolutePath.toLowerCase() }.each { File current ->
            if (!bestDirs.find { File other -> current.absolutePath.toLowerCase().startsWith(other.absolutePath.toLowerCase())}) {
                bestDirs << current
            }
        }
        List filesNotIncludedInDirs = []
        files.sort{ it.absolutePath.toLowerCase() }.each { File current ->
            if (!bestDirs.find { File other -> current.absolutePath.toLowerCase().startsWith(other.absolutePath.toLowerCase())}) {
                filesNotIncludedInDirs << current
            }
        }
        return bestDirs + filesNotIncludedInDirs
    }

    List listSystemResources(List systems) {
        systems.collect { listSystemResources(it) }.flatten()
    }

    List listSystemResources(String systemName) {
        RLSystem system = hyperSpin.getSystem(systemName)
        File mainMenu = hyperSpin.newHyperSpinMediaFile("Main Menu")
        [system.getMediaFolder(),
         new File(mainMenu, "Images/Wheel/${systemName}.png"),
         new File(mainMenu, "Themes/${systemName}.zip"),
         new File(mainMenu, "Video/${systemName}.mp4"),
         new File(mainMenu, "Video/${systemName}.flv"),
         hyperSpin.newHyperSpinFile("Settings/${systemName}.ini"),
         hyperSpin.newHyperSpinFile("Databases/${systemName}")
        ]
    }
}
