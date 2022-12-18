package cz.cvut.fit.bioop.hackernewsclient.command.user

import cz.cvut.fit.bioop.hackernewsclient.command.story.PrintStoryCommand
import cz.cvut.fit.bioop.hackernewsclient.command.{Command, ErrorCommand}

class UserStoriesCommand(id: String,
                         page: Option[Int],
                         size: Option[Int]) extends Command{


  override def execute() : Unit = {
    val user = hackerNewsApi.loadUserById(id)
    if(user.isEmpty) {
      println("User does not exist")
      return
    }

    printStories(if(user.get.submitted.isDefined) user.get.submitted.get else Seq())
  }

  private def printStories(storyIds: Seq[Int]): Unit = {
    val neededIds =
      if(size.isDefined && storyIds.size > size.get)
        storyIds.take(size.get)
      else
        if(storyIds.size > 20) storyIds.take(20) else storyIds

    new PrintStoryCommand(neededIds, page).execute()
  }

}
