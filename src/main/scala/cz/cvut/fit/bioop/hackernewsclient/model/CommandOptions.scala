package cz.cvut.fit.bioop.hackernewsclient.model

case class CommandOptions(page: Option[Int] = None,
                          size: Option[Int] = None,
                          id: Option[String] = None)
