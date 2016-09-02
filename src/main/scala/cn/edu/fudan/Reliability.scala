package cn.edu.fudan

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by FengSi on 2016/09/02 at 11:40.
  */
object Reliability {
  def main(args: Array[String]): Unit = {
    if (args.length != 2) {
      println("wrong input, please input the aircraft path and the delay path")
      System.exit(1)
    }

    val conf = new SparkConf().setMaster("spark://master:7077").setAppName("Reliability")
    val sc = new SparkContext(conf)

//    val aircraft = sc.textFile(args(0)).
//    val delay = sc.textFile(args(1)).
    val aircrafttype = sc.textFile("/user/root/input/aircrafttype1000.csv").
      map(line => line.split(',')).
      map(x => (x(0).toInt, x(2).toInt)).
      lookup(532)

    val aircraft = sc.textFile("/user/root/input/aircraft1000.csv").
      map(line => line.split(',')).
      filter(_(0).equals("1")).
      filter(x => aircrafttype.contains(x(2).toInt)).
      map(x => (x(1).toInt, x(2).toDouble)).
      reduceByKey(_+_)

    val delay = sc.textFile("/user/root/input/delay1000.csv").
      map(line => line.split(',')).
      filter(_(0).equals("1")).
      filter(x => aircrafttype.contains(x(3).toInt)).
      map(x => (x(1).toInt, 1)).
      reduceByKey(_+_)

    val reliability = aircraft.cogroup(delay).
      map(X => (X._1, (X._2._1.head - X._2._2.head) / X._2._1.head))

    reliability.count()

    reliability.saveAsTextFile("/user/root/output/reliability")
  }
}
