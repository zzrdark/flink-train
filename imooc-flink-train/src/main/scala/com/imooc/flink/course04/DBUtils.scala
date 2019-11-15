package com.imooc.flink.course04

import scala.util.Random

/**
  * Author: Michael PK
  */
object DBUtils {

  def getConection() = {
    new Random().nextInt(10) + ""
  }

  def returnConnection(connection:String): Unit ={

  }

}
