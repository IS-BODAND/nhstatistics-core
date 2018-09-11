package tk.iscorp.nhs.data.hentai

import org.jetbrains.annotations.{NonNls, NotNull}

trait HentaiData {
  @NonNls
  @NotNull
  def name: String

  @NotNull
  def amount: Int

  @NonNls
  @NotNull
  override def toString: String = {
    s"[$name ($amount)]"
  }
}
