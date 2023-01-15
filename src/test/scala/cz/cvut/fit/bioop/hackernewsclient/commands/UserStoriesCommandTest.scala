package cz.cvut.fit.bioop.hackernewsclient.commands

import cz.cvut.fit.bioop.hackernewsclient.command.CommandReceiver
import cz.cvut.fit.bioop.hackernewsclient.command.user.UserStoriesCommand
import cz.cvut.fit.bioop.hackernewsclient.model.PagingOptions
import org.mockito.MockitoSugar.{mock, times, verify}
import org.scalatest.funsuite.AnyFunSuite

class UserStoriesCommandTest extends AnyFunSuite{

  val commandReceiver: CommandReceiver = mock[CommandReceiver]
  val pagingOptions: PagingOptions = mock[PagingOptions]
  val testId = "test"

  val userStoriesCommand = new UserStoriesCommand(commandReceiver, testId, pagingOptions)

  test("User stories command execute test"){
    userStoriesCommand.execute()

    verify(commandReceiver, times(1)).runUserStoriesCommand(testId, pagingOptions)
  }

}
