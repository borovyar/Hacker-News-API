package cz.cvut.fit.bioop.hackernewsclient.model.api

import cz.cvut.fit.bioop.hackernewsclient.converter.Converter._
import cz.cvut.fit.bioop.hackernewsclient.parser.HtmlParser._
import upickle.implicits.key

import java.time.format.DateTimeFormatter
import java.time.{Instant, LocalDateTime, ZoneId}

case class User(@key("id") id: String,
                @key("created") created: Long,
                @key("karma") karma: Int,
                @key("about") about: Option[String] = None,
                @key("submitted") submitted: Option[Seq[Int]] = None){

  override def toString: String = {
    val numberOfStories = submitted.getOrElse(List()).size
    val aboutUser = about.getOrElse("...")
    val registrationDate = LocalDateTime.ofInstant(
      Instant.ofEpochSecond(created), ZoneId.systemDefault()
    ).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))

    s"The user ${Console.BOLD}$id wrote about himself: ${toFormattedText(aboutUser)}.\n" +
      s"$registrationDate - registration date and karma is $karma. " +
      s"Number of posted stories - $numberOfStories"
  }
}

object User {
  implicit def rw: ReadWriter[User] = macroRW
}
