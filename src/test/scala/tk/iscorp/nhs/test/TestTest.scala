package tk.iscorp.nhs.test

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Assertions, WordSpec}
import org.junit.Assert._

@RunWith(classOf[JUnitRunner])
class TestTest extends WordSpec {
 "A test" should {
   "work" when afterWord("used with") {
     "Scalatest Assertions assertsions" in {
       Assertions.succeed
     }
     "jUnit Assert assertions" in {
       assertTrue(true)
     }
   }
 }
}
