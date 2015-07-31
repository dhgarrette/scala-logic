package dhg.logic.fol.expression

import dhg.logic.top.expression.Variable
import dhg.logic.base.expression.BaseExpression
import dhg.logic.top.expression.Expression
import dhg.logic.util._
import dhg.logic.fol._

abstract class FolExpression
    extends Expression
    with BaseExpression[FolExpression] {

    def unary_- = FolNegatedExpression(this)
    def &(other: FolExpression) = FolAndExpression(this, other)
    def |(other: FolExpression) = FolOrExpression(this, other)
    def ->(other: FolExpression) = FolIfExpression(this, other)
    def <->(other: FolExpression) = FolIffExpression(this, other)
    def all(vars: Variable*): FolExpression = this all vars
    def all(vars: TraversableOnce[Variable]): FolExpression = vars.foldRight(this)(FolAllExpression(_, _))
    def exists(vars: Variable*): FolExpression = this exists vars
    def exists(vars: TraversableOnce[Variable]): FolExpression = vars.foldRight(this)(FolExistsExpression(_, _))
    def lambda(vars: Variable*): FolExpression = this lambda vars
    def lambda(vars: TraversableOnce[Variable]): FolExpression = vars.foldRight(this)(FolLambdaExpression(_, _))

    override def applyto(other: FolExpression): FolExpression =
        FolApplicationExpression(this, other)

    override def makeVariableExpression(variable: Variable): FolVariableExpression =
        FolVariableExpression(variable)

    def pprint() =
        println(this.pretty())

    def pretty() =
        this._pretty().mkString("\n")

    def _pretty(): List[String] =
        List(this.toString)

}
