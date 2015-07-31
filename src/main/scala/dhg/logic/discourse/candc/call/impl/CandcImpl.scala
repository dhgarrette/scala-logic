package dhg.logic.discourse.candc.call.impl

import dhg.logic.discourse.candc.call._
import dhg.util._
import dhg.logic.util.SubprocessCallable

class CandcImpl(
    override val binary: String,
    val modelsPath: String,
    val defaultArgs: Map[String, String]) extends SubprocessCallable(binary) with Candc {

    def this(binary: String, defaultArgs: Map[String, String] = Map()) =
        this(binary, pathjoin(binary.dropRight(5), "../models"), defaultArgs)

    override def batchParseMultisentence(inputs: Seq[Seq[String]], args: Map[String, String] = Map(), discourseIds: Option[Seq[String]] = None, model: Option[String] = None, verbose: Boolean = false): String = {
        val newDiscourseIds = discourseIds.getOrElse((0 until inputs.length).map(_.toString))
        val defaultArgs = Map[String, String](
            "--models" -> pathjoin(this.modelsPath, model.getOrElse("")))
        return this.call(Some(this.makeInput(inputs, newDiscourseIds)), (this.defaultArgs ++ defaultArgs ++ args).flatMap { case (k, v) => List(k, v) }.toList, verbose)
    }

    private def makeInput(inputs: Seq[Seq[String]], discourseIds: Seq[String]): String = {
        require(inputs.length == discourseIds.length, "Must have the same number of inputs and discourseIds")
        val discourses = for ((d, id) <- (inputs zipSafe discourseIds)) yield "<META>'%s'".format(id) +: d
        return discourses.flatten.mkString("\n")
    }

}

object CandcImpl {

    def findBinary(binDir: Option[String] = None, envar: Option[String] = Some("CANDCHOME"), defaultArgs: Map[String, String] = Map(), verbose: Boolean = false) = {
        new CandcImpl(dhg.util.findBinary("candc", binDir, envar), defaultArgs = defaultArgs)
    }

    def findBinary(binDir: Option[String], envar: Option[String], modelsPath: String, defaultArgs: Map[String, String], verbose: Boolean) = {
        new CandcImpl(dhg.util.findBinary("candc", binDir, envar), modelsPath, defaultArgs)
    }

}
