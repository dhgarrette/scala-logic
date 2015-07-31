package dhg.logic.discourse.candc.parse.output

trait CandcOutputInterpreter[T] {

    def interpret(stdout: String): Map[String, Option[T]]

}