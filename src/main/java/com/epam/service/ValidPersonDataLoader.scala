package com.epam.service

import com.epam.model.{Contacts, Human}
import org.apache.spark.sql.{Dataset, Encoder, SparkSession, functions}

import java.util.Properties
import scala.reflect.runtime.universe._

class ValidPersonDataLoader(path: String) extends ValidDataLoader with ValidPersonJsonFileDataLoaderDependency {
  override def load[T <: Product with Human with Contacts : Encoder : TypeTag](implicit sparkSession: SparkSession): Dataset[T] = {
    import sparkSession.implicits._
    loader.load[T](path)
/*      .withColumn("name", functions.split($"name", " "))
      .withColumn("lastName", $"name".getItem(1))
      .withColumn("name", $"name".getItem(0))
      .as[T]*/
      .transform(ValidatorImpl.validationForAge[T])
      .transform(ValidatorImpl.validationForEmail[T])
      .transform(ValidatorImpl.validationForPhoneNumber[T])
  }
}
