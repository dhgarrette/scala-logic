package utcompling.scalalogic.discourse.candc.boxer.expression

abstract case class BoxerExpression {

    def visit[S, R](function: BoxerExpression => S, combinator: List[S] => R, default: R): R

    def visitConstruct[S, R](function: BoxerExpression => BoxerExpression): BoxerExpression

    def refs: List[(List[BoxerIndex], BoxerVariable)] =
        this match {
            case BoxerDrs(refs, conds) =>
                refs
            case BoxerMerge(_, first, second) =>
                first.refs ++ second.refs
            case BoxerAlfa(_, first, second) =>
                first.refs ++ second.refs
        }

    def conds: List[BoxerExpression] =
        this match {
            case BoxerDrs(refs, conds) =>
                conds
            case BoxerMerge(_, first, second) =>
                first.conds ++ second.conds
            case BoxerAlfa(_, first, second) =>
                first.conds ++ second.conds
        }

    def apply(args: BoxerExpression*) =
        args.foldLeft(this)((f, a) => BoxerApp(f, a))

    def unary_- = BoxerNot("", List(), this)
    def +(other: BoxerExpression) = BoxerMerge("merge", this, other)

}
