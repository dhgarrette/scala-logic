package utcompling.scalalogic.discourse.candc.call.impl

import utcompling.scalalogic.discourse.candc.call._
import scala.collection.mutable.ListBuffer
import dhg.util.FileUtil
import org.junit.Test

class CandcImplTests {

  @Test
  def test() {

    val candc = CandcImpl.findBinary(Some(FileUtil.pathjoin(System.getenv("HOME"), "bin/candc/bin/")))

    println(candc.parseMultisentence(List("John is short .", "He is young .")))
    println(candc.batchParseMultisentence(List(List("John is short .", "He is young ."), List("Bill is tall .", "He is old ."))))
    println(candc.batchParseMultisentence(List(List("John is short .", "He is young ."), List("xbfd aege ertj xcvx"), List("Bill is tall .", "He is old ."))))
    println(candc.batchParseMultisentence(List(List("a man saw a dog ."))))

  }
}
