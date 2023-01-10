package cz.cvut.fit.bioop.hackernewsclient.command.cache

import cz.cvut.fit.bioop.hackernewsclient.command.{Command, CommandReceiver}

class ClearCacheCommand(receiver: CommandReceiver) extends Command{
  /**   *
   * Run a main logic of application, which depends on specific command task
   *
   * @return nothing but often result prints via render classes
   */
  override def execute(): Unit = {
    receiver.runClearCacheCommand()
  }
}
