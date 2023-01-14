package cz.cvut.fit.bioop.hackernewsclient.repository.cache

import cz.cvut.fit.bioop.hackernewsclient.converter.Serializable
import cz.cvut.fit.bioop.hackernewsclient.model.api.{Comment, Story, Update, User}

/**
 * Operations on a cache data such as save, get, update
 */
trait Cache {

  /**
   * Saves entity to the cache
   *
   * @param entity which would be saved
   * @param id of the entity
   * @param converter to/from JSON
   * @tparam T type of the entity
   * @tparam ID type of the id
   */
  def saveEntity[T, ID](entity: T, id: ID)(implicit converter: Serializable[T]): Unit

  /**
   * Finds user in the cache data
   *
   * @param id of the user
   * @return option of user if found
   */
  def getUser(id: String): Option[User]

  /**
   * Finds comment in the cache data
   *
   * @param id of the comment
   * @return option of comment if found
   */
  def getComment(id: Int): Option[Comment]

  /**
   * Finds story in the cache data
   *
   * @param id of the story
   * @return option of story if found
   */
  def getStory(id: Int): Option[Story]

  /**
   * Check if data was update
   * @return true if data is updated, otherwise false
   */
  def isUpdated: Boolean

  /**
   * Performs update of the cache data
   *
   * @param update entity with ids of items which should be udpated
   */
  def performUpdate(update: Update): Unit

  /**
   * Clears all cache data
   */
  def clearCache(): Unit

}
