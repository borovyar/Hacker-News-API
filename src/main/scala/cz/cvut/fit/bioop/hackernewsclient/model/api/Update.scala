package cz.cvut.fit.bioop.hackernewsclient.model.api

import cz.cvut.fit.bioop.hackernewsclient.converter.Converter._
import upickle.implicits.key


case class Update(@key("items") items: Seq[Int],
                  @key("profiles") profiles: Seq[String]){

}

object Update {
  implicit def reader: ReadWriter[Update] = macroRW
}
