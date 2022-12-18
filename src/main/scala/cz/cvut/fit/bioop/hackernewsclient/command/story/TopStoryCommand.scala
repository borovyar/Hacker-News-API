package cz.cvut.fit.bioop.hackernewsclient.command.story

import cz.cvut.fit.bioop.hackernewsclient.command.Command
import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi
import cz.cvut.fit.bioop.hackernewsclient.model.api.Story

class TopStoryCommand(api: HackerNewsApi,
                      page: Option[Int],
                      size: Option[Int]) extends Command{

  override def execute() : Unit = {
    val topIds =
      if (size.isEmpty) api.loadTopStories().take(20)
      else api.loadTopStories().take(size.get)

    new PrintStoryCommand(api, this.renderer, topIds, page).execute()
  }

}
