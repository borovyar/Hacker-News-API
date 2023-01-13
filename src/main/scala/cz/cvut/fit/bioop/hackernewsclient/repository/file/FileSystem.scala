package cz.cvut.fit.bioop.hackernewsclient.repository.file

trait FileSystem[T]{

  def loadData(): Option[T]

  def saveData(data: T): Unit

  def clearData(): Unit

}
