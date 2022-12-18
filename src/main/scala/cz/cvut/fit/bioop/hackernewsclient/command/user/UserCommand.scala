package cz.cvut.fit.bioop.hackernewsclient.command.user

import cz.cvut.fit.bioop.hackernewsclient.command.{Command, ErrorCommand}

class UserCommand(id: String) extends Command{

  override def execute(): Unit ={
    val user = hackerNewsApi.loadUserById(id)

    if(user.isDefined)
      println(user.get)
    else
      println("User does not exist")
  }
}
