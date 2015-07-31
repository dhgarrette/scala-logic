package dhg.logic.drt.expression

import dhg.logic.top.expression.Variable

abstract class DrtBooleanExpression(override val first: DrtExpression, override val second: DrtExpression)
    extends DrtBinaryExpression(first, second) {

}
