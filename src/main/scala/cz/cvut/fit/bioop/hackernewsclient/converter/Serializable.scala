package cz.cvut.fit.bioop.hackernewsclient.converter

trait Serializable[T]{
  def toJson(entity: T): String
}
