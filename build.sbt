name := """activator-kafka-producer-consumer"""

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka_2.11" % "0.10.1.1",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.6",
  "org.twitter4j" % "twitter4j-stream" % "4.0.6",
  "com.typesafe" % "config" % "1.3.1"
)


