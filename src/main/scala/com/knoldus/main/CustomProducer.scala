package com.knoldus.main

import java.io.IOException

import com.knoldus.twitter.TwitterNewsFeed
import twitter4j.TwitterException

object CustomProducer {

  @throws[TwitterException]
  @throws[IOException]
  def main(args: Array[String]) {
    new TwitterNewsFeed().sendTweetsToKafka
  }
}
