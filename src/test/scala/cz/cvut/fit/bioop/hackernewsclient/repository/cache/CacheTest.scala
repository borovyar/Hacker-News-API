package cz.cvut.fit.bioop.hackernewsclient.repository.cache

import cz.cvut.fit.bioop.hackernewsclient.Properties
import cz.cvut.fit.bioop.hackernewsclient.converter._
import cz.cvut.fit.bioop.hackernewsclient.model.api.{Comment, Story, Update, User}
import cz.cvut.fit.bioop.hackernewsclient.model.cache.{CacheData, CacheEntity}
import cz.cvut.fit.bioop.hackernewsclient.repository.file.FileSystem
import cz.cvut.fit.bioop.hackernewsclient.util.TimeManager
import org.mockito.ArgumentMatchersSugar.any
import org.mockito.MockitoSugar._
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite

class CacheTest extends AnyFunSuite with BeforeAndAfterEach{



  val fileSystem: FileSystem[CacheData] = mock[FileSystem[CacheData]]
  val timeManager = new TimeManager(Properties.DEFAULT_TTL)
  val cache = new CacheImpl(fileSystem, timeManager)

  test("Save and find story"){
    val id = 0
    val storyToSave = new Story(id)
    val stringStory = Serializer.storyToJson.toJson(storyToSave)

    when(fileSystem.loadData()).thenReturn(Some(new CacheData(Properties.DEFAULT_UPDATE_TIME, Seq())))
    when(fileSystem.loadEntity[id.type](id)).thenReturn(Some(new CacheEntity(id.toString, Properties.DEFAULT_TTL, stringStory)))

    cache.saveEntity[Story, id.type](storyToSave, id)
    val storyFromCache = cache.getStory(id)

    assert(storyFromCache.isDefined)
    assert(storyFromCache.get == storyToSave)
  }

  test("Save and find user"){
    val id = "jl"
    val userToSave = new User(id, 0L, 0)
    val stringUser = Serializer.userToJson.toJson(userToSave)

    when(fileSystem.loadData()).thenReturn(Some(new CacheData(Properties.DEFAULT_UPDATE_TIME, Seq())))
    when(fileSystem.loadEntity[id.type](id)).thenReturn(Some(new CacheEntity(id, Properties.DEFAULT_TTL, stringUser)))

    cache.saveEntity[User, id.type](userToSave, id)
    val userFromCache = cache.getUser(id)

    assert(userFromCache.isDefined)
    assert(userFromCache.get == userToSave)
  }

  test("Save and find comment"){
    val id = 0
    val commentToSave = new Comment(id)
    val stringComment = Serializer.commentToJson.toJson(commentToSave)

    when(fileSystem.loadData()).thenReturn(Some(new CacheData(Properties.DEFAULT_UPDATE_TIME, Seq())))
    when(fileSystem.loadEntity[id.type](id)).thenReturn(Some(new CacheEntity(id.toString, Properties.DEFAULT_TTL, stringComment)))

    cache.saveEntity[Comment, id.type](commentToSave, id)
    val commentFromCache = cache.getComment(id)

    assert(commentFromCache.isDefined)
    assert(commentFromCache.get == commentToSave)
  }

  test("Find not existed entity"){
    when(fileSystem.loadData()).thenReturn(Some(new CacheData(Properties.DEFAULT_UPDATE_TIME, Seq())))
    when(fileSystem.loadEntity(any[Int])).thenReturn(None)
    when(fileSystem.loadEntity(any[String])).thenReturn(None)

    val commentFromCache = cache.getComment(0)
    val userFromCache = cache.getUser("some")

    assert(commentFromCache.isEmpty)
    assert(userFromCache.isEmpty)
  }

  test("Perform update"){
    val update = new Update(Seq(0,1), Seq("2", "3"))
    val cacheData = Some(new CacheData(Properties.DEFAULT_UPDATE_TIME, Seq()))

    when(fileSystem.loadData()).thenReturn(cacheData)
    when(fileSystem.saveData(any[CacheData])).isLenient()

    cache.performUpdate(update)

    verify(fileSystem, times(4)).loadData()
    verify(fileSystem, times(3)).saveData(any[CacheData])
  }

  test("Clear cache call test"){
    when(fileSystem.clearData()).isLenient()

    cache.clearCache()

    verify(fileSystem, times(1)).clearData()
  }

}
