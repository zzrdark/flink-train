package com.imooc.flink.course05

import org.apache.flink.streaming.api.functions.source.{ParallelSourceFunction, SourceFunction}

/**
  * Author: Michael PK
  */
class CustomParallelSourceFunction extends ParallelSourceFunction[Long]{

  var isRunning = true
  var count = 1L

  override def cancel(): Unit = {
    isRunning = false
  }

  override def run(ctx: SourceFunction.SourceContext[Long]): Unit = {
    while(isRunning) {
      ctx.collect(count)
      count += 1
      Thread.sleep(1000)
    }
  }
}
