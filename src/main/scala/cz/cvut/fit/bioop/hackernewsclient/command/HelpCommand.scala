package cz.cvut.fit.bioop.hackernewsclient.command

class HelpCommand extends Command {
  override def execute(): Unit = {
    val message: String = """|Available options:
                            --help - Information about available command and their usage.

                            Available commands with options:
                            top-stories                    (OPTIONAL) --page=[NUM] - exact number of the page.
                                                           (OPTIONAL) --size=[NUM] - size of all displayed pages.
    """
    Console.out.write(message.getBytes)
    Console.out.flush()
  }
}
