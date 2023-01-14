package cz.cvut.fit.bioop.hackernewsclient.model.api

import cz.cvut.fit.bioop.hackernewsclient.converter.Converter._
import upickle.implicits.key

/***
 * Model of the Comment entity
 */
case class Comment(@key("id") id: Int,
                   @key("by") author: Option[String] = None,
                   @key("time") created: Option[Long] = None,
                   @key("text") text: Option[String] = None){

}

object Comment {
  implicit def rw: ReadWriter[Comment] = macroRW
}

