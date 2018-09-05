
import java.util.Random

import org.apache.spark.sql.SparkSession

/**
  * Usage: GroupByTest [numMappers] [numKVPairs] [KeySize] [numReducers]
  */
object GroupByTest {
  def main(args: Array[String]) {
    val spark = SparkSession
      .builder
      .appName("GroupBy Test").master("local[2]")
      .getOrCreate()

    val numMappers = 100
    val numKVPairs = if (args.length > 1) args(1).toInt else 1000
    val valSize = if (args.length > 2) args(2).toInt else 1000
    val numReducers = if (args.length > 3) args(3).toInt else numMappers

    val pairs1 = spark.sparkContext.parallelize(0 until numMappers, numMappers).flatMap { p =>
      val ranGen = new Random
      val arr1 = new Array[(Int, Array[Byte])](numKVPairs)
      for (i <- 0 until numKVPairs) {
        val byteArr = new Array[Byte](valSize)
        ranGen.nextBytes(byteArr)
        arr1(i) = (ranGen.nextInt(Int.MaxValue), byteArr)
      }
      arr1
    }.cache()
    // Enforce that everything has been calculated and in cache
    println(pairs1.count())

    println(pairs1.groupByKey(numReducers).count())
    print(pairs1.toDebugString)
    spark.stop()
  }
}

