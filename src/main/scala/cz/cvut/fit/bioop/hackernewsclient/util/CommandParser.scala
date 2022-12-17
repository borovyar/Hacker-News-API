package cz.cvut.fit.bioop.hackernewsclient.util

import cz.cvut.fit.bioop.hackernewsclient.command.story.TopStoryCommand
import cz.cvut.fit.bioop.hackernewsclient.command.{Command, HelpCommand}
import cz.cvut.fit.bioop.hackernewsclient.model.{CommandOptions, InputArguments}

object CommandParser {

  def parseCommand(args: InputArguments): Command = {

    if(args.option.isDefined)
      return getOption(args.option.get)

    val command = args.command
    if(command.isEmpty)
      ???

    val commandOptions = Some(getCommandOptions(args.commandOptions))
    getCommand(command.get, commandOptions)
  }

  private def getOption(option: String): Command = {
    option match {
      case "--help" => new HelpCommand
    }
  }

  private def getCommand(command: String, commandOptions: Option[CommandOptions]): Command = {
    command match {
      case "top-stories" => if (commandOptions.isDefined) new TopStoryCommand(
        commandOptions.get.getPage,
        commandOptions.get.getSize
      ) else new TopStoryCommand(None, None)
    }
  }


  private def getCommandOptions(commandOptions: List[String]): CommandOptions = {
    val result = new CommandOptions

    for(cmdOption <- commandOptions)
      cmdOption match {
        case page if page.matches("--page=\\d+") =>
          result.setPage(Some("""\d+""".r.findFirstIn(page).getOrElse("").toInt))
        case size if size.matches("--size=\\d+") =>
          result.setSize(Some("""\d+""".r.findFirstIn(size).getOrElse("").toInt))
      }
    result
  }
}
