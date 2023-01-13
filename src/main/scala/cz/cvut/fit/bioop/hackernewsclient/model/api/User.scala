package cz.cvut.fit.bioop.hackernewsclient.model.api

import cz.cvut.fit.bioop.hackernewsclient.converter.Converter._
import upickle.implicits.key

/***
 * Model of the User entity
 */
case class User(@key("id") id: String,
                @key("created") created: Long,
                @key("karma") karma: Int,
                @key("about") about: Option[String] = None,
                @key("submitted") submitted: Option[Seq[Int]] = None) extends Entity{
}

object User {
  implicit def rw: ReadWriter[User] = macroRW
}
