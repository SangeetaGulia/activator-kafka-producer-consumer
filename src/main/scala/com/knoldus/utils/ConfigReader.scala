package com.knoldus.utils

import com.typesafe.config.ConfigFactory

object ConfigReader {

   val conf = ConfigFactory.load

  def getTwitterConsumerKey: String = conf.getString("twitter.consumerKey")

  def getTwitterConsumerSecretKey: String = conf.getString("twitter.consumerSecret")

  def getTwitterAccessToken: String = conf.getString("twitter.accessToken")

  def getTwitterAccessSecretToken: String = conf.getString("twitter.accessTokenSecret")

  def getKafkaServers: String = conf.getString("kafka.servers")

  def getKafkaTopic: String = conf.getString("kafka.topic")
}
