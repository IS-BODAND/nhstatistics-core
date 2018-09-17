package org.bitbucket.bodand.nhscore.stream

import org.bitbucket.bodand.nhscore.data.Gallery
import org.jetbrains.annotations.NotNull

import java.util.{ArrayList ⇒ JArrayList}

import scala.collection.JavaConverters._

/**
  * Trait that defines methods to read in hentai doujins as [[org.bitbucket.bodand.nhscore.data.Gallery]] instances
  */
trait HentaiInStream {

  /**
    * Reads one doujin with id
    *
    * @param id The ID of the doujin to be read
    * @param isoDate Whether the doujin's upload date should be set to the ISO 8601 date version
    *
    * @throws NullPointerException If any of the parameters is `null`
    *
    * @return The Gallery specified by the input ID. [[org.bitbucket.bodand.nhscore.data.Gallery#dummy]] if ID doesn't point to
    *         any real doujin.
    */
  @NotNull
  def readByID(@NotNull id: String, @NotNull isoDate: Boolean): Gallery

  /**
    * Reads one doujin with id
    * @param id The ID of the doujin to be read
    *
    * @throws NullPointerException If any of the parameters is `null`
    * @return The Gallery specified by the input ID. [[org.bitbucket.bodand.nhscore.data.Gallery#dummy]] if ID doesn't point to
    *         any real doujin.
    */
  @NotNull
  def readByID(@NotNull id: String): Gallery = readByID(id, isoDate = false)

  /**
    * Reads all doujin specified by the Scala List of IDs.
    * @param ids The Scala List of IDs
    * @throws NullPointerException If any of the parameters is `null`
    * @return A Scala List of Galleries read.
    *         If any of the IDs are invalid their returned doujin will be [[org.bitbucket.bodand.nhscore.data.Gallery#dummy]]
    */
  @NotNull
  def readMultipleByID(@NotNull ids: List[String]): List[Gallery] = {
    readMultipleByID(ids, isoDate = false)
  }

  /**
    * Reads all doujin specified by the Scala List of IDs.
    * @param ids The Scala List of IDs
    * @throws NullPointerException If any of the parameters is `null`
    * @return A Scala List of Galleries read.
    *         If any of the IDs are invalid their returned doujin will be [[org.bitbucket.bodand.nhscore.data.Gallery#dummy]]
    */
  @NotNull
  def readMultipleByID(@NotNull ids: List[String], @NotNull isoDate: Boolean): List[Gallery] = {
    for (id ← ids) yield readByID(id, isoDate)
  }

  /**
    * Reads all doujin specified by the ArrayList of IDs.
    * @param ids The ArrayList of IDs
    * @throws NullPointerException If any of the parameters is `null`
    * @return A ArrayList of Galleries read.
    *         If any of the IDs are invalid their returned doujin will be [[org.bitbucket.bodand.nhscore.data.Gallery#dummy]]
    */
  @NotNull
  def readMultipleByID(@NotNull ids: JArrayList[String]): JArrayList[Gallery] = {
    readMultipleByID(ids, isoDate = false)
  }

  /**
    * Reads all doujin specified by the ArrayList of IDs.
    * @param ids The ArrayList of IDs
    * @throws NullPointerException If any of the parameters is `null`
    * @return A ArrayList of Galleries read.
    *         If any of the IDs are invalid their returned doujin will be [[org.bitbucket.bodand.nhscore.data.Gallery#dummy]]
    */
  @NotNull
  def readMultipleByID(@NotNull ids: JArrayList[String], @NotNull isoDate: Boolean): JArrayList[Gallery] = {
    val galleries = (for (id ← ids.asScala) yield readByID(id, isoDate)).asJavaCollection
    new JArrayList[Gallery](galleries)
  }

  /**
    * Reads all doujin specified by the Array of IDs.
    * @param ids The Array of IDs
    * @throws NullPointerException If any of the parameters is `null`
    * @return An array of Galleries read.
    *         If any of the IDs are invalid their returned doujin will be [[org.bitbucket.bodand.nhscore.data.Gallery#dummy]]
    */
  @NotNull
  def readMultipleByID(@NotNull ids: Array[String]): Array[Gallery] = {
    readMultipleByID(ids, isoDate = false)
  }

  /**
    * Reads all doujin specified by the Array of IDs.
    * @param ids The Array of IDs
    * @throws NullPointerException If any of the parameters is `null`
    * @return An array of Galleries read.
    *         If any of the IDs are invalid their returned doujin will be [[org.bitbucket.bodand.nhscore.data.Gallery#dummy]]
    */
  @NotNull
  def readMultipleByID(@NotNull ids: Array[String], @NotNull isoDate: Boolean): Array[Gallery] = {
    ids.map(readByID(_, isoDate))
  }
}
