package dhg.logic.fol.expression

import dhg.logic.fol._

case class FolEqualityExpression(override val first: FolExpression, override val second: FolExpression)
    extends FolBinaryExpression(first, second) {

    override val operator = FolTokens.EQ

}

object FolEqualityExpression {
}
