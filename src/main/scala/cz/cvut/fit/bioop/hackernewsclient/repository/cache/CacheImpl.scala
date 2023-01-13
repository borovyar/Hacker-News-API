package cz.cvut.fit.bioop.hackernewsclient.repository.cache

import cz.cvut.fit.bioop.hackernewsclient.converter.Converter._
import cz.cvut.fit.bioop.hackernewsclient.converter.Serializable
import cz.cvut.fit.bioop.hackernewsclient.model.api.Update
import cz.cvut.fit.bioop.hackernewsclient.model.cache.{CacheData, CacheEntity}
import cz.cvut.fit.bioop.hackernewsclient.repository.file.FileSystem

class CacheImpl(ttl: Option[Long],
                fileSystem: FileSystem[CacheData]) extends Cache {

  private val DEFAULT_TTL = 30 * 6000L
  private val DEFAULT_UPDATE_TIME = 10L * 60 * 1000L

  override def saveEntity[T, ID](entity: T, id: ID)(implicit converter: Serializable[T]): Unit = {
    val cacheEntity = CacheEntity(
      id.toString,
      System.currentTimeMillis() + ttl.getOrElse(DEFAULT_TTL),
      converter.toJson(entity)
    )

    val cacheData = fileSystem.loadData()
    val bufferCache =
      (if (cacheData.isDefined) cacheData.get.entities else Seq()).toBuffer

    val updateTime = if (cacheData.isDefined) cacheData.get.updateTime else System.currentTimeMillis()

    val indexOfCachedEntity = bufferCache.indexWhere({entity => entity.id == id.toString })
    if (indexOfCachedEntity != -1) {
      bufferCache(indexOfCachedEntity) = cacheEntity
    }
    else {
      bufferCache.append(cacheEntity)
    }

    fileSystem.saveData(CacheData(updateTime, bufferCache.toSeq))
  }

  override def getEntity[T: Reader, ID](id: ID): Option[T] = {
    val cacheEntity = loadEntityFromCache[ID](id)

    if(cacheEntity == null || cacheEntity.isEmpty)
      return None

    try {

      Some(read[T](cacheEntity.get.entity))

    }catch {
      case _: Throwable => None
    }
  }

  override def clearCache(): Unit = {
    fileSystem.clearData()
  }

  override def isUpdated: Boolean = {
    val cacheData = fileSystem.loadData()

    if(cacheData.isEmpty)
      return false

    cacheData.get.updateTime > (System.currentTimeMillis() - DEFAULT_UPDATE_TIME)
  }

  override def performUpdate(update: Update): Unit = {
    val cacheData = fileSystem.loadData()
    if(cacheData == null || update == null || cacheData.isEmpty)
      return

    val cacheEntities = cacheData.get.entities
    if(cacheEntities == null || cacheEntities.isEmpty)
      return

    deleteItems(cacheEntities, update.items)
    deleteUsers(cacheEntities, update.profiles)
  }

  private def deleteItems(cacheEntities: Seq[CacheEntity],
                          ids: Seq[Int]): Unit = {
    val updatedCache = cacheEntities.filter(
      entity => ids.indexOf(entity.id.toInt) == -1
    )
    fileSystem.saveData(CacheData(System.currentTimeMillis(), updatedCache))
  }

  private def deleteUsers(cacheEntities: Seq[CacheEntity],
                          ids: Seq[String]): Unit = {
    val updatedCache = cacheEntities.filter(
      entity => ids.indexOf(entity.id) == -1
    )
    fileSystem.saveData(CacheData(System.currentTimeMillis(), updatedCache))
  }

  private def loadEntityFromCache[T](id: T): Option[CacheEntity] ={
    val cacheData = fileSystem.loadData().getOrElse(return None)

    val cacheEntities = cacheData.entities
    val indexOfCachedEntity = cacheEntities.indexWhere({ entity => entity.id == id.toString })

    if (indexOfCachedEntity == -1) {
      return None
    }

    if (cacheEntities(indexOfCachedEntity).ttl > System.currentTimeMillis()) {
      return Some(cacheEntities(indexOfCachedEntity))
    }

    val updatedCacheItems = cacheEntities.toBuffer

    updatedCacheItems.remove(indexOfCachedEntity)
    fileSystem.saveData(CacheData(cacheData.updateTime, updatedCacheItems.toSeq))

    None
  }


}
