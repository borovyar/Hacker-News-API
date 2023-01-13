package cz.cvut.fit.bioop.hackernewsclient.parser.command

import cz.cvut.fit.bioop.hackernewsclient.command.cache.ClearCacheCommand
import cz.cvut.fit.bioop.hackernewsclient.command.comment.CommentCommand
import cz.cvut.fit.bioop.hackernewsclient.command.other.ErrorCommand
import cz.cvut.fit.bioop.hackernewsclient.command.story.TopStoryCommand
import cz.cvut.fit.bioop.hackernewsclient.command.user.{UserCommand, UserStoriesCommand}
import cz.cvut.fit.bioop.hackernewsclient.command.{Command, CommandReceiver}
import cz.cvut.fit.bioop.hackernewsclient.model.{CommandOptions, PagingOptions}

/***
 * Parses main command of application
 */
class ParseCommand(receiver: CommandReceiver) {

  /***
   * Chooses correct Command object using a command from application arguments
   * and command options.
   *
   * @param command string which should be command
   * @param commandOptions to customize command
   * @return Command which has to be executed
   */
  def getCommand(command: String,
                 commandOptions: Option[CommandOptions]): Option[Command] = {

    try {
      command match {
        case "top-stories" => Some(topStoriesCommand(commandOptions))
        case "user" => Some(userCommand(commandOptions))
        case "user-stories" => Some(userStoriesCommand(commandOptions))
        case "comments" => Some(commentsCommand(commandOptions))
        case "clear-cache" => Some(clearCacheCommand())
        case _ => Some(new ErrorCommand("Unknown command"))
      }
    }catch {
      case _: Throwable => Some(new ErrorCommand("Command parsing error. Wrong option arguments"))
    }
  }

  private def topStoriesCommand(commandOptions: Option[CommandOptions]): Command = {
    new TopStoryCommand(
      receiver, PagingOptions(
        commandOptions.flatMap(_.page),
        commandOptions.flatMap(_.size).getOrElse(20)
      )
    )
  }

  private def userCommand(commandOptions: Option[CommandOptions]): Command ={
    if(commandOptions.get.id.isDefined)
      new UserCommand(
        receiver,
        commandOptions.get.id.get
      )
    else
      new ErrorCommand("User without user Id")
  }

  private def userStoriesCommand(commandOptions: Option[CommandOptions]): Command = {
    if(commandOptions.isDefined && commandOptions.get.id.isDefined)
      new UserStoriesCommand(
        receiver,
        commandOptions.get.id.get,
        PagingOptions(
          commandOptions.flatMap(_.page),
          commandOptions.flatMap(_.size).getOrElse(20)
        )
      )
    else
      new ErrorCommand("User without user Id")
  }

  private def commentsCommand(commandOptions: Option[CommandOptions]): Command = {
    if(commandOptions.isDefined && commandOptions.get.id.isDefined)
      new CommentCommand(
        receiver,
        commandOptions.get.id.get.toInt,
        PagingOptions(
          commandOptions.flatMap(_.page),
          commandOptions.flatMap(_.size).getOrElse(20)
        )
      )
    else
      new ErrorCommand("Comment without Id")
  }

  private def clearCacheCommand(): Command = {
    new ClearCacheCommand(receiver)
  }
}
