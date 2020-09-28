package data.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author shijingui on 2018/9/9
 */
object one {


  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("one streaming").setMaster("local[2]")
    val ssc = new StreamingContext(conf, Seconds(5));
    ssc.start();
  }
}
