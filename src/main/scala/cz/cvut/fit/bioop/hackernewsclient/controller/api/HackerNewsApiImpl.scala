package cz.cvut.fit.bioop.hackernewsclient.controller.api

import cz.cvut.fit.bioop.hackernewsclient.converter.Converter.read
import cz.cvut.fit.bioop.hackernewsclient.model.Story
import cz.cvut.fit.bioop.hackernewsclient.util.BufferUtil.BufferFormat

import scala.io.Source



class HackerNewsApiImpl extends HackerNewsApi {

  private val url = "https://hacker-news.firebaseio.com/v0"

  override def loadTopStories(): Seq[Int] = {
    try {

      val response = Source.fromURL(s"$url/topstories.json")
      read[Seq[Int]](response.formattedString())

    } catch {
      case _: Throwable => Seq()
    }
  }

  override def loadStoryById(id: Int): Option[Story] = {
    try {
      val response = Source.fromURL(s"$url/item/$id.json")
      Option(read[Story](response.formattedString()))
    }
    catch {
      case _: Throwable => None
    }
  }
}
