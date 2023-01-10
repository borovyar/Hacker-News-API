package cz.cvut.fit.bioop.hackernewsclient.model

/***
 * Storage of parsed input arguments
 */
case class InputArguments (option: Option[String],
                           command: Option[String],
                           commandOptions: List[String],
                           ttl: Option[Long])
