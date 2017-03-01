package com.knoldus.main

import com.knoldus.kafka.TweetConsumer

object CustomConsumer {
  def main(args: Array[String]) {
    new TweetConsumer().consumeTweets("demo-consumer-group")
  }

}
