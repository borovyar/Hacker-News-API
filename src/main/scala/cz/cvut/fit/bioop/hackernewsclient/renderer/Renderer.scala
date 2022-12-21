package cz.cvut.fit.bioop.hackernewsclient.renderer

import java.io.OutputStream

/***
 * Separate renderer which print entities to output streams
 */
trait Renderer {

  /***
   * Prints entity to the Console as output stream
   *
   * @param entity which should be printed
   * @tparam T type of following entity
   */
  def renderToConsole[T](entity: T): Unit


  /***
   * Prints entity to any custom output stream
   *
   * @param entity which should be printed
   * @param out custom output stream for printing
   * @tparam T type of following entity
   */
  def renderToOutputStream[T](entity: T, out: OutputStream): Unit

}
