package dhg.logic.inference.impl

import dhg.logic.fol.expression.parse.FolLogicParser
import dhg.util._
import org.junit.Test

class Prover9TheoremProverTests {

  @Test
  def test() {

    val tp = Prover9TheoremProver.findBinary(Some(pathjoin(System.getenv("HOME"), "bin/LADR-2009-11A/bin/")), timeout = 5)
    val p = new FolLogicParser().parse(_)

    println(tp.prove(List(p("all x.(man(x) -> mortal(x))"), p("man(socrates)")), p("mortal(socrates)")).get); println()

    println(tp.prove(List(p("all x.(man(x) -> mortal(x))"), p("mortal(socrates)")), p("man(socrates)"))); println()

    println(try {
      tp.prove(List(p("\\ x.(man(x) -> mortal(x))"), p("man(socrates)")), p("mortal(socrates)"))
    } catch { case e => e.getMessage.split("\n")(0) }); println()

  }
}
