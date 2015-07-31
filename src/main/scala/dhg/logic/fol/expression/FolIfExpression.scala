package dhg.logic.fol.expression

import dhg.logic.fol._

case class FolIfExpression(override val first: FolExpression, override val second: FolExpression)
    extends FolBooleanExpression(first, second) {

    override val operator = FolTokens.IMP

}

object FolIfExpression {
}
