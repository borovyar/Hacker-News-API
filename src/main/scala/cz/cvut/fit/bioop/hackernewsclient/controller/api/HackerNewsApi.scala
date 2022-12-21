package cz.cvut.fit.bioop.hackernewsclient.controller.api

import cz.cvut.fit.bioop.hackernewsclient.converter.Converter._
import cz.cvut.fit.bioop.hackernewsclient.model.api.User

trait HackerNewsApi {

  def loadTopStories(): Seq[Int]

  def loadItemById[T: Reader](id: Int): Option[T]

  def loadUserById(id: String): Option[User]

}
