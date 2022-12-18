package cz.cvut.fit.bioop.hackernewsclient.controller.api

import cz.cvut.fit.bioop.hackernewsclient.converter.Converter._
import cz.cvut.fit.bioop.hackernewsclient.model.api.{Comment, Story, User}
import cz.cvut.fit.bioop.hackernewsclient.util.BufferUtil.BufferFormat

import scala.language.experimental.macros
import scala.io.Source



class HackerNewsApiImpl extends HackerNewsApi {

  private val url = "https://hacker-news.firebaseio.com/v0"

  override def loadTopStories(): Seq[Int] = {
    makeApiCall[Seq[Int]](s"$url/topstories.json").get
  }

  override def loadStoryById(id: Int): Option[Story] = {
    makeApiCall[Story](s"$url/item/$id.json")
  }

  override def loadUserById(id: String): Option[User] = {
    makeApiCall[User](s"$url/user/$id.json")
  }

  override def loadCommentById(id: Int): Option[Comment] = {
    makeApiCall[Comment](s"$url/item/$id.json")
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
