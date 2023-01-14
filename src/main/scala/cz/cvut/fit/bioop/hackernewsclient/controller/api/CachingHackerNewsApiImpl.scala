package cz.cvut.fit.bioop.hackernewsclient.controller.api
import cz.cvut.fit.bioop.hackernewsclient.converter.Serializer._
import cz.cvut.fit.bioop.hackernewsclient.model.api.{Comment, Story, Update, User}
import cz.cvut.fit.bioop.hackernewsclient.repository.cache.Cache

class CachingHackerNewsApiImpl(api: HackerNewsApi,
                               cache: Cache) extends HackerNewsApi with CacheApi {

  override def loadTopStories(): Seq[Int] = {
    checkCacheUpdate()
    api.loadTopStories()
  }

  override def loadStoryById(id: Int): Option[Story] = {
    val cachedStory = cache.getStory(id)

    if(cachedStory.isDefined){
      return cachedStory
    }


    api.loadStoryById(id).flatMap({ story =>
      cache.saveEntity[Story, Int](story, story.id)
      Some(story)
    })

  }

  override def loadCommentById(id: Int): Option[Comment] = {
    checkCacheUpdate()

    val cachedComment = cache.getComment(id)

    if(cachedComment.isDefined) {
      return cachedComment
    }


    api.loadCommentById(id).flatMap({ comment =>
      cache.saveEntity[Comment, Int](comment, comment.id)
      Some(comment)
    })
  }

  override def loadUserById(id: String): Option[User] = {
    checkCacheUpdate()

    val cachedUser = cache.getUser(id)

    if(cachedUser.isDefined) {
      return cachedUser
    }

    api.loadUserById(id).flatMap({ user =>
      cache.saveEntity[User, String](user, user.id)
      Some(user)
    })
  }

  override def loadUpdates(): Option[Update] = api.loadUpdates()

  override def clearCache(): Unit = {
    cache.clearCache()
  }

  private def checkCacheUpdate(): Unit = {
    if(!cache.isUpdated)
      cache.performUpdate(loadUpdates().orNull)
  }
}
