package com.epam.service
import org.apache.spark.sql.{Dataset, Encoder, SparkSession}

import scala.reflect.runtime.universe._

object SaveJsonDataFile extends Saver {
  override def save[T <: Product : Encoder : TypeTag](path: String, ds: Dataset[T])(implicit sparkSession: SparkSession): Unit = {
    //System.setProperty("hadoop.home.dir","C:\\Users\\Nastya\\AppData\\winutils-master\\hadoop-3.0.0\\bin")
    ds.write.json("file:///" + path)

  }
}
