package dhg.logic.fol.expression

import dhg.logic.top.expression.Variable
import dhg.logic.base.expression.BaseVariableBinderExpression
import dhg.logic.fol._

abstract class FolVariableBinderExpression(override val operator: String, override val variable: Variable, override val term: FolExpression)
    extends FolExpression
    with BaseVariableBinderExpression[FolExpression] {

    override def toString(): String = {
        val (vars, baseterm) = this.getAllSameScopeBoundVariables()
        return operator + " " + vars.map(_.name).mkString(" ") + FolTokens.DOT + baseterm
    }

    override def _pretty(): List[String] = {
        val (vars, baseterm) = this.getAllSameScopeBoundVariables()
        return (operator + " " + vars.sortWith(_ < _).map(_.name).mkString(" ") + FolTokens.DOT) +: baseterm._pretty.map("  "+_)
    }

}

object FolVariableBinderExpression {
  def unapply(e: FolExpression) = {
    e match {
      case vbe: FolVariableBinderExpression => Some((vbe.operator, vbe.variable, vbe.term)) 
      case _ => None
    }
  }
}
