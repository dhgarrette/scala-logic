package utcompling.scalalogic.base.expression

trait BaseNegatedExpression[T <: BaseExpression[T]] extends BaseExpression[T] {

    val term: T

    override def visit[S, R](function: T => S, combinator: List[S] => R) =
        combinator(List(function(this.term)))

}