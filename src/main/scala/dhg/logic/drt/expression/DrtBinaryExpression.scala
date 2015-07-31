package dhg.logic.drt.expression

import dhg.logic.top.expression.Variable
import dhg.logic.base.expression.BaseBinaryExpression
import dhg.logic.drt.DrtTokens
import dhg.logic.util.StringUtils

abstract class DrtBinaryExpression(val first: DrtExpression, val second: DrtExpression)
    extends DrtExpression
    with BaseBinaryExpression[DrtExpression] {

    val operator: String

    override def getRefs(recursive: Boolean = false) =
        if (recursive)
            this.first.getRefs(true) ++ this.second.getRefs(true)
        else
            Set()

    override def pretty() =
        StringUtils.sideBySideCentering(DrtTokens.OPEN, first.pretty, " ", this.operator, " ", second.pretty, DrtTokens.CLOSE)

}
