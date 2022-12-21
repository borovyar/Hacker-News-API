package cz.cvut.fit.bioop.hackernewsclient.model

case class InputArguments (option: Option[String],
                           command: Option[String],
                           commandOptions: List[String])
