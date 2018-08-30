package org.spark.data.sql

import org.apache.spark.sql.SparkSession

/**
  * User: shijingui
  * Date: 2017/3/10
  */
object SparkSql2 {
  def main(args: Array[String]) {
    val spark = SparkSession.builder().appName("SparkSql-test")
      .config("spark.sql.warehouse.dir", "file:///").master("local").getOrCreate()

    import spark.implicits._
    val df = spark.read.json("src/main/resources/people.json")

    // Displays the content of the DataFrame to stdout
    df.show()

    // Print the schema in a tree format
    df.printSchema()

    // Select only the "name" column
    df.select("name").show()


    // Select everybody, but increment the age by 1
    df.select($"name", $"age" + 1).show()
    // Select people older than 21
    df.filter($"age" > 21).show()
    // Count people by age
    df.groupBy("age").count().show()

    df.createOrReplaceTempView("people")

    val sqlDF = spark.sql("SELECT * FROM people where age=30")

    sqlDF.show()

  }
}
