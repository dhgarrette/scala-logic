package dhg.logic.drt.expression

import dhg.logic.drt._
import dhg.logic.top.expression.Variable
import dhg.logic.base.expression.BaseLambdaExpression
import scala.collection.mutable.ListBuffer
import dhg.logic.util.StringUtils

case class DrtLambdaExpression(val variable: Variable, val term: DrtExpression)
    extends DrtExpression
    with BaseLambdaExpression[DrtExpression] {

    override val operator = DrtTokens.LAMBDA

    override def getRefs(recursive: Boolean = false): Set[Variable] =
        throw new RuntimeException("Not Implemented")

    override def fol() =
        this.term.fol() lambda this.variable

    override def _folModal(world: Variable) =
        this.term._folModal(world) lambda this.variable

    override def toString(): String = {
        val (vars, baseterm) = this.getAllSameScopeBoundVariables()
        return operator + vars.map(_.name).mkString(" ") + DrtTokens.DOT + baseterm
    }

    override def pretty(): String = {
        val (vars, baseterm) = this.getAllSameScopeBoundVariables()
        val operator = "" +
            " \\  \n" +
            " /\\ "
        return StringUtils.sideBySideCentering(operator, vars.map(_.name).mkString(" "), DrtTokens.DOT, baseterm.pretty)
    }

}

object DrtLambdaExpression {
}
