package utcompling.scalalogic.discourse.candc.boxer.expression.interpreter.impl

import utcompling.scalalogic.discourse.candc.boxer.expression._
import utcompling.scalalogic.discourse.candc.boxer.expression.interpreter.BoxerExpressionInterpreter

class OccurrenceMarkingBoxerExpressionInterpreterDecorator extends BoxerExpressionInterpreter[BoxerExpression] {

    override def interpret(e: BoxerExpression) =
        e match {
            case BoxerNamed(discId, indices, variable, name, typ, sense) =>
                BoxerNamed(discId, indices, variable, "%s_%s_d%s%s".format(name, typ, discId, indices.map("_w" + _).mkString(",")), typ, sense)
            case BoxerPred(discId, indices, variable, name, pos, sense) =>
                BoxerPred(discId, indices, variable, "%s_%s_d%s%s".format(name, pos, discId, indices.map("_w" + _).mkString(",")), pos, sense)
            case BoxerRel(discId, indices, event, variable, name, sense) =>
                BoxerRel(discId, indices, event, variable, "%s_d%s%s".format(name, discId, indices.map("_w" + _).mkString(",")), sense)
            case _ => e
        }

}
