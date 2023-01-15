package cz.cvut.fit.bioop.hackernewsclient.repository.file
import cz.cvut.fit.bioop.hackernewsclient.Properties._
import cz.cvut.fit.bioop.hackernewsclient.converter.Converter._
import cz.cvut.fit.bioop.hackernewsclient.model.cache.{CacheData, CacheEntity}
import cz.cvut.fit.bioop.hackernewsclient.util.TimeManager

import java.io.{File, FileNotFoundException, PrintWriter}
import scala.io.Source

class FileCacheSystemImpl(timeManager: TimeManager,
                          fileName: String) extends FileSystem[CacheData] {


  override def loadData(): Option[CacheData] ={
    try{

      val buffer = Source.fromFile(fileName)
      val strings = buffer.getLines().mkString; buffer.close()

      Some(read[CacheData](strings))
    }catch {
      case _: FileNotFoundException => None
    }
  }

  override def saveData(cacheData: CacheData): Unit = {
    val printWriter = new PrintWriter(new File(fileName))

    printWriter.write(write(cacheData))
    printWriter.close()
  }

  override def clearData(): Unit = {
    val file = new File(CACHE_FILE_NAME)

    if(file != null && file.exists())
      file.delete()
  }

  override def loadEntity[ID](id: ID): Option[CacheEntity] = {
    val cacheData = loadData().getOrElse(return None)

    val cacheEntities = cacheData.entities
    val indexOfCachedEntity = cacheEntities.indexWhere({ entity => entity.id == id.toString })

    if (indexOfCachedEntity == -1 || cacheEntities(indexOfCachedEntity).ttl < timeManager.getCurrentMills)
      return removeEntityFromCache(cacheEntities, cacheData, indexOfCachedEntity)

    Some(cacheEntities(indexOfCachedEntity))
  }

  private def removeEntityFromCache(cacheEntities: Seq[CacheEntity],
                                    cacheData: CacheData,
                                    index: Int): Option[CacheEntity] = {

    if (index == -1) {
      return None
    }

    val updatedCacheItems = cacheEntities.toBuffer

    updatedCacheItems.remove(index)
    saveData(CacheData(cacheData.updateTime, updatedCacheItems.toSeq))

    None
  }
}
