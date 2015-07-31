package dhg.logic.discourse.candc.boxer.expression.interpreter

import dhg.logic.base.expression.BaseExpression
import dhg.logic.discourse.candc.boxer.expression.BoxerExpression

abstract class BoxerExpressionInterpreter[T] {

    def interpret(ex: BoxerExpression): T
    
}