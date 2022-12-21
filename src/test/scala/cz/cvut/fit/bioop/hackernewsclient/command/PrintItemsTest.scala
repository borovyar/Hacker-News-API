package cz.cvut.fit.bioop.hackernewsclient.command

import cz.cvut.fit.bioop.hackernewsclient.model.api.{Comment, Story, User}
import cz.cvut.fit.bioop.hackernewsclient.renderer.RendererImpl
import org.scalatest.funsuite.AnyFunSuite

import java.io.{ByteArrayOutputStream, PrintStream}

class PrintItemsTest extends AnyFunSuite{

  test("Comment console render test"){
    val renderer = new RendererImpl
    val testComment = Some(new Comment(
      1,
      Some("jake"),
      None,
      Some("some text")
    ))

    val out = new ByteArrayOutputStream()
    Console.withOut(new PrintStream(out)) {
      renderer.renderToOutputStream(testComment.get, out)
    }
    val output = out.toString("UTF-8")

    assert(output == "\u001B[1mjake at ...\u001B[0m: some text\n")
  }

  test("User console render test"){
    val renderer = new RendererImpl
    val testUser = Some(User(
      "john",
      1623494400,
      10,
      Some("some text."),
      Some(Seq(1, 2, 3))
    ))

    val out = new ByteArrayOutputStream()
    Console.withOut(new PrintStream(out)) {
      renderer.renderToOutputStream(testUser.get, out)
    }
    val output = out.toString("UTF-8")

    assert(output == testUser.get.toString)
  }

  test("Story console render test"){
    val renderer = new RendererImpl
    val testStory = Some(Story(
      1,
      Some("John"),
      Some(Seq(1, 2, 3)),
      Some("www.example.com"),
      Some(10),
      Some("story")
    ))


    val out = new ByteArrayOutputStream()
    Console.withOut(new PrintStream(out)) {
      renderer.renderToOutputStream(testStory.get, out)
    }
    val output = out.toString("UTF-8")

    assert(output == "\u001B[1mstory\u001B[0m (www.example.com)\n\t10 points by John | 3 comments\n")
  }

}
