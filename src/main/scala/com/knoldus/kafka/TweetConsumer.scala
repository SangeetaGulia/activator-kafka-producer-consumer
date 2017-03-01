package com.knoldus.kafka

import java.util
import java.util.Properties

import com.knoldus.twitter.Tweet
import com.knoldus.utils.ConfigReader
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}

class TweetConsumer {

  def consumeTweets(groupId: String): Unit ={
    val kafkaServer = ConfigReader.getKafkaServer
    val kafkaTopic = ConfigReader.getKafkaTopic

    val properties = new Properties()
    properties.put("bootstrap.servers", kafkaServer)
    properties.put("group.id", groupId)
    properties.put("enable.auto.commit", "true")
    properties.put("auto.commit.interval.ms", "1000")
    properties.put("auto.offset.reset", "earliest")
    properties.put("session.timeout.ms", "30000")
    properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    properties.put("value.deserializer", "com.knoldus.kafka.utils.TweetDeserializer")

    val kafkaConsumer = new KafkaConsumer[String, Tweet](properties)
    kafkaConsumer.subscribe(util.Collections.singletonList(kafkaTopic))

    while(true){
      val records: ConsumerRecords[String, Tweet] = kafkaConsumer.poll(100)
      records.forEach(record => println(record.value()))
    }
  }
}
