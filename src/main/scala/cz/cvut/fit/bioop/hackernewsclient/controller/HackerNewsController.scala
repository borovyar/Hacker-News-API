package cz.cvut.fit.bioop.hackernewsclient.controller

import cz.cvut.fit.bioop.hackernewsclient.command.Command

class HackerNewsController extends Controller {

  override def execute(command: Command): Unit = {
    command.execute()
  }
}
