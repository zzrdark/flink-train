package com.imooc.flink.course04

import org.apache.flink.api.common.accumulators.LongCounter
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration
import org.apache.flink.core.fs.FileSystem.WriteMode

/**
  * Author: Michael PK
  *
  * 基于Flink编程的计数器开发三步曲
  * step1：定义计数器
  * step2: 注册计数器
  * step3: 获取计数器
  */
object CounterApp {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.api.scala._
    val data = env.fromElements("hadoop","spark","flink","pyspark","storm")

//    data.map(new RichMapFunction[String, Long]() {
//      var counter = 0l
//      override def map(in: String): Long = {
//        counter = counter + 1
//        println("counter : " + counter)
//        counter
//      }
//    }).setParallelism(4).print()

    //data.print()

    val info = data.map(new RichMapFunction[String,String]() {

      // step1：定义计数器
      val counter = new LongCounter()

      override def open(parameters: Configuration): Unit = {
        // step2: 注册计数器
        getRuntimeContext.addAccumulator("ele-counts-scala", counter)
      }

      override def map(in: String): String = {
        counter.add(1)
        in
      }
    })

    val filePath = "file:///Users/rocky/IdeaProjects/imooc-workspace/data/04/sink-scala-counter-out/"
    info.writeAsText(filePath, WriteMode.OVERWRITE).setParallelism(3)
    val jobResult = env.execute("CounterApp")

    // step3: 获取计数器
    val num = jobResult.getAccumulatorResult[Long]("ele-counts-scala")

    println("num: " + num)
  }

}
