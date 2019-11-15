package com.imooc.flink.course08

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer
import org.apache.flink.streaming.util.serialization.KeyedSerializationSchemaWrapper

/**
  * Author: Michael PK
  */
object KafkaConnectorProducerApp {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    // 从socket接收数据，通过Flink，将数据Sink到Kafka
    val data = env.socketTextStream("localhost", 9999)

    val topic = "pktest"
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "192.168.199.233:9092")

//    val kafkaSink = new FlinkKafkaProducer[String](topic,
//      new KeyedSerializationSchemaWrapper[String](new SimpleStringSchema()),
//      properties)

    val kafkaSink = new FlinkKafkaProducer[String](topic,
      new KeyedSerializationSchemaWrapper[String](new SimpleStringSchema()),
      properties,
      FlinkKafkaProducer.Semantic.EXACTLY_ONCE)

    data.addSink(kafkaSink)

    env.execute("KafkaConnectorProducerApp")
  }
}
