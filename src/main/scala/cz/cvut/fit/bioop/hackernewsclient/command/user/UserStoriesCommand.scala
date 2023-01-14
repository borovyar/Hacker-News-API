package cz.cvut.fit.bioop.hackernewsclient.command.user

import cz.cvut.fit.bioop.hackernewsclient.command.{Command, CommandReceiver}
import cz.cvut.fit.bioop.hackernewsclient.model.PagingOptions

class UserStoriesCommand(commandReceiver: CommandReceiver,
                         id: String,
                         pageOptions: PagingOptions) extends Command{

  override def execute() : Unit = {
    commandReceiver.runUserStoriesCommand(id, pageOptions)
  }

}
