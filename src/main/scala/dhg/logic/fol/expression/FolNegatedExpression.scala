package dhg.logic.fol.expression

import dhg.logic.fol._
import dhg.logic.base.expression.BaseNegatedExpression

case class FolNegatedExpression(override val term: FolExpression)
    extends FolExpression
    with BaseNegatedExpression[FolExpression] {

    override def toString() =
        FolTokens.NOT + term

    override def _pretty(): List[String] = {
        val prettyterm = term._pretty
        return (FolTokens.NOT + prettyterm.head) +: prettyterm.tail.map("  "+_)
    }

}

object FolNegatedExpression {
}
