/*******************************************************************************
 Copyright 2018 bodand

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ******************************************************************************/

package tk.iscorp.nhs.core.stream

import org.jetbrains.annotations.NotNull
import tk.iscorp.nhs.core.data.Gallery

import java.util.{ArrayList ⇒ JArrayList}

import scala.collection.JavaConverters._

/**
  * Trait that defines methods to read in hentai doujins as [[tk.iscorp.nhs.core.data.Gallery]] instances
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
    * @return The Gallery specified by the input ID. [[tk.iscorp.nhs.core.data.Gallery#dummy]] if ID doesn't point to
    *         any real doujin.
    */
  @NotNull
  def readByID(@NotNull id: String, @NotNull isoDate: Boolean): Gallery

  /**
    * Reads one doujin with id
 *
    * @param id The ID of the doujin to be read
    *
    * @throws NullPointerException If any of the parameters is `null`
    * @return The Gallery specified by the input ID. [[tk.iscorp.nhs.core.data.Gallery#dummy]] if ID doesn't point to
    *         any real doujin.
    */
  @NotNull
  def readByID(@NotNull id: String): Gallery = readByID(id, isoDate = false)

  /**
    * Reads all doujin specified by the Scala List of IDs.
 *
    * @param ids The Scala List of IDs
 *
    * @throws NullPointerException If any of the parameters is `null`
    * @return A Scala List of Galleries read.
    *         If any of the IDs are invalid their returned doujin will be [[tk.iscorp.nhs.core.data.Gallery#dummy]]
    */
  @NotNull
  def readMultipleByID(@NotNull ids: List[String]): List[Gallery] = {
    readMultipleByID(ids, isoDate = false)
  }

  /**
    * Reads all doujin specified by the Scala List of IDs.
 *
    * @param ids The Scala List of IDs
 *
    * @throws NullPointerException If any of the parameters is `null`
    * @return A Scala List of Galleries read.
    *         If any of the IDs are invalid their returned doujin will be [[tk.iscorp.nhs.core.data.Gallery#dummy]]
    */
  @NotNull
  def readMultipleByID(@NotNull ids: List[String], @NotNull isoDate: Boolean): List[Gallery] = {
    for (id ← ids) yield readByID(id, isoDate)
  }

  /**
    * Reads all doujin specified by the ArrayList of IDs.
 *
    * @param ids The ArrayList of IDs
 *
    * @throws NullPointerException If any of the parameters is `null`
    * @return A ArrayList of Galleries read.
    *         If any of the IDs are invalid their returned doujin will be [[tk.iscorp.nhs.core.data.Gallery#dummy]]
    */
  @NotNull
  def readMultipleByID(@NotNull ids: JArrayList[String]): JArrayList[Gallery] = {
    readMultipleByID(ids, isoDate = false)
  }

  /**
    * Reads all doujin specified by the ArrayList of IDs.
 *
    * @param ids The ArrayList of IDs
 *
    * @throws NullPointerException If any of the parameters is `null`
    * @return A ArrayList of Galleries read.
    *         If any of the IDs are invalid their returned doujin will be [[tk.iscorp.nhs.core.data.Gallery#dummy]]
    */
  @NotNull
  def readMultipleByID(@NotNull ids: JArrayList[String], @NotNull isoDate: Boolean): JArrayList[Gallery] = {
    val galleries = (for (id ← ids.asScala) yield readByID(id, isoDate)).asJavaCollection
    new JArrayList[Gallery](galleries)
  }

  /**
    * Reads all doujin specified by the Array of IDs.
 *
    * @param ids The Array of IDs
 *
    * @throws NullPointerException If any of the parameters is `null`
    * @return An array of Galleries read.
    *         If any of the IDs are invalid their returned doujin will be [[tk.iscorp.nhs.core.data.Gallery#dummy]]
    */
  @NotNull
  def readMultipleByID(@NotNull ids: Array[String]): Array[Gallery] = {
    readMultipleByID(ids, isoDate = false)
  }

  /**
    * Reads all doujin specified by the Array of IDs.
 *
    * @param ids The Array of IDs
 *
    * @throws NullPointerException If any of the parameters is `null`
    * @return An array of Galleries read.
    *         If any of the IDs are invalid their returned doujin will be [[tk.iscorp.nhs.core.data.Gallery#dummy]]
    */
  @NotNull
  def readMultipleByID(@NotNull ids: Array[String], @NotNull isoDate: Boolean): Array[Gallery] = {
    ids.map(readByID(_, isoDate))
  }
}
