package cz.cvut.fit.bioop.hackernewsclient.command.user

import cz.cvut.fit.bioop.hackernewsclient.command.Command
import cz.cvut.fit.bioop.hackernewsclient.command.story.PrintStoryCommand
import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi
import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer

class UserStoriesCommand(api: HackerNewsApi,
                         renderer: Renderer,
                         id: String,
                         page: Option[Int],
                         size: Option[Int]) extends Command{


  override def execute() : Unit = {
    //load user using id from hacker news api
    val user = api.loadUserById(id)

    if(user.isEmpty)
      renderer.renderToConsole("User does not exist")
    else
      //print stories of the user if they are present
      printStories(if(user.get.submitted.isDefined) user.get.submitted.get else Seq())
  }

  private def printStories(storyIds: Seq[Int]): Unit =
    new PrintStoryCommand(api, renderer, storyIds, page, size).execute()

}
