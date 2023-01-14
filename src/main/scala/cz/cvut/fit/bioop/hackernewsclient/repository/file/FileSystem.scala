package cz.cvut.fit.bioop.hackernewsclient.repository.file

import cz.cvut.fit.bioop.hackernewsclient.model.cache.CacheEntity

/**
 * Allows to perform standard operations in saving&loading of the entities
 * @tparam T of the data format which system would operate on
 */
trait FileSystem[T]{

  /**
   * Load data from the file
   * @return a data in the given format type
   */
  def loadData(): Option[T]

  /**
   * Save data to the file
   * @param data in the given format
   */
  def saveData(data: T): Unit

  /**
   * Loads specific entity from the file
   * @param id of the entity
   * @tparam ID type of the id parameter
   * @return option of the cached entity
   */
  def loadEntity[ID](id: ID): Option[CacheEntity]

  /**
   * Delete saved data from the file
   */
  def clearData(): Unit

}
