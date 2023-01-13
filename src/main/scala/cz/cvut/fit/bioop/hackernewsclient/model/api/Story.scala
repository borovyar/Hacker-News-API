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
                ) extends Entity {


}

object Story {
  implicit def reader: ReadWriter[Story] = macroRW
}
