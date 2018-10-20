package tk.iscorp.nhs.core.test.impl

import java.io.{ByteArrayOutputStream, PrintStream}

import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.core.stream.impl.StandardHentaiOutStream

@RunWith(classOf[JUnitRunner])
class StandardHentaiOutStreamTest extends WordSpec {
  private val originalSTDOUT = System.out
  private val byteArrayOutStream = new ByteArrayOutputStream()
  private val testSTDOUT = new PrintStream(byteArrayOutStream)
  private var alreadyBackRead = ""
  private var allBackRead = ""

  private var standardOutStream: StandardHentaiOutStream = _

  import tk.iscorp.nhs.core.test.TestUtils.TestDoujin._

  "The JVM" should afterWord("allow") {
    "the redirection of System.out" in {
      System.setOut(testSTDOUT)
    }
    "from rereading from the redirected System.out" when {
      "the Java way is used" in {
        val testLine = "Java STDOUT Redirect test-line"
        System.out.println(testLine)
        val backReadOutput = readBackFromRedirectedSTDOUT
        assertEquals(testLine, backReadOutput)
      }
      "the Scala way is used" in {
        val testLine = "Scala STDOUT Redirect test-line"
        println(testLine)
        val backReadOutput = readBackFromRedirectedSTDOUT
        assertEquals(testLine, backReadOutput)
      }
    }
  }

  "A StandardHentaiOutStream" when {
    "assigning to variable" should afterWord("and that variable is") {
      "newly defined" in {
        val tmp = new StandardHentaiOutStream
        assertNotNull(tmp)
      }
      "already defined" in {
        standardOutStream = new StandardHentaiOutStream
        assertNotNull(standardOutStream)
      }
    }
    "printing" should {
      "print values" when {
        "print is used" in {
          standardOutStream.print(testDoujin)
          assertArrayEquals(testDoujinNoAppendByteArray,
                            readBackFromRedirectedSTDOUT.getBytes)
        }
        "print with Scala List is used" in {
          standardOutStream.print(testDoujinList)
          assertEquals(
            testDoujinListNoAppendByteArray.map(c ⇒ c.toChar).mkString.trim,
            readBackFromRedirectedSTDOUT
          )
        }
        "print with Java ArrayList is used" in {
          standardOutStream.print(testDoujinArrayList)
          assertArrayEquals(testDoujinArrayListNoAppendByteArray,
                            readBackFromRedirectedSTDOUT.getBytes)
        }
        "print with Array is used" in {
          standardOutStream.print(testDoujinArray)
          assertArrayEquals(testDoujinArrayNoAppendByteArray,
                            readBackFromRedirectedSTDOUT.getBytes)
        }
        "<< is used" in {
          val bonusValue = "Janus Pannonius:\n"
          print(bonusValue)
          standardOutStream << testDoujin
          assertArrayEquals(bonusValue.getBytes() ++ testDoujinAppendByteArray,
                            readBackFromRedirectedSTDOUT.getBytes)
        }
        "<< with Scala List is used" in {
          val bonusValue = "Janus Pannonius:\n"
          print(bonusValue)
          standardOutStream << testDoujinList
          assertArrayEquals(bonusValue.getBytes() ++ testDoujinListAppendByteArray,
                            readBackFromRedirectedSTDOUT.getBytes)
        }
        "<< with Java ArrayList is used" in {
          val bonusValue = "Janus Pannonius:\n"
          print(bonusValue)
          standardOutStream << testDoujinArrayList
          assertArrayEquals(
            bonusValue.getBytes() ++ testDoujinArrayListAppendByteArray,
            readBackFromRedirectedSTDOUT.getBytes
          )
        }
        "<< with Array is used" in {
          val bonusValue = "Janus Pannonius:\n"
          print(bonusValue)
          standardOutStream << testDoujinArray
          assertArrayEquals(bonusValue.getBytes() ++ testDoujinArrayAppendByteArray,
                            readBackFromRedirectedSTDOUT.getBytes)
        }
      }
      "throw NPE" when {
        "print is called with (null)" in {
          assertThrows[NullPointerException] {
            standardOutStream.print(null)
          }
        }
        "print is called with (null, true)" in {
          assertThrows[NullPointerException] {
            standardOutStream.print(null, append = true)
          }
        }
        "print is called with (null, false)" in {
          assertThrows[NullPointerException] {
            standardOutStream.print(null, append = false)
          }
        }
      }
    }
  }

  "The JVM" should {
    "allow the redefinition of System.out to the original" in {
      System.setOut(originalSTDOUT)
    }
  }

  private def readBackFromRedirectedSTDOUT: String =
  {
    allBackRead = byteArrayOutStream.toString()
    val newlyBackRead = getNotYetBackRead
    alreadyBackRead = allBackRead
    newlyBackRead.trim
  }

  private def getNotYetBackRead: String = allBackRead.substring(alreadyBackRead.length)
}
