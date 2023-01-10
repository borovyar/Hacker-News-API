package cz.cvut.fit.bioop.hackernewsclient.repository

import cz.cvut.fit.bioop.hackernewsclient.model.PagingOptions
import cz.cvut.fit.bioop.hackernewsclient.model.api.{Comment, Story, User}

trait Repository {
  /***
   * Loads top stories from Hacker News API
   *
   * @return sequence of the top stories' ids
   */
  def loadTopStories(pageOptions: PagingOptions): Seq[Story]

  /***
   * Loads comment using an id from Hacker News API
   *
   * @param id of the needed comment
   * @return Optional of the needed comment
   */
  def loadCommentsById(id: Int,
                       pagingOptions: PagingOptions): Seq[Comment]

  /***
   * Loads user using an id from Hacker News API
   *
   * @param id of the needed user
   * @return Optional of the needed user
   */
  def loadUserById(id: String): Option[User]

  def loadUserStories(user: User,
                      pagingOptions: PagingOptions): Seq[Story]

  def clearCache(): Unit
}
