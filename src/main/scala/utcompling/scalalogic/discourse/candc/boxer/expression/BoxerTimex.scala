package utcompling.scalalogic.discourse.candc.boxer.expression

case class BoxerTimex(discId: String, indices: List[BoxerIndex], variable:BoxerVariable, timeExp: BoxerExpression) extends BoxerExpression {

    def visit[S, R](function: BoxerExpression => S, combinator: List[S] => R, default: R): R = 
        default

    def visitConstruct[S, R](function: BoxerExpression => BoxerExpression): BoxerExpression = 
        BoxerTimex(discId, indices, variable, timeExp)

    def ::(index: Int) = BoxerTimex(discId, List(BoxerIndex(index)), variable, timeExp)

    override def toString() =
        "[%s]:timex(%s,%s)".format(indices.mkString(","), variable, timeExp)

}

object BoxerTimeExp {

}