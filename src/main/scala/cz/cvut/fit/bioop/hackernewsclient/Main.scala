package cz.cvut.fit.bioop.hackernewsclient

import cz.cvut.fit.bioop.hackernewsclient.command.ErrorCommand
import cz.cvut.fit.bioop.hackernewsclient.controller.api.{HackerNewsApi, HackerNewsApiImpl}
import cz.cvut.fit.bioop.hackernewsclient.controller.{Controller, HackerNewsController}
import cz.cvut.fit.bioop.hackernewsclient.parser.InputParser
import cz.cvut.fit.bioop.hackernewsclient.renderer.{Renderer, RendererImpl}

object Main {
  def main(args: Array[String]): Unit = {

    val inputArguments = InputParser.parseInput(args)
    if(inputArguments == null)
      new ErrorCommand("Wrong input arguments").execute()
    else {

      val restApi: HackerNewsApi = new HackerNewsApiImpl
      val renderer: Renderer = new RendererImpl
      val controller: Controller = new HackerNewsController(restApi, renderer, inputArguments)

      controller.execute()
    }
  }
}
