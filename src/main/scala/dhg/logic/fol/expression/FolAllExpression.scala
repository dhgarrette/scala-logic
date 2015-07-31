package dhg.logic.fol.expression

import dhg.logic.top.expression.Variable
import dhg.logic.fol._

case class FolAllExpression(override val variable: Variable, override val term: FolExpression)
    extends FolQuantifiedExpression(FolTokens.ALL, variable, term) {

}

object FolAllExpression {
}
