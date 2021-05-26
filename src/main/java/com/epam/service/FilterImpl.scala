package com.epam.service

import com.epam.model.Gender.Gender
import com.epam.model.MaritalStatus.MaritalStatus
import com.epam.model.{HumanStatus, Human}
import org.apache.spark.sql.{Dataset, Encoder}
import scala.reflect.runtime.universe._

object FilterImpl extends Filter {
  override def filterByAge[T <: Product with Human : Encoder : TypeTag](ds: Dataset[T], min: Int, max: Int): Dataset[T] = {
    ds.filter((n: T) => n.age >= min)
      .filter((n: T) => n.age <= max)
  }

  override def filterByGenderMaritalStatusAndNumberOfChildren[T <: Product with HumanStatus : Encoder: TypeTag](ds: Dataset[T],
                                                                                                                gender: Gender,
                                                                                                                maritalStatus: MaritalStatus,
                                                                                                                numOfChildren: Int): Dataset[T] = {
    ds.filter((n: T) => n.gender.equals(gender))
      .filter((n: T) => n.maritalStatus.equals(maritalStatus))
      .filter((n: T) => n.numberOfChildren >= numOfChildren)
  }

  override def filterNameByPrefix[T <: Product with Human : Encoder : TypeTag](ds: Dataset[T], prefix: String): Dataset[T] = {

    ds.filter((n:T) =>n.name.startsWith(prefix))
  }
}