package cn.edu.fudan

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by FengSi on 2016/08/13 at 21:57.
  */
object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WordCount").setMaster("spark://master:7077")
      .setJars(Array("/target/SparkLearning-1.0-SNAPSHOT.jar"))
    val sc = new SparkContext(conf)
    val text = sc.textFile("hdfs://master:9000/user/root/input/bn.txt").
      flatMap(line => line.split(" ")).
      map(word => (word, 1)).
      reduceByKey(_+_)

    val a = text.count()
    println(a)

  }
}
