package dhg.logic.fol.expression

import dhg.logic.fol._

case class FolIffExpression(override val first: FolExpression, override val second: FolExpression)
    extends FolBooleanExpression(first, second) {

    override val operator = FolTokens.IFF

}

object FolIffExpression {
}
