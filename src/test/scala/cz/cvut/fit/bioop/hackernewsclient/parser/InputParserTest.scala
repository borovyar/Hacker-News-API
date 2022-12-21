package cz.cvut.fit.bioop.hackernewsclient.parser

import org.scalatest.funsuite.AnyFunSuite

class InputParserTest extends AnyFunSuite{

  test("Correct input arguments test") {
    val argsStory = Array("top-stories")
    val resultStory = InputParser.parseInput(argsStory)

    val argsHelp = Array("--help")
    val resultHelp = InputParser.parseInput(argsHelp)

    val argsUser = Array("user", "--id=jl")
    val resultUser = InputParser.parseInput(argsUser)


    assert(resultStory.command.get == "top-stories" && resultStory.option.isEmpty && resultStory.commandOptions.isEmpty)
    assert(resultHelp.option.get == "--help" && resultHelp.command.isEmpty && resultHelp.commandOptions.isEmpty)
    assert(resultUser.command.get == "user" && resultUser.commandOptions == List("--id=jl") && resultUser.option.isEmpty)
  }

  test("Bad input arguments test") {
    val argsMult = Array("top-stories", "user")
    val resultMult = InputParser.parseInput(argsMult)

    val argsEmpty = Array("")
    val resultEmpty = InputParser.parseInput(argsEmpty)

    assert(resultMult == null)
    assert(resultEmpty.command.get == "" && resultEmpty.option.isEmpty && resultEmpty.commandOptions.isEmpty)
  }
}
