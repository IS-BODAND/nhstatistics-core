package org.bitbucket.bodand.nhscore.test.stream.impl

import org.apache.commons.io.FileUtils
import org.bitbucket.bodand.nhscore.data.Gallery
import org.bitbucket.bodand.nhscore.stream.impl.FileHentaiOutStream
import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner

import java.io.File
import java.util.{ArrayList ⇒ JArrayList}

import scala.collection.JavaConverters._

@RunWith(classOf[JUnitRunner])
class FileHentaiOutStreamTest extends WordSpec {
  private val testDoujin                               =
    Gallery.dummy(name = "Test", japName = "Tesutu", uploadDate = "2018-09-15", id = 999999)
  private val file                                       = new File("testFile.bodandkeep.txt")
  private val testDoujinString           : String      = testDoujin.toString
  private val testDoujinAppendByteArray: Array[Byte] = ("\n" + "-" * 32 + "\n" + testDoujinString).getBytes
  private val testDoujinNoAppendByteArray: Array[Byte] = testDoujinString.getBytes
  private val testDoujinsList: List[Gallery] = List(Gallery.dummy(), Gallery.dummy(), Gallery.dummy(), Gallery.dummy())
  private val testDoujinsListString: String        = testDoujinsList.map(_.toString)
     .mkString("\n" + "-" * 32 + "\n")
  private val testDoujinsListNoAppendByteArray: Array[Byte]   = testDoujinsListString.getBytes()
  private val testDoujinsListAppendByteArray: Array[Byte]   = ("\n" + "-" * 32 + "\n" + testDoujinsListString).getBytes()
  private val testDoujinsArrayString: String    = testDoujinsArray.map(_.toString)
     .mkString("\n" + "-" * 32 + "\n")

  private val testDoujinsArray: Array[Gallery]           = testDoujinsList.toArray
  private val testDoujinsArrayNoAppendByteArray: Array[Byte]     = testDoujinsArrayString.getBytes()
  private val testDoujinsArrayAppendByteArray: Array[Byte]     = ("\n" + "-" * 32 + "\n" + testDoujinsArrayString)
     .getBytes()
  private val testDoujinsArrayListString: String = testDoujinsArrayList.asScala.map(_.toString)
     .mkString("\n" + "-" * 32 + "\n")

  private val testDoujinsArrayList: JArrayList[Gallery]  = new JArrayList[Gallery](testDoujinsList.asJavaCollection)
  private val testDoujinsArrayListNoAppendByteArray: Array[Byte] = testDoujinsArrayListString.getBytes
  private val testDoujinsArrayListAppendByteArray: Array[Byte] = ("\n" + "-" * 32 + "\n" + testDoujinsArrayListString).getBytes
  private var outStream: FileHentaiOutStream             = _

  "A DefaultFileHentaiOutStream" should {
    "initialize" in {
      outStream = new FileHentaiOutStream(file)
    }
    "print values" when {
      "print is used" when {
        "append flag" can afterWord("be") {
          "false" when {
            "not set" in {
              outStream.print(testDoujin)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                testDoujinNoAppendByteArray)
            }
            "set" in {
              outStream.print(testDoujin, append = false)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                testDoujinNoAppendByteArray)
            }
          }
          "true" when {
            "set" in {
              val bonusValue = "Janus Pannonius:\n"
              FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
              outStream.print(testDoujin, append = true)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                bonusValue.getBytes() ++ testDoujinAppendByteArray)
            }
          }
        }
      }
      "print with Scala List is used" when {
        "append flag" can afterWord("be") {
          "false" when {
            "not set" in {
              outStream.print(testDoujinsList)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                testDoujinsListNoAppendByteArray)
            }
            "set" in {
              outStream.print(testDoujinsList, append = false)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                testDoujinsListNoAppendByteArray)
            }
          }
          "true" when {
            "set" in {
              val bonusValue = "Janus Pannonius:\n"
              FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
              outStream.print(testDoujinsList, append = true)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                bonusValue.getBytes() ++ testDoujinsListAppendByteArray)
            }
          }
        }
      }
      "print with Java ArrayList is used" when {
        "append flag" can afterWord("be") {
          "false" when {
            "not set" in {
              outStream.print(testDoujinsArrayList)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                testDoujinsArrayListNoAppendByteArray)
            }
            "set" in {
              outStream.print(testDoujinsArrayList, append = false)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                testDoujinsArrayListNoAppendByteArray)
            }
          }
          "true" when {
            "set" in {
              val bonusValue = "Janus Pannonius:\n"
              FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
              outStream.print(testDoujinsArrayList, append = true)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                bonusValue.getBytes() ++ testDoujinsArrayListAppendByteArray)
            }
          }
        }
      }
      "print with Array is used" when {
        "append flag" can afterWord("be") {
          "false" when {
            "not set" in {
              outStream.print(testDoujinsArray)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                testDoujinsArrayNoAppendByteArray)
            }
            "set" in {
              outStream.print(testDoujinsArray, append = false)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                testDoujinsArrayNoAppendByteArray)
            }
          }
          "true" when {
            "set" in {
              val bonusValue = "Janus Pannonius:\n"
              FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
              outStream.print(testDoujinsArray, append = true)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                bonusValue.getBytes() ++ testDoujinsArrayAppendByteArray)
            }
          }
        }
      }
      "<< is used" in {
        val bonusValue = "Janus Pannonius:\n"
        FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
        outStream << testDoujin
        assertArrayEquals(FileUtils.readFileToByteArray(file),
                          bonusValue.getBytes() ++ testDoujinAppendByteArray)
      }
      "<< with Scala List is used" in {
        val bonusValue = "Janus Pannonius:\n"
        FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
        outStream << testDoujinsList
        assertArrayEquals(FileUtils.readFileToByteArray(file),
                          bonusValue.getBytes() ++ testDoujinsListAppendByteArray)
      }
      "<< with Java ArrayList is used" in {
        val bonusValue = "Janus Pannonius:\n"
        FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
        outStream << testDoujinsArrayList
        assertArrayEquals(FileUtils.readFileToByteArray(file),
                          bonusValue.getBytes() ++ testDoujinsArrayListAppendByteArray)
      }
      "<< with Array is used" in {
        val bonusValue = "Janus Pannonius:\n"
        FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
        outStream << testDoujinsArray
        assertArrayEquals(FileUtils.readFileToByteArray(file),
                          bonusValue.getBytes() ++ testDoujinsArrayAppendByteArray)
      }
    }
    "throw NPE" when {
      "print is called with (null)" in {
        assertThrows[NullPointerException]{
                                            outStream.print(null)
                                          }
      }
      "print is called with (null, true)" in {
        assertThrows[NullPointerException]{
                                            outStream.print(null, append = true)
                                          }
      }
      "print is called with (null, false)" in {
        assertThrows[NullPointerException]{
                                            outStream.print(null, append = false)
                                          }
      }
    }
 }
}
