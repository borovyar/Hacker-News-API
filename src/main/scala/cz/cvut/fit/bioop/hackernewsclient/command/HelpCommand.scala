package cz.cvut.fit.bioop.hackernewsclient.command

import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer

class HelpCommand(renderer: Renderer) extends Command {
  override def execute(): Unit = {
    val message: String = """Options:
                            --help - Information about available command and their usage.

                            Commands with options:
                            top-stories                    (OPTIONAL) --page=[NUM] - specific number of the story.
                                                           (OPTIONAL) --size=[NUM] - size of all displayed stories.


                            user-stories                   --id=[ANY] - id of user which stories you would like to see.
                                                           (OPTIONAL) --page=[NUM] - specific number of the story.
                                                           (OPTIONAL) --size=[NUM] - size of all displayed stories.

                            user                           --id=[ANY] - id of user which you would like to see.

                            comments                       --id=[ANY] - id of item which comments you would like to see.
                                                           (OPTIONAL) --page=[NUM] - specific number of the comment.
                                                           (OPTIONAL) --size=[NUM] - size of all displayed comments.


    """
    renderer.renderToConsole(message)
  }
}
