package cz.cvut.fit.bioop.hackernewsclient.command.story

import cz.cvut.fit.bioop.hackernewsclient.command.{Command, CommandReceiver}
import cz.cvut.fit.bioop.hackernewsclient.model.PagingOptions

class TopStoryCommand(commandReceiver: CommandReceiver,
                      pageOptions: PagingOptions) extends Command{

  override def execute() : Unit = {
    commandReceiver.runTopStoriesCommand(pageOptions)
  }

}
