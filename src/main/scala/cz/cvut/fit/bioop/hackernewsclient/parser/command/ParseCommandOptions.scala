package cz.cvut.fit.bioop.hackernewsclient.parser.command

import cz.cvut.fit.bioop.hackernewsclient.model.CommandOptions

object ParseCommandOptions {

  def getCommandOptions(commandOptions: List[String]): CommandOptions = {
    var newSize: Option[Int] = None
    var newPage: Option[Int] = None
    var newId: Option[String] = None

    for (cmdOption <- commandOptions)
      cmdOption match {
        case page if page.matches("--page=\\d+") =>
          newPage = Some("""\d+""".r.findFirstIn(page).getOrElse("").toInt)
        case size if size.matches("--size=\\d+") =>
          newSize = Some("""\d+""".r.findFirstIn(size).getOrElse("").toInt)
        case id if id.matches("--id=([^\\\\s]*)") =>
          newId = Some("""--id=([^\\s]*)""".r.findFirstMatchIn(id).get.group(1))
      }

    CommandOptions(newPage, newSize, newId)
  }

}
