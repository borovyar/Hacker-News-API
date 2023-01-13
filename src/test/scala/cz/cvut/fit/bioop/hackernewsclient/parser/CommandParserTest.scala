package cz.cvut.fit.bioop.hackernewsclient.parser

import cz.cvut.fit.bioop.hackernewsclient.command.CommandReceiver
import cz.cvut.fit.bioop.hackernewsclient.command.cache.ClearCacheCommand
import cz.cvut.fit.bioop.hackernewsclient.command.comment.CommentCommand
import cz.cvut.fit.bioop.hackernewsclient.command.other.{ErrorCommand, HelpCommand}
import cz.cvut.fit.bioop.hackernewsclient.command.story.TopStoryCommand
import cz.cvut.fit.bioop.hackernewsclient.command.user.{UserCommand, UserStoriesCommand}
import cz.cvut.fit.bioop.hackernewsclient.model.InputArguments
import cz.cvut.fit.bioop.hackernewsclient.parser.command.{CommandParser, ParseCommand, ParseOption}
import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.doNothing
import org.mockito.MockitoSugar.mock
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite

class CommandParserTest extends AnyFunSuite with BeforeAndAfterEach{

  var receiver: CommandReceiver = mock[CommandReceiver]
  var renderer: Renderer = mock[Renderer]
  var parseCommand = new ParseCommand(receiver)
  var parseOption = new ParseOption()


  override protected def beforeEach(): Unit = {
    doNothing().when(renderer).renderToConsole(any())
  }

  test("Receive HelpCommand Test"){
    val inputArguments =  InputArguments(Some("--help"), None, List(), None)
    val receivedCommand = new CommandParser(parseCommand, parseOption, receiver).parseCommand(inputArguments)

    assert(receivedCommand.get.isInstanceOf[HelpCommand])
  }

  test("Receive TopStory Test"){
    val inputArguments = InputArguments(None, Some("top-stories"), List(), None)
    val receivedCommand = new CommandParser(parseCommand, parseOption, receiver).parseCommand(inputArguments)

    assert(receivedCommand.get.isInstanceOf[TopStoryCommand])
  }

  test("Receive ErrorCommand during top-stories parsing"){
    val inputArguments = InputArguments(None, Some("top-stories"), List("--page=wrong"), None)
    val receivedCommand = new CommandParser(parseCommand, parseOption, receiver).parseCommand(inputArguments)

    assert(receivedCommand.get.isInstanceOf[ErrorCommand])
  }

  test("Receive UserCommand Test"){
    val inputArguments = InputArguments(None, Some("user"), List("--id=jl"), None)
    val receivedCommand = new CommandParser(parseCommand, parseOption, receiver).parseCommand(inputArguments)

    assert(receivedCommand.get.isInstanceOf[UserCommand])
  }

  test("Receive ErrorCommand during UserCommand parsing"){
    val inputArguments = InputArguments(None, Some("user"), List(), None)
    val receivedCommand = new CommandParser(parseCommand, parseOption, receiver).parseCommand(inputArguments)

    assert(receivedCommand.get.isInstanceOf[ErrorCommand])
  }

  test("Receive UserStoriesCommand Test"){
    val inputArguments = InputArguments(None, Some("user-stories"), List("--id=jl"), None)
    val receivedCommand = new CommandParser(parseCommand, parseOption, receiver).parseCommand(inputArguments)

    assert(receivedCommand.get.isInstanceOf[UserStoriesCommand])
  }

  test("Receive ErrorCommand during UserStoriesCommand parsing"){
    val inputArguments = InputArguments(None, Some("user-stories"), List(), None)
    val receivedCommand = new CommandParser(parseCommand, parseOption, receiver).parseCommand(inputArguments)

    assert(receivedCommand.get.isInstanceOf[ErrorCommand])
  }

  test("Receive CommentCommand Test"){
    val inputArguments = InputArguments(None, Some("comments"), List("--id=20302"), None)
    val receivedCommand = new CommandParser(parseCommand, parseOption, receiver).parseCommand(inputArguments)

    assert(receivedCommand.get.isInstanceOf[CommentCommand])
  }

  test("Receive ErrorCommand during CommentCommand parsing"){
    val inputArguments = InputArguments(None, Some("comments"), List(), None)
    val receivedCommand = new CommandParser(parseCommand, parseOption, receiver).parseCommand(inputArguments)

    assert(receivedCommand.get.isInstanceOf[ErrorCommand])
  }

  test("Receive ClearCacheCommand Test"){
    val inputArguments = InputArguments(None, Some("clear-cache"), List(), None)
    val receivedCommand = new CommandParser(parseCommand, parseOption, receiver).parseCommand(inputArguments)

    assert(receivedCommand.get.isInstanceOf[ClearCacheCommand])
  }

  test("Receive ErrorCommand during ClearCacheCommand parsing"){
    val inputArguments = InputArguments(Some("top-stories"), Some("clear-cache"), List(), None)
    val receivedCommand = new CommandParser(parseCommand, parseOption, receiver).parseCommand(inputArguments)

    assert(receivedCommand.get.isInstanceOf[ErrorCommand])
  }

}
