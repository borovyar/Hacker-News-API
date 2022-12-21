package cz.cvut.fit.bioop.hackernewsclient.controller.api

import cz.cvut.fit.bioop.hackernewsclient.converter.Converter._
import cz.cvut.fit.bioop.hackernewsclient.model.api.User
import cz.cvut.fit.bioop.hackernewsclient.util.BufferUtil.BufferFormat

import scala.io.Source
import scala.language.experimental.macros



class HackerNewsApiImpl extends HackerNewsApi {

  private val url = "https://hacker-news.firebaseio.com/v0"

  override def loadTopStories(): Seq[Int] = {
    makeApiCall[Seq[Int]](s"$url/topstories.json").get
  }

  override def loadItemById[T: Reader](id: Int): Option[T] = {
    makeApiCall[T](s"$url/item/$id.json")
  }

  override def loadUserById(id: String): Option[User] = {
    makeApiCall[User](s"$url/user/$id.json")
  }

  private def makeApiCall[T: Reader](path: String): Option[T] = {
    try {
      val response = Source.fromURL(path)
      Option(read[T](response.formattedString()))
    }
    catch {
      case _: Throwable => None
    }
  }
}
