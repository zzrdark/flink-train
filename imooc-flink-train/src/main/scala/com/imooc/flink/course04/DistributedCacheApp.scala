package com.imooc.flink.course04

import org.apache.commons.io.FileUtils
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration

/**
  * Author: Michael PK
  * step1: 注册一个本地/HDFS文件
  * step2：在open方法中获取到分布式缓存的内容即可
  */
object DistributedCacheApp {

  def main(args: Array[String]): Unit = {

    val env = ExecutionEnvironment.getExecutionEnvironment

    val filePath = "file:///Users/rocky/IdeaProjects/imooc-workspace/data/04/hello.txt"

    // step1: 注册一个本地/HDFS文件
    env.registerCachedFile(filePath, "pk-scala-dc")

    import org.apache.flink.api.scala._
    val data = env.fromElements("hadoop","spark","flink","pyspark","storm")


    data.map(new RichMapFunction[String,String] {

      // step2：在open方法中获取到分布式缓存的内容即可
      override def open(parameters: Configuration): Unit = {
        val dcFile = getRuntimeContext.getDistributedCache().getFile("pk-scala-dc")

        val lines = FileUtils.readLines(dcFile)  // java


        /**
          * 此时会出现一个异常：java集合和scala集合不兼容的问题
          */
        import scala.collection.JavaConverters._
        for(ele <- lines.asScala){ // scala
          println(ele)
        }
      }

      override def map(value: String): String = {
        value
      }
    }).print()
  }

}
