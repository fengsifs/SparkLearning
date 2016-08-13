package cn.edu.fudan

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by FengSi on 2016/08/13 at 21:57.
  */
object WordCount {
  def main(args: Array[String]): Unit = {
    if (args.length != 2) {
      println("wrong input, please input the input path and the output path")
      System.exit(1)
    }
    val conf = new SparkConf().setAppName("WordCount").setMaster("spark://master:7077")
    val sc = new SparkContext(conf)
    val text = sc.textFile(args(0)).
      flatMap(line => line.split(" ")).
      map(word => (word, 1)).
      reduceByKey(_+_)

    text.saveAsTextFile(args(1))

  }
}
