package utcompling.scalalogic.discourse.candc.call.impl

import utcompling.scalalogic.discourse.candc.call.Boxer
import dhg.util.FileUtil
import utcompling.scalalogic.util.SubprocessCallable

class BoxerImpl(override val binary: String, defaultArgs: Map[String, String] = Map()) extends SubprocessCallable(binary) with Boxer {

    override def callBoxer(candcOut: String, args: Map[String, String] = Map(), verbose: Boolean = false): String = {
        val tempFilename = FileUtil.mktemp(prefix = "boxer-", suffix = ".in")

        FileUtil.writeUsing(tempFilename) { f =>
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

    def findBinary(binDir: Option[String] = None, envar: Option[String] = Some("CANDCHOME"), defaultArgs: Map[String, String] = Map(), verbose: Boolean = false) =
        new BoxerImpl(FileUtil.findBinary("boxer", binDir, envar), defaultArgs)

}
