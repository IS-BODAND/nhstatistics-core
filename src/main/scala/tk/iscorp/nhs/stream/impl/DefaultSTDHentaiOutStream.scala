package tk.iscorp.nhs.stream.impl

import org.jetbrains.annotations.{NotNull, Nullable}
import tk.iscorp.nhs.data.Gallery
import tk.iscorp.nhs.stream.HentaiOutStream

/**
  * Basic implementation of trait [[tk.iscorp.nhs.stream.HentaiOutStream]] which prints to STDOUT
  */
class DefaultSTDHentaiOutStream extends HentaiOutStream {
  /**
    * Prints a [[tk.iscorp.nhs.data.Gallery]]
    *
    * @param doujin The Gallery to be printed
    * @param append If true the current contents are not overridden if sream prints to a file
    * @throws NullPointerException If any of the parameters is `null`
    */
  override def print(doujin: Gallery, append: Boolean): Unit = print(doujin)

  /**
    * Prints a [[tk.iscorp.nhs.data.Gallery]]
    * Overrides current contents if stream prints to a file
    *
    * @param doujin The Gallery to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  override def print(doujin: Gallery): Unit = println(doujin.toString)

  /**
    * Prints a separator line between two [[tk.iscorp.nhs.data.Gallery]] instances
    */
  override protected def separatorLine(): Unit = println("\n" + "-" * 32 + "\n")

}
