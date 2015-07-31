package dhg.logic.fol.expression

import dhg.logic.fol._

abstract class FolBooleanExpression(override val first: FolExpression, override val second: FolExpression)
    extends FolBinaryExpression(first, second) {

    override def _pretty(): List[String] = {
        val first = this.first._pretty
        val second = this.second._pretty
        return List(FolTokens.OPEN) ++ first.dropRight(1).map("  "+_) ++ List("  " + first.last + " " + operator) ++ 
                                       second.map("  "+_) ++ List(FolTokens.CLOSE)
    }

}
