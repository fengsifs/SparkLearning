package cn.edu.fudan.anomalyDetection

/**
  * Created by FengSi on 2016/10/25 at 15:38.
  */
class CreatePST {
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
}
