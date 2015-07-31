package dhg.logic.fol.expression

import dhg.logic.top.expression.Variable
import dhg.logic.fol._

case class FolExistsExpression(override val variable: Variable, override val term: FolExpression)
    extends FolQuantifiedExpression(FolTokens.EXISTS, variable, term) {

}

object FolExistsExpression {
}
