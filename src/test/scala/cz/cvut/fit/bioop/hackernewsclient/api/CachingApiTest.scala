package cz.cvut.fit.bioop.hackernewsclient.api

import cz.cvut.fit.bioop.hackernewsclient.controller.api.{CachingHackerNewsApiImpl, HackerNewsApi}
import cz.cvut.fit.bioop.hackernewsclient.model.api.{Comment, Story, Update, User}
import cz.cvut.fit.bioop.hackernewsclient.repository.cache.Cache
import org.mockito.MockitoSugar.{mock, times, verify, when}
import org.scalatest.funsuite.AnyFunSuite

class CachingApiTest extends AnyFunSuite{

  val hackerNewsApi: HackerNewsApi = mock[HackerNewsApi]; val cache: Cache = mock[Cache]
  val cachingApi = new CachingHackerNewsApiImpl(hackerNewsApi, cache)

  test("Load top stories without updating"){
    val expectedTopStories = Seq(0, 1, 2)

    when(cache.isUpdated).thenReturn(true)
    when(hackerNewsApi.loadTopStories()).thenReturn(Seq(0, 1, 2))

    val result = cachingApi.loadTopStories()

    assert(expectedTopStories == result)
  }

  test("Load top stories with updating"){
    val expectedUpdate = Update(Seq(0,1,2), Seq("test", "id"))

    when(cache.isUpdated).thenReturn(false)
    when(hackerNewsApi.loadUpdates()).thenReturn(Some(expectedUpdate))
    when(cache.performUpdate(expectedUpdate)).isLenient()
    when(hackerNewsApi.loadTopStories()).thenReturn(Seq())

    val result = cachingApi.loadTopStories()

    assert(result == Seq())
    verify(cache, times(1)).performUpdate(expectedUpdate)
  }

  test("Load story by id from api"){
    val id = 2222
    val expectedStory = new Story(id)

    when(cache.isUpdated).thenReturn(false)
    when(cache.getStory(id)).thenReturn(None)
    when(hackerNewsApi.loadStoryById(id)).thenReturn(Some(expectedStory))

    val result = cachingApi.loadStoryById(id)

    assert(expectedStory == result.get)
  }

  test("Load story by id from cache"){
    val id = 2222
    val expectedStory = new Story(id)

    when(cache.isUpdated).thenReturn(false)
    when(cache.getStory(id)).thenReturn(Some(expectedStory))

    val result = cachingApi.loadStoryById(id)

    assert(expectedStory == result.get)
  }

  test("Load comment by id from api"){
    val id = 2222
    val expectedComment = new Comment(id)

    when(cache.isUpdated).thenReturn(false)
    when(cache.getComment(id)).thenReturn(None)
    when(hackerNewsApi.loadCommentById(id)).thenReturn(Some(expectedComment))

    val result = cachingApi.loadCommentById(id)

    assert(expectedComment == result.get)
  }

  test("Load comment by id from cache"){
    val id = 2222
    val expectedComment = new Comment(id)

    when(cache.isUpdated).thenReturn(false)
    when(cache.getComment(id)).thenReturn(Some(expectedComment))

    val result = cachingApi.loadCommentById(id)

    assert(expectedComment == result.get)
  }

  test("Load user by id from api"){
    val id = "jl"
    val expectedUser = new User(id, 0L, 0)

    when(cache.isUpdated).thenReturn(false)
    when(cache.getUser(id)).thenReturn(None)
    when(hackerNewsApi.loadUserById(id)).thenReturn(Some(expectedUser))

    val result = cachingApi.loadUserById(id)

    assert(expectedUser == result.get)
  }

  test("Load user by id from cache"){
    val id = "jl"
    val expectedUser = new User(id, 0L, 0)

    when(cache.isUpdated).thenReturn(false)
    when(cache.getUser(id)).thenReturn(Some(expectedUser))

    val result = cachingApi.loadUserById(id)

    assert(expectedUser == result.get)
  }
}
