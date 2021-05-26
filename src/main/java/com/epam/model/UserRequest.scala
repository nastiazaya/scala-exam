package com.epam.model

import com.epam.model.Gender.Gender
import com.epam.model.MaritalStatus.MaritalStatus

case class UserRequest(minAge: Option[Int], maxAge: Option[Int], gender: Option[Gender],
                       prefixName: Option[String], maritalStatus: Option[MaritalStatus],
                       numberOfChildren: Option[Int])
