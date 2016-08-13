package cn.edu.fudan

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.linalg.{Matrix, Vectors}
import org.apache.spark.mllib.linalg.distributed.RowMatrix

/**
  * Created by FengSi on 2016/08/13 at 21:57.
  */
object MLTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("spark://master:7077").setAppName("PCA")
    val sc = new SparkContext(conf)

    val rows = sc.textFile(args(0)).map { line =>
      val values = line.split(',').map(_.toDouble)
      Vectors.dense(values)}

    val mat = new RowMatrix(rows)

    // Calculate PCA
    val pc: Matrix = mat.computePrincipalComponents(mat.numCols().toInt)

    println("Principal compomemts :")
    println(pc)

    sc.stop()
  }
}
