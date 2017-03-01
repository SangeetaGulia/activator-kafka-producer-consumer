package com.knoldus.utils

import com.typesafe.config.ConfigFactory

object ConfigReader {

  val conf = ConfigFactory.load()

  def getTwitterConsumerKey: String = conf.getString("twitter.consumerSecret")

  def getTwitterConsumerSecretKey: String = conf.getString("twitter.consumerSecret")

  def getTwitterAccessToken: String = conf.getString("twitter.accessToken")

  def getTwitterAccessSecretToken: String = conf.getString("twitter.accessTokenSecret")

  def getKafkaServer = conf.getString("kafka.server")

  def getKafkaTopic = conf.getString("kafka.topic")

}
