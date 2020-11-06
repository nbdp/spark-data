package org.spark.data

import org.apache.spark.sql.SparkSession

/**
 * User: shijingui
 * Date: 2017/1/24
 */
object WordCount2 {
  def main(args: Array[String]) {
    val sparkSession = SparkSession.builder().
      appName("word count").
      config("spark.sql.warehouse.dir", "file:///").
      master("local").getOrCreate()

    val data = sparkSession.read.textFile("src/main/resources/word.txt").rdd
    val wordCount = data.flatMap(word => word.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)
//    for ((k, v) <- wordCount) {
//      println(k + ":" + v)
//    }
  }
}
