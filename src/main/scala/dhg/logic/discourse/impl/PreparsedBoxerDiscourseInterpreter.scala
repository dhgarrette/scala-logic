package dhg.logic.discourse.impl

import dhg.logic.discourse._
import dhg.logic.discourse.impl._
import dhg.logic.discourse.candc.boxer._
import dhg.logic.discourse.candc.boxer.expression._
import dhg.logic.discourse.candc.boxer.expression.interpreter.impl._
import dhg.logic.drt.expression._
import dhg.logic.top.expression._
import dhg.logic.discourse.candc.call._
import dhg.logic.discourse.candc.call.impl._
import dhg.logic.discourse.candc.parse.output.impl._
import dhg.util._
import dhg.util._
import dhg.logic.discourse.candc.boxer.expression.interpreter.BoxerExpressionInterpreter
import dhg.logic.discourse.candc.boxer.expression.parse.BoxerExpressionParser

/**
 * Discourse Interpreter that simply interprets pre-parsed strings
 */
class PreparsedBoxerDiscourseInterpreter[T](
  interpretations: List[Option[String]],
  boxerExpressionInterpreter: BoxerExpressionInterpreter[T] = new Boxer2DrtExpressionInterpreter())
  extends DiscourseInterpreter[T] {

  /**
   * Hook to which all interpret calls delegate.
   */
  override def batchInterpretMultisentence(inputs: List[List[String]], discourseIds: Option[List[String]] = None, question: Boolean = false, verbose: Boolean = false): List[Option[T]] = {
    val newDiscourseIds = discourseIds.getOrElse((0 until inputs.length).map(_.toString).toList)
    require(inputs.length == newDiscourseIds.length)

    (interpretations zipSafe newDiscourseIds)
      .map {
        case (Some(drsString), discourseId) =>
          val lineParser = new BoxerExpressionParser(discourseId)
          Some(boxerExpressionInterpreter.interpret(lineParser.parse(drsString)))
        case (None, _) => None
      }
      .toList
  }

}
