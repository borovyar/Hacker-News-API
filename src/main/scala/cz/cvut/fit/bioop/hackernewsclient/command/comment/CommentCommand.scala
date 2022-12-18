package cz.cvut.fit.bioop.hackernewsclient.command.comment

import cz.cvut.fit.bioop.hackernewsclient.command.Command

class CommentCommand(id: Int,
                     page: Option[Int],
                     size: Option[Int]) extends Command{

  override def execute(): Unit = {
    val story = hackerNewsApi.loadStoryById(id)

    if(story.isEmpty || story.get.comments.isEmpty)
      println("Story or Comments do not exist")
    else
      printComments(story.get.comments.get)
  }

  private def printComments(ids: Seq[Int]): Unit = {
    val neededIds =
      if(size.isDefined && ids.size > size.get)
        ids.take(size.get)
      else
        if(ids.size > 5) ids.take(5) else ids

    if(page.isDefined && page.get - 1 <= neededIds.size)
      printComment(neededIds.apply(page.get - 1))
    else
      neededIds.foreach(printComment)
  }

  private def printComment(id: Int): Unit = println(hackerNewsApi.loadCommentById(id).get)
}
