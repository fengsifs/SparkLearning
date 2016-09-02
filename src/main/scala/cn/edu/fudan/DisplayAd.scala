package cn.edu.fudan

import org.apache.spark.ml.{Pipeline, PipelineStage}
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.feature._
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.types.{DoubleType, StringType, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by FengSi on 2016/09/02 at 14:08.
  */
object DisplayAd {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("spark://master:7077").setAppName("DisplayAd")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val rows = sc.textFile("/user/root/input/dac_sample.txt").
      map(line => line.split("\t")).
      map(x => Row(x(0).toDouble, x(1), x(2), x(14), x(15)))

    object schema {
      val label = StructField("label", DoubleType)
      val intFeature1 = StructField("intFeature1", StringType)
      val intFeature2 = StructField("intFeature2", StringType)
      val CatFeature1 = StructField("CatFeature1", StringType)
      val CatFeature2 = StructField("CatFeature2", StringType)

      val struct = StructType(Array(label, intFeature1, intFeature2, CatFeature1, CatFeature2))
    }

    val df = sqlContext.createDataFrame(rows, schema.struct)

    val weight = Array(0.8, 0.2)
    val seed = 42L

    val dff = df.randomSplit(weight, seed)

    var dfTrain = dff(0)
    var dfTest = dff(1)

    dfTrain = dfTrain.withColumn("intFeature1Temp",dfTrain.col("intFeature1").cast("double"))
    dfTrain = dfTrain.withColumn("intFeature2Temp",dfTrain.col("intFeature2").cast("double"))
    dfTrain = dfTrain.drop("intFeature1").withColumnRenamed("intFeature1Temp","intFeature1")
    dfTrain = dfTrain.drop("intFeature2").withColumnRenamed("intFeature2Temp","intFeature2")
    dfTest = dfTest.withColumn("intFeature1Temp",dfTest.col("intFeature1").cast("double"))
    dfTest = dfTest.withColumn("intFeature2Temp",dfTest.col("intFeature2").cast("double"))
    dfTest = dfTest.drop("intFeature1").withColumnRenamed("intFeature1Temp","intFeature1")
    dfTest = dfTest.drop("intFeature2").withColumnRenamed("intFeature2Temp","intFeature2")

    val strCols = Array("CatFeature1","CatFeature2")

    dfTrain = dfTrain.na.fill("NA", strCols)
    dfTest = dfTest.na.fill("NA", strCols)

    val cat1 = new StringIndexer().setInputCol("CatFeature1").setOutputCol("indexedCat1").setHandleInvalid("skip")
    val cat2 = new StringIndexer().setInputCol("CatFeature2").setOutputCol("indexedCat2").setHandleInvalid("skip")
    dfTrain = cat1.fit(dfTrain).transform(dfTrain)
    dfTrain = cat2.fit(dfTrain).transform(dfTrain)

    val cat1E = new OneHotEncoder().setInputCol(cat1.getOutputCol).setOutputCol("CatVector1")
    val cat2E = new OneHotEncoder().setInputCol(cat2.getOutputCol).setOutputCol("CatVector2")
    dfTrain = cat1E.transform(dfTrain)
    dfTrain = cat2E.transform(dfTrain)
    dfTrain.take(1)

    val vectorAsCols = Array("intFeature1","intFeature2","CatVector1","CatVector2")
    val vectorAssembler = new VectorAssembler().setInputCols(vectorAsCols).setOutputCol("vectorFeature")
    dfTrain = vectorAssembler.transform(dfTrain)
    val logModel = new LogisticRegression().setMaxIter(10).setRegParam(0.1).setElasticNetParam(0.0).
      setFeaturesCol("vectorFeature").setLabelCol("label")

    val pipelineStage: Array[PipelineStage] = Array(cat1, cat2, cat1E, cat2E, vectorAssembler, logModel)
    val pipeline = new Pipeline().setStages(pipelineStage)
    val pModel = pipeline.fit(dfTrain)

    val output = pModel.transform(dfTest).select("selectedFeature", "label", "prediction", "rawPrediction", "probability")
    val prediction = output.select("label", "prediction")
    prediction.show()


    rows.take(10)
  }
}
