package org.spark.data.sql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

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
    val df =sqlContext.read.json("src/main/resources/people.json")
//    df.show()

    //
    df.select(df("name"),df("age")+1).show()

    //
    df.filter(df("age")>21).show()

    //
    df.groupBy("age").count().show()
  }
}
