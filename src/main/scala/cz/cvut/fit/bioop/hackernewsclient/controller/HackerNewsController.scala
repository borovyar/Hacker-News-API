package cz.cvut.fit.bioop.hackernewsclient.controller

import cz.cvut.fit.bioop.hackernewsclient.command.Command
import cz.cvut.fit.bioop.hackernewsclient.model.InputArguments
import cz.cvut.fit.bioop.hackernewsclient.parser.command.CommandParser

class HackerNewsController(inputArguments: InputArguments) extends Controller {

  override def execute(): Unit = {
    val command: Command = CommandParser.parseCommand(inputArguments)
    command.execute()
  }
}
