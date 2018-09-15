package tk.iscorp.nhs.stream

import org.jetbrains.annotations.{NonNls, NotNull}
import tk.iscorp.nhs.data.Gallery

import java.util.{ArrayList ⇒ JArrayList}

import scala.collection.JavaConverters._
import scala.language.postfixOps

/**
  * Trait that defines basic functions to print Gallery objects
  */
trait HentaiOutStream {
  /**
    * Prints a [[tk.iscorp.nhs.data.Gallery]]
    * Overrides current contents if stream prints to a file
    * @param doujin The Gallery to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujin: Gallery): Unit

  /**
    * Prints a [[tk.iscorp.nhs.data.Gallery]]
    * @param doujin The Gallery to be printed
    * @param append If true the current contents are not overridden if stream prints to a file
    *
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujin: Gallery, @NotNull append: Boolean): Unit

  /**
    * Prints a Scala List of [[tk.iscorp.nhs.data.Gallery]]
    * @param doujinList The list to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujinList: List[Gallery]): Unit = {
    print(doujinList, append = false)
  }

  /**
    * Prints a Scala List of [[tk.iscorp.nhs.data.Gallery]]
    * Overrides current data if writing to file
    * @param doujinList The list to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujinList: List[Gallery], append: Boolean): Unit = {
    print(doujinList.head, append)
    separatorLine()
    doujinList.tail.foreach { d ⇒
      this << d
      separatorLine()
    }
  }

  /**
    * Prints a Java ArrayList of [[tk.iscorp.nhs.data.Gallery]]
    * Overrides current data if writing to file
    * @param doujinList The list to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujinList: JArrayList[Gallery]): Unit = {
    print(doujinList, append = false)
  }

  /**
    * Prints a Java ArrayList of [[tk.iscorp.nhs.data.Gallery]]
    * @param doujinJList The list to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujinJList: JArrayList[Gallery], append: Boolean): Unit = {
    val usableCollection = doujinJList.asScala.toList
    print(usableCollection.head, append)
    separatorLine()
    usableCollection.tail foreach { d ⇒
      this << d
      separatorLine()
    }
  }

  /**
    * Prints an Array of [[tk.iscorp.nhs.data.Gallery]]
    * Overrides current data if writing to file
    * @param doujins The array to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujins: Array[Gallery]): Unit = {
    print(doujins, append = false)
  }

  /**
    * Prints an Array of [[tk.iscorp.nhs.data.Gallery]]
    * @param doujins The array to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def print(@NotNull doujins: Array[Gallery], append: Boolean): Unit = {
    print(doujins.head, append)
    separatorLine()
    doujins.tail.foreach { d ⇒
      this << d
      separatorLine()
    }
  }

  /**
    * Prints a Java ArrayList of [[tk.iscorp.nhs.data.Gallery]]
    * @param doujinList The list to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def <<(@NotNull doujinList: JArrayList[Gallery]): Unit = {
    doujinList.forEach(d ⇒ {
      this << d
      separatorLine()
    })
  }

  /**
    * Same as print, but
    * doesn't override previous file value
    * @param doujin The doujin to be printed
    *
    * @throws NullPointerException If any of the parameters is `null`
    * @see [[HentaiOutStream#print(Gallery)]]
    */
  def <<(@NotNull doujin: Gallery): Unit = {
    this.print(doujin, append = true)
  }

  /**
    * Prints a Scala List of [[tk.iscorp.nhs.data.Gallery]]
    * @param doujinList The list to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def <<(@NotNull doujinList: List[Gallery]): Unit = {
    doujinList foreach { d ⇒
      this << d
      separatorLine()
    }
  }

  /**
    * Prints an Array of [[tk.iscorp.nhs.data.Gallery]]
    * @param doujins The array to be printed
    * @throws NullPointerException If any of the parameters is `null`
    */
  def <<(@NotNull doujins: Array[Gallery]): Unit = {
    doujins foreach { d ⇒
      this << d
      separatorLine()
    }
  }

  /**
    * Prints a separator line between two [[tk.iscorp.nhs.data.Gallery]] instances
    */
  protected def separatorLine(): Unit
}
