package cz.cvut.fit.bioop.hackernewsclient.command.user

import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi
import cz.cvut.fit.bioop.hackernewsclient.model.api.{Story, User}
import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.{doNothing, times, verify}
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.funsuite.AnyFunSuite

class UserStoriesCommandTest extends AnyFunSuite{

  test("Verify UserStories api and rendering calls"){
    val api = mock[HackerNewsApi]
    val renderer = mock[Renderer]

    val someId = "id"
    val someCreated = 210000
    val someKarma = 0
    val someStoryId = 1
    val testStory = Some(new Story(someStoryId))
    val testUser = Some(new User(someId, someCreated, someKarma, None, Some(Seq(someStoryId)) ))

    when(api.loadUserById(someId)).thenReturn(testUser)
    when(api.loadStoryById(someStoryId)).thenReturn(testStory)
    doNothing().when(renderer).renderToConsole(any())

    val userStoriesCommand = new UserStoriesCommand(api, renderer, someId, None, None)
    userStoriesCommand.execute()

    verify(api).loadUserById(someId)
    verify(renderer, times(1)).renderToConsole(any())
  }

}
