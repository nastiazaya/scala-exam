package com.epam.service


import org.apache.spark.sql.{Dataset, Encoder, SparkSession}
import scala.reflect.runtime.universe._

trait FileLoader {

  def load[T<: Product : Encoder :TypeTag](config: String)(implicit sparkSession: SparkSession): Dataset[T]

}






