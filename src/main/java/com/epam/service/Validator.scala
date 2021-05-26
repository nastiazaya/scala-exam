package com.epam.service

import com.epam.model.{Contacts, Human}
import org.apache.spark.sql.{Dataset, Encoder}

trait Validator {

  def validationForEmail[T <: Product with Contacts: Encoder](ds: Dataset[T]):Dataset[T]

  def validationForPhoneNumber[T <: Product with Contacts: Encoder](ds: Dataset[T]):Dataset[T]

  def validationForAge[T <: Product with Human: Encoder](ds: Dataset[T]):Dataset[T]

}
