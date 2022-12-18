package cz.cvut.fit.bioop.hackernewsclient.controller

import cz.cvut.fit.bioop.hackernewsclient.command.Command
import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi
import cz.cvut.fit.bioop.hackernewsclient.model.InputArguments
import cz.cvut.fit.bioop.hackernewsclient.parser.command.CommandParser
import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer

class HackerNewsController(inputArguments: InputArguments,
                           api: HackerNewsApi,
                           renderer: Renderer) extends Controller {

  override def execute(): Unit = {
    val command: Command = CommandParser.parseCommand(api, inputArguments)
    command.setRenderer(renderer)
    command.execute()
  }
}
