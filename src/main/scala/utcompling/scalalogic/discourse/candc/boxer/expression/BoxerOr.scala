package utcompling.scalalogic.discourse.candc.boxer.expression

case class BoxerOr(discId: String, indices: List[BoxerIndex], first: BoxerExpression, second: BoxerExpression) extends BoxerExpression {

    def visit[S, R](function: BoxerExpression => S, combinator: List[S] => R, default: R): R = 
        combinator(List(function(first), function(second)))

    def visitConstruct[S, R](function: BoxerExpression => BoxerExpression): BoxerExpression = 
        BoxerOr(discId, indices, function(first), function(second))

    override def toString() =
        "[%s]:or(%s,%s)".format(indices.mkString(","), first, second)

}

object BoxerOr {

}