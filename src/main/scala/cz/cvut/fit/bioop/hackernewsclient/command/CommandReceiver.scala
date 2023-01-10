package cz.cvut.fit.bioop.hackernewsclient.command

import cz.cvut.fit.bioop.hackernewsclient.command.other.ErrorCommand
import cz.cvut.fit.bioop.hackernewsclient.model.PagingOptions
import cz.cvut.fit.bioop.hackernewsclient.model.api.User
import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer
import cz.cvut.fit.bioop.hackernewsclient.repository.Repository

class CommandReceiver(renderer: Renderer,
                      repository: Repository) {

  private def withUser(id: String, action: User => Unit): Unit = {
    val user = repository.loadUserById(id)

    if (user.isDefined)
      action(user.get)
    else
      new ErrorCommand("User does not exist").execute()
  }


  def runTopStoriesCommand(pageOptions: PagingOptions): Unit = {
    val topStories = repository.loadTopStories(pageOptions)
    topStories.foreach(renderer.renderStory)
  }

  def runUserCommand(id: String): Unit = {
    withUser(id, user =>
      renderer.renderUser(user)
      )
  }

  def runUserStoriesCommand(id: String,
                            pageOptions: PagingOptions): Unit = {
    withUser(id, user =>
      repository.loadUserStories(user, pageOptions).foreach(renderer.renderStory)
      )
  }

  def runCommentCommand(id: Int,
                        pagingOptions: PagingOptions): Unit = {
    repository.loadCommentsById(id, pagingOptions).foreach(renderer.renderComment)
  }

  def runClearCacheCommand(): Unit = repository.clearCache()


  def runHelpCommand(): Unit = {
    val message: String =
      """Options:
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
