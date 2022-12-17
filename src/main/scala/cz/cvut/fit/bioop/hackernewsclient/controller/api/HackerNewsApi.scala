package cz.cvut.fit.bioop.hackernewsclient.controller.api

import cz.cvut.fit.bioop.hackernewsclient.model.Story

trait HackerNewsApi {

  def loadTopStories(): Seq[Int]

  def loadStoryById(id: Int): Option[Story]

}
