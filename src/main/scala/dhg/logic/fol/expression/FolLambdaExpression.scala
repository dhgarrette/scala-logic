package dhg.logic.fol.expression

import dhg.logic.top.expression.Variable
import dhg.logic.fol._
import dhg.logic.base.expression.BaseLambdaExpression

case class FolLambdaExpression(override val variable: Variable, override val term: FolExpression)
    extends FolVariableBinderExpression(FolTokens.LAMBDA, variable, term)
    with BaseLambdaExpression[FolExpression] {

}

object FolLambdaExpression {
}
