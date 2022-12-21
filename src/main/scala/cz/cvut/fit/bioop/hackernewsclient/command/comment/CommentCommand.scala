package cz.cvut.fit.bioop.hackernewsclient.command.comment

import cz.cvut.fit.bioop.hackernewsclient.command.Command
import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi
import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer

class CommentCommand(api: HackerNewsApi,
                     renderer: Renderer,
                     id: Int,
                     page: Option[Int],
                     size: Option[Int]) extends Command{

  override def execute(): Unit = {
    //load of the story with given id
    val story = api.loadStoryById(id)

    if(story.isEmpty || story.get.comments.isEmpty)
      renderer.renderToConsole("Story or Comments does not exist")
    else
      //print of the story comments
      printComments(story.get.comments.get)

  }

  private def printComments(ids: Seq[Int]): Unit = {
    val commentsIds = getNeededIDs(ids)
    for(id <- commentsIds) {
      //load comment from hacker news api
      val comment = api.loadCommentById(id)
      renderer.renderToConsole(if(comment.isDefined) comment.get else "Not existed id")
    }
  }


private def getNeededIDs(ids: Seq[Int]): Seq[Int] = {
  page.flatMap(page => return if(page - 1 <= ids.size) Seq(ids.apply(page - 1)) else Seq(ids.last))
  size.flatMap(size => return if(size <= ids.size) ids.take(size) else ids)

  ids.take(20)
  }

}
