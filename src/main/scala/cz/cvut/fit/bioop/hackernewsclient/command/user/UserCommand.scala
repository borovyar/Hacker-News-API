package cz.cvut.fit.bioop.hackernewsclient.command.user

import cz.cvut.fit.bioop.hackernewsclient.command.{Command, CommandReceiver}

class UserCommand(receiver: CommandReceiver,
                  id: String) extends Command{

  override def execute(): Unit ={
    receiver.runUserCommand(id)
  }
}
