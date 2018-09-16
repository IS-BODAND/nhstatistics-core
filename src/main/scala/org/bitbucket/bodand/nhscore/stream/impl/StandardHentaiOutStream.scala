package org.bitbucket.bodand.nhscore.stream.impl

import org.bitbucket.bodand.nhscore.data.Gallery
import org.bitbucket.bodand.nhscore.stream.HentaiOutStream
import org.jetbrains.annotations.{NotNull, Nullable}

/**
  * Basic implementation of trait [[org.bitbucket.bodand.nhscore.stream.HentaiOutStream]] which prints to STDOUT
  */
class StandardHentaiOutStream extends HentaiOutStream {
  /**
    * Prints a [[org.bitbucket.bodand.nhscore.data.Gallery]]
    *
    * @param doujin The Gallery to be printed
    * @param append If true the current contents are not overridden if sream prints to a file
    * @throws NullPointerException If any of the parameters is `null`
    */
  override def print(doujin: Gallery, append: Boolean): Unit = print(doujin)

  /**
    * Prints a [[org.bitbucket.bodand.nhscore.data.Gallery]]
    * Overrides current contents if stream prints to a file
    *
    * @param doujin The Gallery to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  override def print(doujin: Gallery): Unit = println(doujin.toString)

  /**
    * Prints a separator line between two [[org.bitbucket.bodand.nhscore.data.Gallery]] instances
    */
  override protected def separatorLine(): Unit = println("\n" + "-" * 32 + "\n")

}
