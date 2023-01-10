package cz.cvut.fit.bioop.hackernewsclient.repository
import cz.cvut.fit.bioop.hackernewsclient.controller.api.{CacheApi, HackerNewsApi}
import cz.cvut.fit.bioop.hackernewsclient.model.PagingOptions
import cz.cvut.fit.bioop.hackernewsclient.model.api.{Comment, Story, User}

class RepositoryImpl(api: HackerNewsApi with CacheApi) extends Repository {

  private def loadStoryById(id: Int) : Option[Story] = api.loadStoryById(id)

  /**   *
   * Loads top stories from Hacker News API
   *
   * @return sequence of the top stories' ids
   */
  override def loadTopStories(pageOptions: PagingOptions): Seq[Story] = {
    val topStoryIds = api.loadTopStories()
    val pagedIds = getPagedIds(topStoryIds, pageOptions)
    pagedIds.flatMap(loadStoryById)
  }

  /** *
   * Loads comment using an id from Hacker News API
   *
   * @param id of the needed comment
   * @return Optional of the needed comment
   */
  override def loadCommentsById(id: Int,
                               pagingOptions: PagingOptions): Seq[Comment] = {
    val story = loadStoryById(id)

    if(story.isEmpty || story.get.comments.isEmpty)
      return Seq()

    val resultIds = getPagedIds(story.get.comments.get, pagingOptions)
    resultIds.flatMap(id => api.loadCommentById(id))
  }

  /** *
   * Loads user using an id from Hacker News API
   *
   * @param id of the needed user
   * @return Optional of the needed user
   */
  override def loadUserById(id: String): Option[User] = {
    api.loadUserById(id)
  }

  override def loadUserStories(user: User,
                               pagingOptions: PagingOptions): Seq[Story] = {

    if(user.submitted.isEmpty)
      return Seq()

    val resultIds = getPagedIds(user.submitted.get, pagingOptions)
    resultIds.flatMap(loadStoryById)
  }

  private def getPagedIds(ids: Seq[Int],
                          pageOptions: PagingOptions): Seq[Int] = {
    pageOptions.page.flatMap(
      page => return if(page - 1 <= ids.size) Seq(ids.apply(page - 1)) else Seq(ids.last)
    )
    ids.take(pageOptions.size)
  }

  override def clearCache(): Unit = api.clearCache()
}
