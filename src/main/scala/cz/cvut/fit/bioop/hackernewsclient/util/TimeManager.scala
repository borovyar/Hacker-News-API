package cz.cvut.fit.bioop.hackernewsclient.util

import cz.cvut.fit.bioop.hackernewsclient.Properties

class TimeManager(ttl: Long) {

  def getCurrentMills: Long = System.currentTimeMillis()

  def getCurrentMillsWithTtl: Long = getCurrentMills + ttl

  def getUpdateMills: Long = getCurrentMills - Properties.DEFAULT_UPDATE_TIME
}
