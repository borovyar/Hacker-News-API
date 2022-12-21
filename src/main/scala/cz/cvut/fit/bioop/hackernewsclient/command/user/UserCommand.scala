package cz.cvut.fit.bioop.hackernewsclient.command.user

import cz.cvut.fit.bioop.hackernewsclient.command.{Command, ErrorCommand}
import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi
import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer

class UserCommand(api: HackerNewsApi,
                  renderer: Renderer,
                  id: String) extends Command{

  override def execute(): Unit ={
    val user = api.loadUserById(id)

    if(user.isDefined)
      renderer.renderToConsole(user.get)
    else
      new ErrorCommand("User does not exist").execute()
  }
}
