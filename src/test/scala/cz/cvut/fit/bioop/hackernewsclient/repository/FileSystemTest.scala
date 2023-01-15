package cz.cvut.fit.bioop.hackernewsclient.repository

import cz.cvut.fit.bioop.hackernewsclient.Properties
import cz.cvut.fit.bioop.hackernewsclient.model.cache.{CacheData, CacheEntity}
import cz.cvut.fit.bioop.hackernewsclient.repository.file.FileCacheSystemImpl
import cz.cvut.fit.bioop.hackernewsclient.util.TimeManager
import org.mockito.MockitoSugar.{mock, times, verify}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite

import java.io.File

class FileSystemTest extends AnyFunSuite with BeforeAndAfterEach{

  val timeManager: TimeManager = mock[TimeManager]
  val fs = new FileCacheSystemImpl(timeManager, Properties.CACHE_FILE_TEST_NAME)

  override def afterEach(): Unit = {
    val file = new File(Properties.CACHE_FILE_TEST_NAME)
    file.delete()
  }

  test("Load and save cache data test"){
    val cacheEntity = new CacheEntity("test", Properties.DEFAULT_TTL, "test")
    val cacheData = new CacheData(Properties.DEFAULT_UPDATE_TIME,Seq(cacheEntity))

    fs.saveData(cacheData)
    val loadedData = fs.loadData()


    assert(loadedData.isDefined)
    assert(loadedData.get == cacheData)
  }

  test("Load specific entity test"){
    val cacheEntity = new CacheEntity("test", Properties.DEFAULT_TTL, "test")
    val cacheData = new CacheData(Properties.DEFAULT_UPDATE_TIME,Seq(cacheEntity))

    fs.saveData(cacheData)
    val loadedEntity = fs.loadEntity[String]("test")

    assert(loadedEntity.isDefined)
    verify(timeManager, times(1)).getCurrentMills
    assert(loadedEntity.get == cacheEntity)
  }

  test("Clear file test"){
    fs.clearData()

    assert(!new File(Properties.CACHE_FILE_TEST_NAME).exists())
  }
}
