package tk.iscorp.nhs.data.hentai

import org.jetbrains.annotations.{NonNls, NotNull}

sealed abstract case class HentaiLanguage() extends HentaiData

class EnglishHentai(@NonNls @NotNull override val name: String,
                    @NonNls @NotNull override val amount: Int) extends HentaiLanguage

class JapaneseHentai(@NonNls @NotNull override val name: String,
                     @NonNls @NotNull override val amount: Int) extends HentaiLanguage

class ChineseHentai(@NonNls @NotNull override val name: String,
                    @NonNls @NotNull override val amount: Int) extends HentaiLanguage

class OtherLanguageHentai(@NonNls @NotNull override val name: String,
                          @NonNls @NotNull override val amount: Int) extends HentaiLanguage
