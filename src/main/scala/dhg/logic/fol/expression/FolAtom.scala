package dhg.logic.fol.expression

import dhg.logic.top.expression.Variable
import dhg.logic.base.expression.BaseAtom

object FolAtom extends BaseAtom[FolApplicationExpression, FolExpression] {

    override protected def makeVariableExpression(v: Variable) =
        FolVariableExpression(v)

}
