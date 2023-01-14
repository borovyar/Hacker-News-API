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
   * @return option of command which should be executed
   */
  def parseCommand(args: InputArguments): Option[Command] = {

    args.option.flatMap(option => return Some(parseOption.getOption(receiver, option)))

    val commandOptions = Some(ParseCommandOptions.getCommandOptions(args.commandOptions))

    if(commandOptions.isEmpty)
      return Some(new ErrorCommand("Wrong command"))

    val resultCommand = parseCommand.getCommand(
      args.command.getOrElse(return Some(new ErrorCommand("Empty command"))),
      commandOptions
    )

    resultCommand
  }
}
