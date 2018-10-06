/** *****************************************************************************
  * Copyright 2018 bodand
  * *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  * *
  * http://www.apache.org/licenses/LICENSE-2.0
  * *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  * *****************************************************************************/
package tk.iscorp.nhs.core.stream

import java.util.{List ⇒ JavaList}

import scala.collection.JavaConverters._
import scala.language.postfixOps

import org.jetbrains.annotations.{NonNls, NotNull}
import tk.iscorp.nhs.core.data.Gallery

/**
  * Trait that defines basic functions to print Gallery objects
  */
trait HentaiOutStream {

  /**
    * Prints a [[tk.iscorp.nhs.core.data.Gallery]]
    * Overrides current contents if stream prints to a file
    *
    * @param doujin The Gallery to be printed
    *
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujin: Gallery): Unit

  /**
    * Prints a [[tk.iscorp.nhs.core.data.Gallery]]
    *
    * @param doujin The Gallery to be printed
    * @param append If true the current contents are not overridden if stream prints to a file
    *
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujin: Gallery, @NotNull append: Boolean): Unit

  /**
    * Prints a Scala List of [[tk.iscorp.nhs.core.data.Gallery]]
    *
    * @param doujinList The list to be printed
    *
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujinList: List[Gallery]): Unit =
    print(doujinList, append = false)

  /**
    * Prints a Scala List of [[tk.iscorp.nhs.core.data.Gallery]]
    * Overrides current data if writing to file
    *
    * @param doujinList The list to be printed
    *
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujinList: List[Gallery], append: Boolean): Unit =
  {
    print(doujinList.head, append)
    doujinList.tail foreach <<
  }

  /**
    * Prints a Java List of [[tk.iscorp.nhs.core.data.Gallery]]
    * Overrides current data if writing to file
    *
    * @param doujinList The list to be printed
    *
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujinList: JavaList[Gallery]): Unit =
    print(doujinList, append = false)

  /**
    * Prints a Java List of [[tk.iscorp.nhs.core.data.Gallery]]
    *
    * @param doujinJList The list to be printed
    *
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujinJList: JavaList[Gallery], append: Boolean): Unit =
  {
    val usableCollection = doujinJList.asScala.toList
    print(usableCollection.head, append)
    usableCollection.tail foreach <<
  }

  /**
    * Prints an Array of [[tk.iscorp.nhs.core.data.Gallery]]
    * Overrides current data if writing to file
    *
    * @param doujins The array to be printed
    *
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujins: Array[Gallery]): Unit =
    print(doujins, append = false)

  /**
    * Prints an Array of [[tk.iscorp.nhs.core.data.Gallery]]
    *
    * @param doujins The array to be printed
    *
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujins: Array[Gallery], append: Boolean): Unit =
  {
    print(doujins.head, append)
    doujins.tail foreach <<
  }

  /**
    * Prints a Java List of [[tk.iscorp.nhs.core.data.Gallery]]
    *
    * @param doujinList The list to be printed
    *
    * @throws NullPointerException If any of the parameters is `null`
    */
  def <<(@NotNull doujinList: JavaList[Gallery]): HentaiOutStream =
  {
    doujinList.asScala foreach <<
    this
  }

  /**
    * Same as print, but
    * doesn't override previous file value
    *
    * @param doujin The doujin to be printed
    *
    * @throws NullPointerException If any of the parameters is `null`
    * @see [[HentaiOutStream#print(Gallery)]]
    */
  def <<(@NotNull doujin: Gallery): HentaiOutStream =
  {
    this.print(doujin, append = true)
    this
  }

  /**
    * Prints a Scala List of [[tk.iscorp.nhs.core.data.Gallery]]
    *
    * @param doujinList The list to be printed
    *
    * @throws NullPointerException If any of the parameters is `null`
    */
  def <<(@NotNull doujinList: List[Gallery]): HentaiOutStream =
  {
    doujinList foreach <<
    this
  }

  /**
    * Prints an Array of [[tk.iscorp.nhs.core.data.Gallery]]
    *
    * @param doujins The array to be printed
    *
    * @throws NullPointerException If any of the parameters is `null`
    */
  def <<(@NotNull doujins: Array[Gallery]): HentaiOutStream =
  {
    doujins foreach <<
    this
  }

  /**
    * Prints a separator line between two [[tk.iscorp.nhs.core.data.Gallery]] instances
    */
  protected def separatorLine(): Unit
}
