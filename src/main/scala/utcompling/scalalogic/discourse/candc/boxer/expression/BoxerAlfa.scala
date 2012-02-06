package utcompling.scalalogic.discourse.candc.boxer.expression

case class BoxerAlfa(variable: BoxerVariable, first: BoxerExpression, second: BoxerExpression) extends BoxerExpression {

    def visit[S, R](function: BoxerExpression => S, combinator: List[S] => R, default: R): R = 
        combinator(List(function(first), function(second)))

    def visitConstruct[S, R](function: BoxerExpression => BoxerExpression): BoxerExpression = 
        BoxerAlfa(variable, function(first), function(second))

    override def toString(): String =
        "alfa(%s,%s,%s)".format(variable.name, first, second)

}

object BoxerAlfa {

}