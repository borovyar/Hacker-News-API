package cz.cvut.fit.bioop.hackernewsclient.controller.api

/**
 * Additional api which operates on cache
 */
trait CacheApi {
  /**
   * Clears exited cache data
   */
  def clearCache(): Unit
}
