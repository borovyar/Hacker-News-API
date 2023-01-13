package cz.cvut.fit.bioop.hackernewsclient.parser

import org.scalatest.funsuite.AnyFunSuite

class InputParserTest extends AnyFunSuite{

  //Top Story tests

  test("Top story call"){
    val argsStory = Array("top-stories")
    val resultStory = InputParser.parseInput(argsStory)

    assert(resultStory.command.get == "top-stories" &&
      resultStory.option.isEmpty &&
      resultStory.commandOptions.isEmpty)
  }

  test("Top story call with options"){
    val argsStory = Array("top-stories", "--page=2")
    val resultStory = InputParser.parseInput(argsStory)

    assert(resultStory.command.get == "top-stories" &&
      resultStory.option.isEmpty &&
      resultStory.commandOptions == List("--page=2"))
  }

  test("Invalid top story call"){
    val argsStory = Array("top-stories", "user")
    val resultStory = InputParser.parseInput(argsStory)

    assert(resultStory == null)
  }

  ///////////////////////////////////////////////////////////////////////////

  //User tests

  test("User call"){
    val argsStory = Array("user", "--id=jl")
    val resultStory = InputParser.parseInput(argsStory)

    assert(resultStory.command.get == "user" &&
      resultStory.option.isEmpty &&
      resultStory.commandOptions == List("--id=jl")
    )
  }

  test("Invalid user call"){
    val argsStory = Array("user", "random")
    val resultStory = InputParser.parseInput(argsStory)

    assert(resultStory == null)
  }

  ///////////////////////////////////////////////////////////////////////////

  //User stories tests

  test("User stories call"){
    val argsStory = Array("user-stories", "--id=jl")
    val resultStory = InputParser.parseInput(argsStory)

    assert(resultStory.command.get == "user-stories" &&
      resultStory.option.isEmpty &&
      resultStory.commandOptions == List("--id=jl")
    )
  }

  test("User stories call with option"){
    val argsStory = Array("user-stories", "--id=jl", "--page=2")
    val resultStory = InputParser.parseInput(argsStory)

    assert(resultStory.command.get == "user-stories" &&
      resultStory.option.isEmpty &&
      resultStory.commandOptions == List("--page=2", "--id=jl")
    )
  }

  test("Invalid user stories call"){
    val argsStory = Array("user-stories", "random")
    val resultStory = InputParser.parseInput(argsStory)

    assert(resultStory == null)
  }

  ///////////////////////////////////////////////////////////////////////////

  //Comments tests

  test("Comments call"){
    val argsStory = Array("comments", "--id=29876")
    val resultStory = InputParser.parseInput(argsStory)

    assert(resultStory.command.get == "comments" &&
      resultStory.option.isEmpty &&
      resultStory.commandOptions == List("--id=29876")
    )
  }

  test("Comments call with option"){
    val argsStory = Array("comments", "--id=29876", "--page=2")
    val resultStory = InputParser.parseInput(argsStory)

    assert(resultStory.command.get == "comments" &&
      resultStory.option.isEmpty &&
      resultStory.commandOptions == List("--page=2", "--id=29876")
    )
  }

  test("Invalid comments call"){
    val argsStory = Array("user-stories", "random")
    val resultStory = InputParser.parseInput(argsStory)

    assert(resultStory == null)
  }

  ///////////////////////////////////////////////////////////////////////////
}
