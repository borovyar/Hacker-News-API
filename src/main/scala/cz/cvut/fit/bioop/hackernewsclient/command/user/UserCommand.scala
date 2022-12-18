package cz.cvut.fit.bioop.hackernewsclient.command.user

import cz.cvut.fit.bioop.hackernewsclient.command.{Command, ErrorCommand}
import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi

class UserCommand(api: HackerNewsApi,
                  id: String) extends Command{

  override def execute(): Unit ={
    val user = api.loadUserById(id)

    if(user.isDefined)
      renderer.get.renderToConsole(user.get)
    else
      renderer.get.renderToConsole("User does not exist")
  }
}
