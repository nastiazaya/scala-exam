package com.epam.service

import com.epam.model.{Contacts, Human}
import org.apache.spark.sql.{Dataset, Encoder, SparkSession}

import scala.reflect.runtime.universe._

class ValidClientsDataLoader(path: String) extends ValidDataLoader with ValidClientExelFileDataLoaderDependency {
  override def load[T <: Product with Human with Contacts: Encoder : TypeTag](implicit sparkSession: SparkSession): Dataset[T] = {
    loader.load[T](path)
      .transform(ValidatorImpl.validationForAge[T])
      .transform(ValidatorImpl.validationForEmail[T])
      .transform(ValidatorImpl.validationForPhoneNumber[T])
  }
}
