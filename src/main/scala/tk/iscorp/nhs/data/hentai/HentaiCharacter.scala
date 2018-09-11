package tk.iscorp.nhs.data.hentai

import org.jetbrains.annotations.{NonNls, NotNull}

class HentaiCharacter(@NonNls @NotNull override val name: String,
                      @NonNls @NotNull override val amount: Int) extends HentaiData {

}
