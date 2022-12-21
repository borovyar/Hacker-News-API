package cz.cvut.fit.bioop.hackernewsclient.command.story

import cz.cvut.fit.bioop.hackernewsclient.command.Command
import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi
import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer

class TopStoryCommand(api: HackerNewsApi,
                      renderer: Renderer,
                      page: Option[Int],
                      size: Option[Int]) extends Command{

  override def execute() : Unit = {
    //load a top stories and immediately pass them to print command
    val printCommand = new PrintStoryCommand(api, renderer, api.loadTopStories(), page, size)
    printCommand.execute()
  }


}
