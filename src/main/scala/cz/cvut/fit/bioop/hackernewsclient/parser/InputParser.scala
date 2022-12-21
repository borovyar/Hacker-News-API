package cz.cvut.fit.bioop.hackernewsclient.parser

import cz.cvut.fit.bioop.hackernewsclient.model.InputArguments

/***
 * Input parser for application arguments
 */
object InputParser {

  /***
   * Gives only parsed parameters (without commands)
   *
   * @param args arguments of the application
   * @return InputArguments model or null if arguments were not satisfiable
   */
  def parseInput(args: Array[String]): InputArguments = {
    var option: Option[String] = None
    var command: Option[String] = None
    var commandOptions = List.empty[String]

    if(args.isEmpty)
      return null

    args.foreach {
      case opt if opt.startsWith("--") && command.isEmpty => option = Some(opt)
      case cmd if command.isEmpty => command = Some(cmd)
      case cmdOption if cmdOption.startsWith("--") && command.isDefined => commandOptions = cmdOption :: commandOptions
      case _ => return null

    }

    InputArguments(option, command, commandOptions)
  }

}
