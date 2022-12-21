package cz.cvut.fit.bioop.hackernewsclient.command

/***
 * Basic Command trait for the Command Pattern implementation
 */
trait Command {

  /***
   * Run a main logic of application, which depends on specific command task
   *
   * @return nothing but often result prints via render classes
   */
  def execute(): Unit
}
