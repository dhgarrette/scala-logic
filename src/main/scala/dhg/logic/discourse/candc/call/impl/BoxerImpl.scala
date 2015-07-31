package dhg.logic.discourse.candc.call.impl

import dhg.logic.discourse.candc.call.Boxer
import dhg.util._
import dhg.logic.util.SubprocessCallable

class BoxerImpl(override val binary: String, defaultArgs: Map[String, String] = Map()) extends SubprocessCallable(binary) with Boxer {

    override def callBoxer(candcOut: String, args: Map[String, String] = Map(), verbose: Boolean = false): String = {
        val tempFilename = mktemp(prefix = "boxer-", suffix = ".in")

        writeUsing(tempFilename) { f =>
            f.write(candcOut)
        }

        val defaultArgs = Map[String, String](
            "--input" -> tempFilename.getAbsolutePath)
        val stdout = this.call(None, (this.defaultArgs ++ defaultArgs ++ args).flatMap { case (k, v) => List(k, v) }.toList, verbose)
        tempFilename.delete()
        return stdout
    }

}

object BoxerImpl {

    def findBinary(binDir: Option[String] = None, envar: Option[String] = Some("CANDCHOME"), defaultArgs: Map[String, String] = Map(), verbose: Boolean = false): Boxer =
        new BoxerImpl(dhg.util.findBinary("boxer", binDir, envar), defaultArgs)

}
