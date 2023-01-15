package cz.cvut.fit.bioop.hackernewsclient.commands

import cz.cvut.fit.bioop.hackernewsclient.command.CommandReceiver
import cz.cvut.fit.bioop.hackernewsclient.command.comment.CommentCommand
import cz.cvut.fit.bioop.hackernewsclient.model.PagingOptions
import org.mockito.MockitoSugar.{mock, times, verify}
import org.scalatest.funsuite.AnyFunSuite

class CommentCommandTest extends AnyFunSuite{

  val commandReceiver: CommandReceiver = mock[CommandReceiver]
  val pagingOptions: PagingOptions = mock[PagingOptions]
  val testId = 111

  val commentCommand = new CommentCommand(commandReceiver, testId, pagingOptions)

  test("Comment command execute test"){
    commentCommand.execute()

    verify(commandReceiver, times(1)).runCommentCommand(testId, pagingOptions)
  }

}
