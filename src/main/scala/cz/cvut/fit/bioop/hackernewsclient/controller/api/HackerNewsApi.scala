package cz.cvut.fit.bioop.hackernewsclient.controller.api

import cz.cvut.fit.bioop.hackernewsclient.model.api.{Comment, Story, User}

trait HackerNewsApi {

  def loadTopStories(): Seq[Int]

  def loadStoryById(id: Int): Option[Story]

  def loadCommentById(id: Int): Option[Comment]

  def loadUserById(id: String): Option[User]

}
