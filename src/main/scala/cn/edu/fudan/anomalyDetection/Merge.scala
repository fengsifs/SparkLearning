package cn.edu.fudan.anomalyDetection

/**
  * Created by FengSi on 2016/10/25 at 14:45.
  */
class Merge {
  def merge(a: PSTNode, b: PSTNode) : PSTNode = {
    a.count = a.count + b.count
    for (i <- a.nextSymbol.indices)
      a.nextSymbol(i) = merge(a.nextSymbol(i), b.nextSymbol(i))
    for (i <- a.nextProbability.indices)
      a.nextProbability(i) = a.nextSymbol(i).count / a.count
    a
  }
}
