package cz.cvut.fit.bioop.hackernewsclient.model.api

import cz.cvut.fit.bioop.hackernewsclient.parser.HtmlParser._
import cz.cvut.fit.bioop.hackernewsclient.converter.Converter._
import upickle.implicits.key

import java.time.format.DateTimeFormatter
import java.time.{Instant, LocalDateTime, ZoneId}

case class Comment(@key("id") id: Int,
                   @key("by") author: Option[String] = None,
                   @key("time") created: Option[Long] = None,
                   @key("text") text: Option[String] = None){

  override def toString: String = {
    val date =
      if (created.isDefined)
        LocalDateTime.ofInstant(
          Instant.ofEpochSecond(created.get),
          ZoneId.systemDefault()
        ).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
      else
        "..."

    s"${Console.BOLD}${author.getOrElse("...")} at $date${Console.RESET}:" +
      s" ${toFormattedText(text.getOrElse("..."))}\n"
  }
}

object Comment {
  implicit def rw: ReadWriter[Comment] = macroRW
}

