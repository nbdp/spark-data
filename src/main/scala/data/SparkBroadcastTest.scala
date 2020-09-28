package org.spark.data

import org.apache.spark.{SparkConf, SparkContext}

/**
 * 广播变量
 * User: shijingui
 * Date: 2017/1/24
 */
object SparkBroadcastTest {

  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("Spark broadcast var test")
    val sc = new SparkContext(sparkConf)
    val broadcastAList = sc.broadcast(List("a", "b", "c", "d"))
    val ret = sc.parallelize(List("1", "2", "3")).map(x => broadcastAList.value ++ x).collect

    for (arr <- ret) {
      println(arr)
    }
  }
}
