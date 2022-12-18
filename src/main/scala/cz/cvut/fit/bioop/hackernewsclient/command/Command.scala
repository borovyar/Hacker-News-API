package cz.cvut.fit.bioop.hackernewsclient.command

import cz.cvut.fit.bioop.hackernewsclient.renderer.Renderer


trait Command {
  protected var renderer: Option[Renderer] = None
  def execute(): Unit
  def setRenderer(newRenderer: Renderer): Unit = {renderer = Some(newRenderer)}
}
