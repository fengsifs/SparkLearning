package cn.edu.fudan.anomalyDetection

/**
  * Created by FengSi on 2016/10/25 at 14:13.
  */
case class PSTNode() {
  var nextSymbol = new Array[PSTNode](26)
  var count = 1.0
  var nextProbability = new Array[Double](26)
}
