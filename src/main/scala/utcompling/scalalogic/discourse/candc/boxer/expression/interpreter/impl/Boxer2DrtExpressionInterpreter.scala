package utcompling.scalalogic.discourse.candc.boxer.expression.interpreter.impl

import utcompling.scalalogic.discourse.candc.boxer.expression.interpreter._
import utcompling.scalalogic.discourse.candc.boxer.expression._
import utcompling.scalalogic.drt.expression._
import utcompling.scalalogic.top.expression.Variable

class Boxer2DrtExpressionInterpreter extends BaseBoxerExpressionInterpreter[DrtExpression] {

    override protected def interpretBoxerAlfa(variable: BoxerVariable, first: BoxerExpression, second: BoxerExpression): DrtExpression =
        this.interpret(first) + this.interpret(second)

    override protected def interpretBoxerDrs(refs: List[(List[BoxerIndex], BoxerVariable)], conds: List[BoxerExpression]): DrtExpression =
        DrtBoxExpression(refs.map(v => Variable(v._2.name)), conds.map(this.interpret))

    override protected def interpretBoxerEq(discId: String, indices: List[BoxerIndex], first: BoxerVariable, second: BoxerVariable): DrtExpression =
        DrtEqualityExpression(DrtVariableExpression(Variable(first.name)), DrtVariableExpression(Variable(second.name)))

    override protected def interpretBoxerImp(discId: String, indices: List[BoxerIndex], first: BoxerExpression, second: BoxerExpression): DrtExpression =
        this.interpret(first) -> this.interpret(second)

    override protected def interpretBoxerMerge(pred: String, first: BoxerExpression, second: BoxerExpression): DrtExpression =
        this.interpret(first) + this.interpret(second)

    override protected def interpretBoxerNamed(discId: String, indices: List[BoxerIndex], variable: BoxerVariable, name: String, typ: String, sense: Int): DrtExpression =
        DrtAtom(Variable("%s_%s".format(name, typ)), Variable(variable.name))

    override protected def interpretBoxerNot(discId: String, indices: List[BoxerIndex], drs: BoxerExpression): DrtExpression =
        -this.interpret(drs)

    override protected def interpretBoxerPred(discId: String, index: List[BoxerIndex], variable: BoxerVariable, name: String, pos: String, sense: Int): DrtExpression =
        DrtAtom(Variable("%s".format(name)), Variable(variable.name))

    override protected def interpretBoxerProp(discId: String, indices: List[BoxerIndex], variable: BoxerVariable, drs: BoxerExpression): DrtExpression =
        DrtPropositionExpression(Variable(variable.name), this.interpret(drs))

    override protected def interpretBoxerRel(discId: String, indices: List[BoxerIndex], event: BoxerVariable, variable: BoxerVariable, name: String, sense: Int): DrtExpression =
        DrtAtom(Variable("%s".format(name)), Variable(event.name), Variable(variable.name))
    
    override protected def interpretBoxerCard(discId: String, index: List[BoxerIndex], variable: BoxerVariable, num: String, sense: String): DrtExpression =
        DrtAtom(Variable("%s".format(num)), Variable(variable.name))
        
    override protected def interpretBoxerOr(discId: String, indices: List[BoxerIndex], first: BoxerExpression, second: BoxerExpression): DrtExpression =
        this.interpret(first) -> this.interpret(second)
        
    override protected def interpretBoxerTimex(discId: String, index: List[BoxerIndex], variable: BoxerVariable, timeExp: BoxerExpression): DrtExpression =
        this.interpret(timeExp)
    override protected def interpretBoxerDate(indicesPol: List[BoxerIndex], pol: String, indicesYear: List[BoxerIndex], year: String, indicesMonth: List[BoxerIndex], month: String, indicesDay:List[BoxerIndex], day: String): DrtExpression =
        DrtAtom(Variable("%s".format(pol)), Variable("%s".format(year)), Variable("%s".format(month)), Variable("%s".format(day)))
}

object Boxer2DrtExpressionInterpreter {
    def apply() = new Boxer2DrtExpressionInterpreter()
}
