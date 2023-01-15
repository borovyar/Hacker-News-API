package cz.cvut.fit.bioop.hackernewsclient.commands

import cz.cvut.fit.bioop.hackernewsclient.command.CommandReceiver
import cz.cvut.fit.bioop.hackernewsclient.command.user.UserCommand
import org.mockito.MockitoSugar.{mock, times, verify}
import org.scalatest.funsuite.AnyFunSuite

class UserCommandTest extends AnyFunSuite{

  val commandReceiver: CommandReceiver = mock[CommandReceiver]
  val testId = "test"

  val userCommand = new UserCommand(commandReceiver, testId)

  test("User command execute test"){
    userCommand.execute()

    verify(commandReceiver, times(1)).runUserCommand(testId)
  }
}
