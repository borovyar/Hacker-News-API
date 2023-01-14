package cz.cvut.fit.bioop.hackernewsclient.repository

import cz.cvut.fit.bioop.hackernewsclient.model.PagingOptions
import cz.cvut.fit.bioop.hackernewsclient.model.api.{Comment, Story, User}

/**
 * Repository of the app which contains data and page it
 */
trait Repository {

  /**
   * Loads top stories of Hacker News Api
   *
   * @param pageOptions for the given stories
   * @return sequence of the paged stories
   */
  def loadTopStories(pageOptions: PagingOptions): Seq[Story]

  /**
   * Loads comment using an id
   *
   * @param id of the story with needed comments
   * @param pagingOptions for the given comments
   * @return sequence of the paged comments
   */
  def loadCommentsById(id: Int,
                       pagingOptions: PagingOptions): Seq[Comment]

  /***
   * Loads user using an id
   *
   * @param id of the needed user
   * @return optional of the needed user
   */
  def loadUserById(id: String): Option[User]

  /**
   * Loads user stories using user entity
   * @param user entity whose stories will be displayed
   * @param pagingOptions for given stories
   * @return sequence of user stories
   */
  def loadUserStories(user: User,
                      pagingOptions: PagingOptions): Seq[Story]

  def clearCache(): Unit
}
