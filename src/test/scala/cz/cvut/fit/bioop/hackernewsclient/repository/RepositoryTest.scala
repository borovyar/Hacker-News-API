package cz.cvut.fit.bioop.hackernewsclient.repository

import cz.cvut.fit.bioop.hackernewsclient.controller.api.{CacheApi, HackerNewsApi}
import cz.cvut.fit.bioop.hackernewsclient.model.PagingOptions
import cz.cvut.fit.bioop.hackernewsclient.model.api.{Comment, Story, User}
import org.mockito.ArgumentMatchersSugar.any
import org.mockito.MockitoSugar._
import org.scalatest.funsuite.AnyFunSuite


class RepositoryTest extends AnyFunSuite{

  val hackerNewsApi: HackerNewsApi with CacheApi = mock[HackerNewsApi with CacheApi]
  val repository = new RepositoryImpl(hackerNewsApi)

  val pageOption: PagingOptions = PagingOptions(Some(2), 2)
  val sizeOption: PagingOptions = PagingOptions(None, 2)
  val defaultOption: PagingOptions = PagingOptions(None, 10)

  test("Load top stories from repository with paging"){
    val topStoryIds = Seq(0,1,2)
    val expectedStory = Story(1)

    when(hackerNewsApi.loadTopStories()).thenReturn(topStoryIds)
    when(hackerNewsApi.loadStoryById(any[Int])).thenReturn(Some(expectedStory))

    val resultPage = repository.loadTopStories(pageOption)
    val resultSize = repository.loadTopStories(sizeOption)

    assert(resultPage.size == 1 && resultPage.last == expectedStory)
    assert(resultSize.size == 2 && resultPage.last == expectedStory)
  }

  test("Load user from repository"){
    val id = "jl"
    val expectedUser = User(id, 0L, 0)

    when(hackerNewsApi.loadUserById(id)).thenReturn(Some(expectedUser))

    val result = repository.loadUserById(id)

    assert(result.get == expectedUser)
  }

  test("Load user stories from repository with paging"){
    val idJl = "jl"
    val idOther = "other"
    val expectedUser = User(idJl, 0L, 0, None, Some(Seq(0,1,2)))
    val expectedUserWithoutStories = User(idOther, 0L, 0)
    val expectedStory = Story(1)

    when(hackerNewsApi.loadUserById(idJl)).thenReturn(Some(expectedUser))
    when(hackerNewsApi.loadUserById(idOther)).thenReturn(Some(expectedUserWithoutStories))
    when(hackerNewsApi.loadStoryById(any[Int])).thenReturn(Some(expectedStory))

    val resultOther = repository.loadUserStories(expectedUserWithoutStories, defaultOption)
    val resultJl = repository.loadUserStories(expectedUser, defaultOption)
    val resultJlWithPage = repository.loadUserStories(expectedUser, pageOption)


    assert(resultOther.isEmpty)
    assert(resultJl.size == 3 && resultJl.last == expectedStory)
    assert(resultJlWithPage.size == 1 && resultJl.last == expectedStory)
  }

  test("Load comments from repository with paging"){
    val idStory = 0; val idStoryWithoutComments = 1
    val expectedStory = Story(idStory, None, Some(Seq(0,1)))
    val expectedStoryWithoutComments = Story(idStoryWithoutComments)

    val idComment = 0
    val expectedComment = Comment(idComment)

    when(hackerNewsApi.loadStoryById(idStory)).thenReturn(Some(expectedStory))
    when(hackerNewsApi.loadStoryById(idStoryWithoutComments)).thenReturn(Some(expectedStoryWithoutComments))

    when(hackerNewsApi.loadCommentById(any[Int])).thenReturn(Some(expectedComment))

    val resultWithComments = repository.loadCommentsById(idStory, defaultOption)
    val resultWithout = repository.loadCommentsById(idStoryWithoutComments, defaultOption)
    val resultPaged = repository.loadCommentsById(idStory, pageOption)


    assert(resultWithout.isEmpty)
    assert(resultWithComments.size == 2 && resultWithComments.last == expectedComment)
    assert(resultPaged.size == 1 && resultPaged.last == expectedComment)
  }

}
