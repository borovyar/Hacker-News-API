package cz.cvut.fit.bioop.hackernewsclient.command.story

import cz.cvut.fit.bioop.hackernewsclient.command.Command
import cz.cvut.fit.bioop.hackernewsclient.model.api.Story

class PrintStoryCommand(ids: Seq[Int],
                        page: Option[Int]) extends Command{

  def collectStory(id: Int) : Story = hackerNewsApi.loadStoryById(id).get

  override def execute(): Unit = {
    val stories = ids.map(collectStory)

    if(page.isDefined)
      println(stories.apply(page.get - 1))
    else
      for(story <- stories)
        println(story)
  }
}
