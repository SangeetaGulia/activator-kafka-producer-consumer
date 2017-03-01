package com.knoldus.kafka

import java.util.Properties

import com.knoldus.twitter.Tweet
import com.knoldus.utils.ConfigReader
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

class TweetProducer {

  def send(tweet: Tweet) {
    val kafkaServers = ConfigReader.getKafkaServer
    val kafkaTopic = ConfigReader.getKafkaTopic
    val properties = new Properties
    properties.put("bootstrap.servers", kafkaServers)
    properties.put("acks", "all")
    properties.put("retries", "0")
    properties.put("batch.size", "16384")
    properties.put("linger.ms", "1")
    properties.put("buffer.memory", "33554432")
    properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    properties.put("value.serializer", "com.knoldus.kafka.utils.TweetSerializer")

    try {
      val producer = new KafkaProducer[String, Tweet](properties)
      producer.send(new ProducerRecord[String, Tweet](kafkaTopic, tweet))
    } catch {
        case ex: Exception => {
          ex.printStackTrace()
        }
      }

  }
}
