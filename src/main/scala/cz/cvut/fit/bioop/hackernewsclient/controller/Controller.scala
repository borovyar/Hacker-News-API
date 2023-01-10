package cz.cvut.fit.bioop.hackernewsclient.controller

import cz.cvut.fit.bioop.hackernewsclient.command.Command

/***
 * Central class of application which is kind of "start point"
 */
trait Controller {

  /**
   * Main goal of method is to find needed command and execute it
   */
  def execute(command: Command): Unit

}
