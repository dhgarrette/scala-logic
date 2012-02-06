package utcompling.scalalogic.discourse.impl

import utcompling.scalalogic.discourse.candc.call._
import utcompling.scalalogic.discourse.candc.call.impl._
import scala.collection.mutable.ListBuffer

object CandcDiscourseParserTests {
    def main(args: Array[String]): Unit = {

        val single = """# this file was generated by the following command(s):
#   /Users/dhg/workspace/candc/bin/candc --models /Users/dhg/workspace/candc/bin/../models --candc-printer grs

(xcomp _ is_1 short_2)
(ncsubj is_1 John_0 _)
<c> John|John|NNP|I-NP|I-LOC|N is|be|VBZ|I-VP|O|(S[dcl]\NP)/(S[adj]\NP) short|short|JJ|I-ADJP|O|S[adj]\NP .|.|.|O|O|.

(xcomp _ is_1 young_2)
(ncsubj is_1 He_0 _)
<c> He|he|PRP|I-NP|O|NP is|be|VBZ|I-VP|O|(S[dcl]\NP)/(S[adj]\NP) young|young|JJ|I-ADJP|O|S[adj]\NP .|.|.|O|O|.

id('0', [1, 2]).


"""
        println(new CandcDiscourseParser(new FakeCandc(single)).parseMultisentence(List("John is short .", "He is young .")))
        println(new CandcDiscourseParser(new CandcImpl(FileUtils.pathjoin(System.getenv("HOME"), "bin/candc/bin/candc"))).parse("Every man loves a woman ."))

    }

    class FakeCandc(output: String) extends Candc {
        override def batchParseMultisentence(inputs: List[List[String]], args: Map[String, String] = Map(), discourseIds: Option[Seq[String]] = None, model: Option[String] = None, verbose: Boolean = false): String =
            this.output
    }

}
