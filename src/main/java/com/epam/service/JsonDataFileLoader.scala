package com.epam.service

import org.apache.spark.sql.{Dataset, Encoder, Encoders, SparkSession}
import scala.reflect.runtime.universe._

object JsonDataFileLoader extends FileLoader {
  override def load[T <: Product  : Encoder :  TypeTag](path: String)(implicit sparkSession: SparkSession): Dataset[T] = {
    val schema = Encoders.product[T].schema
    sparkSession.read.option("multiline","true")
      .schema(schema)
      .json(path).as[T]
  }
}