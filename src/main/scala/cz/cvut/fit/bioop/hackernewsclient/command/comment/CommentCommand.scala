package cz.cvut.fit.bioop.hackernewsclient.command.comment

import cz.cvut.fit.bioop.hackernewsclient.command.{Command, CommandReceiver}
import cz.cvut.fit.bioop.hackernewsclient.model.PagingOptions

class CommentCommand(receiver: CommandReceiver,
                     id: Int,
                     pagingOptions: PagingOptions) extends Command{

  override def execute(): Unit = {
    receiver.runCommentCommand(id, pagingOptions)
  }
}
