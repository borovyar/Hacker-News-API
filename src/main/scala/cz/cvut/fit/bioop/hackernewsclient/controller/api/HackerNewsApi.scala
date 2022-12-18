package cz.cvut.fit.bioop.hackernewsclient.controller.api

import cz.cvut.fit.bioop.hackernewsclient.model.api.{Story, User}

trait HackerNewsApi {

  def loadTopStories(): Seq[Int]

  def loadStoryById(id: Int): Option[Story]

  def loadUserById(id: String): Option[User]

}
