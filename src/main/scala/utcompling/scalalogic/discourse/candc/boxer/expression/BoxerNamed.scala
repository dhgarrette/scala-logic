package utcompling.scalalogic.discourse.candc.boxer.expression

case class BoxerNamed(discId: String, indices: List[BoxerIndex], variable: BoxerVariable, name: String, typ: String, sense: Int) extends BoxerExpression {

    def visit[S, R](function: BoxerExpression => S, combinator: List[S] => R, default: R): R = 
        default

    def visitConstruct[S, R](function: BoxerExpression => BoxerExpression): BoxerExpression = 
        BoxerNamed(discId, indices, variable, name, typ, sense)

    override def toString(): String =
        "[%s]:named(%s,%s,%s,%d)".format(indices.mkString(","), variable.name, name, typ, sense)

}

object BoxerNamed {

}