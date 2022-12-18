package cz.cvut.fit.bioop.hackernewsclient.command

class HelpCommand extends Command {
  override def execute(): Unit = {
    val message: String = """Options:
                            --help - Information about available command and their usage.

                            Commands with options:
                            top-stories                    (OPTIONAL) --page=[NUM] - exact number of the story.
                                                           (OPTIONAL) --size=[NUM] - size of all displayed stories.


                            user-stories                   --id=[ANY] - id of user which stories you would like to see.
                                                           (OPTIONAL) --page=[NUM] - exact number of the story.
                                                           (OPTIONAL) --size=[NUM] - size of all displayed stories.

    """
    Console.out.write(message.getBytes)
    Console.out.flush()
  }
}
