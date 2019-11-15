package com.imooc.flink.course04

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration

/**
  * Author: Michael PK
  */
object DataSetDataSourceApp {


  def main(args: Array[String]): Unit = {

    val env = ExecutionEnvironment.getExecutionEnvironment

    // fromCollection(env)

    //textFile(env)
    // csvFile(env)
//    readRecursiveFiles(env)
    readCompressionFiles(env)

  }

  def readCompressionFiles(env:ExecutionEnvironment): Unit = {
    val filePath = "/Users/rocky/IdeaProjects/imooc-workspace/data/04/compression"
    env.readTextFile(filePath).print()
  }

  def readRecursiveFiles(env:ExecutionEnvironment): Unit = {
    val filePath = "/Users/rocky/IdeaProjects/imooc-workspace/data/04/nested"
    env.readTextFile(filePath).print()
    println("~~~~~~~华丽的分割线~~~~~~~~")

    val parameters = new Configuration
    parameters.setBoolean("recursive.file.enumeration", true)
    env.readTextFile(filePath).withParameters(parameters).print()


  }

  def csvFile(env:ExecutionEnvironment): Unit = {
    import org.apache.flink.api.scala._
    val filePath = "file:///Users/rocky/IdeaProjects/imooc-workspace/data/04/people.csv"

    //env.readCsvFile[(String, Int, String)](filePath,ignoreFirstLine=true).print()

    //env.readCsvFile[(String,Int)](filePath, ignoreFirstLine = true, includedFields = Array(0,1)).print()

//    case class MyCaseClass(name:String, age:Int)
//    env.readCsvFile[MyCaseClass](filePath, ignoreFirstLine = true, includedFields = Array(0,1)).print()

    env.readCsvFile[Person](filePath, ignoreFirstLine = true, pojoFields = Array("name","age","work1")).print()
  }

  def textFile(env:ExecutionEnvironment): Unit = {
//    val filePath = "file:///Users/rocky/IdeaProjects/imooc-workspace/data/04/hello.txt"
//    env.readTextFile(filePath).print()

      val filePath = "file:///Users/rocky/IdeaProjects/imooc-workspace/data/04/inputs"
      env.readTextFile(filePath).print()
  }

  def fromCollection(env: ExecutionEnvironment): Unit = {

    import org.apache.flink.api.scala._
    val data = 1 to 10
    env.fromCollection(data).print()
  }

}
