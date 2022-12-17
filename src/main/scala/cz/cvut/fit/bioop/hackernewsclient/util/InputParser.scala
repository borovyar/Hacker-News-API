package cz.cvut.fit.bioop.hackernewsclient.util

import cz.cvut.fit.bioop.hackernewsclient.model.InputArguments

object InputParser {

  def parseInput(args: Array[String]): InputArguments = {
    var option: Option[String] = None
    var command: Option[String] = None
    var commandOptions = List.empty[String]

    args.foreach {
      case opt if opt.startsWith("--") && command.isEmpty => option = Some(opt)
      case cmd if command.isEmpty => command = Some(cmd)
      case cmdOption if command.isDefined => commandOptions = cmdOption :: commandOptions
    }

    new InputArguments(option, command, commandOptions)
  }

}
