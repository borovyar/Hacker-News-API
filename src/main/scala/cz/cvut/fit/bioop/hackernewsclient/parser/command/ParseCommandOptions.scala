package cz.cvut.fit.bioop.hackernewsclient.parser.command

import cz.cvut.fit.bioop.hackernewsclient.model.CommandOptions

object ParseCommandOptions {

  def getCommandOptions(commandOptions: List[String]): CommandOptions = {
    val result = new CommandOptions

    for (cmdOption <- commandOptions)
      cmdOption match {
        case page if page.matches("--page=\\d+") =>
          result.setPage(Some("""\d+""".r.findFirstIn(page).getOrElse("").toInt))
        case size if size.matches("--size=\\d+") =>
          result.setSize(Some("""\d+""".r.findFirstIn(size).getOrElse("").toInt))
        case id if id.matches("--id=([^\\\\s]*)") =>
          result.setId(Some("""--id=([^\\s]*)""".r.findFirstMatchIn(id).get.group(1)))
      }

    result
  }

}
