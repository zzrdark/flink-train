package com.imooc.flink.course08

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

/**
  * Author: Michael PK
  */
object KafkaConnectorConsumerApp {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment


    // checkpoint常用设置参数
    env.enableCheckpointing(4000)
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)
    env.getCheckpointConfig.setCheckpointTimeout(10000)
    env.getCheckpointConfig.setMaxConcurrentCheckpoints(1)



    import org.apache.flink.api.scala._

    val topic = "pktest"
    val properties = new Properties()

    // hadoop000   必须要求你的idea这台机器的hostname和ip的映射关系必须要配置
    properties.setProperty("bootstrap.servers", "192.168.199.233:9092")
    properties.setProperty("group.id", "test")

    val data = env.addSource(new FlinkKafkaConsumer[String](topic,new SimpleStringSchema(), properties))

    data.print()

    env.execute("KafkaConnectorConsumerApp")
  }
}
