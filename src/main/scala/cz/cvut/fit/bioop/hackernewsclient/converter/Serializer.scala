package cz.cvut.fit.bioop.hackernewsclient.converter

import cz.cvut.fit.bioop.hackernewsclient.converter.Converter._
import cz.cvut.fit.bioop.hackernewsclient.model.api.{Comment, Story, User}
import cz.cvut.fit.bioop.hackernewsclient.model.cache.CacheEntity


object Serializer {
  implicit val commentToJson: Serializable[Comment] = (comment: Comment) => {write(comment)}

  implicit val userToJson: Serializable[User] = (user: User) => write(user)

  implicit val storyToJson: Serializable[Story] = (story: Story) => write(story)

  implicit val cacheEntityToJSON: Serializable[CacheEntity] = (cacheEntity: CacheEntity) => write(cacheEntity)

}
