package com.imooc.flink.project

import java.sql.{Connection, DriverManager, PreparedStatement}

import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.source.{RichParallelSourceFunction, SourceFunction}

import scala.collection.mutable

/**
  * Author: Michael PK
  */
class PKMySQLSource extends RichParallelSourceFunction[mutable.HashMap[String,String]]{
  override def cancel(): Unit = ???

  override def run(sourceContext: SourceFunction.SourceContext[mutable.HashMap[String, String]]): Unit = ???
}
