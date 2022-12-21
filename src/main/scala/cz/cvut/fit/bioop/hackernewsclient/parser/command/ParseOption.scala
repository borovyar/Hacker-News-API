package cz.cvut.fit.bioop.hackernewsclient.parser.command

import cz.cvut.fit.bioop.hackernewsclient.command.{Command, ErrorCommand, HelpCommand}
import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer

/***
 * Parses option of the application
 */
object ParseOption {

  /***
   * Distinguishes options and chooses correct command
   *
   * @param renderer for the commands
   * @param option which should be determined
   * @return command related to option
   */
  def getOption(renderer: Renderer, option: String): Command = {
    option match {
      case "--help" => new HelpCommand(renderer)
      case _ => new ErrorCommand("Unknown command")
    }
  }

}
