package cz.cvut.fit.bioop.hackernewsclient.renderer

import cz.cvut.fit.bioop.hackernewsclient.model.api.{Comment, Story, User}

import java.io.OutputStream

/***
 * Separate renderer which prints entities to the output streams
 */
trait Renderer {

  /**
   * Prints message to the console
   * @param message which should be printed
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

  /**
   * Prints to the console entity of Story
   * @param story which should be printed
   */
  def renderStory(story: Story): Unit

  /**
   * Prints to the console entity of Comment
   * @param comment which should be printed
   */
  def renderComment(comment: Comment): Unit

  /**
   * Prints to the console entity of User
   * @param user which should be printed
   */
  def renderUser(user: User): Unit

}
