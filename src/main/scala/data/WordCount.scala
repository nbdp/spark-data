package org.spark.data

import org.apache.spark.{SparkConf, SparkContext}

/**
  * User: shijingui
  * Date: 2017/1/24
  */
object WordCount {

  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("WordCount Test")
    val sc = new SparkContext(sparkConf)

    val wordCount = sc.textFile("src/main/resources/word.txt").flatMap(_.split(" ")).map(word => (word, 1)).reduceByKey(_ + _).collect

    for ((k, v) <- wordCount) {
      println(k + ":" + v)
    }

  }
}
