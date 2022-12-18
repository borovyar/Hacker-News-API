package cz.cvut.fit.bioop.hackernewsclient

import cz.cvut.fit.bioop.hackernewsclient.controller.{Controller, HackerNewsController}
import cz.cvut.fit.bioop.hackernewsclient.parser.InputParser

object Main {
  def main(args: Array[String]): Unit = {

    val inputArguments = InputParser.parseInput(args)
    val controller: Controller = new HackerNewsController(inputArguments)

    controller.execute()
  }
}
