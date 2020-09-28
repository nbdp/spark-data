package data.example

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
 * @User krisjin
 * @Date 2020/9/28
 */
object RDDParallelize {

  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder().master("local[1]")
      .appName("SparkByExamples.com")
      .getOrCreate()

    //使用spark context 创建空 RDD
    //spark.sparkContext.parallelize(Seq.empty)
    val rdd: RDD[Int] = spark.sparkContext.parallelize(List(1, 2, 3, 4, 5))
    val rddCollect: Array[Int] = rdd.collect()
    println("Number of Partitions: " + rdd.getNumPartitions)
    println("Action: First element: " + rdd.first())
    println("Action: RDD converted to Array[Int] : ")
    rddCollect.foreach(println)


    List(11, 22)
  }

}
