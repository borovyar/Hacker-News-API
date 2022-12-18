package cz.cvut.fit.bioop.hackernewsclient.command.user

import cz.cvut.fit.bioop.hackernewsclient.command.story.PrintStoryCommand
import cz.cvut.fit.bioop.hackernewsclient.command.{Command, ErrorCommand}

class UserStoriesCommand(id: String,
                         page: Option[Int],
                         size: Option[Int]) extends Command{


  override def execute() : Unit = {
    val user = hackerNewsApi.loadUserById(id)
    if(user.isEmpty)
      new ErrorCommand("User does not exist").execute()

    val storyIds = user.get.submitted.get
    val neededIds =
      if(size.isDefined && storyIds.size > size.get)
        storyIds.take(size.get)
      else
        if(storyIds.size > 20) storyIds.take(20) else storyIds

    new PrintStoryCommand(neededIds, page).execute()
  }

}
