package data.streaming

//import com.tencent.angel.spark.context.PSContext

import org.apache.spark.sql.SparkSession

/**
 *
 * User:shijingui
 * Date:2019-09-03
 */
object Test {


  def main(args: Array[String]): Unit = {
    // 初始化Spark
    val builder = SparkSession.builder()
      .master("local")
      .appName("appName")
      .config("spark.ps.num", "2")
      .config("B", "y")
    val spark = builder.getOrCreate()

    // 初始化Angel
    //    val context = PSContext.getOrCreate(spark.sparkContext)


  }
}
