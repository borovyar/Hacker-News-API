package cz.cvut.fit.bioop.hackernewsclient.parser.command

import cz.cvut.fit.bioop.hackernewsclient.command.{Command, ErrorCommand}
import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi
import cz.cvut.fit.bioop.hackernewsclient.model.InputArguments
import cz.cvut.fit.bioop.hackernewsclient.parser.command.ParseCommand.getCommand
import cz.cvut.fit.bioop.hackernewsclient.parser.command.ParseCommandOptions.getCommandOptions
import cz.cvut.fit.bioop.hackernewsclient.parser.command.ParseOption.getOption

object CommandParser {

  def parseCommand(api: HackerNewsApi, args: InputArguments): Command = {

    if (args.option.isDefined)
      return getOption(args.option.get)

    val command = args.command
    if (command.isEmpty)
      return new ErrorCommand("Empty command")

    val commandOptions = Some(getCommandOptions(args.commandOptions))
    getCommand(api, command.get, commandOptions)
  }
}
