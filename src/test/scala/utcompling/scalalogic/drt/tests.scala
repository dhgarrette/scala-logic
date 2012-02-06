package utcompling.scalalogic.drt

import utcompling.scalalogic.drt._
import utcompling.scalalogic.top.expression.Variable
import utcompling.scalalogic.drt.expression._

object tests {
    def main(args: Array[String]): Unit = {

        DrtBoxExpression(List(Variable("y")),
            List(DrtApplicationExpression(DrtVariableExpression(Variable("woman")),
                DrtVariableExpression(Variable("x"))))).pprint

    }
}
