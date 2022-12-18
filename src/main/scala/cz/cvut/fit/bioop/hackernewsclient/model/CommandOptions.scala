package cz.cvut.fit.bioop.hackernewsclient.model

class CommandOptions {
  private var page: Option[Int] = None
  private var size: Option[Int] = None
  private var id: Option[String] = None

  def getPage: Option[Int] = page
  def setPage(newPage: Option[Int]): Unit = {page = newPage}

  def getSize: Option[Int] = size
  def setSize(newSize: Option[Int]): Unit = {size = newSize}

  def getId: Option[String] = id
  def setId(newId: Option[String]): Unit = {id = newId}
}
