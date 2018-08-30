package org.spark.data

import org.apache.spark.sql.SparkSession

/**
  * User: shijingui
  * Date: 2017/1/24
  */
object OrderCount {

  def main(args: Array[String]) {
    val sparkSession = SparkSession.builder().appName("order count")
      .master("local")
      .config("spark.sql.warehouse.dir", "file:///")
      .getOrCreate()

    val dataSet = sparkSession.read.textFile("src/main/resources/order.txt")
    val arr = dataSet.collect()
    //    dataSet.map
    //    val tmp = dataSet.flatMap(s => s.split(" ")).collect

    for (s <- arr)
      println(s)

  }

}
