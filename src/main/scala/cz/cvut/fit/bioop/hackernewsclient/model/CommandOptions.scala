package cz.cvut.fit.bioop.hackernewsclient.model

/***
 * Model which contains options of the main command
 */
case class CommandOptions(page: Option[Int] = None,
                          size: Option[Int] = None,
                          id: Option[String] = None)
