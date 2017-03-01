package com.knoldus.twitter

import java.io.IOException

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

  @throws[TwitterException]
  @throws[IOException]
  def sendTweetsToKafka() {
    val listener = new StatusListener() {
      def onStatus(status: Status) {

        val tweet = new Tweet(status.getUser.getName, status.getText, status.getFavoriteCount, status.getUser.isVerified, status.isRetweet)
        new TweetProducer().send(tweet)
        System.out.println("Sent: [ " + tweet + " ] ")
      }

      def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {
      }
      def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {
      }
      def onScrubGeo(l: Long, l1: Long) {
      }
      def onStallWarning(stallWarning: StallWarning) {
      }
      def onException(ex: Exception) {
        ex.printStackTrace()
      }

    }
    val twitterStream = getTwitterConfigurations
    twitterStream.addListener(listener)
    println("Started Listening to tweets")
    twitterStream.sample("en")
  }

}