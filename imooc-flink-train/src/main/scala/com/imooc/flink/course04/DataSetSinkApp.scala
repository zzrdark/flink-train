package com.imooc.flink.course04

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.core.fs.FileSystem.WriteMode

/**
  * Author: Michael PK
  */
object DataSetSinkApp {
  def main(args: Array[String]): Unit = {

    val env = ExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.api.scala._
    val data = 1.to(10)
    val text = env.fromCollection(data)

    val filePath = "/Users/rocky/IdeaProjects/imooc-workspace/data/04/sink-out/"

    text.writeAsText(filePath, WriteMode.OVERWRITE).setParallelism(5)

    env.execute("DataSetSinkApp")
  }

}
