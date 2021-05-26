package com.epam.service

import org.apache.spark.sql.{Dataset, Encoder, SparkSession}
import scala.reflect.runtime.universe._


trait Saver {

  def save[T <: Product : Encoder : TypeTag](path: String, ds: Dataset[T])(implicit sparkSession: SparkSession)

}
