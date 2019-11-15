package com.imooc.flink.scala.course03

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
      .map(x => WC(x.toLowerCase, 1)) // WC(word,count)
      .keyBy(_.word)
      .timeWindow(Time.seconds(5))
      .sum("count")
      .print()
      .setParallelism(1)

    env.execute("StreamingWCScalaApp")
  }

  case class WC(word: String, count: Int)

}
