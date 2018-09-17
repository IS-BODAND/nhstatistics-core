package org.bitbucket.bodand.nhscore.stream

import org.bitbucket.bodand.nhscore.data.Gallery
import org.jetbrains.annotations.{NonNls, NotNull}

import java.util.{ArrayList ⇒ JArrayList}

import scala.collection.JavaConverters._
import scala.language.postfixOps

/**
  * Trait that defines basic functions to print Gallery objects
  */
trait HentaiOutStream {
  /**
    * Prints a [[org.bitbucket.bodand.nhscore.data.Gallery]]
    * Overrides current contents if stream prints to a file
    * @param doujin The Gallery to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujin: Gallery): Unit

  /**
    * Prints a [[org.bitbucket.bodand.nhscore.data.Gallery]]
    * @param doujin The Gallery to be printed
    * @param append If true the current contents are not overridden if stream prints to a file
    *
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujin: Gallery, @NotNull append: Boolean): Unit

  /**
    * Prints a Scala List of [[org.bitbucket.bodand.nhscore.data.Gallery]]
    * @param doujinList The list to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujinList: List[Gallery]): Unit = {
    print(doujinList, append = false)
  }

  /**
    * Prints a Scala List of [[org.bitbucket.bodand.nhscore.data.Gallery]]
    * Overrides current data if writing to file
    * @param doujinList The list to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujinList: List[Gallery], append: Boolean): Unit = {
    print(doujinList.head, append)
    doujinList.tail foreach <<
  }

  /**
    * Prints a Java ArrayList of [[org.bitbucket.bodand.nhscore.data.Gallery]]
    * Overrides current data if writing to file
    * @param doujinList The list to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujinList: JArrayList[Gallery]): Unit = {
    print(doujinList, append = false)
  }

  /**
    * Prints a Java ArrayList of [[org.bitbucket.bodand.nhscore.data.Gallery]]
    * @param doujinJList The list to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujinJList: JArrayList[Gallery], append: Boolean): Unit = {
    val usableCollection = doujinJList.asScala.toList
    print(usableCollection.head, append)
    usableCollection.tail foreach <<
  }

  /**
    * Prints an Array of [[org.bitbucket.bodand.nhscore.data.Gallery]]
    * Overrides current data if writing to file
    * @param doujins The array to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujins: Array[Gallery]): Unit = {
    print(doujins, append = false)
  }

  /**
    * Prints an Array of [[org.bitbucket.bodand.nhscore.data.Gallery]]
    * @param doujins The array to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujins: Array[Gallery], append: Boolean): Unit = {
    print(doujins.head, append)
    doujins.tail foreach <<
  }

  /**
    * Prints a Java ArrayList of [[org.bitbucket.bodand.nhscore.data.Gallery]]
    * @param doujinList The list to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def <<(@NotNull doujinList: JArrayList[Gallery]): HentaiOutStream = {
    doujinList.asScala foreach <<
    this
  }

  /**
    * Same as print, but
    * doesn't override previous file value
    * @param doujin The doujin to be printed
    *
    * @throws NullPointerException If any of the parameters is `null`
    * @see [[HentaiOutStream#print(Gallery)]]
    */
  def <<(@NotNull doujin: Gallery): HentaiOutStream = {
    this.print(doujin, append = true)
    this
  }

  /**
    * Prints a Scala List of [[org.bitbucket.bodand.nhscore.data.Gallery]]
    * @param doujinList The list to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def <<(@NotNull doujinList: List[Gallery]): HentaiOutStream = {
    doujinList foreach <<
    this
  }

  /**
    * Prints an Array of [[org.bitbucket.bodand.nhscore.data.Gallery]]
    * @param doujins The array to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def <<(@NotNull doujins: Array[Gallery]): HentaiOutStream = {
    doujins foreach <<
    this
  }

  /**
    * Prints a separator line between two [[org.bitbucket.bodand.nhscore.data.Gallery]] instances
    */
  protected def separatorLine(): Unit
}
