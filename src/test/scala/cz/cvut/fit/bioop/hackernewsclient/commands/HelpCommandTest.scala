package cz.cvut.fit.bioop.hackernewsclient.commands

import cz.cvut.fit.bioop.hackernewsclient.command.CommandReceiver
import cz.cvut.fit.bioop.hackernewsclient.command.other.HelpCommand
import org.mockito.MockitoSugar.{mock, times, verify}
import org.scalatest.funsuite.AnyFunSuite

class HelpCommandTest extends AnyFunSuite{

  val commandReceiver: CommandReceiver = mock[CommandReceiver]

  val helpCommand = new HelpCommand(commandReceiver)

  test("Top stories command execute test"){
    helpCommand.execute()

    verify(commandReceiver, times(1)).runHelpCommand()
  }

}
