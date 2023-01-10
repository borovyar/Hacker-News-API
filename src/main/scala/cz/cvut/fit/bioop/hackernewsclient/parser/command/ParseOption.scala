package cz.cvut.fit.bioop.hackernewsclient.parser.command

import cz.cvut.fit.bioop.hackernewsclient.command.other.{ErrorCommand, HelpCommand}
import cz.cvut.fit.bioop.hackernewsclient.command.{Command, CommandReceiver}

/***
 * Parses option of the application
 */
class ParseOption {

  /***
   * Distinguishes options and chooses correct command
   *
   * @param option which should be determined
   * @return command related to option
   */
  def getOption(receiver: CommandReceiver, option: String): Command = {
    option match {
      case "--help" => new HelpCommand(receiver)
      case _ => new ErrorCommand("Unknown option")
    }
  }

}
