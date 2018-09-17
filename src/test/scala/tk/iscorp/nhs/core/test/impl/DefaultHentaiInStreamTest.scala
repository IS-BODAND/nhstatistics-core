package tk.iscorp.nhs.core.test.impl

import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.core.data.Gallery
import tk.iscorp.nhs.core.data.hentai._
import tk.iscorp.nhs.core.stream.impl.DefaultHentaiInStream

import java.util

import scala.collection.JavaConverters._

@RunWith(classOf[JUnitRunner])
class DefaultHentaiInStreamTest extends WordSpec {
  private val testDoujinID1 =
    new Gallery("(C71) [Arisan-Antenna (Koari)] Eat The Rich! (Sukatto Golf Pangya)",
                "(C71) [ありさんアンテナ (小蟻)] Eat The Rich! (スカッとゴルフ パンヤ)",
                Array(new HentaiParody("pangya", 78)),
                Array(new HentaiCharacter("kooh", 41)),
                Array(new HentaiTag("lolicon", 45693),
                      new HentaiTag("catgirl", 5796),
                      new HentaiTag("gymshorts", 176)),
                Array(new HentaiArtist("koari", 46)),
                Array(new HentaiGroup("arisan-antenna", 34)),
                Array(new JapaneseHentai(129652)),
                new DoujinshiHentai(138514),
                14, "June 28, 2014, 2:12 p.m.", 1)
  private val testDoujinID2                            =
    new Gallery("(C68) [Toko-ya (HEIZO, Kitoen)] Manatsu no Oni (Higurashi no Naku Koro ni) [English]",
                "(C68) [床子屋 (HEIZO、鬼頭えん)] 真夏のオニ (ひぐらしのなく頃に) [英訳]",
                Array(new HentaiParody("higurashi no naku koro ni", 204)),
                Array(new HentaiCharacter("mion sonozaki", 41),
                      new HentaiCharacter("shion sonozaki", 35),
                      new HentaiCharacter("keiichi maebara", 28)),
                Array(new HentaiTag("big breasts", 66630),
                      new HentaiTag("schoolgirl uniform", 39065),
                      new HentaiTag("rape", 22617)),
                Array(new HentaiArtist("kitoen", 164),
                      new HentaiArtist("heizo", 110)),
                Array(new HentaiGroup("toko-ya", 168)),
                Array(new TranslatedHentai(60245),
                      new EnglishHentai(45046)),
                new DoujinshiHentai(138514),
                55, "June 28, 2014, 2:12 p.m.", 2)
  private val arrayOfTestDoujin: Array[Gallery] = Array(testDoujinID1, testDoujinID2)
  private var inStream: DefaultHentaiInStream = _
  "A DefaultHentaiInStream" ignore {
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
      assertEquals(doujin, testDoujinID1)
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
      var doujin: util.ArrayList[Gallery] = null
      val coll= List("1", "2").asJavaCollection
      doujin = inStream.readMultipleByID(new util.ArrayList[String](coll))
      assertNotNull(doujin)
    }
    "read more doujin correctly to ArrayList" in {
      val coll= List("1", "2").asJavaCollection
      val doujin = inStream.readMultipleByID(new util.ArrayList[String](coll))
      assertEquals(arrayOfTestDoujin(0), doujin.get(0))
      assertEquals(arrayOfTestDoujin(1), doujin.get(1))
    }
  }
}
