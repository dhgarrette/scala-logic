package dhg.logic.drt.expression

import dhg.logic.top.expression.Variable
import dhg.logic.base.expression.BaseAtom

object DrtAtom extends BaseAtom[DrtApplicationExpression, DrtExpression] {

    override protected def makeVariableExpression(v: Variable) =
        DrtVariableExpression(v)

}