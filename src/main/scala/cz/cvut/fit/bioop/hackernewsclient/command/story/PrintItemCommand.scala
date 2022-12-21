package cz.cvut.fit.bioop.hackernewsclient.command.story

import cz.cvut.fit.bioop.hackernewsclient.command.Command
import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi
import cz.cvut.fit.bioop.hackernewsclient.converter.Converter._
import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer

class PrintItemCommand[T: Reader](api: HackerNewsApi,
                                  renderer: Renderer,
                                  ids: Seq[Int],
                                  page: Option[Int],
                                  size: Option[Int]) extends Command{

  def collectItem(id: Int) : T = api.loadItemById[T](id).get

  override def execute(): Unit = {
    val resultIds = getNeededIDs(ids)
    val items = resultIds.map(collectItem)
    items.foreach(renderer.renderToConsole)
  }

  private def getNeededIDs(ids: Seq[Int]): Seq[Int] = {
    page.flatMap(page => return if(page - 1 <= ids.size) Seq(ids.apply(page - 1)) else Seq(ids.last))
    size.flatMap(size => return if(size <= ids.size) ids.take(size) else ids)

    ids.take(20)
  }

}
