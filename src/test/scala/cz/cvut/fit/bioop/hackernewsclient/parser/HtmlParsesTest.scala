package cz.cvut.fit.bioop.hackernewsclient.parser

import org.scalatest.funsuite.AnyFunSuite

class HtmlParsesTest extends AnyFunSuite{

  test("Html text parsing") {
    var testString =
      "<i>This</i><p><b>is</b><p>&quot;<a href=\"fdf\">test </a><weird tag>parser</some closed tag>"

    testString = HtmlParser.toFormattedText(testString)
    assert(testString == "\u001b[3mThis\u001b[0m\n\u001b[1mis\u001b[0m\n\"test parser")
  }

}
