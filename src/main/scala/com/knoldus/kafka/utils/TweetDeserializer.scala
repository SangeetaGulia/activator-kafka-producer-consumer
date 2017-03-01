package com.knoldus.kafka.utils

import java.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.knoldus.twitter.Tweet
import org.apache.kafka.common.serialization.Deserializer

import scala.util.{Failure, Success, Try}

class TweetDeserializer extends Deserializer[Tweet]{

  def deserialize(s: String, bytes: Array[Byte]): Tweet = {
    val mapper = new ObjectMapper
    var tweet = null

    Try{
      mapper.readValue(bytes, classOf[Tweet])
    } match {
      case Success(tweet) => tweet
      case Failure(exception) => throw new Exception("Unable to deserialize !!" + exception.getMessage)
    }
  }

  def close() {
  }

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit ={
  }
}
