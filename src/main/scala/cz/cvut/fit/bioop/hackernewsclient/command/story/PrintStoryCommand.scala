package cz.cvut.fit.bioop.hackernewsclient.command.story

import cz.cvut.fit.bioop.hackernewsclient.command.Command
import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi
import cz.cvut.fit.bioop.hackernewsclient.model.api.Story
import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer

class PrintStoryCommand(api: HackerNewsApi,
                        newRenderer: Option[Renderer],
                        ids: Seq[Int],
                        page: Option[Int]) extends Command{

  def collectStory(id: Int) : Story = api.loadStoryById(id).get

  override def execute(): Unit = {
    this.renderer = newRenderer
    val stories = ids.map(collectStory)

    if(page.isDefined) {
      renderer.get.renderToConsole(stories.apply(page.get - 1))
    } else
      for(story <- stories)
        renderer.get.renderToConsole(story)
  }

}
