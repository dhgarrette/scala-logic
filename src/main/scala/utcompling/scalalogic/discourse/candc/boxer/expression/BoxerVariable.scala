package utcompling.scalalogic.discourse.candc.boxer.expression

import utcompling.scalalogic.top.expression.Variable

case class BoxerVariable(name: String) extends BoxerExpression with Ordered[BoxerVariable] {

    def compare(that: BoxerVariable): Int =
        this.name.compare(that.name)

    def visit[S, R](function: BoxerExpression => S, combinator: List[S] => R, default: R): R = 
        throw new NotDefinedError("BoxerVariable.visit() is not implemented")

    def visitConstruct[S, R](function: BoxerExpression => BoxerExpression): BoxerExpression = 
        throw new NotDefinedError("BoxerVariable.visitConstruct() is not implemented")

    override def toString() =
        name

}

object BoxerVariable {

}
