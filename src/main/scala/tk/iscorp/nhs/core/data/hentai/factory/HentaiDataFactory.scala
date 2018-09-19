/*******************************************************************************
 InfoSoft OpenSource Licence

 Copyright (c) 2018.

 Permission is hereby granted to absolutely free usage of this software in any way
 that doesn't conflict with the the licensee's local laws. Modification and
 redistribution of this software is permitted, but the changes must be stated, and
 the source software (this one) must be stated. Redistributed versions must be
 licensed under the InfoSoft OpenSource Licence. Projects using a (modified or not)
 version of this software, may or may not use the InfoSoft OpenSource Licence.
 Commercial distribution is permitted. This licence must be made available to the
 end user from within the program, and to all programmers from a IS-OSL.LICENCE.txt file.
 Inclusion of the licence in the source file(s) may be used instead of the IS-OSL.LICENCE.txt file.

 ******************************************************************************/

package tk.iscorp.nhs.core.data.hentai.factory

import tk.iscorp.nhs.core.data.hentai.HentaiData

/**
  * Factory for generating HentaiData implementor objects
  *
  * @tparam T Type of HentaiData to generate.
  */
trait HentaiDataFactory[T <: HentaiData] {
  /**
    * Constructs a new HentaiData implementor object
    *
    * @param name   Name of the HentaiData implementor to store
    * @param amount Amount of doujin associated with the HentaiData implementor
    * @param id     Implicit. ID of the doujin this HentaiData is got from.
    *               Only used in logging.
    *
    * @return A new HentaiData object
    */
  def construct(name: String, amount: Int)(implicit id: Int): T
}
