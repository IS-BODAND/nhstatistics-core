package org.bitbucket.bodand.nhscore.stream.impl

import org.apache.commons.io.FileUtils
import org.bitbucket.bodand.nhscore.data.Gallery
import org.bitbucket.bodand.nhscore.stream.HentaiOutStream

import java.io.File

/**
  * Basic implementation of [[org.bitbucket.bodand.nhscore.stream.HentaiOutStream]] that prints to a File
  *
  * @param file The file the stream should print to
  */
class FileHentaiOutStream(private val file: File) extends HentaiOutStream {
  /**
    * Prints a [[org.bitbucket.bodand.nhscore.data.Gallery]]
    * Overrides current contents if stream prints to a file
    *
    * @param doujin The Gallery to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  override def print(doujin: Gallery): Unit =
    FileUtils.writeStringToFile(file, doujin.toString, "UTF-8", false)

  /**
    * Prints a [[org.bitbucket.bodand.nhscore.data.Gallery]]
    *
    * @param doujin The Gallery to be printed
    * @param append If true the current contents are not overridden if stream prints to a file
    * @throws NullPointerException If any of the parameters is `null`
    */
  override def print(doujin: Gallery, append: Boolean): Unit = {
    if (append) separatorLine()
    FileUtils.writeStringToFile(file, doujin.toString, "UTF-8", append)
  }

  /**
    * Prints a separator line between two [[org.bitbucket.bodand.nhscore.data.Gallery]] instances
    */
  override protected def separatorLine(): Unit =
    FileUtils.writeStringToFile(file, "\n" + "-" * 32 + "\n", "UTF-8", true)
}
