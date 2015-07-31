package dhg.logic.drt.expression

import dhg.logic.top.expression.Variable
import dhg.logic.base.expression.BaseVariableExpression
import dhg.logic.fol.expression.FolVariableExpression

case class DrtVariableExpression(variable: Variable)
    extends DrtExpression
    with BaseVariableExpression[DrtExpression] {

    override def fol() =
        FolVariableExpression(this.variable)

    override def _folModal(world: Variable) =
        this.fol()

    override def getRefs(recursive: Boolean = false): Set[Variable] =
        Set[Variable]()

    override def pretty() =
        this.toString

    override def eliminateEquality(): DrtVariableExpression =
        this

}
