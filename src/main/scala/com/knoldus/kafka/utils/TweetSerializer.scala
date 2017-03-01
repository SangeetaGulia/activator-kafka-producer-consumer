package com.knoldus.kafka.utils

import java.io.{ByteArrayOutputStream, ObjectOutputStream}
import java.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.knoldus.twitter.Tweet
import org.apache.kafka.common.serialization.Serializer

import scala.util.{Failure, Success, Try}

class TweetSerializer extends Serializer[Tweet]{

  def serialize(s: String, tweet: Tweet): Array[Byte] = {
    val stream: ByteArrayOutputStream = new ByteArrayOutputStream()
    val oos = new ObjectOutputStream(stream)
    oos.writeObject(tweet)
    oos.close
    stream.toByteArray

    /*val objectMapper = new ObjectMapper
    Try {
      objectMapper.writeValueAsString(tweet).getBytes
    }
    match {
      case Success(tweet: Array[Byte]) => tweet
      case Failure(exception) => throw new Exception("Unable to Serialize !!" + exception.getMessage)
    }*/
  }

  def close() {
  }

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {
  }
}

