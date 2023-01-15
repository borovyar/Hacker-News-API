package cz.cvut.fit.bioop.hackernewsclient

import cz.cvut.fit.bioop.hackernewsclient.command.other.ErrorCommand
import cz.cvut.fit.bioop.hackernewsclient.command.{Command, CommandReceiver}
import cz.cvut.fit.bioop.hackernewsclient.controller.api.{CachingHackerNewsApiImpl, HackerNewsApiImpl}
import cz.cvut.fit.bioop.hackernewsclient.controller.{Controller, HackerNewsController}
import cz.cvut.fit.bioop.hackernewsclient.parser.InputParser
import cz.cvut.fit.bioop.hackernewsclient.parser.command.{CommandParser, ParseCommand, ParseOption}
import cz.cvut.fit.bioop.hackernewsclient.renderer.{Renderer, RendererImpl}
import cz.cvut.fit.bioop.hackernewsclient.repository.cache.CacheImpl
import cz.cvut.fit.bioop.hackernewsclient.repository.file.FileCacheSystemImpl
import cz.cvut.fit.bioop.hackernewsclient.repository.{Repository, RepositoryImpl}
import cz.cvut.fit.bioop.hackernewsclient.util.TimeManager

object Main {
  def main(args: Array[String]): Unit = {

    val inputArguments = InputParser.parseInput(args)
    if(inputArguments == null)
      new ErrorCommand("Wrong input arguments").execute()
    else {

      val timeManager = new TimeManager(inputArguments.ttl.getOrElse(Properties.DEFAULT_TTL))

      val restApi = new CachingHackerNewsApiImpl(new HackerNewsApiImpl, new CacheImpl(new FileCacheSystemImpl(timeManager, Properties.CACHE_FILE_NAME), timeManager))
      val renderer: Renderer = new RendererImpl

      val repository: Repository = new RepositoryImpl(restApi)
      val commandReceiver = new CommandReceiver(renderer, repository)
      val commandParser = new CommandParser(new ParseCommand(commandReceiver), new ParseOption, commandReceiver)
      val command: Option[Command] = commandParser.parseCommand(inputArguments)
      val controller: Controller = new HackerNewsController


      controller.execute(command.getOrElse(new ErrorCommand("Command is None")))
    }
  }
}
