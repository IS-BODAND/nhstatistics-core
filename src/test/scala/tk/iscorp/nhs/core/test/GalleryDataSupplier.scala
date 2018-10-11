package tk.iscorp.nhs.core.test

import java.time.OffsetDateTime

import tk.iscorp.nhs.core.data.Gallery
import tk.iscorp.nhs.core.data.hentai._

object GalleryDataSupplier {
  def no1 =
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
                14,
                OffsetDateTime.parse("2014-06-28T14:12:16.640420+00:00"),
                1,
                9)

  def no1(boolean: Boolean) =
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
                14,
                OffsetDateTime.parse("2014-06-28T14:12:16.640420+00:00"),
                1,
                9)

  def no2 =
    new Gallery("(C68) [Toko-ya (HEIZO, Kitoen)] Manatsu no Oni (Higurashi no Naku Koro ni) [English]",
                "(C68) [床子屋 (HEIZO、鬼頭えん)] 真夏のオニ (ひぐらしのなく頃に) [英訳]",
                Array(new HentaiParody("higurashi no naku koro ni", 204)),
                Array(new HentaiCharacter("mion sonozaki", 41),
                      new HentaiCharacter("shion sonozaki", 35),
                      new HentaiCharacter("keiichi maebara", 28)),
                Array(new HentaiTag("big breasts", 66630),
                      new HentaiTag("schoolgirl uniform", 39065),
                      new HentaiTag("rape", 22617)),
                Array(new HentaiArtist("kitoen", 164), new HentaiArtist("heizo", 110)),
                Array(new HentaiGroup("toko-ya", 168)),
                Array(new TranslatedHentai(60245), new EnglishHentai(45046)),
                new DoujinshiHentai(138514),
                55,
                OffsetDateTime.parse("2014-06-28T14:12:16.640420+00:00"),
                2,
                136)
}
