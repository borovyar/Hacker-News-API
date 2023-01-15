package cz.cvut.fit.bioop.hackernewsclient.commands

import cz.cvut.fit.bioop.hackernewsclient.command.CommandReceiver
import cz.cvut.fit.bioop.hackernewsclient.command.story.TopStoryCommand
import cz.cvut.fit.bioop.hackernewsclient.model.PagingOptions
import org.mockito.MockitoSugar._
import org.scalatest.funsuite.AnyFunSuite

class TopStoriesCommandTest extends AnyFunSuite{

  val commandReceiver: CommandReceiver = mock[CommandReceiver]
  val pagingOptions: PagingOptions = mock[PagingOptions]

  val topStoriesCommand = new TopStoryCommand(commandReceiver, pagingOptions)

  test("Top stories command execute test"){
    topStoriesCommand.execute()

    verify(commandReceiver, times(1)).runTopStoriesCommand(pagingOptions)
  }

}
