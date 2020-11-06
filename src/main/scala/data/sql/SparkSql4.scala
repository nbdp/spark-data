package org.spark.data.sql

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author shijingui on 2018/8/29
 */
object SparkSql4 {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("SparkSql4")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val df = sqlContext.read.json("src/main/resources/people.json")
    //    df.show()

    //
    df.select(df("name"), df("age") + 1).show()

    //
    df.filter(df("age") > 21).show()

    //
    df.groupBy("age").count().show()
    //将dafa frame 注册为一张表
    df.registerTempTable("people")
    val result = sqlContext.sql("select age from people")
    result.show()
  }
}
