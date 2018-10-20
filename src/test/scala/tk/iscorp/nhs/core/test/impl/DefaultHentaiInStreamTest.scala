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

import java.util

import scala.collection.JavaConverters._

import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.core.data.Gallery
import tk.iscorp.nhs.core.stream.impl.DefaultHentaiInStream
import tk.iscorp.nhs.core.test.TestUtils

@RunWith(classOf[JUnitRunner])
class DefaultHentaiInStreamTest extends WordSpec {
  private val testDoujinID1 = TestUtils.galleryId1

  private val testDoujinID2 = TestUtils.galleryId2

  private val arrayOfTestDoujin: Array[Gallery] =
    Array(testDoujinID1, testDoujinID2)
  private var inStream: DefaultHentaiInStream = _
  "A DefaultHentaiInStream" should {
    "initialize" in {
      inStream = new DefaultHentaiInStream
    }
    "read one doujin" in {
      var doujin: Gallery = null
      doujin = inStream.readByID("1")
      assertNotNull(doujin)
    }
    "read one doujin correctly" in {
      val doujin = inStream.readByID("1")
      assertEquals(testDoujinID1, doujin)
    }
    "read more doujin to array" in {
      var doujin: Array[Gallery] = null
      doujin = inStream.readMultipleByID(Array("1", "2"))
      assertNotNull(doujin)
    }
    "read more doujin correctly to array" in {
      val doujin = inStream.readMultipleByID(Array("1", "2"))
      assertEquals(arrayOfTestDoujin(0), doujin(0))
      assertEquals(arrayOfTestDoujin(1), doujin(1))
    }
    "read more doujin to List" in {
      var doujin: List[Gallery] = null
      doujin = inStream.readMultipleByID(List("1", "2"))
      assertNotNull(doujin)
    }
    "read more doujin correctly to List" in {
      val doujin = inStream.readMultipleByID(Array("1", "2"))
      assertEquals(arrayOfTestDoujin(0), doujin(0))
      assertEquals(arrayOfTestDoujin(1), doujin(1))
    }
    "read more doujin to ArrayList" in {
      var doujin: util.List[Gallery] = null
      val coll = List("1", "2").asJavaCollection
      doujin = inStream.readMultipleByID(new util.ArrayList[String](coll))
      assertNotNull(doujin)
    }
    "read more doujin correctly to ArrayList" in {
      val coll = List("1", "2").asJavaCollection
      val doujin = inStream.readMultipleByID(new util.ArrayList[String](coll))
      assertEquals(arrayOfTestDoujin(0), doujin.get(0))
      assertEquals(arrayOfTestDoujin(1), doujin.get(1))
    }
  }
}
