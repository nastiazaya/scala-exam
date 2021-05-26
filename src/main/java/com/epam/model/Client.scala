package com.epam.model

import com.epam.model.Education.Education
import com.epam.model.Gender.Gender
import com.epam.model.MaritalStatus.MaritalStatus

case class Client(name: String, lastName: String, gender: Gender, age: Int, email:String, phone: String,
                  education: Education, occupation: String, salary: Int, maritalStatus: MaritalStatus,
                  numberOfChildren: Int ) extends HumanStatus with Contacts
