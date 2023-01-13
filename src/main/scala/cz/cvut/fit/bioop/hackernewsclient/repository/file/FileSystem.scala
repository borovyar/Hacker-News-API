package cz.cvut.fit.bioop.hackernewsclient.repository.file

import cz.cvut.fit.bioop.hackernewsclient.model.cache.CacheEntity

trait FileSystem[T]{

  def loadData(): Option[T]

  def saveData(data: T): Unit

  def loadEntity[ID](id: ID): Option[CacheEntity]

  def clearData(): Unit

}
