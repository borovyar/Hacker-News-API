package cz.cvut.fit.bioop.hackernewsclient.repository.cache

import cz.cvut.fit.bioop.hackernewsclient.converter.Serializable
import cz.cvut.fit.bioop.hackernewsclient.model.api.{Comment, Story, Update, User}

trait Cache {

  def saveEntity[T, ID](entity: T, id: ID)(implicit converter: Serializable[T]): Unit

  def getUser(id: String): Option[User]
  def getComment(id: Int): Option[Comment]
  def getStory(id: Int): Option[Story]

  def isUpdated: Boolean

  def performUpdate(update: Update): Unit

  def clearCache(): Unit

}
