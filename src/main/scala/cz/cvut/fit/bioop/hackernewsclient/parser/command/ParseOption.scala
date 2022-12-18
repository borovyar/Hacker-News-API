package cz.cvut.fit.bioop.hackernewsclient.parser.command

import cz.cvut.fit.bioop.hackernewsclient.command.{Command, ErrorCommand, HelpCommand}

object ParseOption {

  def getOption(option: String): Command = {
    option match {
      case "--help" => new HelpCommand
      case _ => new ErrorCommand("Unknown command")
    }
  }

}
