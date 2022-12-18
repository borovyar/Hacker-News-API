package cz.cvut.fit.bioop.hackernewsclient.parser.command

import cz.cvut.fit.bioop.hackernewsclient.command.{Command, ErrorCommand}
import cz.cvut.fit.bioop.hackernewsclient.command.story.TopStoryCommand
import cz.cvut.fit.bioop.hackernewsclient.command.user.{UserCommand, UserStoriesCommand}
import cz.cvut.fit.bioop.hackernewsclient.model.CommandOptions

object ParseCommand {

  def getCommand(command: String, commandOptions: Option[CommandOptions]): Command = {
    command match {
      case "top-stories" => topStoriesCommand(commandOptions)
      case "user" => userCommand(commandOptions)
      case "user-stories" => userStoriesCommand(commandOptions)
      case _ => new ErrorCommand("Unknown command")
    }
  }

  private def topStoriesCommand(commandOptions: Option[CommandOptions]): Command = {
    if (commandOptions.isDefined)
      new TopStoryCommand(commandOptions.get.getPage, commandOptions.get.getSize)
    else
      new TopStoryCommand(None, None)
  }

  private def userCommand(commandOptions: Option[CommandOptions]): Command ={
    if(commandOptions.get.getId.isDefined)
      new UserCommand(commandOptions.get.getId.get)
    else
      new ErrorCommand("User without user Id")
  }

  private def userStoriesCommand(commandOptions: Option[CommandOptions]): Command = {
    if(commandOptions.isDefined && commandOptions.get.getId.isDefined)
      new UserStoriesCommand(commandOptions.get.getId.get, commandOptions.get.getPage, commandOptions.get.getSize)
    else
      new ErrorCommand("User without user Id")
  }
}
