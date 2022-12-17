package cz.cvut.fit.bioop.hackernewsclient.command

import cz.cvut.fit.bioop.hackernewsclient.controller.api.{HackerNewsApi, HackerNewsApiImpl}

trait Command {
  val hackerNewsApi: HackerNewsApi = new HackerNewsApiImpl

  def execute(): Unit
}
