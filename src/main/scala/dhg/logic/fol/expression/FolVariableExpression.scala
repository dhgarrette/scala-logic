package dhg.logic.fol.expression

import dhg.logic.top.expression.Variable
import dhg.logic.fol._
import dhg.logic.base.expression.BaseVariableExpression

case class FolVariableExpression(override val variable: Variable)
    extends FolExpression
    with BaseVariableExpression[FolExpression] {

}

object FolVariableExpression {
}
