package cz.cvut.fit.bioop.hackernewsclient.command.other

import cz.cvut.fit.bioop.hackernewsclient.command.Command

class ErrorCommand(message: String) extends Command {
  override def execute(): Unit = {
    println(message)
  }
}
