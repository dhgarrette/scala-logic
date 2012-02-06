package utcompling.scalalogic.util

import scala.sys.process._

class SubprocessCallable(val binary: String) {

    /**
     * Call the binary with the given input.
     *
     * @param inputStr: A string whose contents are used as stdin.
     * @param binary: The location of the binary to call
     * @param args: A list of command-line arguments.
     * @return: stdout
     */
    def call(inputStr: Option[String], args: List[String] = List(), verbose: Boolean = false): String = {
        val (exitcode, stdout, stderr) = this.callAllReturns(inputStr, args, verbose)
        if (exitcode != 0)
            throw new RuntimeException("ERROR CALLING: %s %s\nReturncode: %d\n%s".format(binary, args.mkString(" "), exitcode, stderr))
        return stdout
    }

    def callAllReturns(inputStr: Option[String], args: List[String] = List(), verbose: Boolean = false): (Int, String, String) = {
        if (verbose) {
            println("Calling: " + binary)
            println("Args: " + args)
            println("Input: " + (inputStr match { case Some(v) => "\"" + v + "\""; case None => "None" }))
            println("Command: " + binary + " " + args.mkString(" "))
        }

        // Call via a subprocess
        val (exitcode, stdout, stderr) =
            inputStr match {
                case None => {
                    val out = new StringBuilder
                    val err = new StringBuilder
                    val exitcode = Process(binary +: args) ! ProcessLogger(out.append(_).append("\n"), err.append(_).append("\n"))
                    (exitcode, out.result, err.result)
                }
                case Some(input) => {
                    val out = new StringBuilder
                    val err = new StringBuilder
                    val exitcode = Process(List("echo", input)) #| Process(binary +: args) ! ProcessLogger(out.append(_).append("\n"), err.append(_).append("\n"))
                    (exitcode, out.result, err.result)
                }
            }

        if (verbose) {
            println("Return code: " + exitcode)
            if (stdout.nonEmpty) println("stdout:\n" + stdout + "\n")
            if (stderr.nonEmpty) println("stderr:\n" + stderr + "\n")
        }

        return (exitcode, stdout, stderr)
    }

}

object SubprocessCallable {

    def findBinary(binaryName: String, binDir: Option[String] = None, envar: Option[String] = None, verbose: Boolean = false) =
        new SubprocessCallable(FileUtils.findBinary(binaryName, binDir, envar, verbose))

}
