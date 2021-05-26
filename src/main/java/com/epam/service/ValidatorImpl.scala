package com.epam.service
import com.epam.extension.StringImplicitExtension
import com.epam.model.{Contacts, Human}
import org.apache.spark.sql.{Dataset, Encoder}

object ValidatorImpl extends Validator {
  import StringImplicitExtension._

  def validationForEmail[T <: Product with Contacts: Encoder](ds: Dataset[T]):Dataset[T]= {
    ds.filter( (n: T)=> n.email.isEmail)
  }

  override def validationForPhoneNumber[T <: Product with Contacts: Encoder](ds: Dataset[T]):Dataset[T] = {
    ds.filter((n: T)=> n.phone.isPhoneNumber)
  }

  override def validationForAge[T <: Product with Human: Encoder](ds: Dataset[T]):Dataset[T] = {
    ds.filter((n: T)=> n.age >= 0)
  }

}
