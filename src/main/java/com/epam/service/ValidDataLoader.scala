package com.epam.service

import com.epam.model.{Contacts, Human}
import org.apache.spark.sql.{Dataset, Encoder, SparkSession}

import scala.reflect.runtime.universe._

trait ValidDataLoader {

  def load[T <: Product with Human with Contacts: Encoder : TypeTag](implicit sparkSession: SparkSession): Dataset[T]

}
