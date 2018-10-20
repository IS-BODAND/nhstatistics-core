/*******************************************************************************
 Copyright 2018 bodand

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ******************************************************************************/
package tk.iscorp.nhs.core.test.impl

import java.io.File

import org.apache.commons.io.FileUtils
import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.core.stream.impl.FileHentaiOutStream

@RunWith(classOf[JUnitRunner])
class FileHentaiOutStreamTest extends WordSpec {
  private val file = new File("testFile.bodandkeep.txt")
  private var outStream: FileHentaiOutStream = _

  import tk.iscorp.nhs.core.test.TestUtils.TestDoujin._

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
              assertArrayEquals(FileUtils.readFileToByteArray(file), testDoujinNoAppendByteArray)
            }
            "set" in {
              outStream.print(testDoujin, append = false)
              assertArrayEquals(FileUtils.readFileToByteArray(file), testDoujinNoAppendByteArray)
            }
          }
          "true" when {
            "set" in {
              val bonusValue = "Janus Pannonius:\n"
              FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
              outStream.print(testDoujin, append = true)
              assertArrayEquals(
                FileUtils.readFileToByteArray(file),
                bonusValue.getBytes() ++ testDoujinAppendByteArray
              )
            }
          }
        }
      }
      "print with Scala List is used" when {
        "append flag" can afterWord("be") {
          "false" when {
            "not set" in {
              outStream.print(testDoujinList)
              assertArrayEquals(
                FileUtils.readFileToByteArray(file),
                testDoujinListNoAppendByteArray
              )
            }
            "set" in {
              outStream.print(testDoujinList, append = false)
              assertArrayEquals(
                FileUtils.readFileToByteArray(file),
                testDoujinListNoAppendByteArray
              )
            }
          }
          "true" when {
            "set" in {
              val bonusValue = "Janus Pannonius:\n"
              FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
              outStream.print(testDoujinList, append = true)
              assertArrayEquals(
                FileUtils.readFileToByteArray(file),
                bonusValue.getBytes() ++ testDoujinListAppendByteArray
              )
            }
          }
        }
      }
      "print with Java ArrayList is used" when {
        "append flag" can afterWord("be") {
          "false" when {
            "not set" in {
              outStream.print(testDoujinArrayList)
              assertArrayEquals(
                FileUtils.readFileToByteArray(file),
                testDoujinArrayListNoAppendByteArray
              )
            }
            "set" in {
              outStream.print(testDoujinArrayList, append = false)
              assertArrayEquals(
                FileUtils.readFileToByteArray(file),
                testDoujinArrayListNoAppendByteArray
              )
            }
          }
          "true" when {
            "set" in {
              val bonusValue = "Janus Pannonius:\n"
              FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
              outStream.print(testDoujinArrayList, append = true)
              assertArrayEquals(
                FileUtils.readFileToByteArray(file),
                bonusValue.getBytes() ++ testDoujinArrayListAppendByteArray
              )
            }
          }
        }
      }
      "print with Array is used" when {
        "append flag" can afterWord("be") {
          "false" when {
            "not set" in {
              outStream.print(testDoujinArray)
              assertArrayEquals(
                FileUtils.readFileToByteArray(file),
                testDoujinArrayNoAppendByteArray
              )
            }
            "set" in {
              outStream.print(testDoujinArray, append = false)
              assertArrayEquals(
                FileUtils.readFileToByteArray(file),
                testDoujinArrayNoAppendByteArray
              )
            }
          }
          "true" when {
            "set" in {
              val bonusValue = "Janus Pannonius:\n"
              FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
              outStream.print(testDoujinArray, append = true)
              assertArrayEquals(
                FileUtils.readFileToByteArray(file),
                bonusValue.getBytes() ++ testDoujinArrayAppendByteArray
              )
            }
          }
        }
      }
      "<< is used" in {
        val bonusValue = "Janus Pannonius:\n"
        FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
        outStream << testDoujin
        assertArrayEquals(
          FileUtils.readFileToByteArray(file),
          bonusValue.getBytes() ++ testDoujinAppendByteArray
        )
      }
      "<< with Scala List is used" in {
        val bonusValue = "Janus Pannonius:\n"
        FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
        outStream << testDoujinList
        assertArrayEquals(
          FileUtils.readFileToByteArray(file),
          bonusValue.getBytes() ++ testDoujinListAppendByteArray
        )
      }
      "<< with Java ArrayList is used" in {
        val bonusValue = "Janus Pannonius:\n"
        FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
        outStream << testDoujinArrayList
        assertArrayEquals(
          FileUtils.readFileToByteArray(file),
          bonusValue.getBytes() ++ testDoujinArrayListAppendByteArray
        )
      }
      "<< with Array is used" in {
        val bonusValue = "Janus Pannonius:\n"
        FileUtils.writeStringToFile(file, bonusValue, "UTF-8")
        outStream << testDoujinArray
        assertArrayEquals(
          FileUtils.readFileToByteArray(file),
          bonusValue.getBytes() ++ testDoujinArrayAppendByteArray
        )
      }
    }
    "throw NPE" when {
      "print is called with (null)" in {
        assertThrows[NullPointerException] {
          outStream.print(null)
        }
      }
      "print is called with (null, true)" in {
        assertThrows[NullPointerException] {
          outStream.print(null, append = true)
        }
      }
      "print is called with (null, false)" in {
        assertThrows[NullPointerException] {
          outStream.print(null, append = false)
        }
      }
    }
  }
}
