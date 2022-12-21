package cz.cvut.fit.bioop.hackernewsclient.command.comment

import cz.cvut.fit.bioop.hackernewsclient.command.Command
import cz.cvut.fit.bioop.hackernewsclient.command.story.PrintItemCommand
import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi
import cz.cvut.fit.bioop.hackernewsclient.model.api.{Comment, Story}
import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer

class CommentCommand(api: HackerNewsApi,
                     renderer: Renderer,
                     id: Int,
                     page: Option[Int],
                     size: Option[Int]) extends Command{

  override def execute(): Unit = {
    val story = api.loadItemById[Story](id)

    if(story.isEmpty || story.get.comments.isEmpty)
      renderer.renderToConsole("Story or Comments does not exist")
    else
      printComments(story.get.comments.get)
  }

  private def printComments(ids: Seq[Int]): Unit = {
    val printCommand = new PrintItemCommand[Comment](api, renderer, ids, page, size)
    printCommand.execute()
  }

}
