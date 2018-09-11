package tk.iscorp.nhs.data.hentai

import org.jetbrains.annotations.{NonNls, NotNull}

class HentaiGroup(@NonNls @NotNull override val name: String,
                  @NotNull override val amount: Int) extends HentaiData {

}
