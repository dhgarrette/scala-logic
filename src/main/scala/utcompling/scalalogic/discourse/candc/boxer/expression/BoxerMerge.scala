package utcompling.scalalogic.discourse.candc.boxer.expression

case class BoxerMerge(pred: String, first: BoxerExpression, second: BoxerExpression) extends BoxerExpression {

    def visit[S, R](function: BoxerExpression => S, combinator: List[S] => R, default: R): R = 
        combinator(List(function(first), function(second)))

    def visitConstruct[S, R](function: BoxerExpression => BoxerExpression): BoxerExpression = 
        BoxerMerge(pred, function(first), function(second))

    override def toString(): String =
        "%s(%s,%s)".format(pred, first, second)

}

object BoxerMerge {

}