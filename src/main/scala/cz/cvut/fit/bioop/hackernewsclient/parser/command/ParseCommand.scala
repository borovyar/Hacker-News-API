package cz.cvut.fit.bioop.hackernewsclient.parser.command

import cz.cvut.fit.bioop.hackernewsclient.command.comment.CommentCommand
import cz.cvut.fit.bioop.hackernewsclient.command.{Command, ErrorCommand}
import cz.cvut.fit.bioop.hackernewsclient.command.story.TopStoryCommand
import cz.cvut.fit.bioop.hackernewsclient.command.user.{UserCommand, UserStoriesCommand}
import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi
import cz.cvut.fit.bioop.hackernewsclient.model.CommandOptions

object ParseCommand {

  def getCommand(api: HackerNewsApi,
                 command: String,
                 commandOptions: Option[CommandOptions]): Command = {
    command match {
      case "top-stories" => topStoriesCommand(api, commandOptions)
      case "user" => userCommand(api, commandOptions)
      case "user-stories" => userStoriesCommand(api, commandOptions)
      case "comments" => commentsCommand(api, commandOptions)
      case _ => new ErrorCommand("Unknown command")
    }
  }

  private def topStoriesCommand(api: HackerNewsApi,
                                commandOptions: Option[CommandOptions]): Command = {
    if (commandOptions.isDefined)
      new TopStoryCommand(api, commandOptions.get.getPage, commandOptions.get.getSize)
    else
      new TopStoryCommand(api, None, None)
  }

  private def userCommand(api: HackerNewsApi,
                          commandOptions: Option[CommandOptions]): Command ={
    if(commandOptions.get.getId.isDefined)
      new UserCommand(api, commandOptions.get.getId.get)
    else
      new ErrorCommand("User without user Id")
  }

  private def userStoriesCommand(api: HackerNewsApi,
                                 commandOptions: Option[CommandOptions]): Command = {
    if(commandOptions.isDefined && commandOptions.get.getId.isDefined)
      new UserStoriesCommand(
        api,
        commandOptions.get.getId.get,
        commandOptions.get.getPage,
        commandOptions.get.getSize
      )
    else
      new ErrorCommand("User without user Id")
  }

  private def commentsCommand(api: HackerNewsApi,
                              commandOptions: Option[CommandOptions]): Command = {
    if(commandOptions.isDefined && commandOptions.get.getId.isDefined)
      new CommentCommand(
        api,
        commandOptions.get.getId.get.toInt,
        commandOptions.get.getPage,
        commandOptions.get.getSize
      )
    else
      new ErrorCommand("Comment without Id")
  }
}
