package cz.cvut.fit.bioop.hackernewsclient.parser

import cz.cvut.fit.bioop.hackernewsclient.command.comment.CommentCommand
import cz.cvut.fit.bioop.hackernewsclient.command.other.{ErrorCommand, HelpCommand}
import cz.cvut.fit.bioop.hackernewsclient.command.story.TopStoryCommand
import cz.cvut.fit.bioop.hackernewsclient.command.user.{UserCommand, UserStoriesCommand}
import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi
import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.doNothing
import org.mockito.MockitoSugar.mock
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite

class CommandParserTest extends AnyFunSuite with BeforeAndAfterEach{

  var api: HackerNewsApi = mock[HackerNewsApi]
  var renderer: Renderer = mock[Renderer]


  override protected def beforeEach(): Unit = {
    doNothing().when(renderer).renderToConsole(any())
  }

  test("Receive HelpCommand Test"){
    val inputArguments = InputArguments(Some("--help"), None, List())
    val receivedCommand = CommandParser.parseCommand(api, renderer, inputArguments)

    assert(receivedCommand.isInstanceOf[HelpCommand])
  }

  test("Receive TopStory Test"){
    val inputArguments = InputArguments(None, Some("top-stories"), List())
    val receivedCommand = CommandParser.parseCommand(api, renderer, inputArguments)

    assert(receivedCommand.isInstanceOf[TopStoryCommand])
  }

  test("Receive UserCommand Test"){
    val inputArguments = InputArguments(None, Some("user"), List("--id=jl"))
    val receivedCommand = CommandParser.parseCommand(api, renderer, inputArguments)

    assert(receivedCommand.isInstanceOf[UserCommand])
  }

  test("Receive ErrorCommand during UserCommand parsing"){
    val inputArguments = InputArguments(None, Some("user"), List())
    val receivedCommand = CommandParser.parseCommand(api, renderer, inputArguments)

    assert(receivedCommand.isInstanceOf[ErrorCommand])
  }

  test("Receive UserStoriesCommand Test"){
    val inputArguments = InputArguments(None, Some("user-stories"), List("--id=jl"))
    val receivedCommand = CommandParser.parseCommand(api, renderer, inputArguments)

    assert(receivedCommand.isInstanceOf[UserStoriesCommand])
  }

  test("Receive ErrorCommand during UserStoriesCommand parsing"){
    val inputArguments = InputArguments(None, Some("user-stories"), List())
    val receivedCommand = CommandParser.parseCommand(api, renderer, inputArguments)

    assert(receivedCommand.isInstanceOf[ErrorCommand])
  }

  test("Receive CommentCommand Test"){
    val inputArguments = InputArguments(None, Some("comments"), List("--id=20302"))
    val receivedCommand = CommandParser.parseCommand(api, renderer, inputArguments)

    assert(receivedCommand.isInstanceOf[CommentCommand])
  }

  test("Receive ErrorCommand during CommentCommand parsing"){
    val inputArguments = InputArguments(None, Some("comments"), List())
    val receivedCommand = CommandParser.parseCommand(api, renderer, inputArguments)

    assert(receivedCommand.isInstanceOf[ErrorCommand])
  }
}
