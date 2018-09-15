package tk.iscorp.nhs.test

import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestTest extends WordSpec {
 "A test" should {
   "work" when afterWord("used with") {
     "ScalaTest Assertions assertions" in {
        succeed
     }
     "jUnit Assert assertions" in {
       assertTrue(true)
     }
   }
 }
}
