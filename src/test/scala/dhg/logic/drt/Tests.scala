package dhg.logic.drt

import dhg.logic.drt._
import dhg.logic.top.expression.Variable
import dhg.logic.drt.expression._
import org.junit.Test

class Tests {

  @Test
  def test() {

    DrtBoxExpression(List(Variable("y")),
      List(DrtApplicationExpression(DrtVariableExpression(Variable("woman")),
        DrtVariableExpression(Variable("x"))))).pprint

  }
}
