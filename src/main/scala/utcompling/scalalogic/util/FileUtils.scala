package utcompling.scalalogic.util

import scala.util.Random
import java.io._
import scala.collection.mutable.ListBuffer

object FileUtils {

    val pathsep: String = "/"
    val random = new Random(System.currentTimeMillis())

    def pathjoin(parts: String*): String =
        (parts.dropRight(1).map(p => (if (p.endsWith(pathsep)) p.dropRight(pathsep.length) else p)) :+ parts.last).mkString(pathsep)

    def openForWrite(filename: String): BufferedWriter =
        new BufferedWriter(new FileWriter(filename))

    def mktemp(suffix: String = "", prefix: String = ""): String = {
        val filename = prefix + "temp-" + random.nextInt(Int.MaxValue) + suffix
        return pathjoin(System.getProperty("java.io.tmpdir"), filename)
    }

    def remove(filename: String) = {
        new File(filename).delete()
    }

    def deleteAll(filename: String) = {
        def deleteFile(dfile: File): Unit = {
            if (dfile.isDirectory) {
                val subfiles = dfile.listFiles
                if (subfiles != null)
                    subfiles.foreach { f => deleteFile(f) }
            }
            dfile.delete
        }
        deleteFile(new File(filename))
    }

    def exists(filename: String) =
        new File(filename).exists

    def findBinary(name: String, binDir: Option[String] = None, envar: Option[String] = None, verbose: Boolean = false): String = {
        val checked = new ListBuffer[String]

        if (binDir.isDefined) {
            val path = FileUtils.pathjoin(binDir.get, name)
            if (FileUtils.exists(path))
                return path
            else
                checked += path
        }

        if (envar.isDefined) {
            val envpath = System.getenv(envar.get)
            if (envpath != null) {
                val path = FileUtils.pathjoin(envpath, name)
                if (FileUtils.exists(path))
                    return path
                else
                    checked += path
            }
        }

        try {
            return scala.sys.process.Process(List("which", name)) !!;
        } catch {
            case _ => {
                checked += "which " + name
            }
        }

        throw new RuntimeException("No binary found.  Checked the following:\n" + checked.map((" ") * 16 + _).mkString("\n"))
    }

    /**
     * Automatic Resource Management
     *
     * using(new BufferedReader(new FileReader("file"))) { r =>
     *   var count = 0
     *   while (r.readLine != null) count += 1
     *   println(count)
     * }
     */
    def using[T <: { def close() }](resource: T)(block: T => Unit) {
        try {
            block(resource)
        } finally {
            if (resource != null) resource.close()
        }
    }

}