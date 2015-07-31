package dhg.logic.fol.expression

import dhg.logic.top.expression.Variable
import dhg.logic.fol._

abstract class FolQuantifiedExpression(override val operator: String, override val variable: Variable, override val term: FolExpression)
    extends FolVariableBinderExpression(operator, variable, term) {

}
