package cz.cvut.fit.bioop.hackernewsclient.command.story

import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi
import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.{doNothing, times, verify}
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.funsuite.AnyFunSuite

class TopCommandStoryTest extends AnyFunSuite {

  test("Verify TopStoryCommand api with rendering calls"){
    val api = mock[HackerNewsApi]
    val renderer = mock[Renderer]

    when(api.loadTopStories()).thenReturn(Seq(1, 2, 3))
    when(api.loadStoryById(any())).thenReturn(None)
    doNothing().when(renderer).renderToConsole(any())

    val topStoryCommand = new TopStoryCommand(api, renderer, None, None)
    topStoryCommand.execute()

    verify(api).loadTopStories()
    verify(renderer, times(3)).renderToConsole(any())
  }



}
