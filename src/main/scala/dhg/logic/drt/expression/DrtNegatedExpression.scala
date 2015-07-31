package dhg.logic.drt.expression

import dhg.logic.top.expression.Variable
import dhg.logic.base.expression.BaseNegatedExpression
import dhg.logic.drt.DrtTokens
import dhg.logic.util.StringUtils

case class DrtNegatedExpression(val term: DrtExpression)
    extends DrtExpression
    with BaseNegatedExpression[DrtExpression] {

    override def fol() =
        -this.term.fol()

    override def _folModal(world: Variable) =
        -this.term._folModal(world)

    override def getRefs(recursive: Boolean = false) =
        this.term.getRefs(recursive)

    override def pretty(): String = {
        val operator = "" +
            "__ \n" +
            "  |"
        return StringUtils.sideBySideCentering(operator, " ", term.pretty)
    }

    override def toString() =
        DrtTokens.NOT + term

}

object DrtNegatedExpression {
}
