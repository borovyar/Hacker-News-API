package cz.cvut.fit.bioop.hackernewsclient.parser.command

import cz.cvut.fit.bioop.hackernewsclient.command.other.ErrorCommand
import cz.cvut.fit.bioop.hackernewsclient.command.{Command, CommandReceiver}
import cz.cvut.fit.bioop.hackernewsclient.model.InputArguments

/***
 *  Main object of parsing command procedure
 */
class CommandParser(parseCommand: ParseCommand,
                    parseOption: ParseOption,
                    receiver: CommandReceiver) {

  /***
   * Determines correct commands with their options for application input
   *
   * @param args application input
   * @return Command which should be executed
   */
  def parseCommand(args: InputArguments): Command = {

    args.option.flatMap(option => return parseOption.getOption(receiver, option))

    val commandOptions = Some(ParseCommandOptions.getCommandOptions(args.commandOptions))

    if(commandOptions == null)
      return new ErrorCommand("Wrong command")

    parseCommand.getCommand(
      args.command.getOrElse(return new ErrorCommand("Empty command")),
      commandOptions
    )
  }
}
