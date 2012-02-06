package utcompling.scalalogic.discourse.candc.boxer.expression

case class BoxerEq(discId: String, indices: List[BoxerIndex], first: BoxerVariable, second: BoxerVariable) extends BoxerExpression {

    def visit[S, R](function: BoxerExpression => S, combinator: List[S] => R, default: R): R = 
        default

    def visitConstruct[S, R](function: BoxerExpression => BoxerExpression): BoxerExpression = 
        BoxerEq(discId, indices, first, second)

    override def toString(): String =
        "[%s]:eq(%s,%s)".format(indices.mkString(","), first.name, second.name)

}

object BoxerEq {
    
}