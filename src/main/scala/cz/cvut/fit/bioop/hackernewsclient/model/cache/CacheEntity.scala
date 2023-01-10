package cz.cvut.fit.bioop.hackernewsclient.model.cache

import cz.cvut.fit.bioop.hackernewsclient.converter.Converter._
import upickle.implicits.key

case class CacheEntity(@key("id") id: String,
                  @key("ttl") ttl: Long,
                  @key("entity") entity: String) {

}

object CacheEntity {
  implicit def rw: ReadWriter[CacheEntity] = macroRW
}

