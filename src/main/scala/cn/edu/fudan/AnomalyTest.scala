package cn.edu.fudan

import cn.edu.fudan.anomalyDetection.PSTNode
import org.apache.spark.{SparkConf, SparkContext}


/**
  * Created by FengSi on 2016/10/25 at 10:18.
  */
object AnomalyTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("spark://master:7077").setAppName("DisplayAd")
    val sc = new SparkContext(conf)

    val data = sc.textFile(args(0)).flatMap(line => line.split(" ")).map(_.toDouble).
      zipWithIndex().map(a => (a._2/args(2).toLong, a._1)).reduceByKey(_+_).
      map(a => convertToSymbol(a._2)).reduce(_+_)

    val test = sc.textFile(args(1)).flatMap(line => line.split(" ")).map(_.toDouble).
      zipWithIndex().map(a => (a._2/args(2).toLong, a._1)).reduceByKey(_+_).
      map(a => convertToSymbol(a._2)).reduce(_+_)

    val dataPST = (sc.parallelize(0 to data.length-args(3).toInt)
      map(i => (1, createPST(data.substring(i, i+args(3).toInt))))
      reduceByKey merge
      map(_._2) take 1)(0)

    sc parallelize(0 to 10) filter(_%2 == 0) foreach println

    val anomalies = detectAnomalies(dataPST, test)

    anomalies foreach println

//    val testPST = sc.parallelize(0 to test.length-args(3).toInt).
//      map(i => (1, createPST(data.substring(i, i+args(3).toInt)))).
//      reduceByKey(merge)


  }

  def convertToSymbol(d: Double): String = d toString

  def createPST(s: String): PSTNode = {
    val cs = s.toCharArray.map(_ - 'a')
    val root = PSTNode()
    var node = root
    for (i <- cs.indices) {
      node.nextSymbol(i) = PSTNode()
      node.nextProbability(i) = 1.0
      node = node.nextSymbol(i)
    }
    root
  }

  def merge(a: PSTNode, b: PSTNode) : PSTNode = {
    a.count = a.count + b.count
    for (i <- a.nextSymbol.indices)
      a.nextSymbol(i) = merge(a.nextSymbol(i), b.nextSymbol(i))
    for (i <- a.nextProbability.indices)
      a.nextProbability(i) = a.nextSymbol(i).count / a.count
    a
  }

  def detectAnomalies(d : PSTNode, t : String) : Array[Int] = {
    new Array(0)
  }
}
