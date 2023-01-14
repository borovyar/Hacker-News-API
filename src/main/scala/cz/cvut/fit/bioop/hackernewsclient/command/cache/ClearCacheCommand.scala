package cz.cvut.fit.bioop.hackernewsclient.command.cache

import cz.cvut.fit.bioop.hackernewsclient.command.{Command, CommandReceiver}

class ClearCacheCommand(receiver: CommandReceiver) extends Command{
  override def execute(): Unit = {
    receiver.runClearCacheCommand()
  }
}
