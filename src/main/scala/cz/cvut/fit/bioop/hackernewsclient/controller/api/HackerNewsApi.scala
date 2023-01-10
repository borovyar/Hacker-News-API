package cz.cvut.fit.bioop.hackernewsclient.controller.api

import cz.cvut.fit.bioop.hackernewsclient.model.api.{Comment, Story, Update, User}

trait HackerNewsApi {

  /***
   * Loads top stories from Hacker News API
   *
   * @return sequence of the top stories' ids
   */
  def loadTopStories(): Seq[Int]

  /***
   * Loads story using an id from Hacker News API
   *
   * @param id of the needed story
   * @return Optional of the needed story
   */
  def loadStoryById(id: Int): Option[Story]

  /***
   * Loads comment using an id from Hacker News API
   *
   * @param id of the needed comment
   * @return Optional of the needed comment
   */
  def loadCommentById(id: Int): Option[Comment]

  /***
   * Loads user using an id from Hacker News API
   *
   * @param id of the needed user
   * @return Optional of the needed user
   */
  def loadUserById(id: String): Option[User]


  def loadUpdates(): Option[Update]

}
