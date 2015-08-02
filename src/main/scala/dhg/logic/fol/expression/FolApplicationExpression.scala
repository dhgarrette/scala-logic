package dhg.logic.fol.expression

import dhg.logic.top.expression.Variable
import dhg.logic.fol._
import dhg.logic.base.expression.BaseApplicationExpression
import dhg.logic.base.expression.BaseApplicationExpressionUncurried

case class FolApplicationExpression(override val function: FolExpression, override val argument: FolExpression)
    extends FolExpression
    with BaseApplicationExpression[FolExpression] {

    override def visit[S](function: FolExpression => S, combinator: List[S] => S) =
        combinator(List(function(this.function), function(this.argument)))

    override def simplify(): FolExpression = {
        val function = this.function.simplify()
        val argument = this.argument.simplify()
        function match {
            case le: FolLambdaExpression => {
                le.term.replace(le.variable, argument, false, true).simplify()
            }
            case _ => {
                this.construct(List(function, argument))
            }
        }
    }

}

object FolApplicationExpression {
}

object FolApplicationExpressionUncurried extends BaseApplicationExpressionUncurried[FolExpression] {
  
}
