package cn.edu.fudan

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

/**
  * Created by FengSi on 2016/08/13 at 21:55.
  */
object CsvTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("CsvTest").setMaster("spark://master:7077")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val df = sqlContext.read.
      format("com.databricks.spark.csv").
      option("header", "true").
      option("inferSchema", "true").
      load(args(0))

    df.show(10)
  }
}
