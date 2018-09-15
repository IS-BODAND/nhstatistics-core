package tk.iscorp.nhs.test.stream.impl

import org.apache.commons.io.FileUtils
import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.data.Gallery
import tk.iscorp.nhs.stream.impl.FileHentaiOutStream

import java.io.File
import java.util.{ArrayList ⇒ JArrayList}

import scala.collection.JavaConverters._

@RunWith(classOf[JUnitRunner])
class FileHentaiOutStreamTest extends WordSpec {
  private val file                                       = new File("testFile.bodandkeep.txt")
  private val testDoujin                                 =
    Gallery.dummy(name = "Test", japName = "Tesutu", uploadDate = "2018-09-15", id = 999999)
  private val testDoujinByteArray: Array[Byte]           = testDoujin.toString.getBytes
  private val testDoujinsList: List[Gallery]             = List(Gallery.dummy(), Gallery.dummy(), Gallery.dummy(), Gallery.dummy())
  private val testDoujinsListByteArray: Array[Byte]      = testDoujinsList.map(_.toString)
     .mkString("", "\n" + "-" * 32 + "\n", "\n" + "-" * 32 + "\n").getBytes()
  private val testDoujinsArray: Array[Gallery]           = testDoujinsList.toArray
  private val testDoujinsArrayByteArray: Array[Byte]     = testDoujinsArray.map(_.toString)
     .mkString("", "\n" + "-" * 32 + "\n", "\n" + "-" * 32 + "\n").getBytes()
  private val testDoujinsArrayList: JArrayList[Gallery]  = new JArrayList[Gallery](testDoujinsList.asJavaCollection)
  private val testDoujinsArrayListByteArray: Array[Byte] =
    testDoujinsArrayList.asScala.map(_.toString)
       .mkString("", "\n" + "-" * 32 + "\n", "\n" + "-" * 32 + "\n").getBytes
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
                                testDoujinByteArray)
            }
            "set" in {
              outStream.print(testDoujin, append = false)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                testDoujinByteArray)
            }
          }
          "true" when {
            "set" in {
              val bonusValue = "Janus Pannonius:\n"
              FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
              outStream.print(testDoujin, append = true)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                bonusValue.getBytes() ++ testDoujinByteArray)
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
                                testDoujinsListByteArray)
            }
            "set" in {
              outStream.print(testDoujinsList, append = false)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                testDoujinsListByteArray)
            }
          }
          "true" when {
            "set" in {
              val bonusValue = "Janus Pannonius:\n"
              FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
              outStream.print(testDoujinsList, append = true)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                bonusValue.getBytes() ++ testDoujinsListByteArray)
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
                                testDoujinsArrayListByteArray)
            }
            "set" in {
              outStream.print(testDoujinsArrayList, append = false)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                testDoujinsArrayListByteArray)
            }
          }
          "true" when {
            "set" in {
              val bonusValue = "Janus Pannonius:\n"
              FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
              outStream.print(testDoujinsArrayList, append = true)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                bonusValue.getBytes() ++ testDoujinsArrayListByteArray)
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
                                testDoujinsArrayByteArray)
            }
            "set" in {
              outStream.print(testDoujinsArray, append = false)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                testDoujinsArrayByteArray)
            }
          }
          "true" when {
            "set" in {
              val bonusValue = "Janus Pannonius:\n"
              FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
              outStream.print(testDoujinsArray, append = true)
              assertArrayEquals(FileUtils.readFileToByteArray(file),
                                bonusValue.getBytes() ++ testDoujinsArrayByteArray)
            }
          }
        }
      }
      "<< is used" in {
        val bonusValue = "Janus Pannonius:\n"
        FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
        outStream << testDoujin
        assertArrayEquals(FileUtils.readFileToByteArray(file),
                          bonusValue.getBytes() ++ testDoujinByteArray)
      }
      "<< with Scala List is used" in {
        val bonusValue = "Janus Pannonius:\n"
        FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
        outStream << testDoujinsList
        assertArrayEquals(FileUtils.readFileToByteArray(file),
                          bonusValue.getBytes() ++ testDoujinsListByteArray)
      }
      "<< with Java ArrayList is used" in {
        val bonusValue = "Janus Pannonius:\n"
        FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
        outStream << testDoujinsArrayList
        assertArrayEquals(FileUtils.readFileToByteArray(file),
                          bonusValue.getBytes() ++ testDoujinsArrayListByteArray)
      }
      "<< with Array is used" in {
        val bonusValue = "Janus Pannonius:\n"
        FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
        outStream << testDoujinsArray
        assertArrayEquals(FileUtils.readFileToByteArray(file),
                          bonusValue.getBytes() ++ testDoujinsArrayByteArray)
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
