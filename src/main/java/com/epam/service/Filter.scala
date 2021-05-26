package com.epam.service

import com.epam.model.Gender.Gender
import com.epam.model.MaritalStatus.MaritalStatus
import com.epam.model.{HumanStatus, Human}
import org.apache.spark.sql.{Dataset, Encoder}
import scala.reflect.runtime.universe._


trait Filter {

  def filterByAge[T <: Product with Human : Encoder : TypeTag](ds: Dataset[T], min: Int, max: Int): Dataset[T]

  def filterByGenderMaritalStatusAndNumberOfChildren[T <: Product with HumanStatus : Encoder : TypeTag](ds: Dataset[T],
                                                                                                        gender:Gender,
                                                                                                        maritalStatus: MaritalStatus,
                                                                                                        numOfChildren: Int): Dataset[T]

  def filterNameByPrefix[T <: Product with Human : Encoder : TypeTag](ds: Dataset[T], prefix: String): Dataset[T]

}


