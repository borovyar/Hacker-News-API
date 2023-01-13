package cz.cvut.fit.bioop.hackernewsclient.model.cache

import cz.cvut.fit.bioop.hackernewsclient.converter.Converter._
import upickle.implicits.key

case class CacheData(@key("update_time") updateTime: Long,
                     @key("entities") entities: Seq[CacheEntity])

object CacheData {
  implicit def rw: ReadWriter[CacheData] = macroRW
}