package data.example

import org.apache.spark.sql.SparkSession

/**
 * @User krisjin
 * @Date 2020/9/28
 */
object RDDTextRead {

  def main(args: Array[String]): Unit = {

    val spark: SparkSession = SparkSession.builder().master("local[1]")
      .appName("SparkByExamples.com")
      .getOrCreate()

    //    val rdd = spark.sparkContext.textFile("/usr/local/gitrep/spark-data/src/main/resources/test/*")
    //    val rdd = spark.sparkContext.wholeTextFiles("/usr/local/gitrep/spark-data/src/main/resources/test/*")
    //    val rdd = spark.sparkContext.textFile("/usr/local/gitrep/spark-data/src/main/resources/test/readtext1.txt,/usr/local/gitrep/spark-data/src/main/resources/test/readtext2.txt")
    //    val rdd = spark.sparkContext.textFile("/usr/local/gitrep/spark-data/src/main/resources/test/*.txt")
    val rdd = spark.sparkContext.textFile("/usr/local/gitrep/spark-data/src/main/resources/sam_tianchi_mum_baby_trade_history.csv")

    //one cluster
    //    rdd.collect().foreach(l => {
    //      println(l)
    //    })


    //    rdd.foreach(l => {
    //      println(l)
    //    })

    //    rdd.foreach(l => {
    //      println(l._1 + " => " + l._2)
    //    })

    val rdd2 = rdd.map(l => {
      l.split(",")
    })
    rdd2.foreach(c => {
      println("Col1:" + c(0) + ",Col2:" + c(1))
    })

    //    val rdd3 = rdd2.mapPartitionsWithIndex((idx, iter) => if (idx == 0) iter.drop(1) else iter


  }

}
