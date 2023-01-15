package cz.cvut.fit.bioop.hackernewsclient.commands

import cz.cvut.fit.bioop.hackernewsclient.command.CommandReceiver
import cz.cvut.fit.bioop.hackernewsclient.command.cache.ClearCacheCommand
import org.mockito.MockitoSugar.{mock, times, verify}
import org.scalatest.funsuite.AnyFunSuite

class ClearCacheCommandTest extends AnyFunSuite{

  val commandReceiver: CommandReceiver = mock[CommandReceiver]

  val clearCacheCommand = new ClearCacheCommand(commandReceiver)

  test("Clear command command execute test"){
    clearCacheCommand.execute()

    verify(commandReceiver, times(1)).runClearCacheCommand()
  }

}
