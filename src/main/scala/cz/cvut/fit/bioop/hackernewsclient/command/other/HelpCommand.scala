package cz.cvut.fit.bioop.hackernewsclient.command.other

import cz.cvut.fit.bioop.hackernewsclient.command.{Command, CommandReceiver}

class HelpCommand(receiver: CommandReceiver) extends Command {
  override def execute(): Unit = {
    receiver.runHelpCommand()
  }
}
