package cz.cvut.fit.bioop.hackernewsclient.renderer

import cz.cvut.fit.bioop.hackernewsclient.model.api.{Comment, Story, User}

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
  def renderToConsole(message: String): Unit


  /***
   * Prints entity to any custom output stream
   *
   * @param entity which should be printed
   * @param out custom output stream for printing
   * @tparam T type of following entity
   */
  def renderToOutputStream[T](entity: T, out: OutputStream): Unit

  def renderStory(story: Story): Unit
  def renderComment(comment: Comment): Unit
  def renderUser(user: User): Unit

}
