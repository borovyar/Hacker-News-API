package cz.cvut.fit.bioop.hackernewsclient.parser.command

import cz.cvut.fit.bioop.hackernewsclient.command.comment.CommentCommand
import cz.cvut.fit.bioop.hackernewsclient.command.story.TopStoryCommand
import cz.cvut.fit.bioop.hackernewsclient.command.user.{UserCommand, UserStoriesCommand}
import cz.cvut.fit.bioop.hackernewsclient.command.{Command, ErrorCommand}
import cz.cvut.fit.bioop.hackernewsclient.controller.api.HackerNewsApi
import cz.cvut.fit.bioop.hackernewsclient.model.CommandOptions
import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer

object ParseCommand {

  def getCommand(api: HackerNewsApi,
                 renderer: Renderer,
                 command: String,
                 commandOptions: Option[CommandOptions]): Command = {

    command match {
      case "top-stories" => topStoriesCommand(api, renderer, commandOptions)
      case "user" => userCommand(api, renderer, commandOptions)
      case "user-stories" => userStoriesCommand(api, renderer, commandOptions)
      case "comments" => commentsCommand(api, renderer, commandOptions)
      case _ => new ErrorCommand("Unknown command")
    }
  }

  private def topStoriesCommand(api: HackerNewsApi,
                                renderer: Renderer,
                                commandOptions: Option[CommandOptions]): Command = {
    new TopStoryCommand(
      api,
      renderer,
      commandOptions.flatMap(_.page),
      commandOptions.flatMap(_.size)
    )
  }

  private def userCommand(api: HackerNewsApi,
                          renderer: Renderer,
                          commandOptions: Option[CommandOptions]): Command ={
    if(commandOptions.get.id.isDefined)
      new UserCommand(
        api,
        renderer,
        commandOptions.get.id.get
      )
    else
      new ErrorCommand("User without user Id")
  }

  private def userStoriesCommand(api: HackerNewsApi,
                                 renderer: Renderer,
                                 commandOptions: Option[CommandOptions]): Command = {
    if(commandOptions.isDefined && commandOptions.get.id.isDefined)
      new UserStoriesCommand(
        api,
        renderer,
        commandOptions.get.id.get,
        commandOptions.get.page,
        commandOptions.get.size
      )
    else
      new ErrorCommand("User without user Id")
  }

  private def commentsCommand(api: HackerNewsApi,
                              renderer: Renderer,
                              commandOptions: Option[CommandOptions]): Command = {
    if(commandOptions.isDefined && commandOptions.get.id.isDefined)
      new CommentCommand(
        api,
        renderer,
        commandOptions.get.id.get.toInt,
        commandOptions.get.page,
        commandOptions.get.size
      )
    else
      new ErrorCommand("Comment without Id")
  }
}
