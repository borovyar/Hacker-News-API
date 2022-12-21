package cz.cvut.fit.bioop.hackernewsclient.command.comment

import cz.cvut.fit.bioop.hackernewsclient.command.story.TopStoryCommand
import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi
import cz.cvut.fit.bioop.hackernewsclient.model.api.{Comment, Story}
import cz.cvut.fit.bioop.hackernewsclient.parser.HtmlParser.toFormattedText
import cz.cvut.fit.bioop.hackernewsclient.renderer.{Renderer, RendererImpl}
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.{doNothing, times, verify}
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import java.io.{ByteArrayOutputStream, PrintStream}

class CommentCommandTest extends AnyFunSuite{

  test("Verify CommentCommand api and rendering calls"){
    val api = mock[HackerNewsApi]
    val renderer = mock[Renderer]

    val someId = 1
    val testComment = Some(new Comment(someId))
    val testStory = Some(new Story(someId, None, Some(Seq(someId))))

    when(api.loadCommentById(someId)).thenReturn(testComment)
    when(api.loadStoryById(any())).thenReturn(testStory)
    doNothing().when(renderer).renderToConsole(any())

    val commentCommand = new CommentCommand(api, renderer, someId, None, None)
    commentCommand.execute()

    verify(api).loadCommentById(someId)
    verify(renderer, times(1)).renderToConsole(any())
  }

}
