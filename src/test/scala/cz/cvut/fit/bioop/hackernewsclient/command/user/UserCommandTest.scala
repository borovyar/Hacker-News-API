package cz.cvut.fit.bioop.hackernewsclient.command.user

import cz.cvut.fit.bioop.hackernewsclient.command.story.TopStoryCommand
import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi
import cz.cvut.fit.bioop.hackernewsclient.model.api.User
import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.{doNothing, times, verify}
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.funsuite.AnyFunSuite

class UserCommandTest extends AnyFunSuite{

  test("Verify UserCommand api and rendering calls"){
    val api = mock[HackerNewsApi]
    val renderer = mock[Renderer]

    val someId = "id"
    val someCreated = 210000
    val someKarma = 0
    val testUser = Some(new User(someId, someCreated, someKarma))

    when(api.loadUserById(someId)).thenReturn(testUser)
    doNothing().when(renderer).renderToConsole(any())

    val userCommand = new UserCommand(api, renderer, someId)
    userCommand.execute()

    verify(api).loadUserById(someId)
    verify(renderer, times(1)).renderToConsole(any())
  }

}
