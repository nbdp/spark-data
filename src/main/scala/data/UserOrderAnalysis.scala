package org.spark.data

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 用户订单分析
  * User: shijingui
  * Date: 2017/2/3
  */
object UserOrderAnalysis {


  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("user order analysis")
    val sc = new SparkContext(sparkConf)

    val data = sc.textFile("src/main/resources/UserPurchaseHistory.csv").map(line => line.split(","))
      .map(purchaseRecord => (purchaseRecord(0), purchaseRecord(1), purchaseRecord(2)))

    //求购买次数
    val numPurchases = data.count()
    //求有多少个不同客户购买过商品
    val uniqueUsers = data.map { case (user, product, price) => user }.distinct().count()
    //求和得出总收入
    val totalRevenue = data.map { case (user, product, price) => price.toDouble }.sum()
    //求最畅销的产品
    val topProduct = data.map { case (user, product, price) => (product, 1) }.reduceByKey(_ + _).collect().sortBy(-_._2)
    val top = topProduct(0)

    println("共卖出多少商品:" + numPurchases)
    println("共有多少客户：" + uniqueUsers)
    println("总收入:" + totalRevenue)
    println("最畅销的产品：" + top)

    main2
  }


  def main2: Unit = {
    val sparkSession = SparkSession.builder().
      appName("user order analysis").
      config("spark.sql.warehouse.dir", "file:///").
      master("local[2]").getOrCreate()

    val rdd = sparkSession.read.textFile("src/main/resources/UserPurchaseHistory.csv").rdd
    val data = rdd.map(line => line.split(",")).map(purchaseRecord => (purchaseRecord(0), purchaseRecord(1), purchaseRecord(2)))

    //求购买次数
    val numPurchases = data.count()
    //求有多少个不同客户购买过商品
    val uniqueUsers = data.map { case (user, product, price) => user }.distinct().count()
    //求和得出总收入
    val totalRevenue = data.map { case (user, product, price) => price.toDouble }.sum()
    //求最畅销的产品
    val topProduct = data.map { case (user, product, price) => (product, 1) }.reduceByKey(_ + _).collect().sortBy(-_._2)
    val top = topProduct(0)

    println("=========================================================")
    println("共卖出多少商品:" + numPurchases)
    println("共有多少客户：" + uniqueUsers)
    println("总收入:" + totalRevenue)
    println("最畅销的产品：" + top)
  }
}
