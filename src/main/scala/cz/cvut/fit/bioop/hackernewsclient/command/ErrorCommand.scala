package cz.cvut.fit.bioop.hackernewsclient.command

class ErrorCommand(message: String) extends Command {
  override def execute(): Unit = {
    println(message)
  }
}
