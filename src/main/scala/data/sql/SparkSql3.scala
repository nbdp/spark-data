package org.spark.data.sql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

/**
  * @author shijingui on 2018/8/28
  */
object SparkSql3 {


  def main(args: Array[String]): Unit = {


    //1.使用反射方法创建data frame

    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("RDD2DataFrameReflection")
    val sc = new SparkContext(conf);

    //创建方式
    val sqlContext = new SQLContext(sc);

    import sqlContext.implicits._

    //    val sQLContext =new org.apache.spark.sql.hive.HiveContext();



    //定义一个case class ,参数名即为表的列名
    //
    case class Person(name: String)
    //从文本创建RDD
    val rdd = sc.textFile("/usr/local/test/people.txt").map(_.split(","))

    //还是rdd包含了case class
    val rddContainingCaseClass = rdd.map(p => Person(p(0)))

    //包含了case class 的rdd被隐式转换成data frame,注意toDF是data frame是方法，不是rdd 的
//    val person = rddContainingCaseClass.toDF();
//    person.registerTempTable("person");
//    person.show();





    //2.使用程序动态从RDD创建DataFrame
    val schemaString = "name age";
    //导入以来的数据类型
    import org.apache.spark.sql.Row
    import org.apache.spark.sql.types.{StructType,StructField,StringType}



    val schema = StructType(schemaString.split(" ").map(fieldName => StructField(fieldName,StringType,true)))




  }
}
