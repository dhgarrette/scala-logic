package dhg.logic.fol.expression

import dhg.logic.fol._
import dhg.logic.base.expression.BaseBinaryExpression

abstract class FolBinaryExpression(override val first: FolExpression, override val second: FolExpression)
    extends FolExpression
    with BaseBinaryExpression[FolExpression] {

}
