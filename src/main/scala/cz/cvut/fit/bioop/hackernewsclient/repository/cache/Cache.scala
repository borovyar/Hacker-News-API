package cz.cvut.fit.bioop.hackernewsclient.repository.cache

import cz.cvut.fit.bioop.hackernewsclient.converter.Converter._
import cz.cvut.fit.bioop.hackernewsclient.converter.Serializable
import cz.cvut.fit.bioop.hackernewsclient.model.api.Update

trait Cache {

  def saveEntity[T, ID](entity: T, id: ID)(implicit converter: Serializable[T]): Unit

  def getEntity[T: Reader, ID](id: ID): Option[T]

  def isUpdated: Boolean

  def performUpdate(update: Update): Unit

  def clearCache(): Unit

}
