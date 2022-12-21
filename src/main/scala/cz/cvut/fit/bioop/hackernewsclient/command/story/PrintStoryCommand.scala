package cz.cvut.fit.bioop.hackernewsclient.command.story

import cz.cvut.fit.bioop.hackernewsclient.command.Command
import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi
import cz.cvut.fit.bioop.hackernewsclient.model.api.Story
import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer

class PrintStoryCommand(api: HackerNewsApi,
                        renderer: Renderer,
                        ids: Seq[Int],
                        page: Option[Int],
                        size: Option[Int]) extends Command{

  def collectItem(id: Int) : Option[Story] = api.loadStoryById(id)

  override def execute(): Unit = {
    val resultIds = getNeededIDs(ids)
    val stories = resultIds.map(collectItem)
    for(story <- stories) renderer.renderToConsole(if(story.isDefined) story.get else "Not existed id")
  }

  private def getNeededIDs(ids: Seq[Int]): Seq[Int] = {
    page.flatMap(page => return if(page - 1 <= ids.size) Seq(ids.apply(page - 1)) else Seq(ids.last))
    size.flatMap(size => return if(size <= ids.size) ids.take(size) else ids)

    if(ids.size < 20)
      return ids

    ids.take(20)
  }

}
