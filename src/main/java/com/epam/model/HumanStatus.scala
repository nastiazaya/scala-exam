package com.epam.model

import com.epam.model.Gender.Gender
import com.epam.model.MaritalStatus.MaritalStatus

trait HumanStatus extends Human {

  def gender: Gender
  def maritalStatus: MaritalStatus
  def numberOfChildren: Int

}
