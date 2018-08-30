package org.spark.data

import org.apache.spark.{SparkConf, SparkContext}

/**
 * User: shijingui
 * Date: 2017/1/24
 */
object SparkContextTest {

  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("Spark Context Test");

    //创建spark入口
    val sc = new SparkContext(sparkConf);
    val collection = Array(1, 2, 3, 4, 5, 6, 7, 8);
    //创建并行集合RDD
    val distData = sc.parallelize(collection)
    val d = distData.reduce((a, b) => a + b)
    val total: Int = distData.sum.toInt

    println(d)
    println(total)
  }


}
