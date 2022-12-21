package cz.cvut.fit.bioop.hackernewsclient.model.api

import cz.cvut.fit.bioop.hackernewsclient.converter.Converter._
import upickle.implicits.key

/***
 * Model of the Story entity
 */
case class Story(@key("id") id: Int,
                 @key("by") author: Option[String] = None,
                 @key("kids") comments: Option[Seq[Int]] = None,
                 @key("url") url: Option[String] = None,
                 @key("score") score: Option[Int] = None,
                 @key("title") title: Option[String] = None,
                ){


  override def toString: String = {
    val commentsNumber = if(comments.isDefined) comments.get.size else 0

    s"${Console.BOLD}${title.getOrElse("-")}${Console.RESET} (${url.getOrElse("-")})\n" +
      s"\t${score.getOrElse("-")} points by ${author.getOrElse("-")} | $commentsNumber comments\n"

  }
}

object Story {
  implicit def reader: ReadWriter[Story] = macroRW
}
