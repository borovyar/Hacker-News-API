package cz.cvut.fit.bioop.hackernewsclient.repository.cache

import cz.cvut.fit.bioop.hackernewsclient.converter.Converter._
import cz.cvut.fit.bioop.hackernewsclient.converter.Serializer
import cz.cvut.fit.bioop.hackernewsclient.model.api._
import cz.cvut.fit.bioop.hackernewsclient.model.cache.{CacheData, CacheEntity}
import cz.cvut.fit.bioop.hackernewsclient.repository.file.FileSystem
import cz.cvut.fit.bioop.hackernewsclient.util.TimeManager

import scala.collection.mutable

class CacheImpl(fileSystem: FileSystem[CacheData],
                timeManager: TimeManager) extends Cache {

  override def saveEntity[T: Reader, ID](entity: T, id: ID): Unit = {
    val entityJsonString = getJsonString(entity)

    val cacheEntity = CacheEntity(
      id.toString,
      timeManager.getCurrentMillsWithTtl,
      entityJsonString
    )

    val cacheData = fileSystem.loadData()
    var entitySeq: Seq[CacheEntity] = Seq()
    var updateTime = timeManager.getCurrentMills

    if(cacheData.isDefined) {
      entitySeq = serviceLoadedData(cacheData.get, cacheEntity, id.toString)
      updateTime = cacheData.get.updateTime
    } else
      entitySeq = serviceEmptyData(cacheEntity)

    fileSystem.saveData(CacheData(updateTime, entitySeq))
  }

  override def clearCache(): Unit = {
    fileSystem.clearData()
  }

  override def isUpdated: Boolean = {
    val cacheData = fileSystem.loadData()

    if (cacheData.isEmpty)
      return false

    cacheData.get.updateTime > timeManager.getUpdateMills
  }

  override def performUpdate(update: Update): Unit = {
    val cacheData = fileSystem.loadData()
    if (update == null || cacheData.isEmpty)
      return

    val cacheEntities = cacheData.get.entities
    if (cacheEntities.isEmpty)
      return

    deleteItems(cacheEntities, update.items)
    deleteUsers(cacheEntities, update.profiles)
  }

  override def getUser(id: String): Option[User] = {
    val cacheEntity = fileSystem.loadEntity[id.type](id)

    if (cacheEntity.isEmpty)
      return None

    try {
      Some(read[User](cacheEntity.get.entity))
    } catch {
      case _: Throwable => None
    }
  }

  override def getComment(id: Int): Option[Comment] = {
    val cacheEntity = fileSystem.loadEntity[id.type](id)

    if (cacheEntity.isEmpty)
      return None

    try {
      Some(read[Comment](cacheEntity.get.entity))
    } catch {
      case _: Throwable => None
    }
  }

  override def getStory(id: Int): Option[Story] = {
    val cacheEntity = fileSystem.loadEntity[id.type](id)

    if (cacheEntity.isEmpty)
      return None

    try {
      Some(read[Story](cacheEntity.get.entity))
    } catch {
      case _: Throwable => None
    }
  }

  private def getJsonString(entity: Any): String = {
    entity match {
      case comment: Comment => Serializer.commentToJson.toJson(entity.asInstanceOf[Comment])
      case story: Story => Serializer.storyToJson.toJson(entity.asInstanceOf[Story])
      case user: User => Serializer.userToJson.toJson(entity.asInstanceOf[User])
      case cache: CacheEntity => Serializer.cacheEntityToJSON.toJson(entity.asInstanceOf[CacheEntity])
      case _ => "Unknown entity"
    }
  }

  private def deleteItems(cacheEntities: Seq[CacheEntity],
                          ids: Seq[Int]): Unit = {
    val updatedCache = cacheEntities.filter(
      entity => ids.indexOf(entity.id.toInt) == -1
    )
    fileSystem.saveData(CacheData(timeManager.getCurrentMills, updatedCache))
  }

  private def deleteUsers(cacheEntities: Seq[CacheEntity],
                          ids: Seq[String]): Unit = {
    val updatedCache = cacheEntities.filter(
      entity => ids.indexOf(entity.id) == -1
    )
    fileSystem.saveData(CacheData(timeManager.getCurrentMills, updatedCache))
  }

  private def serviceLoadedData(cacheData: CacheData,
                                cacheEntity: CacheEntity,
                                id: String): Seq[CacheEntity] = {
    val bufferCache = cacheData.entities.toBuffer
    val indexOfCachedEntity = bufferCache.indexWhere({ entity => entity.id == id})

    if (indexOfCachedEntity != -1) {
      bufferCache(indexOfCachedEntity) = cacheEntity
    }
    else {
      bufferCache.append(cacheEntity)
    }

    bufferCache.toSeq
  }

  private def serviceEmptyData(cacheEntity: CacheEntity): Seq[CacheEntity] = {
    val bufferCache:mutable.Buffer[CacheEntity] = Seq().toBuffer
    bufferCache.append(cacheEntity).toSeq
  }
}
