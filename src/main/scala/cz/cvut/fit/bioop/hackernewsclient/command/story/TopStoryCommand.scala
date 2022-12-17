package cz.cvut.fit.bioop.hackernewsclient.command.story

import cz.cvut.fit.bioop.hackernewsclient.command.Command
import cz.cvut.fit.bioop.hackernewsclient.model.Story

class TopStoryCommand(page: Option[Int],
                      size: Option[Int]) extends Command{

  def collectStory(id: Int) : Story = hackerNewsApi.loadStoryById(id).get

  override def execute() : Unit = {
    val topIds =
      if (size.isEmpty) hackerNewsApi.loadTopStories().take(20)
      else hackerNewsApi.loadTopStories().take(size.get)

    val stories = topIds.map(collectStory)

    if(page.isDefined)
      println(stories.apply(page.get - 1))
    else
      for(story <- stories)
        println(story)
  }

}
