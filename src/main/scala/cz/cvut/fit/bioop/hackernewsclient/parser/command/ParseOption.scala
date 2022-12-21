package cz.cvut.fit.bioop.hackernewsclient.parser.command

import cz.cvut.fit.bioop.hackernewsclient.command.{Command, ErrorCommand, HelpCommand}
import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer

object ParseOption {

  def getOption(renderer: Renderer,option: String): Command = {
    option match {
      case "--help" => new HelpCommand(renderer)
      case _ => new ErrorCommand("Unknown command")
    }
  }

}
