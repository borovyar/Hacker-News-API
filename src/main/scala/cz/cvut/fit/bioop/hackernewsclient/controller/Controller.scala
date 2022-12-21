package cz.cvut.fit.bioop.hackernewsclient.controller

/***
 * Central class of application which is kind of "start point"
 */
trait Controller {

  /**
   * Main of method is to find needed command and execute it
   */
  def execute(): Unit

}
