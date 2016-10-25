package cn.edu.fudan

import cn.edu.fudan.anomalyDetection.Test


/**
  * Created by FengSi on 2016/10/25 at 10:18.
  */
object AnomalyTest {
  def main(args: Array[String]): Unit = {
    val a = "abcbcbab"
    val b = "bbb"
    val c = Test.anomalies(a,b,3,3,30,0.05)
    println(c)
  }
}
