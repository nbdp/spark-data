package org.spark.data.sql

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author shijingui on 2018/8/29
  */
object SparkSql1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("Rdd2DataFrame")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    // 第一步，构造出元素为Row的普通RDD
    val studentRDD = sc.textFile("/usr/local/test/people.txt", 1)
      .map { line => Row(line.split(",")(0).toInt, line.split(",")(1), line.split(",")(2).toInt) }

    // 第二步，编程方式动态构造元数据
    val structType = StructType(Array(
      StructField("id", IntegerType, true),
      StructField("name", StringType, true),
      StructField("age", IntegerType, true)))

    // 第三步，进行RDD到DataFrame的转换
    val studentDF = sqlContext.createDataFrame(studentRDD, structType)

    // 继续正常使用
    studentDF.registerTempTable("students")

    val teenagerDF = sqlContext.sql("select * from students where age>1")
    teenagerDF.show()
    val teenagerRDD = teenagerDF.rdd.collect().foreach { row => println(row) }
  }
}