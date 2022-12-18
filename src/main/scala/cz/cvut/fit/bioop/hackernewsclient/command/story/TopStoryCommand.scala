package cz.cvut.fit.bioop.hackernewsclient.command.story

import cz.cvut.fit.bioop.hackernewsclient.command.Command
import cz.cvut.fit.bioop.hackernewsclient.model.api.Story

class TopStoryCommand(page: Option[Int],
                      size: Option[Int]) extends Command{

  override def execute() : Unit = {
    val topIds =
      if (size.isEmpty) hackerNewsApi.loadTopStories().take(20)
      else hackerNewsApi.loadTopStories().take(size.get)

    new PrintStoryCommand(topIds, page).execute()
  }

}
