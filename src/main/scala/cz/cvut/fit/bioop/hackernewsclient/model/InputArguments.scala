package cz.cvut.fit.bioop.hackernewsclient.model

class InputArguments (opt: Option[String],
                      cmd: Option[String],
                      cmdOptions: List[String]){
  def option: Option[String] = opt
  def command: Option[String] = cmd
  def commandOptions: List[String] = cmdOptions
}
