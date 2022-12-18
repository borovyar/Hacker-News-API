package cz.cvut.fit.bioop.hackernewsclient.renderer

import java.io.OutputStream

trait Renderer {

  def renderToConsole[T](entity: T): Unit

  def renderToOutputStream[T](entity: T, out: OutputStream): Unit

}
