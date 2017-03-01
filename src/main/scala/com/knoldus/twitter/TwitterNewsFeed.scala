package com.knoldus.twitter

import com.knoldus.kafka.TweetProducer
import com.knoldus.utils.ConfigReader
import twitter4j._
import twitter4j.conf.{Configuration, ConfigurationBuilder}

case class Tweet(userName: String, text: String, favCount: Int, isVerified: Boolean, isRetweet: Boolean)

class TwitterNewsFeed {

  private def getTwitterConfigurations: TwitterStream = {
    val configBuilder: Configuration = new ConfigurationBuilder()
      .setOAuthConsumerKey(ConfigReader.getTwitterConsumerKey)
      .setOAuthConsumerSecret(ConfigReader.getTwitterConsumerSecretKey)
      .setOAuthAccessToken(ConfigReader.getTwitterAccessToken)
      .setOAuthAccessTokenSecret(ConfigReader.getTwitterAccessSecretToken)
      .build()

    new TwitterStreamFactory(configBuilder).getInstance()
  }

  def sendTweetsToKafka = {

    println("sending tweets to kafka")

    val listener = new StatusListener {

      override def onStatus(status: Status) = {
        println("Status changed")
        val tweet = Tweet(status.getUser.getName, status.getText, status.getFavoriteCount, status.getUser.isVerified, status.isRetweet)
        new TweetProducer().send(tweet)
        println("Sent: " + tweet)
      }

      override def onStallWarning(warning: StallWarning) = {
      }

      override def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) = {
      }

      override def onScrubGeo(userId: Long, upToStatusId: Long) = {
      }

      override def onTrackLimitationNotice(numberOfLimitedStatuses: Int) = {
      }

      override def onException(ex: Exception) = {
      }

    }

    val twitterStream = getTwitterConfigurations
    twitterStream.addListener(listener)
    twitterStream.sample("en")

  }

}