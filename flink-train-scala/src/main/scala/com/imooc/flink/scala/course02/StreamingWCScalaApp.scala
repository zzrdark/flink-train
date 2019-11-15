package com.imooc.flink.scala.course02

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * 使用Scala开发Flink的实时处理应用程序
  */
object StreamingWCScalaApp {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment


    StreamExecutionEnvironment.createLocalEnvironment()

    // 引入隐式转换
    import org.apache.flink.api.scala._

    val text = env.socketTextStream("localhost",9999)


    text.flatMap(_.split(","))
      .map((_,1))
      .keyBy(0)
      .timeWindow(Time.seconds(5))
      .sum(1)
      .print()
      .setParallelism(1)

    //env.execute("StreamingWCScalaApp")
  }

}
