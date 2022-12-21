package cz.cvut.fit.bioop.hackernewsclient.parser.command

import cz.cvut.fit.bioop.hackernewsclient.command.{Command, ErrorCommand}
import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi
import cz.cvut.fit.bioop.hackernewsclient.model.InputArguments
import cz.cvut.fit.bioop.hackernewsclient.parser.command.ParseCommand.getCommand
import cz.cvut.fit.bioop.hackernewsclient.parser.command.ParseCommandOptions.getCommandOptions
import cz.cvut.fit.bioop.hackernewsclient.parser.command.ParseOption.getOption
import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer

object CommandParser {

  def parseCommand(api: HackerNewsApi,
                   renderer: Renderer,
                   args: InputArguments): Command = {

    args.option.flatMap(_ => return getOption(renderer, args.option.get))

    val command = args.command
    val commandOptions = Some(getCommandOptions(args.commandOptions))

    getCommand(api, renderer, command.getOrElse(return new ErrorCommand("Empty command")), commandOptions)
  }
}
